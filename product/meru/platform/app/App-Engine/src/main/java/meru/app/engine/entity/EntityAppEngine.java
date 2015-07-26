package meru.app.engine.entity;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import meru.app.AppContext;
import meru.app.config.AppConfig;
import meru.app.engine.AppEngine;
import meru.app.service.ServiceManager;
import meru.persistence.EntityQuery;
import meru.persistence.PersistentStore;
import meru.sys.lang.ClassHelper;
import meru.sys.lang.ClassLoaderHelper;
import meru.transaction.TransactionManager;

public class EntityAppEngine extends AppEngine {

    protected PersistentStore mPersistentStore;
    protected EntityLifeCycleRepository mEntityLifeCycleRepository;

    protected TransactionManager mTransactionManager;

    public EntityAppEngine(AppConfig appConfig,
                           AppContext appContext,
                           PersistentStore persistentStore,
                           ServiceManager serviceManager) throws Exception {
        super(appConfig,
              appContext);

        mPersistentStore = persistentStore;
        mEntityLifeCycleRepository = new EntityLifeCycleRepository();

        mServiceManager = serviceManager;
        mTransactionManager = serviceManager.getService(ServiceManager.SERVICE_TRANSACTION_MANAGER, TransactionManager.class);
    }

    public List<EntityLifeCycle<?>> initializeLifeCycleListeners() throws ClassNotFoundException,
                                                                  InstantiationException,
                                                                  IllegalAccessException {

        File file = new File(mAppContext.getApplicationHome()
                                        .getAbsolutePath() + "/WEB-INF");
        @SuppressWarnings("rawtypes")
        List<Class<? extends AbstractEntityLifeCycle>> listeners = ClassLoaderHelper.loadClassesExtending(file,
                                                                                                          AbstractEntityLifeCycle.class);
        

        List<EntityLifeCycle<?>> entityLifeCycles = new ArrayList<EntityLifeCycle<?>>(1);

        if (listeners != null) {
            for (@SuppressWarnings("rawtypes")
            Class<? extends AbstractEntityLifeCycle> listener : listeners) {
                EntityLifeCycle<?> lifeCycle = (EntityLifeCycle<?>) listener.newInstance();

                System.out.println("   #### " + lifeCycle.getClass());
                entityLifeCycles.add(lifeCycle);

                if (lifeCycle instanceof AbstractEntityLifeCycle) {
                    ((AbstractEntityLifeCycle<?>) lifeCycle).setApplicationContext(mAppContext,
                                                                                   mAppConfig,
                                                                                   this,
                                                                                   mServiceManager);
                }

                ParameterizedType parameterizedType = (ParameterizedType) listener.getGenericSuperclass();

                Class<?> resourceClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

                mEntityLifeCycleRepository.addEntityLifeCycleListener(resourceClass,
                                                                      lifeCycle);

            }
        }

        mEntityLifeCycleRepository.initEntityLifeCycles();

        return entityLifeCycles;
    }

    private final void beginTransactionIfNotStarted() {
        try {
            if (!mTransactionManager.isTransactionActive()) {
                mTransactionManager.begin();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getEntityLifeCycle(Class<?> resourceClass,
                                    Class<T> lifeCycleClass) {
        return (T) mEntityLifeCycleRepository.getEntityLifeCycle(resourceClass);
    }

    @Override
    public Object save(Object entity) {
        return saveEntity(entity);
    }

    @Override
    public <T> Object remove(Class<T> entityClass, Object id) {
        return removeEntity(entityClass,
                            id);
    }

    @Override
    public <T> T get(Class<T> entityClass, Object id) {
        return getEntity(entityClass,
                         id);
    }

    @Override
    public <T> T getFirst(EntityQuery<T> entityQuery) {
        return getFirstEntity(entityQuery);
    }

    @Override
    public <T> List<T> get(EntityQuery<T> entityQuery) {
        return getEntities(entityQuery);
    }

    public Object saveEntity(Object resource) {

        beginTransactionIfNotStarted();

        preSaveEntity(resource);

        Object id = ClassHelper.getFieldValue(resource,
                                              "id");

        if (id == null) {

            if (mEntityLifeCycleRepository.preCreate(resource)) {

                // if (resource instanceof AuditableEntity) {
                //
                // AuditableEntity auditableEntity = (AuditableEntity) resource;
                // auditableEntity.setCreatedBy("engine");
                // auditableEntity.setCreatedTime(mSystemCalendar.getUTCCalendar());
                //
                // }

                mPersistentStore.createEntity(resource);
                Object result = mEntityLifeCycleRepository.postCreate(resource);
                if (result != null) {
                    return result;
                }
            }
        } else {
            if (mEntityLifeCycleRepository.preModify(resource)) {

                // if (resource instanceof AuditableEntity) {
                //
                // AuditableEntity auditableEntity = (AuditableEntity) resource;
                // auditableEntity.setModifiedBy("engine");
                // auditableEntity.setModifiedTime(mSystemCalendar.getUTCCalendar());
                //
                // }

                resource = mPersistentStore.updateEntity(resource);
                Object result = mEntityLifeCycleRepository.postModify(resource);
                if (result != null) {
                    return result;
                }
            }
        }

        return resource;
    }

    public <T> Object removeEntity(Class<T> resourceClass, Object id) {

        beginTransactionIfNotStarted();

        if (mEntityLifeCycleRepository.preDelete(resourceClass,
                                                 id)) {

            T entity = mPersistentStore.getEntity(resourceClass,
                                                  id);

            if (mEntityLifeCycleRepository.preDelete(entity)) {

                mPersistentStore.deleteEntity(resourceClass,
                                              id);
                Object result = mEntityLifeCycleRepository.postDelete(entity);

                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public <T> T getEntity(Class<T> resourceClass, Object id) {
        T result = mEntityLifeCycleRepository.preGet(resourceClass,
                                                     id);
        if (result != null) {
            return result;
        }

        T resource = mPersistentStore.getEntity(resourceClass,
                                                id);

        result = mEntityLifeCycleRepository.postGet(resourceClass,
                                                    resource);
        if (result != null) {
            return result;
        }

        return resource;
    }

    public <T> List<T> getEntities(EntityQuery<T> entityQuery) {
        List<T> result = mEntityLifeCycleRepository.preGet(entityQuery);
        if (result != null) {
            return result;
        }

        List<T> resourceList = mPersistentStore.getEntities(entityQuery);

        result = mEntityLifeCycleRepository.postGet(entityQuery,
                                                    resourceList);
        if (result != null) {
            return result;
        }

        return resourceList;
    }

    public <T> T getFirstEntity(EntityQuery<T> entityQuery) {
        T result = mEntityLifeCycleRepository.preGetFirst(entityQuery);
        if (result != null) {
            return result;
        }

        T resource = mPersistentStore.getFirstEntity(entityQuery);

        result = mEntityLifeCycleRepository.postGetFirst(entityQuery,
                                                         resource);

        if (result != null) {
            return result;
        }

        return resource;

    }

    @Override
    public long nextSequenceNumber(String sequenceName) {
        return mPersistentStore.nextSequenceNumber(sequenceName);
    }

    private void preSaveEntity(Object resource) {

        try {
            List<Field> fields = ClassHelper.getAllFields(resource.getClass());
            // String packageName = resource.getClass()
            // .getPackage()
            // .getName();

            for (Field field : fields) {

                Class<?> type = field.getType();

                // Package fieldPackage = field.getType()
                // .getPackage();

                // if (fieldPackage != null &&
                // packageName.equals(field.getType()
                // .getPackage()
                // .getName())) {

                field.setAccessible(true);
                Object value = field.get(resource);

                if (value != null) {
                    Long id = null;

                    if (!ClassHelper.isBuildInJavaTypes(type)) {

                        // TODO
                        boolean isPropertyGroup = "PropertyGroup".equals(type.getSimpleName());

                        id = (Long) ClassHelper.getFieldValue(value,
                                                              "id");
                        if (id != null && id != 0) {
                            if (isPropertyGroup || !handleChildEntity(field)) {
                                value = get(type,
                                            id);
                            } else {

                                value = save(value);

                            }

                            field.set(resource,
                                      value);

                        } else {
                            if (isPropertyGroup) {
                                field.set(resource,
                                          null);
                            } else {

                                if (handleChildEntity(field)) {
                                    // to set the child objects
                                    // for example, Address needs to be filled
                                    // with area, city, etc
                                    //preSaveEntity(value);
                                    save(value);
                                } else {
                                    // save(value);
                                    field.set(resource,
                                              null);
                                }
                            }
                        }

                    }

                }

            }
            // }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private boolean handleChildEntity(Field field) {
        OneToOne oneToOne = field.getAnnotation(OneToOne.class);
        if (oneToOne != null) {
            CascadeType[] cascades = oneToOne.cascade();
            if (cascades != null) {
                for (CascadeType cascade : cascades) {
                    if (cascade == CascadeType.ALL) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public <T> EntityQuery<T> createQuery(Class<T> entityClass) {
        return mPersistentStore.createQuery(entityClass);
    }

    @Override
    public void closeSession() {
        mPersistentStore.closeCurrentThreadSession();
    }

}

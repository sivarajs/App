package meru.app.engine.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import meru.persistence.EntityQuery;
import meru.sys.lang.ClassHelper;

public class EntityLifeCycleRepository {

    protected Map<Class<?>, EntityLifeCycle<?>> mEntityLifeCycleListeners;
    protected Map<Class<?>, List<Class<?>>> mParentEntityLifeCycleListeners;
    protected Map<Class<?>, List<AbstractEntityLifeCycle<?>>> mEntityObservers;

    public EntityLifeCycleRepository() {
        mEntityLifeCycleListeners = new ConcurrentHashMap<Class<?>, EntityLifeCycle<?>>();
        mParentEntityLifeCycleListeners = new ConcurrentHashMap<Class<?>, List<Class<?>>>();
        mEntityObservers = new ConcurrentHashMap<Class<?>, List<AbstractEntityLifeCycle<?>>>();
    }

    final void addEntityLifeCycleListener(Class<?> clas,
                                          EntityLifeCycle<?> lifeCycle) {

        mEntityLifeCycleListeners.put(clas,
                                      lifeCycle);

        if (lifeCycle instanceof AbstractEntityLifeCycle) {
            AbstractEntityLifeCycle<?> abstractEntityLifeCycle = (AbstractEntityLifeCycle<?>) lifeCycle;
            Class<?>[] objserveableClasses = abstractEntityLifeCycle.observeableEntities();

            if (objserveableClasses != null) {
                for (Class<?> observeableClass : objserveableClasses) {
                    List<AbstractEntityLifeCycle<?>> entityObservers = mEntityObservers.get(observeableClass);

                    if (entityObservers == null) {
                        entityObservers = new ArrayList<AbstractEntityLifeCycle<?>>(1);
                        mEntityObservers.put(observeableClass,
                                             entityObservers);
                    }

                    entityObservers.add(abstractEntityLifeCycle);

                }
            }

        }

    }

    final void addParentEntityLifeCycleListeners(Class<?> entityClass, Class<?> claz) {

        Class<?> superClass = claz.getSuperclass();

        if (superClass == Object.class) {
            return;
        }

        List<Class<?>> lifeCycles = mParentEntityLifeCycleListeners.get(entityClass);
        lifeCycles.add(superClass);

        addParentEntityLifeCycleListeners(entityClass, superClass);
    }

    public final void initEntityLifeCycles() {

        for (Map.Entry<Class<?>, EntityLifeCycle<?>> resourceLifeCycle : mEntityLifeCycleListeners.entrySet()) {

            EntityLifeCycle<?> lifeCycle = resourceLifeCycle.getValue();

            if (lifeCycle instanceof AbstractEntityLifeCycle) {
                ((AbstractEntityLifeCycle<?>) lifeCycle).init();
            }
        }
    }

    private void notifyEntityObservers(Object entity, byte type) {
        List<AbstractEntityLifeCycle<?>> entityObservers = mEntityObservers.get(entity.getClass());
        if (entityObservers != null) {
            for (AbstractEntityLifeCycle<?> lifeCycle : entityObservers) {
                lifeCycle.onObserveableEntityCreate(entityObservers);
                switch (type) {
                case 1:
                    lifeCycle.onObserveableEntityCreate(entity);
                    break;
                case 2:
                    lifeCycle.onObserveableEntityModify(entity);
                    break;
                case 3:
                    lifeCycle.onObserveableEntityDelete(entity);
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public final <T> EntityLifeCycle<T> getEntityLifeCycle(Class<?> clas) {

        return (EntityLifeCycle<T>) mEntityLifeCycleListeners.get(clas);
    }

    public final List<Class<?>> getParentEntityLifeCycle(Class<?> clas) {

        List<Class<?>> lifeCycles = mParentEntityLifeCycleListeners.get(clas);

        if (lifeCycles == null) {

            lifeCycles = createParentEntityLifeCycleList(clas);

        }

        return lifeCycles;
    }

    private synchronized List<Class<?>> createParentEntityLifeCycleList(Class<?> clas) {

        List<Class<?>> lifeCycles = mParentEntityLifeCycleListeners.get(clas);

        if (lifeCycles == null) {

            lifeCycles = new ArrayList<Class<?>>(1);
            mParentEntityLifeCycleListeners.put(clas,
                                                lifeCycles);

            addParentEntityLifeCycleListeners(clas, clas);

        }

        return lifeCycles;

    }

    public final <T> Object notifyEntityLifeCycles(String methodName,
                                                   Class<T> resourceClass,
                                                   Object... args) {

        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

        if (resourceLifeCycleListener != null) {

            Class<?>[] classes = new Class[args.length + 1];
            int i = 0;
            classes[i++] = Class.class;
            for (Object arg : args) {
                classes[i++] = arg.getClass();
            }

            Object[] objects = new Object[args.length + 1];
            i = 0;
            objects[i++] = resourceClass;
            for (Object arg : args) {
                objects[i++] = arg;
            }

            Object result = ClassHelper.invoke(resourceLifeCycleListener,
                                               methodName,
                                               classes,
                                               objects);

            return result;
        }

        return null;
    }

    public <T> boolean preCreate(T resource) {

        List<Class<?>> lifeCycles = getParentEntityLifeCycle(resource.getClass());

        if (lifeCycles != null) {
            lifeCycles.forEach((Class<?> resourceClass) -> {

                EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

                if (resourceLifeCycleListener != null) {
                    resourceLifeCycleListener.preCreate(resource);
                }

            });
        }
        
        
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resource.getClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.preCreate(resource);
        }

        return true;
    }

    public <T> Object postCreate(T resource) {

        List<Class<?>> lifeCycles = getParentEntityLifeCycle(resource.getClass());

        if (lifeCycles != null) {
            lifeCycles.forEach((Class<?> resourceClass) -> {

                EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

                if (resourceLifeCycleListener != null) {
                    resourceLifeCycleListener.postCreate(resource);
                }

            });
        }
        
        notifyEntityObservers(resource,
                              (byte) 1);

        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resource.getClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.postCreate(resource);
        }

        return null;
    }

    public <T> boolean preModify(T resource) {

        List<Class<?>> lifeCycles = getParentEntityLifeCycle(resource.getClass());

        if (lifeCycles != null) {
            lifeCycles.forEach((Class<?> resourceClass) -> {

                EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

                if (resourceLifeCycleListener != null) {
                    resourceLifeCycleListener.preModify(resource);
                }

            });
        }

        notifyEntityObservers(resource,
                              (byte) 2);
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resource.getClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.preModify(resource);
        }

        return true;

    }

    public <T> Object postModify(T resource) {
        
        List<Class<?>> lifeCycles = getParentEntityLifeCycle(resource.getClass());

        if (lifeCycles != null) {
            lifeCycles.forEach((Class<?> resourceClass) -> {

                EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

                if (resourceLifeCycleListener != null) {
                    resourceLifeCycleListener.postModify(resource);
                }

            });
        }
        
        notifyEntityObservers(resource,
                              (byte) 3);
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resource.getClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.postModify(resource);
        }

        return null;
    }

    public <T> boolean preDelete(Class<T> resourceClass, Object id) {
        
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.preDelete(resourceClass,
                                                       id);
        }

        return true;

    }

    public <T> boolean preDelete(T resource) {
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resource.getClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.preDelete(resource);
        }

        return true;

    }

    public <T> Object postDelete(T resource) {
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resource.getClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.postDelete(resource);
        }

        return null;
    }

    public <T> List<T> preGet(EntityQuery<T> resourceFilter) {
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceFilter.getEntityClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.preGet(resourceFilter);
        }

        return null;
    }

    public <T> List<T> postGet(EntityQuery<T> resourceFilter,
                               List<T> resourceList) {
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceFilter.getEntityClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.postGet(resourceFilter,
                                                     resourceList);
        }

        return null;
    }

    public <T> T preGetFirst(EntityQuery<T> resourceFilter) {
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceFilter.getEntityClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.preGetFirst(resourceFilter);
        }

        return null;
    }

    public <T> T postGetFirst(EntityQuery<T> resourceFilter, T entity) {
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceFilter.getEntityClass());

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.postGetFirst(resourceFilter,
                                                          entity);
        }

        return null;
    }

    public <T> T preGet(Class<T> resourceClass, Object id) {

        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.preGet(resourceClass,
                                                    id);
        }

        return null;
    }

    public <T> T postGet(Class<T> resourceClass, T resource) {
        EntityLifeCycle<T> resourceLifeCycleListener = getEntityLifeCycle(resourceClass);

        if (resourceLifeCycleListener != null) {
            return resourceLifeCycleListener.postGet(resource);
        }

        return null;
    }

}

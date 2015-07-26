package meru.app.binding;

import java.util.List;

import meru.app.engine.AppEngine;
import meru.app.registry.EntityClassRegistry;
import meru.persistence.EntityQuery;
import meru.ui.faces.EntityDataProvider;

public class EntityDataProviderImpl implements EntityDataProvider {

    private AppEngine mAppEngine;
    private EntityClassRegistry mEntityClassRegistry;

    public EntityDataProviderImpl(AppEngine appEngine,
                                  EntityClassRegistry entityClassRegistry) {
        mAppEngine = appEngine;
        mEntityClassRegistry = entityClassRegistry;
    }

 
    @SuppressWarnings("unchecked")
    public <T> EntityQuery<T> createEntityQuery(String entityName) {
        return mAppEngine.createQuery((Class<T>)getEntityClass(entityName));
    }
    
    public <T> EntityQuery<T> createEntityQuery(Class<T> entityClass) {
        return mAppEngine.createQuery(entityClass);
    }
    
    public <T> T getResourceLifeCycle(Class<?> entityClass,
                                      Class<T> lifeCycleClass) {

        return null;
    }

    public final Class<?> getEntityClass(String entityName) {

        return mEntityClassRegistry.getEntityClass(entityName);
    }

    public Object getEntity(String entityName,
                            Object id) {
        return mAppEngine.get(getEntityClass(entityName),
                              id);
    }

    public <T> T getEntity(Class<T> entityClass,
                           Object id) {

        return mAppEngine.get(entityClass,
                              id);
    }

    @SuppressWarnings("unchecked")
    public List<Object> getEntities(String entityName,
                                    String filter) {
        EntityQuery<?> entityQuery = mAppEngine.createQuery(getEntityClass(entityName));

        if (filter != null) {
            try {
              entityQuery.setQueryParameters(filter);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        
        return (List<Object>) mAppEngine.get(entityQuery);
    }

    
    public Object getFirstMatchingEntity(String entityName,
                                               String filter) {
        EntityQuery<?> entityQuery = mAppEngine.createQuery(getEntityClass(entityName));

        if (filter != null) {
            try {
                entityQuery.setQueryParameters(filter);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        return mAppEngine.getFirst(entityQuery);
    }

    public <T> List<T> getEntities(EntityQuery<T> entityQuery) {

        return mAppEngine.get(entityQuery);
    }

    public <T> T getFirstMatchingEntity(EntityQuery<T> entityQuery) {
        return mAppEngine.getFirst(entityQuery);

    }
}

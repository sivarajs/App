package meru.ui.faces;



import java.util.List;

import meru.persistence.EntityQuery;

public interface EntityDataProvider {

    <T> EntityQuery<T> createEntityQuery(Class<T> entityClass);
    <T> EntityQuery<T> createEntityQuery(String entityName);
    <T> T getResourceLifeCycle(Class<?> entityClass, Class<T> lifeCycleClass);
    
    Class<?> getEntityClass(String entityName);

    Object getEntity(String entityeName,
                     Object Id);

    <T> T getEntity(Class<T> entityeClass,
                     Object Id);
    List<Object> getEntities(String entityName,
                             String filter);

    <T> List<T> getEntities(EntityQuery<T> resourceFilter);
    <T> T getFirstMatchingEntity(EntityQuery<T> resourceFilter);
}

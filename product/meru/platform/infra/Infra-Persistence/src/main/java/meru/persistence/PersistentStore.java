package meru.persistence;

import java.util.List;

public interface PersistentStore {

    public <T> T createEntity(T entity);

    public <T> T updateEntity(T entity);

    public <T> void deleteEntity(Class<T> entityClass,
                                 Object id);

    public <T> void deleteEntity(T entity);

    public <T> T getEntity(Class<T> entityClass,
                           Object id);

    public <T> List<T> getEntities(EntityQuery<T> entityQuery);

    public <T> T getFirstEntity(EntityQuery<T> entityQuery);

    /*
     * public <T> int countEntities(Class<T> entityClass, Q query);
     * 
     * public <T> boolean entityExists(Class<T> entityClass, Q query);
     */
    public long nextSequenceNumber(String sequenceName);

    public <T> EntityQuery<T> createQuery(Class<T> entityClass);

    public void closeCurrentThreadSession();
    
    
}

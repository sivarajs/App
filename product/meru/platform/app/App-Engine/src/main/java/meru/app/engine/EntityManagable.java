package meru.app.engine;

import java.util.List;

import meru.persistence.EntityQuery;

public interface EntityManagable {
    
    Object save(Object entity);

    <T> Object remove(Class<T> entityClass,
                                      Object id);

    <T> T get(Class<T> entityClass,
                              Object id);

    <T> T getFirst(EntityQuery<T> entityQuery);

    <T> List<T> get(EntityQuery<T> entityQuery);

}

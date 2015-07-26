package meru.app.engine.entity;

import java.util.List;

import meru.persistence.EntityQuery;

public interface EntityLifeCycle<T> {

    public boolean preCreate(T resource);

    public Object postCreate(T resource);

    public boolean preModify(T resource);

    public Object postModify(T resource);

    public boolean preDelete(Class<T> resourceClass,
                             Object id);

    public Object postDelete(Class<T> resourceClass,
                             Object id);

    public boolean preDelete(T resource);

    public Object postDelete(T resource);

    public List<T> preGet(EntityQuery<T> resourceFilter);

    public List<T> postGet(EntityQuery<T> resourceFilter,
                           List<T> resourceList);

    public T preGetFirst(EntityQuery<T> resourceFilter);

    public T postGetFirst(EntityQuery<T> resourceFilter,
                          T resource);

    public T preGet(Class<T> resourceClass,
                    Object id);

    public T postGet(T resource);

}
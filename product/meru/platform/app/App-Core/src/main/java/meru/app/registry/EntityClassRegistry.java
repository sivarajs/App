package meru.app.registry;

import java.util.HashMap;
import java.util.Map;

public class EntityClassRegistry {

    private Map<String, Class<?>> mClassMap;

    public EntityClassRegistry() {

        mClassMap = new HashMap<String, Class<?>>();

        addCommonClasses();

        populateClassMap();
    }

    public Class<?> getEntityClass(String entityName) {

        Class<?> entityClass = mClassMap.get(entityName);

        if (entityClass == null) {
            throw new NoClassDefFoundError(entityName);
        }

        return entityClass;
    }

    private void addCommonClasses() {
    }

    protected void addResourceClass(String resourceName,
                                    Class<?> resourceClass) {
        mClassMap.put(resourceName,
                      resourceClass);
    }

    protected void populateClassMap() {

    }

}

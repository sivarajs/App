package meru.sys.lang;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import meru.sys.type.converter.TypeConverter;
import meru.sys.type.converter.TypeConverterFactory;

public class ClassHelper {

    public static boolean implementsInterface(Class<?> clas,
                                              Class<?> interfaceClass) {

        for (Class<?> type : clas.getInterfaces()) {
            if (type.equals(interfaceClass)) {
                return true;
            }
            else {
                return implementsInterface(type,
                                           interfaceClass);
            }
        }

        return false;
    }

    public static boolean extendsClass(Class<?> clas,
                                       Class<?> extendedClass) {

        Class<?> superClass = clas.getSuperclass();

        if (extendedClass.equals(superClass)) {
            return true;
        }

        if (superClass != null) {
            return extendsClass(clas.getSuperclass(),
                                extendedClass);
        }

        return false;

    }

    public static Object invoke(Object object,
                                String method,
                                Class<?>[] argTypes,
                                Object[] argValues) {

        Method methodObj = null;

        try {
            methodObj = object.getClass()
                              .getMethod(method,
                                         argTypes);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(method + " not found in the class " + object.getClass()
                                                                                   .getName());

        } catch (RuntimeException e) {
            throw e;
        }

        return invoke(object,
                      methodObj,
                      argValues);
    }

    public static Object invoke(Object object,
                                Method method,
                                Object[] args) {

        try {
            return method.invoke(object,
                                 args);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof RuntimeException) {
                throw (RuntimeException) e.getTargetException();
            }
            throw new RuntimeException(e.getTargetException());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object toTypeValue(Class<?> type,
                                      String fieldValue) {

        if (type == String.class) {
            return fieldValue;
        }

        TypeConverter<?> typeConverter = TypeConverterFactory.getTypeConverter(type);

        if (typeConverter == null) {
            throw new RuntimeException("Unknown Type : " + type);
        }

        return typeConverter.deserialize(fieldValue);

    }

    @SuppressWarnings("unchecked")
    public static <T> String toString(T value) {

        if (value instanceof String) {
            return (String)value;
        }

        TypeConverter<T> typeConverter = (TypeConverter<T>) TypeConverterFactory.getTypeConverter(value.getClass());

        if (typeConverter == null) {
            throw new RuntimeException("Unknown Type : " + value.getClass());
        }

        return typeConverter.serialize(value);

    }

    
    
    public static Object toFieldTypeValue(Class<?> claz,
                                          String fieldName,
                                          String fieldValue,
                                          boolean isList) {

        Field field = getField(claz,
                               fieldName);
        Class<?> fieldType = field.getType();

        if (isList) {
            return makeList(fieldType,
                            fieldValue);
        }

        return toTypeValue(fieldType,
                           fieldValue);
    }

    public static Field getField(Class<?> claz,
                                 String name) {

        return getField(claz,
                        name,
                        null);
    }

    public static Field getField(Class<?> claz,
                                 String name,
                                 ObjectFieldVisitor fieldVisitor) {
        try {
            return getFieldInternal(claz,
                                    name,
                                    null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getFieldInternal(Class<?> claz,
                                          String name,
                                          ObjectFieldVisitor fieldVisitor) throws NoSuchFieldException {

        if (claz != Object.class) {

            Field[] fields = claz.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();

                if (name.indexOf(".") >= 0) {
                    String fName = name.substring(0,
                                                  name.indexOf("."));
                    if (fieldName.equals(fName)) {

                        if (fieldVisitor != null) {
                            fieldVisitor.visit(claz,
                                               field);
                        }

                        return getFieldInternal(field.getType(),
                                                name.substring(name.indexOf(".") + 1),
                                                fieldVisitor);
                    }
                }
                else {
                    if (fieldName.equals(name)) {

                        if (fieldVisitor != null) {
                            fieldVisitor.visit(claz,
                                               field);
                        }

                        return field;
                    }
                }
            }

            Class<?> superClass = claz.getSuperclass();
            return getFieldInternal(superClass,
                                    name,
                                    fieldVisitor);
        }

        throw new NoSuchFieldException(name);
    }

    public static List<Field> getAllFields(Class<?> claz) {

        List<Field> fieldList = new ArrayList<Field>(1);

        if (claz != Object.class) {
            Field[] fields = claz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));

            Class<?> superClass = claz.getSuperclass();

            if (superClass != Object.class) {
                fieldList.addAll(getAllFields(superClass));
            }

        }

        return fieldList;
    }

    public static Object getFieldValue(Object object,
                                       String fieldName) {

        String[] attributes = null;

        if (fieldName.indexOf(".") >= 0) {
            attributes = fieldName.split("[.]");
        }

        if (attributes != null) {
            int i = 0;
            for (String attr : attributes) {
                object = getFieldValue(object,
                                       attr);

                if (object == null) {
                    return null;
                }
                if (++i == attributes.length) {
                    return object;
                }
            }

        }

        String getMethod = "get" + StringHelper.capitalizeFirstLetter(fieldName);

        Method method = null;
        try {

            method = object.getClass()
                           .getMethod(getMethod,
                                      (Class[]) null);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return invoke(object,
                      method,
                      null);
    }

    public static void setFieldValue(Object object,
                                     String name,
                                     Object value,
                                     boolean deep) {

        if (deep) {
            ObjectFieldVisitor fieldVisitor = new ObjectFieldVisitor(object,
                                                                     value);
            getField(object.getClass(),
                     name,
                     fieldVisitor);
        }
        else {
            setFieldValue(object,
                          name,
                          value);
        }
    }

    public static void setFieldValue(Object object,
                                     String name,
                                     Object value) {

        Field field = getField(object.getClass(),
                               name);
        setFieldValue(object,
                      field,
                      value);
    }

 /*   public static boolean isFieldRequired(Class<?> claz,
                                          String fieldName) {

        // Field field = null;

        // if (fieldName.indexOf(".") != -1) {
        // String childObjFieldName = fieldName.substring(0,
        // fieldName.indexOf("."));
        // field = getField(claz,
        // childObjFieldName);
        //
        // OneToOne oneToOne = field.getAnnotation(OneToOne.class);
        // if (oneToOne != null) {
        // CascadeType[] cascades = oneToOne.cascade();
        // if (cascades == null || cascades.length == 0) {
        //
        // JoinColumn column = field.getAnnotation(JoinColumn.class);
        // return (column != null) ? column.nullable() : false;
        //
        // }
        // }
        // }

        Field field = getField(claz,
                               fieldName);

        Column column = field.getAnnotation(Column.class);

        if (column != null) {
            return !column.nullable();
        }

        JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);

        if (joinColumn != null) {
            return !joinColumn.nullable();
        }

        return true;
    }*/

    public static Class<?> getParameterizedClass(Object object,
                                                 String fieldName) {

        Field field = getField(object.getClass(),
                               fieldName);
        Type type = field.getGenericType();

        if (type instanceof ParameterizedType) {

            return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];

        }

        throw new IllegalArgumentException("The field '" + fieldName + "' in the class '" + object.getClass() + "' is not a parameterized type");
    }

    private static boolean isPrimitive(Class<?> claz) {

        if (claz.isPrimitive()) {
            return true;
        }

        if (claz.isArray()) {
            return false;
        }

        return claz.getPackage()
                   .getName()
                   .startsWith("java.lang");
    }

    public static boolean isBuildInJavaTypes(Class<?> claz) {
        return isPrimitive(claz) || claz.getPackage()
                                        .getName()
                                        .startsWith("java.");
    }

    public static void exposeFieldValues(Object object,
                                         ClassFieldValueVisitor visitor) {

        List<Field> fields = getAllFields(object.getClass());

        try {
            for (Field field : fields) {

                field.setAccessible(true);

                String name = field.getName();

                if (!name.startsWith("this$")) {
                    Object value = field.get(object);

                    visitor.visit(name,
                                  value);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void setFieldValue(Object object,
                                      Field field,
                                      Object value) {
        field.setAccessible(true);

        Class<?> type = field.getType();

        TypeConverter<?> adapter = TypeConverterFactory.getTypeConverter(type);

        if (adapter != null) {

            if (value instanceof String) {

                String val = (String) value;

                if (val.trim()
                       .equals("")) {
                    value = null;
                }
                else {

                    value = adapter.deserialize((String) value);
                }
            }

        }

        try {

            field.set(object,
                      value);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static Object newFieldInstance(Object object,
                                          String fieldName) {

        Field field = getField(object.getClass(),
                               fieldName);
        return newFieldInstance(object,
                                field);
    }

    private static Object newFieldInstance(Object object,
                                           Field field) {

        Class<?> claz = field.getType();

        Object instance = null;

        if (claz == List.class) {

            instance = createList(claz);

        }
        else {

            try {
                instance = claz.newInstance();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        setFieldValue(object,
                      field,
                      instance);

        return instance;
    }

    private static <T> List<T> createList(Class<T> type) {

        return new ArrayList<T>();
    }

    private static List<Object> makeList(Class<?> type,
                                         String values) {

        String[] vals = values.split(",");

        List<Object> items = new ArrayList<Object>(vals.length);

        for (String val : vals) {

            items.add(toTypeValue(type,
                                  val));
        }

        return items;

    }

    public static class ObjectFieldVisitor {

        private Object mObject;
        private Object mValue;

        public ObjectFieldVisitor(Object object,
                                  Object value) {
            mObject = object;
            mValue = value;
        }

        public void visit(Class<?> childClass,
                          Field field) {

            if (isPrimitive(field.getType())) {
                setFieldValue(mObject,
                              field,
                              mValue);
            }
            else {
                field.setAccessible(true);
                try {
                    if (field.get(mObject) == null) {
                        mObject = newFieldInstance(mObject,
                                                   field);
                    }
                    else {
                        mObject = field.get(mObject);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public static interface ClassFieldValueVisitor {

        public void visit(String name,
                          Object value);
    }

}
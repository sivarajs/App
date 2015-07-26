package meru.data.format.json;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import meru.sys.lang.ClassHelper;
import meru.sys.lang.ClassHelper.ClassFieldValueVisitor;
import meru.sys.type.converter.CalendarConverter;
import meru.sys.type.converter.DateConverter;

public class JsonBuilder {

    public static final DecimalFormat FLOAT_FORMAT = new DecimalFormat("#.##");

    private StringBuilder mStringBuilder;
    private ClassFieldValueVisitor mClassFieldVisitor;

    public JsonBuilder() {
    }

    public final String build(Object object) {

        mStringBuilder = new StringBuilder();
        
        mClassFieldVisitor = new ClassFieldVisitorImpl();
        addObject(object);

        return mStringBuilder.toString();
    }

    public String build(Map<String, String> map) {
        mStringBuilder = new StringBuilder();
        if (!map.isEmpty()) {
            mStringBuilder.append("{");
            int j = 0;
            for (Map.Entry<String, String> value : map.entrySet()) {
                String val = value.getValue();
                if (val == null)
                    continue;
                if (j++ > 0) {
                    mStringBuilder.append(",");
                }

                addField(value.getKey(),
                         escape(val));

            }

            mStringBuilder.append("}");
        }

        return mStringBuilder.toString();
    }

    private static String escape(String value) {
        return value.replace('"',
                             '\'');
    }

    private void addObject(Object object) {

        mStringBuilder.append(JsonConstants.OBJECT_BEGIN);

        ClassHelper.exposeFieldValues(object,
                                      mClassFieldVisitor);

        mStringBuilder.append(JsonConstants.OBJECT_END);
    }

    private void addFieldSeparator() {

        if (mStringBuilder.charAt(mStringBuilder.length() - 1) != JsonConstants.OBJECT_BEGIN) {
            mStringBuilder.append(JsonConstants.ITEM_SEPARATOR);
        }
    }

    private void addArray(String name,
                          List<?> values) {

        mStringBuilder.append(JsonConstants.ARRAY_BEGIN);

        for (int i = 0; i < values.size(); i++) {

            addObject(values.get(i));

            if (i < values.size() - 1) {
                mStringBuilder.append(JsonConstants.ITEM_SEPARATOR);
            }
        }

        mStringBuilder.append(JsonConstants.ARRAY_END);
    }

    private void addList(String name,
                         List<?> values) {

        mStringBuilder.append(JsonConstants.ARRAY_BEGIN);

        int size = values.size();
        for (int i = 0; i < size; i++) {

            addObject(values.get(i));

            if (i < size - 1) {
                mStringBuilder.append(JsonConstants.ITEM_SEPARATOR);
            }
        }

        mStringBuilder.append(JsonConstants.ARRAY_END);
    }

    private void addField(String name,
                          Object value) {

        if (value == null) {
            return;
        }

        addFieldSeparator();
        mStringBuilder.append("\"")
                      .append(name)
                      .append("\"")
                      .append(JsonConstants.NAME_VALUE_SEPARATOR);

        Class<?> type = value.getClass();

        if (type == Float.class || type == float.class) {
            mStringBuilder.append("\"")
                          .append(FLOAT_FORMAT.format(value))
                          .append("\"");

        }
        else if (type.isPrimitive() || ClassHelper.extendsClass(type,
                                                                Number.class)
                || type == Boolean.class) {

            mStringBuilder.append("\"")
                          .append(value)
                          .append("\"");

        }
        else if (type == String.class) {

            value = ((String) value).replace('"',
                                             '\'');
            mStringBuilder.append("\"")
                          .append(value)
                          .append("\"");
        }
        else if (value instanceof java.sql.Timestamp || value instanceof Date) {
            mStringBuilder.append("\"")
                          .append(new DateConverter().serialize((Date) value))
                          .append("\"");
        }
        else if (value instanceof Calendar) {
            mStringBuilder.append("\"")
                          .append(new CalendarConverter().serialize((Calendar) value))
                          .append("\"");
        }
        else if (value instanceof List) {

            addList(name,
                    (List<?>) value);

        }
        else if (type.isArray()) {
            addArray(name,
                     Arrays.asList(type.cast(value)));
            // addArray(name, type.getClass().cast(value));

        }

        else {

            addObject(value);
        }

    }

    class ClassFieldVisitorImpl implements ClassFieldValueVisitor {
        public void visit(String name,
                          Object value) {

            addField(name,
                     value);
        }
    }

}

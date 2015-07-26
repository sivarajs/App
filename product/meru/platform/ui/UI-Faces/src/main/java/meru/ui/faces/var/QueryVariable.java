package meru.ui.faces.var;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

public class QueryVariable extends ViewVariable {

    public static final String NAME = "query";

    private Map<String, String[]> mValues;

    public QueryVariable(Map<String, String[]> values) {
        super(NAME);
        mValues = values;
    }

    @Override
    public Object getFieldValue(String fieldName) {
        if (mValues == null) {
            return null;
        }

        String[] value = mValues.get(fieldName);

        if (value != null) {
            return getValue(value[0]);
        }

        return null;
    }

    private static String getValue(String paramValue) {
        if (paramValue.startsWith("${")) {
            return paramValue.replace("${",
                                      "#{");
        }

        try {
            return URLDecoder.decode(paramValue,
                                     "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

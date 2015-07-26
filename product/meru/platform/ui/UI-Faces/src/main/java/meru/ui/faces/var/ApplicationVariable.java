package meru.ui.faces.var;

import java.util.Map;

public class ApplicationVariable extends ViewVariable {

    public static final String NAME = "application";

    private Map<String, Object> mValues;

    public ApplicationVariable( Map<String, Object> values) {
        super(NAME);
        mValues = values;
    }

    @Override
    public Object getFieldValue(String fieldName) {
        return getValue(fieldName);
      }
    
    public final Object getValue(String varName) {
        return mValues.get(varName);
    }
}

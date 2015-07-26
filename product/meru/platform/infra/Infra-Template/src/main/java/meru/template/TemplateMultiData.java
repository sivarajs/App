package meru.template;

import java.util.HashMap;
import java.util.Map;

import meru.sys.lang.ClassHelper;

public class TemplateMultiData extends TemplateData {

    private Map<String, Object> mData;

    public TemplateMultiData() {
        mData = new HashMap<String, Object>(2);
    }

    public void addObject(String name,
                          Object object) {
        mData.put(name,
                  object);
    }

    public String getExpressionValue(String expression) {

        
        int index = expression.indexOf(".");
        String name = expression.substring(0,
                                           index);
        Object object = mData.get(name);
        if (object != null) {
            Object result = ClassHelper.getFieldValue(object,
                                                      expression.substring(index + 1));

            if (result != null) {
                return ClassHelper.toString(result);
            }
        }

        return null;
    }

}

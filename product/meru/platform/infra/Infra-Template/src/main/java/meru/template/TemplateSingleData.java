package meru.template;

import meru.sys.lang.ClassHelper;

public class TemplateSingleData extends TemplateData {

  private Object mData;
  
  public TemplateSingleData(Object data) {
    mData = data;
  }
  
  public String getExpressionValue(String expression) {
    
    int index = expression.indexOf(".");
    Object result = ClassHelper.getFieldValue(mData, expression.substring(index+1));
    
    if (result != null) {
      return ClassHelper.toString(result);
    }
    
    return null;
  }
  
}

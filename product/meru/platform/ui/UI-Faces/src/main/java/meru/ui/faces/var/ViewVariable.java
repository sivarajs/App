package meru.ui.faces.var;

import java.beans.Introspector;

import meru.sys.lang.ClassHelper;

public class ViewVariable {

  private String mName;
  private Object mValue;
  
  public ViewVariable(String name) {
    mName = Introspector.decapitalize(name);
  }
  
  public String getName() {
    return mName;
  }
  
  public boolean represents(String variableName) {
    return mName.equals(variableName);
  }
  
  public void setValue(Object value) {
    mValue = value;
  }
  
  public Object getValue() {
    return mValue;
  }
  
  public Object getFieldValue(String fieldName) {
    return ClassHelper.getFieldValue(mValue, fieldName);
  }
  
}

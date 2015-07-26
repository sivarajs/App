package meru.ui.faces.renderer.expr;

import meru.ui.faces.renderer.UIView;

public class UIConstantExpression extends UIExpression {

  private String mValue;
  
  public UIConstantExpression(String value) {
    
    value = value.trim();
    if (!value.equals("null")) {
    
      mValue = value;
    }
  }
  
  @Override
  public Object getValue(UIView view) {
    return mValue;
  }
}

package meru.ui.faces.renderer.expr;

import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.html.HtmlBuilder;
import meru.el.EL;
public class ObjectFieldExpressionParser implements EL.ExpressionParser {

  private HtmlBuilder mHtmlBuilder;
  private Object mObject;
  
  public ObjectFieldExpressionParser(HtmlBuilder htmlBuilder, Object object) {
    mHtmlBuilder = htmlBuilder;
    mObject = object;
  }
  
  @Override
  public void readVariable(String variable) {
    mHtmlBuilder.addText("", false);
    
    Object value = ClassHelper.getFieldValue(mObject, variable.substring(variable.indexOf('.')+1));
    
    if (value != null) {
      mHtmlBuilder.addText(value.toString(), false);
    }
    
  }
  
  @Override
  public void readText(String text) {
    mHtmlBuilder.addText(text, false);
  }
}

package meru.template;


public class TemplateStringData extends TemplateData {

  private String mData;
  
  public TemplateStringData(String data) {
    mData = data;
  }
  
  public String getData() {
    return mData;
  }
  
  public String getExpressionValue(String expression) {
    
    return mData;
  }
  
}

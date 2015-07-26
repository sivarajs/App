package meru.xml;

import java.util.Stack;

public class XMLBuilder {

  private String mEncoding = "UTF-8";
  protected StringBuilder mStringBuilder;
  private Stack<String> mStack;
  
  private boolean mTagClosed = true;
  
  public XMLBuilder() {
    mStringBuilder = new StringBuilder();
    mStack = new Stack<String>();
  }
  
  public void reset() {
    mStringBuilder.delete(0, mStringBuilder.length());
  }
  
  public boolean isEmpty() {
      return mStringBuilder.length() == 0;
  }
  
  public String getXML() {
    return mStringBuilder.toString();
  }
  
  public String toString() {
    return mStringBuilder.toString();
  }
  
  public String getCharacterEncoding() {
     return mEncoding;
  }
  
  public XMLBuilder startDocument() {
    return this;
  }
  
  public XMLBuilder endDocument() {
    return this; 
  }
  
  public XMLBuilder startElement(String name) {
    
    closeBeginTag();
    
    if (name == null) {
      return this;
    }
    
    mStringBuilder.append("<");
    mStringBuilder.append(name);
    mTagClosed = false;
    
    
    mStack.push(name);
    
    return this;
   
  }
  
  public XMLBuilder endElement() {
    
    String name = mStack.pop();
    
    return _endElement(name);
  }
  
  public XMLBuilder endElement(String name) {
    
    mStack.pop();
    return _endElement(name);
  }
  
  private XMLBuilder _endElement(String name) {
    
    closeBeginTag();
    
    if (name == null) {
      return this;
    }
    
    mStringBuilder.append("</");
    mStringBuilder.append(name);
    mStringBuilder.append(">");
    
    
    return this;
    
  }
  
  public final XMLBuilder addClassAttribute(String value) {
    return addAttribute("class",value);
  }
  
  public final XMLBuilder addStyleAttribute(String value) {
    return addAttribute("style",value);
  }
  
  public XMLBuilder addAttribute(String name, Object value) {
    if (value == null) {
      return this;
    }
    return addAttribute(name,value.toString());
  }
  
  public final XMLBuilder addAttribute(String name, String value) {
    
    if (value == null || value.equals("")) {
      return this;
    }
    
    mStringBuilder.append(" ");
    mStringBuilder.append(name);
    mStringBuilder.append("=\"");
    mStringBuilder.append(value);
    mStringBuilder.append("\"");
    return this;
  }
  
  public XMLBuilder startCDATA() {
    
    closeBeginTag();
    return this;
    
  }
  
  public XMLBuilder endCDATA() {
    return this;
  }

  public XMLBuilder addText(String text) {
    
    closeBeginTag();
    
    if (text != null) {
      mStringBuilder.append(text);
    }
    
    return this;
  }
  
  public XMLBuilder addText(String text, boolean closeTag) {
    
    if (closeTag) {
      closeBeginTag();
    }
    mStringBuilder.append(text);
    return this;
    
  }

  
  public XMLBuilder addHtmlText(String htmlText) {
    
    closeBeginTag();
    mStringBuilder.append(htmlText);
    return this;
    
  }
  
  public XMLBuilder closeBeginTag() {
    
    if (!mTagClosed) {
      
      mStringBuilder.append(">");
      mTagClosed = true;  
    }
    
    return this;
  }
  
  public XMLBuilder completeBeginTag() {
      
      if (!mTagClosed) {
        
        mStringBuilder.append("/>");
        mTagClosed = true;  
      }
      
      return this;
    }

  
}

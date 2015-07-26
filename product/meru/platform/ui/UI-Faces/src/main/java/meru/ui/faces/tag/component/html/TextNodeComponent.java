package meru.ui.faces.tag.component.html;

import meru.xml.XMLNodeHelper;

import org.w3c.dom.Node;

public class TextNodeComponent extends HtmlComponent {

  protected String value;

  public TextNodeComponent() {
  }

  public void loadTextNode(Node node) {
    value = XMLNodeHelper.getNodeValue(node);
    if (!value.equals("")) {
      value = value.replace("<",
                            "&lt;");
      value = value.replace(">",
                            "&gt;");
    }
  }
  
  public String getValue() {

    return value;
  }
}

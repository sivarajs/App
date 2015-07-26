package meru.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLNodeHelper {

  public static boolean isTrue(Element element,
                               String attrName) {

    String value = getAttribute(element,
                                attrName);
    return "true".equals(value);
  }

  public static boolean isAbsentOrTrue(Element element,
                                       String attrName) {

    String value = getAttribute(element,
                                attrName);
    return value == null || "true".equals(value);
  }

  public static boolean isPresent(Element element,
                                  String attrName) {

    return getAttribute(element,
                        attrName) != null;
  }

  public static boolean isAbsentOrFalse(Element element,
                                        String attrName) {

    String value = getAttribute(element,
                                attrName);
    return value == null || "false".equals(value);
  }

  public static Element getGrandParent(Element element,
                                       String name) {

    Node node = element.getParentNode();

    while (node != null) {

      if (node.getNodeType() == Node.ELEMENT_NODE
          && name.equalsIgnoreCase(node.getLocalName())) {

        return (Element) node;

      }

      node = node.getParentNode();
    }

    // throw new
    // IllegalArgumentException("Parent '"+name+"' is not found for '"+element.getLocalName()+"'");
    return null;

  }

  public static String getAttribute(Element element,
                                    String attrName) {

    String value = element.getAttribute(attrName);

    return "".equals(value) ? null : value;
  }

  public static String getNodeValue(Node node) {

    switch (node.getNodeType()) {

      case Node.CDATA_SECTION_NODE:
        return node.getNodeValue();
      case Node.TEXT_NODE:
        return node.getNodeValue();
      case Node.ELEMENT_NODE:
        if (node.getFirstChild() != null) {
          return node.getFirstChild()
                      .getNodeValue();
        }
        return "";

    }
    
    throw new IllegalArgumentException("Invalid Node : " + node.getNodeName());
  }

  public static void setCSSStyleAttribute(Element element,
                                     String attrName,
                                     String value) {

    appendAttribute(element,
                    attrName,
                    value,
                    ";");

  }
  
  public static void setCSSClassAttribute(Element element,
                                          String value) {

         appendAttribute(element,
                         "class",
                         value,
                         " ");

       }

  public static void appendAttribute(Element element,
                                     String attrName,
                                     String value,
                                     String separator) {

    String val = getAttribute(element,
                              attrName);
    if (val == null) {
      val = value;
    }

    else {
      if (!val.endsWith(separator)) {
        val += separator;
      }

      val += value;
    }

    element.setAttribute(attrName,
                         val);
  }

  public static void setAttribute(Element element,
                                  String attrName,
                                  String value) {

    element.setAttribute(attrName,
                         value);
  }

  public static boolean containsName(Node node,
                                     String ns,
                                     String nodeName) {

    if (ns == null) {
      return node.getLocalName()
                 .equals(nodeName);
    }

    return node.getNamespaceURI()
               .equals(ns) && node.getLocalName()
                                  .equals(nodeName);
  }

  public static boolean containsElement(Node node) {

    node = node.getFirstChild();

    while (node != null) {

      if (node.getNodeType() == Node.ELEMENT_NODE) {
        return true;
      }

      node = node.getNextSibling();
    }

    return false;
  }

  public static void insertFirst(Element parent,
                                 Node node) {

    if (parent.getFirstChild() == null) {
      parent.appendChild(node);
    }
    else {
      parent.insertBefore(node,
                          parent.getFirstChild());
    }

  }

  public static void insertAt(Element parent,
                              Node node,
                              int index) {

    Node child = parent.getFirstChild();

    int count = 0;
    boolean added = false;
    while (child != null) {

      if (child.getNodeType() == Node.ELEMENT_NODE) {

        if (count == index) {

          if (parent.getFirstChild() == null) {
            parent.appendChild(node);
          }
          else {
            parent.insertBefore(node,
                                child);
          }
          added = true;
          break;
        }
        count++;

      }

      child = child.getNextSibling();

    }

    if (!added) {
      // throw new IndexOutOfBoundsException(count+" < "+index);
      parent.appendChild(node);
    }
  }

  public static int getChildElementCount(Node node) {

    Node child = node.getFirstChild();

    int count = 0;

    while (child != null) {

      if (child.getNodeType() == Node.ELEMENT_NODE) {

        count++;

      }

      child = child.getNextSibling();
    }

    return count;
  }

  public static Element getFirstChildElement(Element element,
                                             String nodeName) {

    Node node = element.getFirstChild();

    while (node != null) {

      if (node.getNodeType() == Node.ELEMENT_NODE && node.getLocalName()
                                                         .equals(nodeName)) {
        return (Element) node;
      }

      node = node.getNextSibling();
    }

    return null;
  }

  public static Element getFirstChildElement(Element element,
                                             String ns,
                                             String nodeName) {

    if (ns == null) {
      return getFirstChildElement(element,
                                  nodeName);
    }

    Node node = element.getFirstChild();

    while (node != null) {

      if (ns.equals(node.getNamespaceURI())
          && node.getNodeType() == Node.ELEMENT_NODE && node.getLocalName()
                                                            .equals(nodeName)) {
        return (Element) node;
      }

      node = node.getNextSibling();
    }

    return null;
  }

}

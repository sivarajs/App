package meru.ui.faces.tag;

import java.util.ArrayList;
import java.util.List;

import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;

public abstract class UITag {

    protected static final String ATTR_ENTITY = "entity";
    protected static final String ATTR_FILTER = "filter";

    private String tagName;
    protected Element uiElement;

    protected String cssStyle;
    protected String cssClass;

    protected UITag parent;
    protected List<UITag> childComponents = new ArrayList<UITag>(1);
    
    public UITag(String tagName) {
        this.tagName = tagName;
        cssClass = tagName;
    }

    public UITag(String tagName,
                 String kindOf) {
        this.tagName = tagName;
        cssClass = tagName;
        setCSSClass(kindOf);
    }

    public void setUIElement(Element element) {
        uiElement = element;
    }
    
    private void setParent(UITag parent) {
      this.parent = parent;
  }
    
    public UITag getParent(String name) {
        if (parent == null) {
            return null;
        }
        
        if (name.equals(parent.tagName)) {
            return parent;
        }
        
        return parent.getParent(name);
    }
    
    public void addChildTag(UITag childTag) {
        childTag.setParent(this);
        childComponents.add(childTag);
    }

    public abstract String getTagNamespace();

    public String getTagName() {
        return tagName;
    }

    public final Element getUIElement() {
        return uiElement;
    }

    public final String getUIElementTagLocalName() {
        return uiElement == null ? null : uiElement.getLocalName();
    }

    public boolean isPseudoComponent() {
        return false;
    }

    public boolean isChildless() {
        return false;
    }

    public String getId() {
        return getAttribute("id");
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getLabel() {
        return getAttribute("label");
    }
    
    public String getLabelClass() {
        return getAttribute("labelClass");
    }
    
    public String getLabelStyle() {
        return getAttribute("labelStyle");
    }

    public String getWidth() {
        return getAttribute("width");
    }

    public String getHeight() {
        return getAttribute("height");
    }

    public String getBinds() {
        return getAttribute("binds");
    }

    public String rendered() {
        return getAttribute("rendered");
    }

    public String onClick() {
        return getAttribute("onclick");
    }

    public boolean requiresClientProcessing() {
        return true;
    }

    public void setChildTags(List<UITag> childTags) {
        childComponents = childTags;
    }

    public List<UITag> getChildTags() {
        return childComponents;
    }
    
    public final UITag getFirstChildTag(String tagName) {
        for (UITag tag : childComponents) {
            if (tag.tagName.equals(tagName)) {
                return tag;
            }
        }
        
        return null;
    }

    
    public void setCSSClass(String compClass) {

        if (compClass != null) {
            cssClass = (cssClass == null) ? compClass : compClass +" "+cssClass;
        }
    }

    public void setCSSStyle(String style) {

        if (style != null) {
            cssStyle = (cssStyle == null) ? style : cssStyle + ";" + style;
        }
    }

    public boolean load(Element uiElement) {
        this.uiElement = uiElement;

        if (this instanceof Identifiable && !XMLNodeHelper.isPresent(uiElement,
                                                                     "id")) {
            XMLNodeHelper.setAttribute(uiElement,
                                       "id",
                                       newId());
        }

        cssStyle = getAttribute("style");
        String cssClass = getAttribute("styleClass");
        setCSSClass(cssClass);
        cssClass = getAttribute("class");
        setCSSClass(cssClass);
        return load();
    }

    public boolean ignoreTextNode() {
        return false;
    }

    protected boolean load() {
        return true;
    }

    protected final String getAttribute(String name) {

        return XMLNodeHelper.getAttribute(uiElement,
                                          name);
    }

    protected final String getMandatoryAttribute(String name) {

        String value = XMLNodeHelper.getAttribute(uiElement,
                                                  name);

        if (value == null) {

            throw new RuntimeException("The attribute '" + name
                    + "' is mandatory in the element '"
                    + uiElement.getLocalName() + "'");

        }

        return value;
    }

    protected final String getAttribute(String name,
                                        String defaultVal) {

        String value = getAttribute(name);

        if (value == null) {

            return defaultVal;
        }

        return value;
    }

    protected final Integer getIntAttribute(String name,
                                            Integer defaultValue) {

        String value = getAttribute(name);

        if (value == null) {
            return (defaultValue == null) ? null : defaultValue;
        }

        return Integer.parseInt(value);
    }

    protected final long getLongAttribute(String name,
                                          Long defaultValue) {

        String value = getAttribute(name);

        if (value == null) {
            return (defaultValue == null) ? null : defaultValue;
        }

        return Long.parseLong(value);
    }

    protected final boolean isAttributeTrue(String name) {

        return XMLNodeHelper.isTrue(uiElement,
                                    name);
    }

    protected Element createElement(String namespace,
                                    String name) {
        return uiElement.getOwnerDocument()
                        .createElementNS(namespace,
                                         name);
    }

    // TODO
    static long time = System.currentTimeMillis();
    static long count;

    public String newId() {

        return String.valueOf(time + "-" + (count++));

    }

}

package meru.ui.faces.tag.component.output;

import meru.el.EL;
import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

public abstract class Output extends UIComponent {

    public static final String ATTR_ATTR = "attr";
    public static final String ATTR_VALUE = "value";
    
    private static final String LABEL_CLASS = "label";
    private static final String OUTPUT_CLASS = "output";

    public Output(String componentName) {
        super(componentName);
    }

    public String getAttr() {
        return getAttribute(ATTR_ATTR);
    }

    public final String getValue() {
        return getAttribute(ATTR_VALUE);
    }

    public String getLabelClass(String labelClass) {
        if (labelClass == null) {
            return LABEL_CLASS;
        }
        
        return labelClass+","+LABEL_CLASS;
    }
    
    public String getOutputClass() {
        String styleClass = getAttribute("outputClass");
        if (styleClass == null) {
            return OUTPUT_CLASS;
        }
        
        return styleClass+","+OUTPUT_CLASS;
    }
    
    @Override
    protected boolean load() {

        String value = getAttribute(ATTR_VALUE);
        if (value != null) {

            if (EL.isBindVariable(value)) {
                XMLNodeHelper.setAttribute(uiElement,
                                           ATTR_ATTR,
                                           value);
            }
        }
        return true;
    }
}

package meru.ui.faces.tag.component.input;

import meru.el.EL;
import meru.persistence.PersistenceUtil;
import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

public class Input extends UIComponent {

    public static final String NAME = "inputText";

    public static final String ATTR_TYPE = "type";
    public static final String ATTR_NAME = "name";
    public static final String ATTR_LABEL = "label";
    public static final String ATTR_ATTR = "attr";
    public static final String ATTR_VAR = "var";
    public static final String ATTR_VALUE = "value";
    public static final String ATTR_DEFAULT = "default";
    public static final String ATTR_DISABLED = "disabled";
    public static final String ATTR_REG_EXP = "regex";
    public static final String ATTR_LENGTH = "len";
    public static final String ATTR_REQUIRED = "required";
    public static final String ATTR_HELP = "help";
    
    public static final String ATTR_ON_CHANGE = "onchange";

    private static final String LABEL_CLASS = "label";
    private static final String INPUT_CLASS = "input";
    
    
    
    private String type;

    public Input(String name) {
        super(name);
        type = "text";
        if (!name.equals(NAME)) {
        	setCSSClass(NAME);	
        }
    }

    public Input(String name,
                 String type) {
        super(name);
        this.type = type;
        setCSSClass(NAME);
    }

    public String getName() {
        return getAttribute(ATTR_NAME);
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return getAttribute(ATTR_VALUE);
    }

    public String getVar() {
        return getAttribute(ATTR_VAR);
    }

    public String getDefault() {
        return getAttribute(ATTR_DEFAULT);
    }

    public String getDisabled() {
        return getAttribute(ATTR_DISABLED);
    }
    
    public String getHelp() {
        return getAttribute(ATTR_HELP);
    }

    public String isRequired() {
        return getAttribute(ATTR_REQUIRED);
    }

    public String getOnChange() {
        return getAttribute(ATTR_ON_CHANGE);
    }
    
    public String getLabelClass(String labelClass) {
        if (labelClass == null) {
            return LABEL_CLASS;
        }
        
        return labelClass+","+LABEL_CLASS;
    }
    
    public String getInputClass(String inputClass) {
        if (inputClass == null) {
            return INPUT_CLASS;
        }
        
        return inputClass+","+INPUT_CLASS;
    }
    
    public boolean isRequired(Class<?> entityClass) {

        if (XMLNodeHelper.isTrue(uiElement,
                                 ATTR_REQUIRED)) {
            return true;
        }

        String inputValue = getValue();

        if (inputValue == null || !EL.isBindVariable(inputValue)) {
            return false;
        }

        String fieldName = EL.getBindVariable(inputValue);
        fieldName = fieldName.substring(fieldName.indexOf(".") + 1);

        boolean req = PersistenceUtil.isFieldRequired(entityClass,
                                                  fieldName);
        XMLNodeHelper.setAttribute(uiElement,
                                   ATTR_REQUIRED,
                                   req ? "true" : "false");

        return req;
    }

}

package meru.ui.faces.tag.component.table;

import meru.ui.faces.tag.component.UIComponent;

public class EntityFilter extends UIComponent {

    public static final String NAME = "filter";
    
    public EntityFilter() {
        super(NAME);
    }
    
    public String getLabel() {
        return getAttribute("label");
    }
    
    public String getValue() {
        return getAttribute("value");
    }
}

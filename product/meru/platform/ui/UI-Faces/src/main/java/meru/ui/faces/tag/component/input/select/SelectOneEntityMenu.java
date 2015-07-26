package meru.ui.faces.tag.component.input.select;

import meru.ui.faces.tag.component.input.Input;

public class SelectOneEntityMenu extends Input {

    public static final String NAME = "selectOneEntityMenu";

    public SelectOneEntityMenu() {
        super(NAME);
    }

    public String getEntity() {
        return getMandatoryAttribute(ATTR_ENTITY);
    }

    public String getFilter() {
        return getAttribute(ATTR_FILTER);
    }
    
    @Override
    public String getDefault() {
        return getAttribute("default");
    }
}

package meru.ui.faces.tag.component.table;

import org.w3c.dom.Element;

public class EntityColumns extends DynamicColumns {

    public static final String NAME = "entityColumns";

    public EntityColumns() {
        super(NAME);
    }

    @Override
    protected boolean load() {
        ((Element) uiElement.getParentNode()
                            .getParentNode()).setAttribute("dynamic",
                                                           "true");
        return true;
    }
    
    public String getEntityName() {
        return getMandatoryAttribute("entity");
    }

    public String getFilter() {
        return getAttribute("filter");
    }
}

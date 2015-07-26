package meru.ui.faces.tag.component.tree;

import meru.xml.XMLNodeHelper;


public class EntityTree extends Tree {

    public static final String NAME = "entityTree";

    public EntityTree() {
        super(NAME);
    }

    public String getEntity() {
        return getMandatoryAttribute("entity");
    }
    
    public String getFilter() {
        return getAttribute("filter");
    }
    
    public String getTitle() {
        return getAttribute("title");
    }
    
    public String getSelectedEntityId() {
        return getAttribute("selectedEntityId");
    }
    
    public boolean hideRoot() {
        return XMLNodeHelper.isTrue(uiElement, "hideRoot");
    }
}

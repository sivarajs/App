package meru.ui.faces.tag.component;

import meru.xml.XMLNodeHelper;

public class UIEntity extends UIComponent {

    public static final String NAME = "entity";

    public UIEntity() {
        super(NAME);
    }

    public String getName() {
        return getMandatoryAttribute("name");
    }

    public String getEntityId() {
        return getAttribute("entityId");
    }

    public String getFilter() {
        return getAttribute("filter");
    }
    
    public boolean ignoreIfNotAvailable() {
        return XMLNodeHelper.isTrue(uiElement, "ignoreIfNotAvailable");
    }
    
    @Override
    public boolean isPseudoComponent() {
        return true;
    }
}

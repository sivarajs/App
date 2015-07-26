package meru.ui.faces.tag.component.panel;

import meru.ui.faces.tag.component.UIComponent;

public class PanelGroup extends UIComponent {

    public static final String NAME = "panelGroup";

    public PanelGroup() {
        super(NAME);
    }

    public String getTitle() {
        return getAttribute("title");
    }
    
    public String getPanelContentClass() {
        return getAttribute("panelContentClass");
    }
    
    public String getPanelContentStyle() {
        return getAttribute("panelContentStyle");
    }

}

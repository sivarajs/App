package meru.ui.faces.tag.component.toolbar;

import meru.ui.faces.tag.component.UIComponent;

public class ToolbarCommand extends UIComponent {

    public static final String NAME = "toolbarCommand";

    public ToolbarCommand() {
        super(NAME);
    }

    
    public String getCommand() {
        return getAttribute("command");
    }

    public String getBGClass() {
        return getAttribute("bgClass");
    }

}

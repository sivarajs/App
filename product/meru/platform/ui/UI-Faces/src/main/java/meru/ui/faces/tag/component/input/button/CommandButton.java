package meru.ui.faces.tag.component.input.button;

import meru.ui.faces.tag.component.UIComponent;

public class CommandButton extends UIComponent {

    public static final String NAME = "commandButton";

    public CommandButton() {
        super(NAME);
    }

    public String getValue() {
        return getMandatoryAttribute("value");
    }

    @Override
    public boolean isChildless() {
        return true;
    }
}

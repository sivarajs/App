package meru.ui.faces.tag.component.template;

import meru.ui.faces.tag.component.UIFileComponent;

public class Template extends UIFileComponent {

    public static final String NAME = "template";

    public Template() {
        super(NAME);
    }

    public String getSrc() {
        return getMandatoryAttribute("src");
    }

    @Override
    public boolean isPseudoComponent() {
        return true;
    }
}

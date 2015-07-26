package meru.ui.faces.tag.component;


public class UIInclude extends UIFileComponent {

    public static final String NAME = "include";

    public UIInclude() {
        super(NAME);
    }

    @Override
    public boolean isPseudoComponent() {
        return true;
    }
}

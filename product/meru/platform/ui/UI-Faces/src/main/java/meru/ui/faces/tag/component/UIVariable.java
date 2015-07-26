package meru.ui.faces.tag.component;

public class UIVariable extends UIComponent {

    public static final String NAME = "variable";

    public UIVariable() {
        super(NAME);
    }

    public String getNameAttr() {
        return getMandatoryAttribute("name");
    }

    public String getValue() {
        return getMandatoryAttribute("value");
    }
    
    @Override
    public boolean isPseudoComponent() {
        return true;
    }
}

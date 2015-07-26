package meru.ui.faces.tag.component;


public class UIFileComponent extends UIComponent {

    public UIFileComponent(String name) {
        super(name);
    }

    public String getSrc() {
        return getMandatoryAttribute("src");
    }

}

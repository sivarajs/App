package meru.ui.faces.tag.component.input;

public class InputHidden extends InputText {

    public static final String NAME = "inputHidden";

    public InputHidden() {
        super(NAME);
    }

    public String getType() {
        return "hidden";
    }
    
    @Override
    public boolean isRequired(Class<?> entityClass) {
        return false;
    }

}

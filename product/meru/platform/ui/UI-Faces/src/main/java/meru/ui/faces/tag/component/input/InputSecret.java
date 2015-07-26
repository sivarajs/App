package meru.ui.faces.tag.component.input;


public class InputSecret extends InputText {

    public static final String NAME = "inputSecret";
    
    public InputSecret() {
        super(NAME);
    }
    
    public String getType() {
        return "password";
    }
}

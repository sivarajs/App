package meru.ui.faces.tag.component.input;

public class InputText extends Input {

    public static final String NAME = "inputText";

    private String type;

    public InputText() {
        super(NAME);
        type = "text";
    }

    public InputText(String name) {
        super(name);
        type = "text";
    }
    
    public InputText(String name,
                     String type) {
        super(name);
        this.type = type;
        setCSSClass(NAME);
    }

    public String getType() {
        return type;
    }
}

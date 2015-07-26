package meru.ui.faces.tag.component.input;

public class InputFile extends InputText {

    public static final String NAME = "inputFile";

    public InputFile() {
        super(NAME);
    }

    public String getType() {
        return "file";
    }
}

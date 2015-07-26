package meru.ui.faces.tag.component.input;

import meru.ui.faces.tag.Identifiable;

public class InputTextarea extends InputText implements Identifiable {

    public static final String NAME = "inputTextarea";

    public InputTextarea() {
        super(NAME);
    }

    public InputTextarea(String name) {
        super(name);
    }

    @Override
    protected boolean load() {
        super.load();
        String textareaType = getAttribute(ATTR_TYPE);
        if (textareaType != null && textareaType.equalsIgnoreCase("rich")) {
            setCSSClass("ckeditor");
        }

        return true;
    }
}

package meru.ui.faces.tag.component.dialog;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;

public class Dialog extends UIComponent implements Identifiable {

    public static final String NAME = "dialog";

    public Dialog() {
        super(NAME);
    }

    public String getTitle() {
        return getAttribute("title");
    }
}

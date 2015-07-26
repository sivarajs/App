package meru.ui.faces.tag.component.popup;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;

public class Popup extends UIComponent implements Identifiable {

    public static final String NAME = "popup";
    
    public Popup() {
        super(NAME);
    }

    public String getTitle() {
        return getAttribute("title");
    }

}

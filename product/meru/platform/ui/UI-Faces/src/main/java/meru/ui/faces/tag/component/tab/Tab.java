package meru.ui.faces.tag.component.tab;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;

public class Tab extends UIComponent implements Identifiable {

    public static final String NAME = "tab";

    public Tab() {
        super(NAME);
    }

    public String getTitle() {
        return getAttribute("title");
    }

    @Override
    public String newId() {
        
        return parent.getBinds()+"-"+getTitle().replace(' ', '_')+"Tab";
        
    }
}

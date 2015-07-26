package meru.ui.faces.tag.component.tab;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;

public class Tabs extends UIComponent implements Identifiable {

    public static final String NAME = "tabs";

    public Tabs() {
        super(NAME);
    }

    public String getTitle() {
        return getAttribute("title");
    }
    
    @Override
    public boolean ignoreTextNode() {
        return true;
    }
   
    @Override
    public String newId() {
        
        return getBinds()+"Tabs";
        
    }
}

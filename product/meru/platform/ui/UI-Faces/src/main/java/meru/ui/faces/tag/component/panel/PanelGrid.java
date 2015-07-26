package meru.ui.faces.tag.component.panel;

import meru.ui.faces.tag.component.UIComponent;

public class PanelGrid extends UIComponent {

    public static final String NAME = "panelGrid";

    public PanelGrid() {
        super(NAME);
    }
    
    public PanelGrid(String name) {
        super(name);
    }

    public int getColumns() {
        return getIntAttribute("columns",
                               1);
    }
    
    public String getColumnWidth() {
        int columns = getColumns();
        return 100/columns+"%";
    }

    public String getInputStyle() {
        return getAttribute("inputStyle");
    }

    public String getInputClass() {
        return getAttribute("inputClass");
    }

}

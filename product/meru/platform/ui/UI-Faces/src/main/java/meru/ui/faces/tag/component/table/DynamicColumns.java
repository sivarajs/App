package meru.ui.faces.tag.component.table;

import org.w3c.dom.Element;

public class DynamicColumns extends Column {

    public static final String NAME = "dynamicColumns";

    public DynamicColumns() {
        super(NAME);
    }

    public DynamicColumns(String name) {
        super(name);
    }

    @Override
    protected boolean load() {
        ((Element) uiElement.getParentNode()
                            .getParentNode()).setAttribute("dynamic",
                                                           "true");
        return true;
    }

}

package meru.ui.faces.tag.component.table;

import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

public class Column extends UIComponent {

    public static final String NAME = "column";

    public Column() {
        super(NAME);
    }

    public Column(String name) {
        super(name);
    }

    public String getAlign() {
        return getAttribute("align",
                            "left");
    }

    public String getValue() {
        return getAttribute("value");
    }

    public String getHref() {
        return getAttribute("href");
    }

    public String getType() {
        return getAttribute("type");
    }

    public String getContentStyle() {
        return getAttribute("contentStyle");
    }

    public String getContentClass() {
        return getAttribute("contentClass");
    }

    public String getValueAttr() {
        return getAttribute("value");
    }

    public String getOnInputChange() {
        return getAttribute("onInputChange");
    }

    public boolean isRemoveable() {
        return XMLNodeHelper.isTrue(uiElement,
                                    "removeable");
    }

    public boolean isEditable() {
        return XMLNodeHelper.isTrue(uiElement,
                                    "editable");
    }

    public boolean isNumberable() {
        return XMLNodeHelper.isTrue(uiElement,
                                    "numberable");
    }
}

package meru.ui.faces.tag.block;

import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;

public class ElseBlock extends UIBlock {

    public static final String NAME = "else";

    public ElseBlock() {
        super(NAME);
    }

    public String getCondition() {
        return XMLNodeHelper.getAttribute((Element) uiElement.getParentNode(),
                                          "condition");
    }

}

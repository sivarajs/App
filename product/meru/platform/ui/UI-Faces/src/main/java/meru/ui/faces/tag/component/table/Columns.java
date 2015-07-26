package meru.ui.faces.tag.component.table;

import java.util.List;

import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;

public class Columns extends UIComponent {

    public static final String NAME = "columns";

    public Columns() {
        super(NAME);
    }

    public List<UITag> getColumns() {
        List<UITag> columns = childComponents;
        return columns;
    }

    @Override
    protected boolean load() {

        if (XMLNodeHelper.isTrue((Element) uiElement.getParentNode(),
                                 "numberable")) {
            Element element = uiElement.getOwnerDocument()
                                       .createElementNS(NAMESPACE,
                                                        "column");

            element.setAttribute("width",
                                 "20");
            element.setAttribute("numberable",
                                 "true");
            uiElement.appendChild(element);
            uiElement.insertBefore(element, uiElement.getFirstChild());
        }

        if (XMLNodeHelper.isTrue((Element) uiElement.getParentNode(),
                                 "removeable")) {
            Element element = uiElement.getOwnerDocument()
                                       .createElementNS(NAMESPACE,
                                                        "column");

            element.setAttribute("width",
                                 "20");
            element.setAttribute("removeable",
                                 "true");
            uiElement.appendChild(element);
        }

        return true;
    }
}

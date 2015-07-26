package meru.ui.faces.tag.component.toolbar;

import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;

public class Toolbar extends UIComponent {

    public static final String NAME = "toolbar";

    public Toolbar() {
        super(NAME);
    }
    
    public String getEntity() {
        return getAttribute("entity");
    }

    public static Element getResourceLifeCycleToolbar(Element element,
                                                     String lifecyle) {

        Element buttonElem = null;

        int count = 0;
        if (lifecyle.contains("c")) {

            buttonElem = element.getOwnerDocument()
                                .createElementNS(UIComponent.NAMESPACE,
                                                 ToolbarCommand.NAME);
            buttonElem.setAttribute("label",
                                    "New");
            buttonElem.setAttribute("bgClass",
                                    "buttonNew");

            XMLNodeHelper.insertFirst(element,
                                      buttonElem);
            count++;
        }

        if (lifecyle.contains("d")) {
            buttonElem = element.getOwnerDocument()
                                .createElementNS(UIComponent.NAMESPACE,
                                                 ToolbarCommand.NAME);
            buttonElem.setAttribute("label",
                                    "Delete");
            buttonElem.setAttribute("bgClass",
                                    "buttonDelete");

            XMLNodeHelper.insertAt(element,
                                   buttonElem,
                                   count++);
        }

        if (count > 0) {

            Element separatorElem = element.getOwnerDocument()
                                           .createElementNS(UIComponent.NAMESPACE,
                                                            ToolbarSeparator.NAME);

            XMLNodeHelper.insertAt(element,
                                   separatorElem,
                                   count++);
        }

        return element;
    }
}

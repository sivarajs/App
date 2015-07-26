package meru.ui.faces.tag.component.pane.split;

import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SplitPane extends UIComponent {

    public static final String NAME = "splitPane";

    public SplitPane() {
        super(NAME);
    }

    public String getOrientation() {
        return getAttribute("orientation",
                            Orientation.VERTICAL.type);
    }

    @Override
    protected boolean load() {

        Element splitter = uiElement.getOwnerDocument()
                                    .createElementNS(UIComponent.NAMESPACE,
                                                     Splitter.NAME);

        Node node = uiElement.getFirstChild();

        int i = 0;
        while (node != null) {

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                i++;
                if (i == 1) {
                    XMLNodeHelper.setCSSClassAttribute((Element)node,"spLeft");
                }
                else if (i == 2) {
                    XMLNodeHelper.setCSSClassAttribute((Element)node,"spRight");
                    break;
                }
            }

            node = node.getNextSibling();
        }

        if (node == null) {
            throw new RuntimeException("Invalid Splitpane. Missing children.");
        }

        uiElement.insertBefore(splitter,
                               node);
        return true;
    }

    public enum Orientation {
        VERTICAL("vertical"),
        HORIZONTAL("horizontal");

        private String type;

        private Orientation(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

}

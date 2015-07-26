package meru.ui.faces.tag.component.template;

import java.util.List;

import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.UIComponent;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Node;

public class Parameter extends UIComponent {

    public static final String NAME = "parameter";

    public Parameter() {
        super(NAME);
    }

    public String getName() {
        return getMandatoryAttribute("name");
    }
    
    public boolean IsDeclaration() {
      return XMLNodeHelper.isTrue(uiElement, "isDecl");
  }
    
    @Override
    public boolean isPseudoComponent() {
        return true;
    }
    
    public void setChildComponents(List<UITag> childComponents) {
        this.childComponents = childComponents;
    }

    public boolean isTemplateParent() {
        Node parent = uiElement;
        while ((parent = parent.getParentNode()) != null) {

            if (Template.NAME.equals(parent.getLocalName())) {
                return true;
            }

        }

        return false;
    }

}

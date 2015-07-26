package meru.ui.faces.tag.component.table;

import java.beans.Introspector;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;
import meru.ui.faces.tag.component.toolbar.Toolbar;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;

public class EntityTable extends DataTable implements Identifiable {

    public static final String NAME = "entityTable";

    public EntityTable() {
        super(NAME);
    }

    //This method is required to create a predictable name for the table to create xpath for selenium
    @Override
    public String newId() {
        return getVar()+"Table";
    }
    
    public String getEntity() {
        return getMandatoryAttribute("entity");
    }
    
    public String getFilter() {
        return getAttribute("filter");
    }
    
    public boolean includeHeader() {
      return !"no".equals(getAttribute("header"));
  }

    public String getVar() {
        
        String entity = getEntity();
        String var = getAttribute("var");

        if (var == null) {
            var = Introspector.decapitalize(entity);
        }
        
        return var;
    }
    
    public boolean isAutoLoad() {
        return XMLNodeHelper.isAbsentOrTrue(uiElement, "autoLoad");
    }
    
    public boolean isDynamic() {
        return XMLNodeHelper.isTrue(uiElement, "dynamic");
    }
    

    @Override
    protected boolean load() {
        String lifeCycle = getLifeCycle();
        if (lifeCycle == null) {
            lifeCycle = "cd";
        }

        if (!lifeCycle.equals("none")) {

            Element element = XMLNodeHelper.getFirstChildElement(uiElement,
                                                                 UIComponent.NAMESPACE,
                                                                 Toolbar.NAME);

            if (element == null) {
                element = uiElement.getOwnerDocument()
                                   .createElementNS(UIComponent.NAMESPACE,
                                                    Toolbar.NAME);
                XMLNodeHelper.insertFirst(uiElement,
                                          element);
            }

            element.setAttribute("entity",
                                 getEntity());
            Toolbar.getResourceLifeCycleToolbar(element,
                                                lifeCycle);
        }
        Element filtersElem = XMLNodeHelper.getFirstChildElement(uiElement,
                                                                 EntityFilters.NAME);
        if (filtersElem != null) {

            Element popupElem = XMLNodeHelper.getFirstChildElement(uiElement,
                                                                   UIComponent.NAMESPACE,
                                                                   EntityFilters.NAME);

            popupElem.setAttribute("id",
                                   newId());
            // popupElem.appendChild(filtersElem.cloneNode(true));

            uiElement.removeChild(filtersElem);
        }

        return true;
    }
}

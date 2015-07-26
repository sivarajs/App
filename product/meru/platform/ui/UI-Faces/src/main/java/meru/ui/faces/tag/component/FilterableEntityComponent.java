package meru.ui.faces.tag.component;

public class FilterableEntityComponent extends UIComponent {

    public FilterableEntityComponent(String componentName) {
        super(componentName);
    }

    public FilterableEntityComponent(String componentName,
                                     String kindOf) {
        super(componentName,
              kindOf);
    }

    public String getEntityName() {
        return getMandatoryAttribute("entity");
    }

    public String getFilter() {
        return getAttribute("filter");
    }
}

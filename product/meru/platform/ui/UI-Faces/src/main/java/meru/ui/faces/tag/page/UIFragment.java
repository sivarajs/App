package meru.ui.faces.tag.page;

import meru.ui.faces.tag.component.UIComponent;

public abstract class UIFragment extends UIComponent {

    public static final String NAMESPACE = "app.ui.faces.page";
    
    public UIFragment(String componentName) {
        super(componentName);
    }
    
    @Override
    public String getTagNamespace() {
        return NAMESPACE;
    }
}

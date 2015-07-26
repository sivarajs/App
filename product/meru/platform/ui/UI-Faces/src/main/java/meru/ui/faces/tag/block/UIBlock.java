package meru.ui.faces.tag.block;

import meru.ui.faces.tag.component.UIComponent;

public abstract class UIBlock extends UIComponent {

    public static final String NAMESPACE = "app.ui.faces.block";
    
    public UIBlock(String componentName) {
        super(componentName);
    }
    
    @Override
    public String getTagNamespace() {
        return NAMESPACE;
    }
}

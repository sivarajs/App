package meru.ui.faces.tag.activity;

import meru.ui.faces.tag.component.UIComponent;

public abstract class UIActivity extends UIComponent {

    public static final String NAMESPACE = "app.ui.faces.activity";

    public UIActivity(String componentName) {
        super(componentName);
    }

    @Override
    public String getTagNamespace() {
        return NAMESPACE;
    }

}

package meru.ui.faces.tag.component;

import meru.ui.faces.tag.UITag;

public abstract class UIComponent extends UITag {

    public static final String NAMESPACE = "app.ui.faces.component";

    public UIComponent(String tagName) {
        super(tagName);
    }

    public UIComponent(String tagName,
                       String kindOf) {
        super(tagName,
              kindOf);
    }

    @Override
    public String getTagNamespace() {
        return NAMESPACE;
    }

    public final UIComponent getFirstChildComponent(String tagName) {

        return (UIComponent) getFirstChildTag(tagName);
    }
}

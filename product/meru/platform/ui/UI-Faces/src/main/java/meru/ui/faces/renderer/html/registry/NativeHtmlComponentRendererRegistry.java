package meru.ui.faces.renderer.html.registry;

import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.renderer.html.component.NativeHtmlComponentRenderer;
import meru.ui.faces.renderer.registry.UIComponentRendererRegistry;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.html.HtmlComponent;

public class NativeHtmlComponentRendererRegistry extends
        UIComponentRendererRegistry {

    public NativeHtmlComponentRendererRegistry() {

        super(HtmlComponent.NAMESPACE);

    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public <T extends UITag> UITagRenderer<T> getTagRenderer(String name) {

        return (UITagRenderer<T>) new NativeHtmlComponentRenderer();
    }

}

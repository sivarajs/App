package meru.ui.faces.renderer.html.registry;

import meru.ui.faces.renderer.html.activity.HtmlActivityRenderer;
import meru.ui.faces.renderer.registry.UIComponentRendererRegistry;
import meru.ui.faces.tag.activity.RedirectActivity;
import meru.ui.faces.tag.activity.ThrowActivity;
import meru.ui.faces.tag.activity.UIActivity;

public class HtmlActivityRendererRegistry extends UIComponentRendererRegistry {

    public HtmlActivityRendererRegistry() {
        super(UIActivity.NAMESPACE);
        mBuilders.put(ThrowActivity.NAME,HtmlActivityRenderer.class);
        mBuilders.put(RedirectActivity.NAME,HtmlActivityRenderer.class);
    }

}

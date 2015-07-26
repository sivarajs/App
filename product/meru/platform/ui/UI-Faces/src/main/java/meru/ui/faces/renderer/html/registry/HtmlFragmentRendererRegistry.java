package meru.ui.faces.renderer.html.registry;

import meru.ui.faces.renderer.html.page.HtmlSectionRenderer;
import meru.ui.faces.renderer.registry.UIComponentRendererRegistry;
import meru.ui.faces.tag.page.Section;
import meru.ui.faces.tag.page.UIFragment;

public class HtmlFragmentRendererRegistry extends UIComponentRendererRegistry {

    public HtmlFragmentRendererRegistry() {
        super(UIFragment.NAMESPACE);
        mBuilders.put(Section.NAME,HtmlSectionRenderer.class);
    }

}

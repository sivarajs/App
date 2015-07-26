package meru.ui.faces.renderer.registry;

import java.util.HashMap;
import java.util.Map;

import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.renderer.html.registry.HtmlActivityRendererRegistry;
import meru.ui.faces.renderer.html.registry.HtmlBlockRendererRegistry;
import meru.ui.faces.renderer.html.registry.HtmlComponentRendererRegistry;
import meru.ui.faces.renderer.html.registry.HtmlFragmentRendererRegistry;
import meru.ui.faces.renderer.html.registry.NativeHtmlComponentRendererRegistry;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.activity.UIActivity;
import meru.ui.faces.tag.block.UIBlock;
import meru.ui.faces.tag.component.UIComponent;
import meru.ui.faces.tag.component.html.HtmlComponent;
import meru.ui.faces.tag.page.UIFragment;

public class UITagRendererRegistryFactory {

    private static final Map<String, UITagRendererRegistry> mComponentBuilderRegistries = new HashMap<String, UITagRendererRegistry>(2);

    static {
        mComponentBuilderRegistries.put(UIComponent.NAMESPACE,
                                        new HtmlComponentRendererRegistry());
        mComponentBuilderRegistries.put(HtmlComponent.NAMESPACE,
                                        new NativeHtmlComponentRendererRegistry());

        mComponentBuilderRegistries.put(UIFragment.NAMESPACE,
                                        new HtmlFragmentRendererRegistry());

        mComponentBuilderRegistries.put(UIBlock.NAMESPACE,
                                        new HtmlBlockRendererRegistry());

        mComponentBuilderRegistries.put(UIActivity.NAMESPACE,
                                        new HtmlActivityRendererRegistry());
    }

    public static void addTagRendererRegistry(String namespace,
                                              UITagRendererRegistry rendererRegistry) {
        mComponentBuilderRegistries.put(namespace,
                                        rendererRegistry);
    }

    public static <T extends UITag> UITagRenderer<T> getTagRenderer(String namespace,
                                                                    String name) {

        if (namespace == null) {
            namespace = HtmlComponent.NAMESPACE;
        }

        UITagRendererRegistry compRegistry = mComponentBuilderRegistries.get(namespace);

        if (compRegistry == null) {
            throw new IllegalArgumentException("Unknown namespace : " + namespace);
        }

        return compRegistry.getTagRenderer(name);
    }

}

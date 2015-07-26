package meru.ui.faces.renderer.html.registry;

import meru.ui.faces.renderer.html.block.HtmlBlockRenderer;
import meru.ui.faces.renderer.registry.UIComponentRendererRegistry;
import meru.ui.faces.tag.block.ElseBlock;
import meru.ui.faces.tag.block.ForEachBlock;
import meru.ui.faces.tag.block.IfBlock;
import meru.ui.faces.tag.block.UIBlock;

public class HtmlBlockRendererRegistry extends UIComponentRendererRegistry {

    public HtmlBlockRendererRegistry() {
        super(UIBlock.NAMESPACE);
        mBuilders.put(IfBlock.NAME,HtmlBlockRenderer.class);
        mBuilders.put(ElseBlock.NAME,HtmlBlockRenderer.class);
        mBuilders.put(ForEachBlock.NAME,HtmlBlockRenderer.class);
    }

}

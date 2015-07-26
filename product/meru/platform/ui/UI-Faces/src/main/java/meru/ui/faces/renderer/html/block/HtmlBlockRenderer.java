package meru.ui.faces.renderer.html.block;

import meru.ui.faces.renderer.RendererContext;
import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.renderer.html.HtmlRendererContext;
import meru.ui.faces.renderer.html.HtmlView;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.renderer.html.registry.UIViewRegistry;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.block.UIBlock;

public class HtmlBlockRenderer<T extends UIBlock> extends
        HtmlComponentRenderer<T> {

    private HtmlRendererContext htmlViewContext;
    private HtmlView mHtmlView;

    public HtmlBlockRenderer() {

    }

    @Override
    public void render(T uiBlock,
                       RendererContext viewContext) {

        mHtmlView = UIViewRegistry.getUIView(uiBlock.getTagNamespace(),
                                             uiBlock.getTagName());
        
        ((UIBlockView) mHtmlView).setBlock(uiBlock);
        htmlViewContext = ((HtmlRendererContext) viewContext).newContext(mHtmlView);
        ((HtmlRendererContext) viewContext).addUIView(mHtmlView);
        
        for (UITag uiComponent : uiBlock.getChildTags()) {

            UITagRenderer<UITag> componentRenderer = htmlViewContext.getTagRenderer(uiComponent.getTagNamespace(),
                                                                                    uiComponent.getTagName());

            componentRenderer.render(uiComponent,
                                     htmlViewContext);
        }

        //htmlViewContext.addUIView(mHtmlView);
        
        htmlViewContext.flushHtml();

        //((HtmlRendererContext) viewContext).addUIView(mHtmlView);
    }

}

package meru.ui.faces.renderer.html.activity;

import meru.ui.faces.renderer.RendererContext;
import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.renderer.html.HtmlRendererContext;
import meru.ui.faces.renderer.html.HtmlView;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.renderer.html.registry.UIViewRegistry;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.activity.UIActivity;

public class HtmlActivityRenderer<T extends UIActivity> extends
        HtmlComponentRenderer<T> {

    private HtmlRendererContext htmlViewContext;
    private HtmlView mHtmlView;

    @Override
    public void render(T uiActivity,
                       RendererContext viewContext) {

        mHtmlView = UIViewRegistry.getUIView(uiActivity.getTagNamespace(),
                                             uiActivity.getTagName());
        ((UIActivityView) mHtmlView).setActivity(uiActivity);
        htmlViewContext = ((HtmlRendererContext) viewContext).newContext(mHtmlView);

        for (UITag uiComponent : uiActivity.getChildTags()) {

            UITagRenderer<UITag> componentRenderer = htmlViewContext.getTagRenderer(uiComponent.getTagNamespace(),
                                                                                    uiComponent.getTagName());

            componentRenderer.render(uiComponent,
                                     htmlViewContext);
        }

        htmlViewContext.flushHtml();

        ((HtmlRendererContext) viewContext).addUIView(mHtmlView);
    }

}

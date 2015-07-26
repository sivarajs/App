package meru.ui.faces.renderer.html;

import javax.servlet.ServletContext;

import meru.ui.faces.EntityDataProvider;
import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.tag.UITag;

public class HtmlFileRenderer {

    private HtmlRendererContext mHtmlRendererContext;
    private HtmlFileView mHtmlView;

    public HtmlFileRenderer(HtmlFileView htmlView,
                            ServletContext servletContext,
                            EntityDataProvider entityDataProvider) {
        mHtmlView = htmlView;
        mHtmlRendererContext = new HtmlRendererContext(htmlView,
                                                       servletContext,
                                                       entityDataProvider);
    }

    public void render() {

        UITag uiComponent = mHtmlRendererContext.loadTag(mHtmlView.getURL());
        UITagRenderer<UITag> componentRenderer = mHtmlRendererContext.getTagRenderer(uiComponent.getTagNamespace(),
                                                                                     uiComponent.getTagName());

        componentRenderer.render(uiComponent,
                                 mHtmlRendererContext);

        mHtmlRendererContext.flushHtml();
    }

}

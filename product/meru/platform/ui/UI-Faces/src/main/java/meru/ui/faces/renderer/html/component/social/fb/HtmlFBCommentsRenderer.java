package meru.ui.faces.renderer.html.component.social.fb;

import meru.app.AppRequest;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.social.fb.FBComments;

public class HtmlFBCommentsRenderer extends HtmlComponentRenderer<FBComments> {

    @Override
    public void addAttributes() {

        htmlBuilder.addAttribute("data-href",
                                 AppRequest.currentRequest()
                                           .getResourceURI());
        htmlBuilder.addAttribute("data-num-posts",
                                 uiComponent.getTotalPosts());
        htmlBuilder.addAttribute("data-width",
                                 uiComponent.getCommentsBoxWidth());
    }

    protected boolean preRenderComponent() {

        htmlBuilder.startElement("div")
                   .addClassAttribute("fb-root")
                   .endElement();
        return true;
    }
}

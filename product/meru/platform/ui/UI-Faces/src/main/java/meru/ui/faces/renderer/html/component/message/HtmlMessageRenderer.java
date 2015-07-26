package meru.ui.faces.renderer.html.component.message;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.message.Message;

public class HtmlMessageRenderer extends HtmlComponentRenderer<Message> {

    public HtmlMessageRenderer() {
        super("span");
    }

    @Override
    protected boolean preRenderChildren() {
        return false;
    }
}

package meru.ui.faces.renderer.html.component.tab;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.tab.Tab;

public class HtmlTabRenderer extends HtmlComponentRenderer<Tab> {

    public HtmlTabRenderer() {
        super("li");
    }

    @Override
    protected boolean preRenderChildren() {

        htmlBuilder.startElement("a")
                   .addAttribute("href",
                                 "javascript:;");

        htmlBuilder.addText(uiComponent.getTitle());

        htmlBuilder.endElement();

        return false;
    }
}

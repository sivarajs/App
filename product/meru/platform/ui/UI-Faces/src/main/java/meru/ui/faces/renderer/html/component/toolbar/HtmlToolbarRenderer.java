package meru.ui.faces.renderer.html.component.toolbar;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.toolbar.Toolbar;

public class HtmlToolbarRenderer extends HtmlComponentRenderer<Toolbar> {

    public HtmlToolbarRenderer() {
    }

    @Override
    protected void addAttributes() {
        htmlBuilder.addAttribute("entity", uiComponent.getEntity());
    }
    
    @Override
    protected boolean preRenderChildren() {
        htmlBuilder.startElement("div")
                   .addAttribute("class",
                                 "buttons");
        return true;
    }

    @Override
    protected void postRenderChildren() {

        htmlBuilder.endElement();

        htmlBuilder.startElement("div")
                   .addClassAttribute("clr")
                   .endElement();
    }

}

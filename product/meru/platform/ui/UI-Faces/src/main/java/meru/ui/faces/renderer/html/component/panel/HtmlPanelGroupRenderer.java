package meru.ui.faces.renderer.html.component.panel;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.panel.PanelGroup;

public class HtmlPanelGroupRenderer extends HtmlComponentRenderer<PanelGroup> {

    @Override
    protected boolean preRenderChildren() {

        addTitleBar();

        String pcClass = uiComponent.getPanelContentClass();
        if (pcClass != null) {
            pcClass += " panelContent";
        }
        else {
            pcClass = "panelContent";
        }

        htmlBuilder.startElement("div")
                   .addClassAttribute(pcClass)
                   .addStyleAttribute(uiComponent.getPanelContentStyle());

        return true;
    }

    @Override
    protected void postRenderChildren() {

        htmlBuilder.endElement("div");

    }

    private void addTitleBar() {

        String title = uiComponent.getTitle();

        if (title != null) {

            htmlBuilder.startElement("div")
                       .addAttribute("class",
                                     "panelHead");

            htmlBuilder.startElement("span")
                       .addAttribute("class",
                                     "title")
                       .addText(title)
                       .endElement("span");

            // if (uiComponent.isCloseable()) {
            //
            // htmlBuilder.startElement("div")
            // .addAttribute("class",
            // "closeable")
            // .endElement("div");
            // }

            htmlBuilder.endElement("div");

        }

    }

}

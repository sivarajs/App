package meru.ui.faces.renderer.html.component.pane.split;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.pane.split.SplitPane;

public class HtmlSplitPaneRenderer extends HtmlComponentRenderer<SplitPane> {

    @Override
    protected void addAttributes() {

        if (uiComponent.getOrientation() != null) {
            htmlBuilder.addAttribute("orientation",
                                     uiComponent.getOrientation());
        }
    }

    @Override
    protected void postRenderChildren() {

        htmlBuilder.startElement("div")
                   .addClassAttribute("clr")
                   .endElement();
    }
}

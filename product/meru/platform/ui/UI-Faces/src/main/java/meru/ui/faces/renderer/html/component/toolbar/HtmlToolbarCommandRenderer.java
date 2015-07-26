package meru.ui.faces.renderer.html.component.toolbar;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.toolbar.ToolbarCommand;

public class HtmlToolbarCommandRenderer extends HtmlComponentRenderer<ToolbarCommand> {

    public HtmlToolbarCommandRenderer() {
        // super("span");
    }

    @Override
    protected void addAttributes() {
        htmlBuilder.addAttribute("command",
                                 uiComponent.getCommand());
    }

    @Override
    protected boolean preRenderChildren() {

        htmlBuilder.startElement("span");
        if (uiComponent.getBGClass() != null) {
            htmlBuilder.addClassAttribute(uiComponent.getBGClass());
        }
        htmlBuilder.addText(uiComponent.getLabel())
                   .endElement("span");

        return false;
    }

}

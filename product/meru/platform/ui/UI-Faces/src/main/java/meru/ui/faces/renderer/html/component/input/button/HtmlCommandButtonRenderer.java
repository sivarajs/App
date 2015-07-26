package meru.ui.faces.renderer.html.component.input.button;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.input.button.CommandButton;

public class HtmlCommandButtonRenderer extends
                HtmlComponentRenderer<CommandButton> {

    public HtmlCommandButtonRenderer() {
        super("input");
    }

    @Override
    protected void addAttributes() {
        htmlBuilder.addAttribute("type",
                                 "submit")
                   .addAttribute("value",
                                 uiComponent.getValue());

    }

}

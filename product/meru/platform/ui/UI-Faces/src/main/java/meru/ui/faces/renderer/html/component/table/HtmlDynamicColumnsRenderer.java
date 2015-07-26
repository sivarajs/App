package meru.ui.faces.renderer.html.component.table;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.table.DynamicColumns;

public class HtmlDynamicColumnsRenderer extends HtmlComponentRenderer<DynamicColumns> {

    public HtmlDynamicColumnsRenderer() {
        super("th");
    }

    @Override
    protected void addAttributes() {

        htmlBuilder.addAttribute("value",
                                 uiComponent.getValueAttr())
                   .addAttribute("label",
                                 uiComponent.getLabel())
                   .addAttribute("width",
                                 uiComponent.getWidth());

    }

    @Override
    protected boolean preRenderChildren() {

        /*
         * htmlBuilder.startElement("div") .addStyleAttribute("width:" +
         * uiComponent.getWidth() + "px;");
         * 
         * htmlBuilder.addText(uiComponent.getLabel());
         * 
         * htmlBuilder.endElement("div");
         */
        return false;
    }

}

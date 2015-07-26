package meru.ui.faces.renderer.html.component.table;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.table.EntityColumns;

public class HtmlEntityColumnsRenderer extends HtmlComponentRenderer<EntityColumns> {

    public HtmlEntityColumnsRenderer() {
        super("th");
    }

    @Override
    protected void addAttributes() {

        htmlBuilder.addAttribute("value",
                                 uiComponent.getValueAttr())
                   .addAttribute("label",
                                 uiComponent.getLabel())
                   .addAttribute("entity",
                                 uiComponent.getEntityName())
                   .addAttribute("filter",
                                 uiComponent.getFilter())
                   .addAttribute("onInputChange",
                                 uiComponent.getOnInputChange())
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

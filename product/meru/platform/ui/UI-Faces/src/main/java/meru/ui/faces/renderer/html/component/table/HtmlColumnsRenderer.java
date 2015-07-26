package meru.ui.faces.renderer.html.component.table;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.table.Columns;

import org.w3c.dom.Element;

public class HtmlColumnsRenderer extends HtmlComponentRenderer<Columns> {

    @Override
    protected boolean preRenderChildren() {

        htmlBuilder.startElement("table")
                   .addStyleAttribute("width:100%")
                   .addAttribute("cellspacing",
                                 "0")
                   .addAttribute("cellpadding",
                                 "0");

        if (uiComponent.getWidth() != null) {
            htmlBuilder.addAttribute("width",
                                     uiComponent.getWidth());
        }

        htmlBuilder.startElement("thead");

        htmlBuilder.startElement("tr")
                   .addAttribute("class",
                                 "headerRow");

        String rowSelection = ((Element) uiComponent.getUIElement()
                                                    .getParentNode()).getAttribute("rowSelection");
        if ("multiple".equalsIgnoreCase(rowSelection)) {
            addHeaderMultiRowSelect(rowSelection);
        }

        return true;

    }

    @Override
    protected void postRenderChildren() {

        htmlBuilder.endElement("tr")
                   .endElement("thead")
                   .endElement("table");

    }

    private void addHeaderMultiRowSelect(String rowSelection) {

        htmlBuilder.startElement("th")
                   .addAttribute("width",
                                 "20")
                   .addAttribute("rowSelection",
                                 rowSelection)
                   .addClassAttribute("rowSelection");

        htmlBuilder.startElement("div");

        htmlBuilder.addText("<input type='checkbox'/>");

        htmlBuilder.endElement("div");

        htmlBuilder.endElement("th");

    }

}

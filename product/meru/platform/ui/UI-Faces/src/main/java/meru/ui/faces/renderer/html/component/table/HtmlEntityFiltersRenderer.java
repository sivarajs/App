package meru.ui.faces.renderer.html.component.table;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.table.Columns;

public class HtmlEntityFiltersRenderer<T extends Columns> extends
                HtmlComponentRenderer<T> {

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

        // if (RowSelection.isMultipleRowSelection(mRowSelection)) {
        // addHeaderMultiRowSelect();
        // }

        return true;

    }

    @Override
    protected void postRenderChildren() {

        htmlBuilder.endElement("tr")
                   .endElement("thead")
                   .endElement("table");

    }

}

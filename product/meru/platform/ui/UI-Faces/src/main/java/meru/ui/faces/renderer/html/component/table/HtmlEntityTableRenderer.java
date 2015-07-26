package meru.ui.faces.renderer.html.component.table;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.table.EntityTable;

public class HtmlEntityTableRenderer extends HtmlComponentRenderer<EntityTable> {

    @Override
    protected void addAttributes() {

        htmlBuilder.addAttribute("entity",
                                 uiComponent.getEntity())
                   .addAttribute("filter",
                                 uiComponent.getFilter())
                   .addAttribute("var",
                                 uiComponent.getVar())
                   .addAttribute("removeable",
                                 uiComponent.isRemoveable()) 
                   .addAttribute("rowSelection",
                                 uiComponent.getRowSelection())               
                   .addAttribute("dynamic",
                                 uiComponent.isDynamic());

    }

    @Override
    protected boolean preRenderChildren() {

        addTitleBar();
        
        return uiComponent.includeHeader();
    }

    @Override
    protected void postRenderChildren() {

        addContent();
    }

    private void addTitleBar() {

        String title = uiComponent.getTitle();

        if (title != null) {

            htmlBuilder.startElement("div")
                       .addAttribute("class",
                                     "titleBar");

            htmlBuilder.startElement("span")
                       .addText(title)
                       .endElement("span");

            htmlBuilder.endElement("div");

        }
    }

    private void addContent() {

        String contentClass = "gridContent";
        if (uiComponent.getContentStyleClass() != null) {
            contentClass += " " + uiComponent.getContentStyleClass();
        }

        htmlBuilder.startElement("div")
                   .addAttribute("class",
                                 contentClass);

        if (uiComponent.getContentStyle() != null) {
            htmlBuilder.addAttribute("style",
                                     uiComponent.getContentStyle());
        }

        htmlBuilder.addAttribute("onscroll",
                                 "$($(this).prev('.columns')[0]).scrollLeft($(this).scrollLeft());");

        htmlBuilder.startElement("table")
                   .addStyleAttribute("width:100%")
                   .addAttribute("cellpadding",
                                 "0")
                   .addAttribute("cellspacing",
                                 "0");

        if (uiComponent.getWidth() != null) {
            htmlBuilder.addAttribute("width",
                                     uiComponent.getWidth());
        }

        htmlBuilder.startElement("tbody")
                   .addAttribute("class",
                                 "gridRows")
                   .addText("");

        if (uiComponent.isAutoLoad()) {
            viewContext.addUIView(new EntityTableHtmlView(uiComponent));
        }

        htmlBuilder.endElement()
                   .endElement()
                   .endElement();
    }
}

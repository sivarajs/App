package meru.ui.faces.renderer.html.page;

import meru.ui.faces.tag.page.Section;

public class HtmlSectionWorkAreaRenderer extends HtmlFragmentRenderer<Section> {

    @Override
    protected boolean preRenderChildren() {

//        htmlBuilder.startElement("div")
//                   .addClassAttribute("tab")
//                   .startElement("div")
//                   .addClassAttribute("tabScroll")
//                   .startElement("div")
//                   .addClassAttribute("left")
//                   .endElement()
//                   .startElement("div")
//                   .addClassAttribute("right")
//                   .endElement()
//                   .startElement("div")
//                   .addClassAttribute("drop")
//                   .endElement()
//                   .endElement()
//                   .startElement("ul")
//                   .startElement("li")
//                   .addText("Workspace")
//                   .endElement()
//                   .startElement("li")
//                   .addText("Workspace1")
//                   .endElement()
//
//                   .endElement();

        return true;
    }

    @Override
    protected void postRenderChildren() {

//        htmlBuilder.endElement("ul");

    }
}

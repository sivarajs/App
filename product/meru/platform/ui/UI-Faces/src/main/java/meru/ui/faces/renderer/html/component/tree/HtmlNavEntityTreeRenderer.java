package meru.ui.faces.renderer.html.component.tree;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.tree.NavEntityTree;

public class HtmlNavEntityTreeRenderer extends
        HtmlComponentRenderer<NavEntityTree> {

    @Override
    public boolean preRenderChildren() {

        addTitleBar();

        htmlBuilder.startElement("div")
                   .addAttribute("class",
                                 "navTreeContent")
                   .closeBeginTag();

        NavEntityTreeHtmlView tree = new NavEntityTreeHtmlView(uiComponent);
        viewContext.addUIView(tree);

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
                                     "treeHead");

            htmlBuilder.startElement("div")
                       .addAttribute("class",
                                     "title")
                       .addText(title)
                       .endElement("div");

            htmlBuilder.endElement("div");

        }

    }

}

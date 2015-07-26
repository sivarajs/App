package meru.ui.faces.renderer.html.component.tree;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.tree.EntityTree;

public class HtmlEntityTreeRenderer extends HtmlComponentRenderer<EntityTree> {

    protected void addAttributes() {
        
        super.addAttributes();
        
        htmlBuilder.addAttribute("resource",
                                 uiComponent.getEntity())
                   .addAttribute("selectedItemId",
                                 uiComponent.getSelectedEntityId());
      }

      @Override
      public boolean preRenderChildren() {

        addTitleBar();

        htmlBuilder.startElement("div")
                   .addAttribute("class",
                                 "treeContent")
                   .closeBeginTag();

        viewContext.addUIView(new EntityTreeHtmlView(uiComponent));
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

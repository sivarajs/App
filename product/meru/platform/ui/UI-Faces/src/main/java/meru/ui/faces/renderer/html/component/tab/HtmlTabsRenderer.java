package meru.ui.faces.renderer.html.component.tab;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.tab.Tab;
import meru.ui.faces.tag.component.tab.Tabs;

public class HtmlTabsRenderer extends HtmlComponentRenderer<Tabs> {

    public HtmlTabsRenderer() {
    }

    @Override
    protected boolean preRenderChildren() {

        htmlBuilder.startElement("ul");

        boolean isFirst = true;
        for (UITag childTab : uiComponent.getChildTags()) {

          if (isFirst && childTab instanceof Tab) {
            childTab.setCSSClass("sel");
            isFirst = false;
          }

          viewContext.renderTag(childTab);

        }

        return false;
    }
    
    @Override
    protected void postRenderChildren() {

      htmlBuilder.endElement("ul");

      htmlBuilder.startElement("div")
                 .addAttribute("class",
                               "tabContent");

      boolean isFirst = true;
      for (UITag childTab : uiComponent.getChildTags()) {

        htmlBuilder.startElement("div")
                   .addAttribute("class",
                                 "tabContentInner tci-" + uiComponent.getId());
        htmlBuilder.addAttribute("id",
                                 "tc-" + childTab.getId());

        if (!isFirst) {
          htmlBuilder.addAttribute("style",
                                   "display:none");

        }
        else {
          isFirst = false;
        }

        for (UITag grandChildTab : childTab.getChildTags()) {
          viewContext.renderTag(grandChildTab);
        }

        htmlBuilder.endElement("div");

      }

      htmlBuilder.endElement("div");

    }
}

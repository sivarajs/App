package meru.ui.faces.renderer.html.component.menu;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.menu.EntityMenu;

public class HtmlEntityMenuRenderer extends HtmlComponentRenderer<EntityMenu> {

  public HtmlEntityMenuRenderer() {
    super("span");
  }

  @Override
  protected boolean preRenderChildren() {

    String label = uiComponent.getLabel();
    if (label != null) {
      htmlBuilder.startElement("a")
                 .addClassAttribute(uiComponent.getLabelClass())
                 .addAttribute("href",
                               "javascript:;")
                 .addText(label)
                 .endElement();
    }

    viewContext.addUIView(new EntityMenuView(uiComponent));

    return true;
  }

}

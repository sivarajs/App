package meru.ui.faces.renderer.html.component.popup;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.popup.PopupLink;

public class HtmlPopupLinkRenderer extends HtmlComponentRenderer<PopupLink> {

  public HtmlPopupLinkRenderer() {
    super("span");
  }

  @Override
  public void addAttributes() {
    htmlBuilder.addAttribute("src",
                             uiComponent.getSrc());
  }

  @Override
  public boolean preRenderChildren() {
    htmlBuilder.startElement("a")
               .addAttribute("href",
                             uiComponent.getHref())
               .addText(uiComponent.getLabel())
               .endElement();

    // Popup popup = uiComponent.getPopup();
    // viewContext.getTagRenderer(popup.getTagNamespace(),
    // popup.getTagName())
    // .render(popup,
    // viewContext);
    return true;
  }
}

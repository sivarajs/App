package meru.ui.faces.renderer.html.component.table;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.table.Column;

public class HtmlColumnRenderer extends HtmlComponentRenderer<Column> {

  public HtmlColumnRenderer() {
    super("th");
  }

  @Override
  protected void addAttributes() {

    htmlBuilder.addAttribute("value",
                             uiComponent.getValueAttr())
               .addAttribute("type",
                             uiComponent.getType())
               .addAttribute("href",
                             uiComponent.getHref())              
               .addAttribute("contentStyle",
                             uiComponent.getContentStyle())
               .addAttribute("onInputChange",
                             uiComponent.getOnInputChange())
               .addAttribute("width",
                             uiComponent.getWidth());

  }

  @Override
  protected boolean preRenderChildren() {

    String style = "width:" + uiComponent.getWidth() + "px;";
    if (uiComponent.getAlign() != null) {
      style += "text-align:" + uiComponent.getAlign();
    }

    htmlBuilder.startElement("div")
               .addStyleAttribute(style);

    htmlBuilder.addText(uiComponent.getLabel());

    htmlBuilder.endElement("div");
    return false;
  }

}

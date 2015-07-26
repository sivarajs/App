package meru.ui.faces.renderer.html.component.list;

import meru.el.EL;
import meru.ui.faces.renderer.html.HtmlFieldView;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.list.EntityList;

public class HtmlEntityListRenderer extends HtmlComponentRenderer<EntityList> {

  @Override
  public String getHtmlTag() {
    return "ul";
  }
  
  @Override
  protected void addAttributes() {
    super.addAttributes();
    htmlBuilder.addAttribute("selectable", uiComponent.getSelectable());
  }

  @Override
  protected boolean preRenderChildren() {
    addTitleBar();
    viewContext.addUIView(new EntityListHtmlView(uiComponent));
    return false;
  }

  private void addTitleBar() {

    String title = uiComponent.getTitle();

    if (title != null) {

      htmlBuilder.startElement("div")
                 .addAttribute("class",
                               "listHead");

      htmlBuilder.startElement("span")
                 .addAttribute("class",
                               "title");

      if (EL.isBindVariable(title)) {
        htmlBuilder.closeBeginTag();
        HtmlFieldView field = new HtmlFieldView(EL.getBindVariable(title));
        viewContext.addUIView(field);
      }
      else {
        htmlBuilder.addText(title);

      }
      htmlBuilder.endElement("span");

      htmlBuilder.endElement("div");

    }

  }

}

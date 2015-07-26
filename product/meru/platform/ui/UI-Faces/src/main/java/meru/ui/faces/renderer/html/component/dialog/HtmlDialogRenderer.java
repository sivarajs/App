package meru.ui.faces.renderer.html.component.dialog;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.dialog.Dialog;

public class HtmlDialogRenderer extends HtmlComponentRenderer<Dialog> {

  @Override
  protected boolean preRenderChildren() {

    addTitleBar();

    // htmlBuilder.startElement("div")
    // .addStyleAttribute("margin:4px;");

    htmlBuilder.startElement("div")
               .addAttribute("class",
                             "dlgContent");
    return true;
  }

  @Override
  protected void postRenderChildren() {
    htmlBuilder.startElement("div")
               .addClassAttribute("clr")
               .endElement();
    htmlBuilder.endElement();
  }

  private void addTitleBar() {

    String title = uiComponent.getTitle();

    if (title != null) {

      htmlBuilder.startElement("div")
                 .addAttribute("class",
                               "dlgHead");

      htmlBuilder.startElement("div")
                 .addAttribute("class",
                               "title")
                 .addText(title)
                 .endElement("div");

      addCloseIcon();
      htmlBuilder.endElement("div");

    }

  }

  private void addCloseIcon() {
    htmlBuilder.startElement("div")
               .addAttribute("class",
                             "dlgClose")
               .endElement();

  }
}

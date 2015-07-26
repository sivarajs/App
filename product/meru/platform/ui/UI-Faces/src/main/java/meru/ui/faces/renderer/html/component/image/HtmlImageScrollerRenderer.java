package meru.ui.faces.renderer.html.component.image;

import java.util.List;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.expr.ViewExpressionParser;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.image.ImageScroller;

public class HtmlImageScrollerRenderer extends HtmlComponentRenderer<ImageScroller> {

  public HtmlImageScrollerRenderer() {
    super("div");
  }

  @Override
  public boolean preRenderChildren() {
    htmlBuilder.startElement("div")
               .addClassAttribute("isIn");

/*    htmlBuilder.startElement("a")
               .addClassAttribute("next")
               .addAttribute("href",
                             "javascript:;")
               .addText("&lt;")
               .endElement();*/

    htmlBuilder.startElement("div")
               .addClassAttribute("ulDiv")
               .startElement("div")
               .addClassAttribute("ulDivIn")
               .startElement("ul");

    String entityName = uiComponent.getEntity();
    if (entityName != null) {

      String filter = uiComponent.getFilter();

      if (EL.containsBindVariable(filter)) {
        filter = ViewExpressionParser.parseText(filter,
                                                viewContext.getCurrentView(),
                                                true);
      }

      List<Object> entityImages = viewContext.getEntityDataProvider()
                                             .getEntities(entityName,
                                                          filter);

      for (Object object : entityImages) {
        String img = (String) ClassHelper.getFieldValue(object,
                                                        "image");
        startImg(img);
        htmlBuilder.startElement("img")
                   .addAttribute("src",
                                 img)
                   .endElement();
        endImg();
      }
    }

    return true;
  }

  @Override
  public void postRenderChildren() {

    htmlBuilder.endElement()
               .endElement()
               .endElement();

   /* htmlBuilder.startElement("a")
               .addClassAttribute("prev")
               .addAttribute("href",
                             "javascript:;")
               .addText("&gt;")
               .endElement();*/

    htmlBuilder.endElement();

  }

  @Override
  public boolean preRenderChild(UITag childComponent) {

    if (!"img".equals(childComponent.getUIElementTagLocalName())) {
      return false;
    }

    startImg(childComponent.getUIElement()
                           .getAttribute("lsrc"));

    return true;
  }

  private void startImg(String lsrc) {
    htmlBuilder.startElement("li")
               .addAttribute("lsrc",
                             lsrc)
               .startElement("a")
               .addAttribute("href",
                             "javascript:;");
  }

  private void endImg() {

    htmlBuilder.endElement("a")
               .endElement();

  }

  @Override
  protected void postRenderChild(UITag childComponent) {

    if ("img".equals(childComponent.getUIElementTagLocalName())) {
      endImg();
    }

  }

}

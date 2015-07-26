package meru.ui.faces.renderer.html.component.image;

import java.util.List;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.expr.ViewExpressionParser;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.image.ImageGallery;

public class HtmlImageGalleryRenderer extends HtmlComponentRenderer<ImageGallery> {

  public HtmlImageGalleryRenderer() {
    super("div");
  }

  @Override
  public boolean preRenderChildren() {

    /*
     * String style = "width:" + uiComponent.getImageWidth() + "px;height:" +
     * uiComponent.getImageHeight() + "px;";
     * 
     * htmlBuilder.startElement("div") .addClassAttribute("vimg")
     * .addStyleAttribute(style) .endElement();
     */

    htmlBuilder.addHtmlText("<div u='loading' class='loading'><div class='divIn'></div><div class='img'></div></div>");
    htmlBuilder.startElement("div")
               .addAttribute("u",
                             "slides")
               .addClassAttribute("imgContainer");

    String entityName = uiComponent.getEntityName();
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

        htmlBuilder.startElement("div");
        htmlBuilder.startElement("img")
                   .addClassAttribute("galleryImage jqzoom")
                   .addAttribute("id",
                                 img.hashCode())
                   .addAttribute("src",
                                 img)
                   .addAttribute("u",
                                 "image")
                   .addAttribute("data-zoom-image",
                                 img.replace("300x300/",
                                             ""))
                   .addAttribute("alt",
                                 img.replace("300x300/",
                                             ""))
                   .endElement();
      /*  htmlBuilder.startElement("img")
                   .addAttribute("src",
                                 img)
                   .addAttribute("u",
                                 "thumb")
                   .endElement();*/
        htmlBuilder.endElement();
      }
    }

    return true;
  }

  @Override
  public void postRenderChildren() {

    /*
     * String style = "width:" + uiComponent.getImageWidth() + "px;height:" +
     * uiComponent.getImageHeight() + "px;";
     * 
     * htmlBuilder.startElement("div") .addClassAttribute("vimg")
     * .addStyleAttribute(style) .endElement();
     */
    htmlBuilder.endElement();
//    htmlBuilder.addHtmlText("<div u='thumbnavigator' class='jssort07 thumbnail'><div class='thumbnailDiv'></div><div u='slides' style='cursor: move;'><div u='prototype' class='p' style='POSITION: absolute; WIDTH: 99px; HEIGHT: 66px; TOP: 0; LEFT: 0;'><thumbnailtemplate class='i' style='position:absolute;'></thumbnailtemplate><div class='o'></div></div></div><span u='arrowleft' class='jssora11l' style='width: 37px; height: 37px; top: 123px; left: 8px;'></span><span u='arrowright' class='jssora11r' style='width: 37px; height: 37px; top: 123px; right: 8px'></span></div>");
  }
}

package meru.ui.faces.tag.component.image;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.FilterableEntityComponent;

public class ImageGallery extends FilterableEntityComponent implements Identifiable {

  public static final String NAME = "imageGallery";

  private int imgWidth;
  private int imgHeight;

  public ImageGallery() {
    super(NAME);
  }

  public int getImageWidth() {
    return imgWidth;
  }

  public int getImageHeight() {
    return imgHeight;
  }
  
  @Override
  protected boolean load() {
    super.load();
  /*  if (getAttribute("imgWidth") != null) {
      imgWidth = Integer.parseInt(getAttribute("imgWidth"));
    }

    if (getAttribute("imgHeight") != null) {
      imgHeight = Integer.parseInt(getAttribute("imgHeight"));
    }

    String entity = getAttribute("entity");
    if (entity != null) {
      Element scrollerElem = uiElement.getOwnerDocument()
                                      .createElementNS(NAMESPACE,
                                                       ImageScroller.NAME);
      scrollerElem.setAttribute("entity",
                                entity);
      scrollerElem.setAttribute("filter",
                                getMandatoryAttribute("filter"));
      
      uiElement.appendChild(scrollerElem);
    }
*/
    return true;
  }

}

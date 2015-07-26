package meru.ui.faces.tag.component.image;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;

public class ImageScroller extends UIComponent implements Identifiable {

    public static final String NAME = "imageScroller";

    public ImageScroller() {
        super(NAME);
    }
    
    public String getLargeImgSrc() {
      return getAttribute("lsrc");
    }
    
    public String getEntity() {
      return getAttribute("entity");
    }

    public String getFilter() {
      return getAttribute("filter");
    }
}

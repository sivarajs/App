package meru.ui.faces.tag.component.other;

import meru.ui.faces.tag.component.UIComponent;

public class BreadCrumb extends UIComponent {

  public static final String NAME = "breadCrumb";

  public BreadCrumb() {
    super(NAME);
  }

  public String getPath() {
    return getAttribute("path");
  }

  public String getType() {
    return getAttribute("type");
  }

  public String getSuffix() {
    return getAttribute("suffix");
  }

}

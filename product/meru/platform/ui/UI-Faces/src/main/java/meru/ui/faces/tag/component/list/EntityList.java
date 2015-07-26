package meru.ui.faces.tag.component.list;

import meru.ui.faces.tag.component.FilterableEntityComponent;

public class EntityList extends FilterableEntityComponent {

  public static final String NAME = "entityList";

  public EntityList() {
    super(NAME,
          "list");
  }

  public String getTitle() {
    return getAttribute("title");
  }
  
  public String onItemClick() {
    return getAttribute("onItemClick");
  }
  
  public String getHref() {
    return getAttribute("href");
  }
  
  public String getDefault() {
    return getAttribute("default");
  }
  
  public String getSelectable() {
    return getAttribute("selectable");
  }
}

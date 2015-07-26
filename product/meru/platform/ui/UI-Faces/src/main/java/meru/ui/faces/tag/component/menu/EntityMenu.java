package meru.ui.faces.tag.component.menu;

import meru.ui.faces.tag.component.UIComponent;

public class EntityMenu extends UIComponent {

    public static final String NAME = "entityMenu";

    public EntityMenu() {
        super(NAME,
              "menu");
    }

    public String getName() {
        return getAttribute("name");
    }

    public String getEntity() {
        return getAttribute("entity");
    }

    public String getFilter() {
        return getAttribute("filter");
    }

    public String getHref() {
      return getAttribute("href");
  }

}

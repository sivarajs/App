package meru.ui.faces.renderer.html.component.grid;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.grid.EntityGrid;

public class HtmlEntityGridRenderer extends HtmlComponentRenderer<EntityGrid> {

  @Override
  public boolean preRenderChildren() {

    EntityGridHtmlView entityGridView = new EntityGridHtmlView(uiComponent);
    createChildView(entityGridView);
    return false;
  }

}

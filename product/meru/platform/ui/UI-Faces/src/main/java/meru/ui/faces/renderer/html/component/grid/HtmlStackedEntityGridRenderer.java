package meru.ui.faces.renderer.html.component.grid;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.grid.StackedEntityGrid;

public class HtmlStackedEntityGridRenderer extends
        HtmlComponentRenderer<StackedEntityGrid> {

    @Override
    public boolean preRenderChildren() {

      StackedEntityGridHtmlView entityGridView = new StackedEntityGridHtmlView(uiComponent);
      createChildView(entityGridView);
      return false;
    }

}

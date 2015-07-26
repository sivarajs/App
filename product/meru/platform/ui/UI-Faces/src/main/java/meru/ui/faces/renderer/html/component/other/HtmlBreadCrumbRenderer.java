package meru.ui.faces.renderer.html.component.other;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.other.BreadCrumb;

public class HtmlBreadCrumbRenderer extends HtmlComponentRenderer<BreadCrumb> {

  public HtmlBreadCrumbRenderer() {
    super("div");
  }

 
  @Override
  protected boolean preRenderChildren() {

    createChildView(new BreadCrumbHtmlView(uiComponent));

    return true;
  }

}

package meru.ui.faces.renderer.html.component.wizard;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.wizard.WizardItem;

public class HtmlWizardItemRenderer extends HtmlComponentRenderer<WizardItem> {

  public HtmlWizardItemRenderer() {
    super("div");
  }

 
  @Override
  protected boolean preRenderChildren() {

    //htmlBuilder.addText(uiComponent.getTitle());

    return true;
  }

}

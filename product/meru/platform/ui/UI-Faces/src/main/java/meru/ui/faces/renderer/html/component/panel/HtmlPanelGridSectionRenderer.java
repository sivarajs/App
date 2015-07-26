package meru.ui.faces.renderer.html.component.panel;

import meru.ui.faces.tag.component.panel.PanelGridSection;

public class HtmlPanelGridSectionRenderer extends HtmlPanelGridRenderer {

    
    public HtmlPanelGridSectionRenderer() {
        super("div");
    }
  

  @Override
  protected boolean preRenderChildren() {

    addTitleBar();

    return true;

  }

   private void addTitleBar() {

    PanelGridSection section = (PanelGridSection) uiComponent;

    if (section.getTitle() != null) {
			htmlBuilder.startElement("div")
						.addAttribute("class", "pgsTitle")
						.addText(section.getTitle())
						.endElement();
        
    }
  }


}

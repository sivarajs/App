package meru.ui.faces.renderer.html.component.output;

import meru.ui.faces.tag.component.output.OutputText;
import meru.ui.faces.tag.component.panel.PanelGrid;

public class HtmlOutputTextRenderer extends HtmlOutputRenderer<OutputText> {

    public HtmlOutputTextRenderer() {
        super("span");
    }

    @Override
    protected boolean preRender() {

        if (uiComponent.getLabel() != null) {

            PanelGrid panelGrid = (PanelGrid)uiComponent.getParent("panelGrid");
            
            htmlBuilder.startElement("div")
                       .addClassAttribute("labeledInput")
                       .addStyleAttribute("width:" + panelGrid.getColumnWidth());

            htmlBuilder.startElement("div")
                       .addClassAttribute(uiComponent.getLabelClass(panelGrid.getLabelClass()))
                       .addStyleAttribute(panelGrid.getLabelStyle())
                       .addText(uiComponent.getLabel())
                       .endElement();

            htmlBuilder.startElement("div")
                       .addClassAttribute("input");

        }

        return true;
    }

    @Override
    protected void postRender() {

        if (uiComponent.getLabel() != null) {
            htmlBuilder.endElement()
                       .endElement();
        }
    }
    
    @Override
    protected boolean preRenderChildren() {
        processValue();
        return false;
    }
}

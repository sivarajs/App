package meru.ui.faces.renderer.html.component.wizard;

import java.util.List;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.block.IfBlock;
import meru.ui.faces.tag.component.wizard.Wizard;
import meru.ui.faces.tag.component.wizard.WizardItem;

public class HtmlWizardRenderer extends HtmlComponentRenderer<Wizard> {

  @Override
  protected boolean preRenderChildren() {

    htmlBuilder.startElement("ul")
               .closeBeginTag();

    List<UITag> childTags = uiComponent.getChildTags(); // byte index = 1;
    String width = (90 / childTags.size()) + "%";

    for (UITag childTag : childTags) {
      WizardItem wizardItem = null;

      if (childTag instanceof IfBlock) {
        IfBlock ifBlock = (IfBlock) childTag;

        wizardItem = (WizardItem) ifBlock.getFirstChildComponent("wizardItem");
        if (!canBeRendered(wizardItem)) {
          wizardItem = null;
        }

      }
      else {

        wizardItem = (WizardItem) childTag;

      }

      if (wizardItem != null) {

        String style = "width:" + width;
        if (wizardItem.getCssStyle() != null) {
          style += ";" + wizardItem.getCssStyle();
        }
        htmlBuilder.startElement("li")
                   .addStyleAttribute(style)
                   .addAttribute("cid",
                                 wizardItem.getId())
                   .addAttribute("listener",
                                 wizardItem.getListener())
                   /*.addAttribute("class",
                                 wizardItem.getCssClass())*/
                   .addAttribute("src",
                                 wizardItem.getSrc())
                   .addText(wizardItem.getTitle(),
                            true)
                   .endElement();
      }

    }
    htmlBuilder.endElement();

    htmlBuilder.startElement("div")
               .addClassAttribute("clr")
               .endElement();

    htmlBuilder.startElement("div")
               .addClassAttribute("wizardContent")
               .closeBeginTag();

    return true;
  }

  @Override
  protected void postRenderChildren() {
    htmlBuilder.endElement();

    htmlBuilder.startElement("div")
               .addClassAttribute("wizardContent")
               .endElement();

    htmlBuilder.startElement("div")
               .addClassAttribute("clr")
               .endElement();

    htmlBuilder.startElement("div")
               .addClassAttribute("wizButtonGroup")
               .startElement("span")
               .addClassAttribute("commandButton lfloat back")
               .addText("Back",
                        true)
               .endElement()
               .startElement("span")
               .addClassAttribute("commandButton lfloat proceed")
               .addText("Proceed",
                        true)
               .endElement()
               .endElement()
               .startElement("div")
               .addClassAttribute("clr")
               .endElement();

  }

}

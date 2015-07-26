package meru.ui.faces.renderer.html.component.output;

import meru.el.EL;
import meru.ui.faces.renderer.expr.FieldExpressionParser;
import meru.ui.faces.tag.component.output.OutputLink;

public class HtmlOutputLinkRenderer extends HtmlOutputRenderer<OutputLink> {

  public HtmlOutputLinkRenderer() {
    super("a");
  }

  @Override
  protected void addAttributes() {

    
    String value = uiComponent.getValue();

    
    if (EL.containsBindVariable(value)) {

      htmlBuilder.addText(" href=\"",
                          false);
      EL.parseText(value,
                   new FieldExpressionParser(htmlBuilder,
                                             viewContext,
                                             uiComponent.escape()));

      htmlBuilder.addText("\"",
                          false);
    }
    else {

      htmlBuilder.addAttribute("href",
                               value);

    }

  }

  @Override
  protected boolean preRenderChildren() {

    String label = uiComponent.getLabel();
    if (label != null) {
      if (EL.isBindVariable(label)) {
        EL.parseText(label,
                     new FieldExpressionParser(htmlBuilder,
                                               viewContext,
                                               uiComponent.escape()));
      }
      else {
        htmlBuilder.addText(label);
      }
    }

    return true;
  }
}

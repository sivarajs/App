package meru.ui.faces.renderer.html.component.input;

import meru.el.EL;
import meru.ui.faces.renderer.expr.FieldExpressionParser;
import meru.ui.faces.tag.component.input.InputTextarea;

public class HtmlInputTextareaRenderer extends HtmlInputRenderer<InputTextarea> {

  public HtmlInputTextareaRenderer() {
    super("textarea");
  }

  @Override
  protected void addAttributes() {
    super.addAttributes();
    htmlBuilder.addAttribute("rows",
                             "5");
  }

  @Override
  protected boolean preRenderChildren() {
    String value = uiComponent.getValue();

    if (value != null) {

      if (EL.isBindVariable(value)) {

        EL.parseText(value,
                     new FieldExpressionParser(htmlBuilder,
                                               viewContext));

      }
    }

    return false;
  }

}

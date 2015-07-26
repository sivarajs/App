package meru.ui.faces.renderer.html.component.output;

import meru.el.EL;
import meru.ui.faces.renderer.expr.FieldExpressionParser;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.output.Output;

public class HtmlOutputRenderer<T extends Output> extends
                HtmlComponentRenderer<T> {

    public HtmlOutputRenderer(String tagName) {
        super(tagName);
    }

    @Override
    protected void addAttributes() {
        htmlBuilder.addAttribute(Output.ATTR_ATTR,
                                 uiComponent.getAttr());
    }

    protected void processValue() {

        String value = uiComponent.getValue();
        if (EL.containsBindVariable(value)) {

            htmlBuilder.addText("");

            EL.parseText(value,
                         new FieldExpressionParser(htmlBuilder,
                                                   viewContext));
        }
        else {
            htmlBuilder.addText(value);
        }
    }

}

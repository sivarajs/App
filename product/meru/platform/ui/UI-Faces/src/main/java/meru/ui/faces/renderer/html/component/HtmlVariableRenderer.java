package meru.ui.faces.renderer.html.component;

import meru.el.EL;
import meru.ui.faces.renderer.expr.ViewExpressionParser;
import meru.ui.faces.tag.component.UIVariable;

public class HtmlVariableRenderer extends HtmlComponentRenderer<UIVariable> {

    public HtmlVariableRenderer() {
    }

    @Override
    public boolean preRender() {

        String value = uiComponent.getValue();
        if (EL.containsBindVariable(value)) {

            value = ViewExpressionParser.parseText(value,
                                                   viewContext.getCurrentView(),
                                                   true);
        }

        viewContext.getCurrentView()
                   .setVariable(uiComponent.getNameAttr(),
                                value);
        return false;
    }

}

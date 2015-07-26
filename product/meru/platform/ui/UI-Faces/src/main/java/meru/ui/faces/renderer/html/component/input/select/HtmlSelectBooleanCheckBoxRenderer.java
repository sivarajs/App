package meru.ui.faces.renderer.html.component.input.select;

import meru.el.EL;
import meru.ui.faces.renderer.html.component.input.HtmlInputRenderer;
import meru.ui.faces.tag.component.input.Input;
import meru.ui.faces.tag.component.input.select.SelectBooleanCheckBox;
import app.entity.YesNoBoolean;

public class HtmlSelectBooleanCheckBoxRenderer extends
                HtmlInputRenderer<SelectBooleanCheckBox> {

    public HtmlSelectBooleanCheckBoxRenderer() {
    }

    @Override
    protected void processValueAttribute() {

        String value = uiComponent.getValue();

        if (value != null) {

            if (EL.isBindVariable(value)) {

                htmlBuilder.addAttribute(Input.ATTR_ATTR,
                                         value);
                if (uiComponent.getName() == null) {
                    htmlBuilder.addAttribute(Input.ATTR_NAME,
                                             EL.getBindVariable(value));
                }
            }
        }

        htmlBuilder.addAttribute(Input.ATTR_VALUE,
                                 YesNoBoolean.No.getValue());
    }
}

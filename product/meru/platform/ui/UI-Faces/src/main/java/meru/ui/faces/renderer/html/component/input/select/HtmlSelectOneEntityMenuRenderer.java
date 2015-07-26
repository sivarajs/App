package meru.ui.faces.renderer.html.component.input.select;

import meru.el.EL;
import meru.ui.faces.renderer.html.component.input.HtmlInputRenderer;
import meru.ui.faces.tag.component.input.Input;
import meru.ui.faces.tag.component.input.select.SelectOneEntityMenu;

public class HtmlSelectOneEntityMenuRenderer extends
        HtmlInputRenderer<SelectOneEntityMenu> {

    public HtmlSelectOneEntityMenuRenderer() {
        super("select");
    }

    @Override
    protected void addAttributes() {
        super.addAttributes();
        htmlBuilder.addAttribute("name",
                                 uiComponent.getName())
                   .addAttribute("entity",
                                 uiComponent.getEntity())
                   .addAttribute("filter",
                                 uiComponent.getFilter());

        processValueAttribute();
    }

    @Override
    public boolean preRenderChildren() {

        if (uiComponent.getEntity() != null) {

            htmlBuilder.closeBeginTag();

            viewContext.addUIView(new SelectOneEntityMenuHtmlView(uiComponent));
        }

        return true;
    }

    protected void processValueAttribute() {

        String value = uiComponent.getValue();

        if (value != null) {

            if (EL.isBindVariable(value)) {

                value = EL.getBindVariable(value);

                htmlBuilder.addAttribute(Input.ATTR_NAME,
                                         value.substring(0,
                                                         value.lastIndexOf("."))
                                                 + ".id");
            }
        }
    }

}

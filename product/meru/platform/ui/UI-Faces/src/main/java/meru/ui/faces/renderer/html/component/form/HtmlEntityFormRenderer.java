package meru.ui.faces.renderer.html.component.form;

import java.beans.Introspector;

import meru.el.EL;
import meru.ui.faces.renderer.expr.FieldExpressionParser;
import meru.ui.faces.tag.component.form.EntityForm;
import meru.ui.faces.tag.component.input.Input;

public class HtmlEntityFormRenderer extends HtmlFormRenderer<EntityForm> {

    private EntityForm entityForm;
    private String var;

    @Override
    protected void addAttributes() {
        super.addAttributes();
        entityForm = (EntityForm) uiComponent;

        String entity = entityForm.getEntity();

        htmlBuilder.addAttribute("entity",
                                 entity)
                   .addAttribute("entityId",
                                 entityForm.getEntityId())
                   .addAttribute("onsubmit",
                                 "return false;");

        String listenVar = entityForm.getVar();

        if (listenVar == null) {
            listenVar = Introspector.decapitalize(entity);
        }

        htmlBuilder.addAttribute("var",
                                 listenVar);
        var = listenVar;
    }

    @Override
    protected boolean preRenderChildren() {

        String listenVar = entityForm.getVar();

        String id = null;

        if (listenVar == null) {
            id = Introspector.decapitalize(entityForm.getEntity()) + ".id";
        }
        else {
            id = listenVar + ".id";
        }

        htmlBuilder.startElement("input")
                   .addAttribute("type",
                                 "hidden")
                   .addAttribute(COMPONENT_NAME,
                                 "inputHidden")
                   .addAttribute(Input.ATTR_NAME,
                                 id);

        if (entityForm.getEntityId() != null) {
            viewContext.addUIView(new EntityFormHtmlView(uiComponent));

            htmlBuilder.addText(" value='",
                                false);
            EL.parseText(entityForm.getEntityId(),
                         new FieldExpressionParser(htmlBuilder,
                                                   viewContext));
            htmlBuilder.addText("'",
                                false);
        }

        htmlBuilder.endElement();

        return true;
    }

    @Override
    protected void postRenderChildren() {
        if (uiComponent.isSubmittable()) {
            htmlBuilder.startElement("div")
                       .addClassAttribute("save")
                       .addStyleAttribute(entityForm.getSubmitButtonStyle())
                       .startElement("input")
                       .addAttribute("id",
                                     var + "Submit")
                       .addAttribute("class",
                                     "buttonSave")
                       .addAttribute("type",
                                     "submit")
                       .addAttribute("value",
                                     entityForm.getSubmitButtonLabel())
                       .endElement()
                       .endElement();
        }

    }

}

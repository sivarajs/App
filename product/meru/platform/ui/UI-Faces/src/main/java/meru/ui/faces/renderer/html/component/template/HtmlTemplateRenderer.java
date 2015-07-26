package meru.ui.faces.renderer.html.component.template;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.template.Template;

public class HtmlTemplateRenderer extends HtmlComponentRenderer<Template> {

    @Override
    public boolean preRender() {

/*        List<UIComponent> childComps = uiComponent.getChildComponents();
        for (UIComponent component : childComps) {

            if (component instanceof Parameter) {
                Parameter parameter = (Parameter) component;
                viewContext.getCurrentView()
                           .setVariable(parameter.getName(),
                                        component);
            }
            else if (component instanceof UIVariable) {
                UIVariable variable = (UIVariable) component;
                viewContext.getCurrentView()
                           .setVariable(variable.getName(),
                                        variable.getValue());
            }
        }
*/
        return true;
    }

}

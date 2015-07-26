package meru.ui.faces.renderer.html.component.template;

import java.util.List;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.template.Parameter;

public class HtmlParameterRenderer extends HtmlComponentRenderer<Parameter> {

    @Override
    protected boolean preRender() {

        if (uiComponent.isTemplateParent() || uiComponent.IsDeclaration()) {

            viewContext.getCurrentView()
                       .setVariable(uiComponent.getName(),
                                    uiComponent.getChildTags());
            return false;
        }
        else {

            @SuppressWarnings("unchecked")
            List<UITag> childComponents = (List<UITag>) viewContext.getCurrentView()
                                                                               .getVariableValue(uiComponent.getName());

            if (childComponents != null) {
              uiComponent.setChildTags(childComponents);
                //throw new RuntimeException("Unknown parameter : "+uiComponent.getName());
            }
            
        }

        return true;
    }

}

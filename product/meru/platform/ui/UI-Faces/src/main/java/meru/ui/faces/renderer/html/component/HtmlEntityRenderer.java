package meru.ui.faces.renderer.html.component;

import meru.ui.faces.renderer.html.EntityHtmlView;
import meru.ui.faces.renderer.html.SingleEntityHtmlView;
import meru.ui.faces.tag.component.UIEntity;

public class HtmlEntityRenderer extends HtmlComponentRenderer<UIEntity> {

    @Override
    protected boolean preRender() {
        String id = uiComponent.getEntityId();

        if (id == null) {
            viewContext.addUIView(new SingleEntityHtmlView(uiComponent.getName(),
                                                           uiComponent.getFilter()));
        }
        else {

            viewContext.addUIView(new EntityHtmlView(uiComponent.getName(),
                                                     id));
        }
        return true;
    }

}

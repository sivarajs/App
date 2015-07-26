package meru.ui.faces.renderer.html.component.form;

import meru.ui.faces.renderer.html.EntityHtmlView;
import meru.ui.faces.tag.component.form.EntityForm;

public class EntityFormHtmlView extends EntityHtmlView {

    public EntityFormHtmlView(EntityForm entityForm) {
        super(entityForm.getEntity(),
              entityForm.getEntityId());
    }
}

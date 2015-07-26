package meru.ui.faces.tag.registry;

import meru.ui.faces.tag.activity.RedirectActivity;
import meru.ui.faces.tag.activity.ThrowActivity;
import meru.ui.faces.tag.activity.UIActivity;

public class UIActivityRegistry extends UIComponentRegistry {

    public UIActivityRegistry() {
        super(UIActivity.NAMESPACE);
        addTagClass(ThrowActivity.NAME,
                    ThrowActivity.class);
        addTagClass(RedirectActivity.NAME,
                    RedirectActivity.class);
    }
}

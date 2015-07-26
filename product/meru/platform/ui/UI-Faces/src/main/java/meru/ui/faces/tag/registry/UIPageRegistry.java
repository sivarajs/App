package meru.ui.faces.tag.registry;

import meru.ui.faces.tag.page.Section;
import meru.ui.faces.tag.page.UIFragment;

public class UIPageRegistry extends UIComponentRegistry {

    public UIPageRegistry() {
        super(UIFragment.NAMESPACE);
        addTagClass(Section.NAME,
                    Section.class);

    }
}

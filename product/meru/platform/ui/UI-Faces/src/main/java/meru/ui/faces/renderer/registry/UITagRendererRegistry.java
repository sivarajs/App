package meru.ui.faces.renderer.registry;

import java.util.HashMap;
import java.util.Map;

import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.tag.UITag;

public class UITagRendererRegistry {

    protected String mNamespace;
    protected Map<String, Class<?>> mBuilders = new HashMap<String, Class<?>>();

    public UITagRendererRegistry(String namespace) {
        mNamespace = namespace;

    }

    public String getNamespace() {

        return mNamespace;
    }

    @SuppressWarnings("unchecked")
    public <T extends UITag> UITagRenderer<T> getTagRenderer(String name) {

        Class<?> component = mBuilders.get(name);

        if (component == null) {
            throw new NoClassDefFoundError("The renderer class corresponding to the tag '{"
                    + mNamespace + "}" + name + "' is not found");
        }

        try {

            return (UITagRenderer<T>) component.newInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

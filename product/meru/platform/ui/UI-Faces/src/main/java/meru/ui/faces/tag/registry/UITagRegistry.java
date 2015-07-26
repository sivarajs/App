package meru.ui.faces.tag.registry;

import java.util.HashMap;
import java.util.Map;

import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.UIComponent;

public class UITagRegistry<T extends UITag> {

    protected String mNamespace;
    private Map<String, Class<? extends T>> mComponents = new HashMap<String, Class<? extends T>>();

    public UITagRegistry(String namespace) {
        mNamespace = namespace;
    }

    public UITagRegistry() {
        mNamespace = UIComponent.NAMESPACE;
    }

    public String getNamespace() {

        return mNamespace;
    }

    protected void addTagClass(String name, Class<? extends T> tag) {
        mComponents.put(name, tag);
    }
    
    public UITag getTag(String name) {

        Class<?> component = mComponents.get(name);

        if (component == null) {
            throw new RuntimeException("Unknown component : {" + mNamespace
                    + "} " + name);
        }

        try {
            return (UIComponent) component.newInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

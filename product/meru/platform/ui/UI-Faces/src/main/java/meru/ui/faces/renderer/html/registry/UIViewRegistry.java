package meru.ui.faces.renderer.html.registry;

import java.util.HashMap;
import java.util.Map;

import meru.ui.faces.renderer.html.HtmlView;
import meru.ui.faces.renderer.html.activity.RedirectActivityView;
import meru.ui.faces.renderer.html.activity.ThrowActivityView;
import meru.ui.faces.renderer.html.block.ElseBlockView;
import meru.ui.faces.renderer.html.block.ForEachBlockView;
import meru.ui.faces.renderer.html.block.IfBlockView;
import meru.ui.faces.tag.activity.RedirectActivity;
import meru.ui.faces.tag.activity.ThrowActivity;
import meru.ui.faces.tag.activity.UIActivity;
import meru.ui.faces.tag.block.ElseBlock;
import meru.ui.faces.tag.block.ForEachBlock;
import meru.ui.faces.tag.block.IfBlock;
import meru.ui.faces.tag.block.UIBlock;

public class UIViewRegistry {

    protected String mNamespace;
    protected Map<String, Class<?>> mUIViews = new HashMap<String, Class<?>>();

    public UIViewRegistry(String namespace) {
        mNamespace = namespace;

    }

    public String getNamespace() {

        return mNamespace;
    }

    public HtmlView getUIView(String name) {

        Class<?> className = mUIViews.get(name);

        if (className == null) {
            throw new NoClassDefFoundError("The builder class corresponding to the tag '{" + mNamespace + "}" + name + "' is not found");
        }

        try {

            return (HtmlView) className.newInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, UIViewRegistry> mViewRegistries = new HashMap<String, UIViewRegistry>(1);

    public static HtmlView getUIView(String namespace,
                                     String name) {

        UIViewRegistry viewRegistry = mViewRegistries.get(namespace);

        if (viewRegistry == null) {
            // throw
        }
        return viewRegistry.getUIView(name);
    }

    static class UIBlockViewRegistry extends UIViewRegistry {
        UIBlockViewRegistry() {
            super(UIBlock.NAMESPACE);

            mUIViews.put(IfBlock.NAME,
                         IfBlockView.class);
            mUIViews.put(ElseBlock.NAME,
                         ElseBlockView.class);
            mUIViews.put(ForEachBlock.NAME,
                         ForEachBlockView.class);
        }
    }

    static class UIActivityViewRegistry extends UIViewRegistry {
        UIActivityViewRegistry() {
            super(UIActivity.NAMESPACE);

            mUIViews.put(ThrowActivity.NAME,
                         ThrowActivityView.class);
            mUIViews.put(RedirectActivity.NAME,
                         RedirectActivityView.class);

        }
    }

    static {

        mViewRegistries.put(UIBlock.NAMESPACE,
                            new UIViewRegistry.UIBlockViewRegistry());
        mViewRegistries.put(UIActivity.NAMESPACE,
                            new UIViewRegistry.UIActivityViewRegistry());

    }
}

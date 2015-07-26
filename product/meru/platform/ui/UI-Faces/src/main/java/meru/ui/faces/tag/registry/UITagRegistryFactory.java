package meru.ui.faces.tag.registry;

import java.util.HashMap;
import java.util.Map;

import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.activity.UIActivity;
import meru.ui.faces.tag.block.UIBlock;
import meru.ui.faces.tag.component.UIComponent;
import meru.ui.faces.tag.component.html.HtmlComponent;
import meru.ui.faces.tag.page.UIFragment;

public class UITagRegistryFactory {

  private static final Map<String, UITagRegistry<?>> mComponentRegistries = new HashMap<String, UITagRegistry<?>>();
  
  static {
    mComponentRegistries.put(UIComponent.NAMESPACE, new UIComponentRegistry());
    mComponentRegistries.put(HtmlComponent.NAMESPACE, new HtmlUIComponentRegistry());
    mComponentRegistries.put(UIBlock.NAMESPACE, new UIBlockRegistry());
    mComponentRegistries.put(UIFragment.NAMESPACE, new UIPageRegistry());
    mComponentRegistries.put(UIActivity.NAMESPACE, new UIActivityRegistry());
  }
  
  
  public static void addTagRegistry(String namespace, UITagRegistry<? extends UITag> tagRegistry) {
      mComponentRegistries.put(namespace, tagRegistry);
  }
  
  public static UITagRegistry<?> getTagRegistry(String namespace) {
    
    if (namespace == null) {
      namespace = HtmlComponent.NAMESPACE;
    }
    
    UITagRegistry<?> compRegistry = mComponentRegistries.get(namespace);
    
    if (compRegistry == null) {
      throw new IllegalArgumentException("Unknown namespace : "+namespace);
    }
    
    return compRegistry;
  }
  
}

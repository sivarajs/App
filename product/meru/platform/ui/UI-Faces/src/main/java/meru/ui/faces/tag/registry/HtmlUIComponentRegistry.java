package meru.ui.faces.tag.registry;

import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.html.HtmlComponent;

public class HtmlUIComponentRegistry extends UIComponentRegistry {

  public HtmlUIComponentRegistry() {

    super(HtmlComponent.NAMESPACE);
    
  }

  @Override
  public UITag getTag(String name) {

    return new HtmlComponent();
  }
}

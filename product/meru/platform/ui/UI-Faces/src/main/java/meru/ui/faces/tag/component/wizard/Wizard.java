package meru.ui.faces.tag.component.wizard;

import meru.ui.faces.tag.component.UIComponent;

public class Wizard extends UIComponent {

  public static final String NAME = "wizard";

  public Wizard() {
    super(NAME);
  }
  
  @Override
  public boolean ignoreTextNode() {
    return true;
  }

}

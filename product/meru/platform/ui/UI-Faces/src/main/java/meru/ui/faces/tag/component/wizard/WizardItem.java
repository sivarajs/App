package meru.ui.faces.tag.component.wizard;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;

public class WizardItem extends UIComponent implements Identifiable {

  public static final String NAME = "wizardItem";

  public WizardItem() {
    super(NAME);
  }

  public String getTitle() {
    return getAttribute("title");
  }
  
  public String getListener() {
    return getAttribute("listener");
  }
  
  public String getSrc() {
    return getAttribute("src");
  }
}

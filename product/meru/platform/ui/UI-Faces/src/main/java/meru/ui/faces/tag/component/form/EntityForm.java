package meru.ui.faces.tag.component.form;

public class EntityForm extends Form {

  public static final String NAME = "entityForm";

  public EntityForm() {
    super(NAME);
  }

  public String getEntity() {
    return getMandatoryAttribute("entity");
  }

  public String getEntityId() {
    return getAttribute("entityId");
  }

  public String getVar() {
    return getAttribute("var");
  }

  @Override
  public String newId() {

    return getEntity() + "Form";

  }
}

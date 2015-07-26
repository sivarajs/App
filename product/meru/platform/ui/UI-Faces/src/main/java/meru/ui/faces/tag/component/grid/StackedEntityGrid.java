package meru.ui.faces.tag.component.grid;

public class StackedEntityGrid extends EntityGrid {

  public static final String NAME = "stackedEntityGrid";

  public StackedEntityGrid() {
    super(NAME);
  }

  public String getGroupBy() {
    return getMandatoryAttribute("groupBy");
  }

  public String getUnitExpression() {
    return getMandatoryAttribute("unitExpression");
  }

}

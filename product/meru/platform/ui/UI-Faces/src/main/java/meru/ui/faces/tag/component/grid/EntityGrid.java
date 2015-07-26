package meru.ui.faces.tag.component.grid;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.FilterableEntityComponent;

public class EntityGrid extends FilterableEntityComponent implements
    Identifiable {

  public static final String NAME = "entityGrid";

  private int columns;

  public EntityGrid() {
    super(NAME);
  }

  public EntityGrid(String name) {
    super(name, NAME);
  }

  public int getColumns() {
    return columns;
  }
  
  public String getItemStyle() {
    return getAttribute("itemStyle");
  }
  
  public String getItemClass() {
    return getAttribute("itemClass");
  }

  @Override
  protected boolean load() {
    super.load();
    String colsVal = getAttribute("columns");

    if (colsVal != null) {
      columns = Integer.parseInt(colsVal);
    }

    return true;

  }

}

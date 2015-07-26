package meru.ui.faces.renderer.html.component.grid;

import java.io.IOException;

import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.html.ForEachEntityHtmlView;
import meru.ui.faces.tag.component.grid.EntityGrid;

public class EntityGridHtmlView extends ForEachEntityHtmlView<Object> {

  private EntityGrid mEntityGrid;

  private int mColumns;
  private String mWidth;

  public EntityGridHtmlView(EntityGrid entityGrid) {
    super(entityGrid.getEntityName(),
          entityGrid.getFilter());
    mEntityGrid = entityGrid;
    mColumns = mEntityGrid.getColumns();
    if (mColumns > 0)
      mWidth = "width:" + (100 / mColumns) + "%";
    else {
      mWidth = mEntityGrid.getItemStyle();
    }
  }

  @Override
  protected void writeNoEntity() throws IOException {
    mHtmlBuilder.startElement("p")
                .addText("No items found")
                .endElement();
  }

  @Override
  protected void writeEntity(Object entityGrid) throws IOException {
    writeEntity(entityGrid,
                null);
  }

  protected final void writeEntity(Object entity,
                                   String cssClass) throws IOException {

    writeEntityStart(entity,
                     mEntityGrid.getItemClass());

    buildHtmlView();

    writeEntityEnd();
  }

  protected void writeEntityStart(Object entity,
                                  String cssClass) {

    String cClass = "gridItem";
    if (cssClass != null) {
      cClass += " " + cssClass;
    }

    String id = "e" + ClassHelper.getFieldValue(entity,
                                                "id")
                                 .toString();

    mHtmlBuilder.startElement("div")
                .addAttribute("id",
                              id)
                .addClassAttribute(cClass)
                .addStyleAttribute(mWidth)
                .startElement("div")
                .addStyleAttribute("margin:0px;");
  }

  protected void writeEntityEnd() {

    // div
    mHtmlBuilder.endElement()
                .endElement();

  }

  @Override
  protected void postWriteEntities() throws IOException {
    mHtmlBuilder.startElement("div")
                .addClassAttribute("clr")
                .endElement();
  }

}
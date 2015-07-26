package meru.ui.faces.renderer.html.component.grid;

import java.beans.Introspector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.html.HtmlBuilder;
import meru.ui.faces.tag.component.grid.StackedEntityGrid;

public class StackedEntityGridHtmlView extends EntityGridHtmlView {

  private Map<Object, List<Object>> mItemMap;
  private String groupBy;
  private String varName;
  private String unitExpr;

  public StackedEntityGridHtmlView(StackedEntityGrid stackedGrid) {
    super(stackedGrid);
    groupBy = stackedGrid.getGroupBy();
    mItemMap = new LinkedHashMap<Object, List<Object>>();
    varName = Introspector.decapitalize(stackedGrid.getEntityName());
    unitExpr = stackedGrid.getUnitExpression();
  }

  @Override
  protected void writeEntity(Object entity) throws IOException {
    Object groupByObj = ClassHelper.getFieldValue(entity,
                                                  groupBy);
    List<Object> itemList = mItemMap.get(groupByObj);

    if (itemList == null) {

      itemList = new ArrayList<Object>(1);
      mItemMap.put(groupByObj,
                   itemList);

    }

    itemList.add(entity);
  }

  @Override
  protected void postWriteEntities() throws IOException {
    buildGrid();
  }

  private void buildGrid() throws IOException {

    for (List<Object> entities : mItemMap.values()) {

      boolean isFirst = true;
      HtmlBuilder htmlBuilder = null;

      if (entities.size() > 1) {

        htmlBuilder = new HtmlBuilder();
        htmlBuilder.startElement("ul");
        for (Object entity : entities) {

          String id = "e" + ClassHelper.getFieldValue(entity,
                                                      "id")
                                       .toString();
          String expr = EL.parseText(unitExpr,
                                     entity);

          htmlBuilder.startElement("li")
                     .addAttribute("eid",
                                   id);

          if (isFirst) {
            htmlBuilder.addClassAttribute("sel");
            isFirst = false;
          }

          htmlBuilder.addText(expr)
                     .endElement();
        }
        htmlBuilder.endElement();

      }

      isFirst = true;

      for (Object entity : entities) {

        setVariable(varName,
                    entity);

        writeEntityStart(entity,
                         isFirst ? null : "hidden");

        buildHtmlView();

        if (htmlBuilder != null) {
          mHtmlBuilder.addHtmlText(htmlBuilder.getHtml());
        }
        
        writeEntityEnd();

        isFirst = false;
      }

    }

    mHtmlBuilder.startElement("div")
                .addClassAttribute("clr")
                .endElement();
  }
}

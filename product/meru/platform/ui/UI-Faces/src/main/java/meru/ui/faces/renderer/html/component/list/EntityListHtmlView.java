package meru.ui.faces.renderer.html.component.list;

import java.io.IOException;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.expr.ViewExpressionParser;
import meru.ui.faces.renderer.html.ForEachEntityHtmlView;
import meru.ui.faces.tag.component.list.EntityList;

public class EntityListHtmlView extends ForEachEntityHtmlView<Object> {

  private EntityList entityList;
  private String onItemClick;
  private String href;

  public EntityListHtmlView(EntityList entityList) {
    super(entityList.getEntityName(),
          entityList.getFilter());
    href = entityList.getHref();
    onItemClick = entityList.onItemClick();

    this.entityList = entityList;
  }

  @Override
  protected void writeNoEntity() throws IOException {

    String def = entityList.getDefault();

    if (def != null) {
      if (EL.isBindVariable(def)) {
        mHtmlBuilder.closeBeginTag();

        def = ViewExpressionParser.parseText(def,
                                             this,
                                             false);

      }
    }
    else {
      def = "-- No data --";
    }
    mHtmlBuilder.startElement("li")
                .addHtmlText(def)
                .endElement();
  }

  @Override
  protected void writeEntity(Object resource) throws IOException {

    mHtmlBuilder.startElement("li");

    String label = (String) ClassHelper.getFieldValue(resource,
                                                      "name");
    if (href != null) {
      String h = ViewExpressionParser.parseText(href,
                                                this,
                                                false);
      mHtmlBuilder.startElement("a")
                  .addAttribute("href",
                                h)
                  .addHtmlText(label)
                  .endElement();
    }
    else if (onItemClick != null) {
      String onclick = ViewExpressionParser.parseText(onItemClick,
                                                      this,
                                                      false);
      mHtmlBuilder.addAttribute("onclick",
                                onclick);
      mHtmlBuilder.addHtmlText(label);
    }

    mHtmlBuilder.endElement();

  }
}

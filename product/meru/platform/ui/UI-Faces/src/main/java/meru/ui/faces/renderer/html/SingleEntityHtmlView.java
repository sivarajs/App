package meru.ui.faces.renderer.html;

import java.io.IOException;
import java.util.List;

import meru.el.EL;
import meru.ui.faces.renderer.expr.ViewExpressionParser;

public class SingleEntityHtmlView extends HtmlView {

  private String mEntityName;
  private String mFilter;

  private String mVariable;

  protected HtmlBuilder htmlBuilder;

  public SingleEntityHtmlView(String entityName, String filter) {
    mEntityName = entityName;
    mFilter = filter;

    mVariable = Character.toLowerCase(entityName.charAt(0))
        + entityName.substring(1);

    htmlBuilder = new HtmlBuilder();
  }

  @Override
  protected void buildHtml() throws IOException {

    String filter = null;

    if (EL.containsBindVariable(mFilter)) {

      filter = ViewExpressionParser.parseText(mFilter,
                                              this,
                                              true);
    }
    else {
      filter = mFilter;
    }

    List<?> entities = mEntityDataProvider.getEntities(mEntityName,
                                                       filter);

    if (entities != null && !entities.isEmpty()) {

      setVariable(mVariable,
                  entities.get(0));

      // TODO workaround is for entityForm
      if (getParent() != null) {
        getParent().setVariable(mVariable,
                                entities.get(0));
      }
    }
  }
}

package meru.ui.faces.renderer.html.block;

import java.io.IOException;

import meru.ui.faces.renderer.html.ForEachEntityHtmlView;
import meru.ui.faces.tag.block.ForEachBlock;

public class ForEachBlockView extends UIBlockView {

  public ForEachBlockView() {
  }

  @Override
  protected void buildHtml() throws IOException {

    ForEachBlock forEachBlock = (ForEachBlock) mUIBlock;
    ForEachEntityHtmlView<Object> view = new ForEachBlockEntityView(forEachBlock.getEntity(),
                                                                    forEachBlock.getFilter(),
                                                                    this);

    // to read the variable
    view.setParent(this.getParent());
    view.addUIView(this);
    
    view.build(mHtmlBuilder,
               mEntityDataProvider);
  }

  class ForEachBlockEntityView extends ForEachEntityHtmlView<Object> {

    UIBlockView view;

    public ForEachBlockEntityView(String entityName,
                                  String filter,
                                  UIBlockView view) {
      super(entityName,
            filter);
      this.view = view;
    }

    @Override
    protected void writeNoEntity() throws IOException {

    }

    @Override
    protected void writeEntity(Object entity) throws IOException {
      view.generateHtml();
    }

  }

}

package meru.ui.faces.renderer.html.component.menu;

import java.io.IOException;

import meru.el.EL;
import meru.ui.faces.renderer.EntityHierarchy;
import meru.ui.faces.renderer.EntityHierarchy.Folder;
import meru.ui.faces.renderer.EntityHierarchy.Item;
import meru.ui.faces.renderer.html.HierarchicalEntityHtmlView;
import meru.ui.faces.tag.component.menu.EntityMenu;
import app.entity.Hierarchical;

public class EntityMenuView extends HierarchicalEntityHtmlView {

  private EntityMenu mEntityMenu;
  private String mOnClick;
  private String mHref;

  public EntityMenuView(EntityMenu entityMenu) {
    super(entityMenu.getEntity(),
          entityMenu.getFilter());
    mOnClick = entityMenu.onClick();
    mHref = entityMenu.getHref();
    mEntityMenu = entityMenu;
  }

  @Override
  protected void postWriteEntities() throws IOException {

    Folder root = mEntityHierarchy.buildFolders();

    if (!(root.hierarchical instanceof EntityHierarchy.Root) &&  "root".equals(root.hierarchical.getKind())) {
      
      String href = getVariableStringValue("application.root") + "/"
          + mHref;

      href = EL.parseText(href,
                          root);

      mHtmlBuilder.startElement("a")
                  .addClassAttribute(mEntityMenu.getLabelClass())
                  .addAttribute("href",
                                href)
                  .addText(root.label)
                  .endElement();
    }

    addFolder(root);
  }

  private void addFolder(Folder folder) {

    if (!folder.isEmpty()) {

      mHtmlBuilder.startElement("ul");

      for (Item item : folder.getItems()) {

        mHtmlBuilder.startElement("li");

        if (item.hierarchical instanceof Hierarchical) {
          // String viewUri = ((Hierarchical) item.hierarchical).getAction();
          // if (viewUri != null) {
          // String onClick = mOnClick;
          // if (!viewUri.startsWith("/")) {
          // viewUri = getVariableStringValue("application.root") + "/"
          // + viewUri;
          // onClick = mOnClick.replace("#{hierarchical.action}",
          // viewUri);
          //
          // }

          if (mOnClick != null) {

            String onClick = EL.parseText(mOnClick,
                                          item);

            mHtmlBuilder.addAttribute("onclick",
                                      onClick);
          }

        }

        mHtmlBuilder.startElement("div")
                    .startElement("div")
                    .addClassAttribute(item.hierarchical.getKind())

                    .startElement("a");

        if (mHref == null) {
          mHtmlBuilder.addAttribute("href",
                                    "javascript:;");

        }
        else {
          String href = getVariableStringValue("application.root") + "/"
              + mHref;

          href = EL.parseText(href,
                              item);

          mHtmlBuilder.addAttribute("href",
                                    href);
        }

        mHtmlBuilder.addText(item.label)
                    .endElement()
                    .endElement()
                    .endElement();

        if (item instanceof Folder) {
          addFolder((Folder) item);
        }

        mHtmlBuilder.endElement();
      }

      mHtmlBuilder.startElement("div")
                  .addClassAttribute("clr")
                  .endElement();

      mHtmlBuilder.endElement();
    }

  }

}

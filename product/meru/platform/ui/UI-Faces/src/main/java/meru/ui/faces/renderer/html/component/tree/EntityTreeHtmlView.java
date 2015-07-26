package meru.ui.faces.renderer.html.component.tree;

import java.io.IOException;

import meru.el.EL;
import meru.ui.faces.renderer.EntityHierarchy.Folder;
import meru.ui.faces.renderer.EntityHierarchy.Item;
import meru.ui.faces.renderer.html.HierarchicalEntityHtmlView;
import meru.ui.faces.tag.component.tree.EntityTree;

public class EntityTreeHtmlView extends HierarchicalEntityHtmlView {

  private String mOnTreeFolderClick;
  private String mOnTreeItemClick;

  private EntityTree mEntityTree;

  public EntityTreeHtmlView(EntityTree entityTree) {
    super("/",
          entityTree.getEntity(),
          entityTree.getFilter());

    mEntityTree = entityTree;
    mOnTreeFolderClick = entityTree.onTreeFolderClick();
    mOnTreeItemClick = entityTree.onTreeItemClick();
  }

  @Override
  protected void postWriteEntities() throws IOException {

    Folder root = mEntityHierarchy.buildFolders();

    if (mEntityTree.hideRoot()) {
      for (Item item : root.getItems()) {
        addItem(item);
      }
    }
    else {
      mHtmlBuilder.startElement("ul");
      addItem(root);
      mHtmlBuilder.endElement();
    }
  }

  private void addFolder(Folder folder) {

    if (!folder.isEmpty()) {

      mHtmlBuilder.startElement("ul");
      for (Item item : folder.getItems()) {

        addItem(item);
      }

      mHtmlBuilder.endElement();
    }

  }

  private void addItem(Item item) {

    boolean isFolder = (item instanceof Folder);

    mHtmlBuilder.startElement("li");

    String onClick = null;

    mHtmlBuilder.startElement("div");
    if (isFolder) {
      if (mOnTreeFolderClick != null) {
        onClick = EL.parseText(mOnTreeFolderClick,
                               item);
      }

      mHtmlBuilder.addClassAttribute("treeNode folder expanded");
    }
    else {

      if (mOnTreeItemClick != null) {
        onClick = EL.parseText(mOnTreeItemClick,
                               item);
      }

      mHtmlBuilder.addClassAttribute("treeNode item");
    }

    mHtmlBuilder.addAttribute("onclick",
                              onClick)
                .startElement("div")
                .addClassAttribute(item.hierarchical.getKind())

                .startElement("a")
                .addAttribute("href",
                              "javascript:;")
                .addText(item.label)
                .endElement()
                .endElement();

    mHtmlBuilder.startElement("div")
                .addClassAttribute("folderIcon")
                .endElement();

    mHtmlBuilder.endElement();

    if (isFolder) {
      addFolder((Folder) item);
    }

    mHtmlBuilder.endElement();
  }
}

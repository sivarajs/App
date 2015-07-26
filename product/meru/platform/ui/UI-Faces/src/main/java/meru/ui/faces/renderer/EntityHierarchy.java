package meru.ui.faces.renderer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import meru.persistence.EntityQuery;
import app.entity.Hierarchical;

public class EntityHierarchy {

  private Folder mRootFolder;

  private Map<Object, Hierarchical> mHierarchicalMap = new LinkedHashMap<Object, Hierarchical>();

  public EntityHierarchy() {
    mRootFolder = new Folder(new Root());
  }

  public EntityHierarchy(Hierarchical hierarchical) {
    mRootFolder = new Folder(hierarchical);
  }

  public void addHierarchicalEntity(Hierarchical hierarchical) {

    if ("root".equals(hierarchical.getKind())) {
      mRootFolder = new Folder(hierarchical);
      return;
    }

    mHierarchicalMap.put(hierarchical.getId(),
                         hierarchical);
  }

  public Folder buildFolders() {
    buildFolders(mRootFolder,
                 0);
    return mRootFolder;
  }

  private void buildFolders(Folder parentFolder,
                            int depth) {

    for (Hierarchical hierarchical : mHierarchicalMap.values()) {

      Object parentId = hierarchical.getParentId();

      if ((parentId == null && parentFolder == mRootFolder)
          || (parentId != null && parentId.equals(parentFolder.id))) {

        String kind = hierarchical.getKind();
        if (kind.equals("root")
            || (kind.equals("module") || kind.equals("folder"))) {
          Folder childFolder = new Folder(hierarchical,
                                          depth);
          parentFolder.addItem(childFolder);
          buildFolders(childFolder,
                       depth + 1);
        }
        else {
          if (parentId != null && parentId.equals(parentFolder.id)) {
            Item item = new Item(hierarchical);
            parentFolder.addItem(item);
          }
        }
      }

      // if ((parentId == null && parentFolder == mRootFolder)
      // || (parentId != null && parentId.equals(parentFolder.id))) {
      //
      // Folder childFolder = new Folder(hierarchical,
      // depth);
      // parentFolder.addItem(childFolder);
      // buildFolders(childFolder,
      // depth + 1);
      // }
    }
  }

  public static class Root implements Hierarchical {

    String name;

    public Root() {

    }

    public Root(String name) {
      this.name = name;
    }

    @Override
    public Object getId() {
      return EntityQuery.AttributeValue.NULL.getValue();
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getType() {
      return null;
    }

    @Override
    public String getKind() {
      return "root";
    }

    @Override
    public Object getParentId() {
      return null;
    }

    @Override
    public String getAction() {
      return null;
    }

    @Override
    public String getQualifiedName() {
        // TODO Auto-generated method stub
        return null;
    }
  }

  public class Folder extends Item {
    private int depth;
    private List<Item> items;

    public Folder(Hierarchical hierarchical) {
      this(hierarchical,
           (short) 0);
    }

    public Folder(Hierarchical hierarchical, int depth) {

      super(hierarchical);
      items = new ArrayList<Item>();
      this.depth = depth;
    }

    public void addItem(Item item) {

      items.add(item);
      item.parent = this;
    }

    public boolean isEmpty() {
      return items.isEmpty();
    }

    public List<Item> getItems() {
      return items;
    }

    public int getDepth() {
      return depth;
    }

    public Folder getFolder(String label) {

      for (Item item : items) {
        if ((item instanceof Folder) && (item.label.equals(label))) {
          return (Folder) item;
        }

      }

      return null;
    }
  }

  public class Item {
    public Object id;
    public String label;
    public Folder parent;
    public Hierarchical hierarchical;

    public Item(Hierarchical hierarchical) {

      this.id = hierarchical.getId();
      this.label = hierarchical.getName();
      this.hierarchical = hierarchical;
    }

    public Hierarchical getHierarchical() {
      return hierarchical;
    }

    public String getQualifiedName() {

      StringBuilder strBuilder = new StringBuilder("/");

      strBuilder.append(hierarchical.getName());

      Folder parentFolder = parent;

      while (parentFolder != null) {

        if (!parentFolder.label.equals("/")) {
          strBuilder.insert(0,
                            parentFolder.label)
                    .insert(0,
                            "/");
        }

        parentFolder = parentFolder.parent;
      }

      return strBuilder.toString();
    }
  }
}

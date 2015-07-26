package meru.repository.metadata;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MetadataFolder extends MetadataFile {
  
  private List<MetadataFile> mChildren;


  public MetadataFolder(String name) {

    this(name, (MetadataFolder)null);
  }


  public MetadataFolder(String name,
                        MetadataFolder parent) {

    super(name, parent);
    mChildren = new CopyOnWriteArrayList<MetadataFile>();
  }

  
  public List<MetadataFile> getChildren() {

    return mChildren;
  }

  
  private MetadataFile getFile(String name) {

    for (MetadataFile file : mChildren) {
      if (MetadataFile.class.isInstance(file) && file.mName.equals(name)) {
        return file;
      }
    }

    throw new IllegalArgumentException("File '" + name + "' is not found");
  }


  public MetadataFile getMetadataFile(String name) {

    if (name.equals(PATH_SEPARATOR)) {
      return this;
    }
    
    if (name.contains(PATH_SEPARATOR)) {
      String[] comps = name.split(PATH_SEPARATOR);
      MetadataFolder parent = this;
      MetadataFile metadataFile = null;

      for (String comp : comps) {

        if (!comp.equals("")) {

          MetadataFile file = parent.getMetadataFile(comp);
          if (file instanceof MetadataFolder) {
           
              parent = (MetadataFolder) file;
          }
          else {
            
              metadataFile = file;
          }
        }
      }
      
      
      if (metadataFile == null) {
          metadataFile = parent;
      }
          

      return metadataFile;
    }
    else {
      return getFile(name);
    }

  }


  public MetadataFolder getMetadataFolder(String name) {

    MetadataFile file = getMetadataFile(name);
    if (file instanceof MetadataFolder) {
      return (MetadataFolder) file;
    }

    throw new IllegalArgumentException("The folder '" + name + "' does not exist");
  }


  public MetadataFile createMetadataFile(String name) {

    MetadataFolder parent = this;

    if (name.contains(PATH_SEPARATOR)) {
      int lastIndex = name.lastIndexOf(PATH_SEPARATOR);
      parent = createMetadataFolder(name.substring(0, lastIndex));
      name = name.substring(lastIndex + 1);
    }

    return parent.createFile(name);
  }


  private MetadataFile createFile(String name) {

    MetadataFile file = new MetadataFile(name, this);
    mChildren.add(file);
    return file;
  }


  public MetadataFolder createMetadataFolder(String name) {

    if (name.contains(PATH_SEPARATOR)) {

      String[] comps = name.split(PATH_SEPARATOR);
      MetadataFolder parent = this;
      for (String comp : comps) {
        if (!comp.trim().equals("")) {
          MetadataFolder folder = parent.createFolder(comp);
          parent = folder;
        }
      }

      return parent;
    }
    else {
      return createFolder(name);
    }
  }


  private MetadataFolder createFolder(String name) {

    MetadataFolder folder = new MetadataFolder(name, this);
    mChildren.add(folder);
    return folder;
  }


}

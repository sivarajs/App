package meru.repository.metadata;

import java.util.List;

public abstract class MetadataRepository {

  protected RootFolder mRootFolder;


  public MetadataRepository(RootFolder rootFolder) {

    mRootFolder = rootFolder;
  }

  
  public RootFolder getRootFolder() {
    return mRootFolder;
  }
  
  public List<MetadataFile> list(String name) {

    MetadataFolder folder = mRootFolder.getMetadataFolder(name);
    return folder.getChildren();
  }


  public MetadataFile createFile(String name) {

    MetadataFile file = mRootFolder.createMetadataFile(name);
    return file;
  }


  public MetadataFolder createFolder(String name) {

    MetadataFolder folder = mRootFolder.createMetadataFolder(name);
    return folder;
  }


  public MetadataFile getFile(String name) {

    MetadataFile file = mRootFolder.getMetadataFile(name);
    return file;
  }


  public MetadataFolder getFolder(String name) {

    MetadataFolder folder = mRootFolder.getMetadataFolder(name);
    return folder;
  }
}

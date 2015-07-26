package meru.repository.metadata.file;

import java.io.File;

import meru.repository.metadata.RootFolder;

public class RootFileFolder extends RootFolder {

  private File mRootFolder;
  
  public RootFileFolder(File physicalFolder) {

    mRootFolder = physicalFolder;  
  }

  public File getPhysicalFolder() {
    return mRootFolder;
  }
  

}

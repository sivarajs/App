package meru.repository.metadata;

public class MetadataFile {

  public static final char PATH_SEPARATOR_CHAR = '/';
  public static final String PATH_SEPARATOR = String.valueOf(PATH_SEPARATOR_CHAR);

  protected String mName;
  protected MetadataFolder mParent;


  MetadataFile(String name,
               MetadataFolder parent) {

    mName = name;
    mParent = parent;
  }


  public String getName() {

    return mName;
  }


  public MetadataFolder getParent() {

    return mParent;
  }

  public String getGlobalParent() {

    if (mParent == null) {
      return null;
    }

    return mParent.getGlobalName();
  }

  
  
  public String getGlobalName() {

    if (mParent == null) {
      return mName;
    }

    return mParent.getGlobalName() + PATH_SEPARATOR + mName;
  }
}

package meru.app;

import java.io.File;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import meru.sys.uid.MessageDigestUIDGenerator;
import meru.sys.uid.UIDGenerator;

public abstract class AppContext {

    private File mApplicationHome;
    private String mApplicationRoot;
    private UIDGenerator mUIDGenerator;

    public AppContext(File appHome,
                      String appRoot) {
        mApplicationHome = appHome;
        if (appRoot != null && !appRoot.equals("")) {
            mApplicationRoot = appRoot;
        }
        
        
        try {
          mUIDGenerator = new MessageDigestUIDGenerator();
        } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException(e);
        }
    }

    public File getApplicationHome() {
        return mApplicationHome;
    }

    public String getApplicationRoot() {
        return mApplicationRoot;
    }

    
    public UIDGenerator getUIDGenerator() {
      return mUIDGenerator;
    }
    
    public abstract AppPort getHttpPort();

    public abstract AppPort getHttpsPort();

    public abstract InputStream getInputStream(String relativePath);
}

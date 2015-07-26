package meru.app;

public class AppPort {

  private String mProtocol;
  private String mDomain;
  private int mPort;

  private String mBaseURL = "";
  private String mAppURL = "";

  public AppPort(String protocol, String domain, Integer port, String root) {

    mProtocol = protocol;
    mDomain = domain;

    if (protocol != null) {
      mBaseURL = protocol + "://";
    }

    mBaseURL += domain;

    if (port != null && port > 0) {
      mPort = port;
      mBaseURL += ":" + port;
    }

    mAppURL = mBaseURL;
    if (root != null) {
      mAppURL += root;
    }
  }

  public String getProtocol() {
    return mProtocol;
  }

  public String getDomain() {
    return mDomain;
  }

  public int getPort() {
    return mPort;
  }

  public String getBaseUrl() {
    return mBaseURL;
  }
  
  public String getUrl() {
    return mAppURL;
  }

  @Override
  public String toString() {
    return mAppURL;
  }
}

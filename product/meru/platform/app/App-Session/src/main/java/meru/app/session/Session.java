package meru.app.session;

import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import meru.app.security.AuthToken;

public class Session {

  public static final String SESSION_KEY = "S";

  private String mId;
  private Long mUserId;
  private AuthToken mAuthToken;
  private String mIP;
  private long mLastAccessed;
  private Map<String, Object> mProperties;
  private TimeZone mTimeZone;

  public Session(String sessionId, String ipAddress) {
    this(sessionId,
         null,
         ipAddress);
  }

  public Session(String sessionId, AuthToken authToken, String ipAddress) {
    mId = sessionId;
    mAuthToken = authToken;
    if (authToken != null) {
      mUserId = authToken.getUserId();
    }
    mIP = ipAddress;
    mLastAccessed = System.currentTimeMillis();
    mProperties = new ConcurrentHashMap<String, Object>();
  }

  public final void setTimeZone(TimeZone timeZone) {
    mTimeZone = timeZone;
  }

  public TimeZone getTimeZone() {
    return mTimeZone;
  }

  public final void setAuthToken(AuthToken authToken) {
    mAuthToken = authToken;
  }

  public String getId() {
    return mId;
  }

  public String getIPAddress() {
    return mIP;
  }

  public long getElapsedTimeSinceLastRequest() {
    return System.currentTimeMillis() - mLastAccessed;
  }

  public void setProperty(String name,
                          Object value) {
    if (value != null) {
      mProperties.put(name,
                      value);
    }
  }

  public Object getProperty(String name) {
    return mProperties.get(name);
  }

  public String getUser() {
    return (mAuthToken == null) ? null : mAuthToken.getUser();
  }

  public Long getUserId() {
    return mUserId;
  }

  public void setUserId(Long userId) {
    mUserId = userId;
  }
}

package meru.app.session.http;

import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.security.AuthToken;
import meru.app.session.Session;
import meru.app.session.SessionManager;

public class HttpSessionManager extends SessionManager {

  /*
   * private static final Logger mLogger =
   * LoggerFactory.getLogger(SessionManager.class.getPackage() .getName());
   */
  public HttpSessionManager() {

  }

  public final Session getSession(HttpServletRequest request,
                                  HttpServletResponse response) {

    String sessionId = getSessionId(request);
    Session session = mSessionMap.get(sessionId);

    if (session == null) {
      session = createSession(sessionId,
                              null,
                              request,
                              response);
    }

    if (session.getTimeZone() == null) {
      setTimeZone(session,
                  request);
    }

    return session;
  }

  private void setTimeZone(Session session,
                           HttpServletRequest request) {
    String tzOffset = getCookieValue("TZO",
                                     request);

    if (tzOffset != null) {

      int hour = Integer.parseInt(tzOffset) / 60;
      int mins = Math.abs(Integer.parseInt(tzOffset) % 60);

      StringBuilder strBuilder = new StringBuilder("GMT");

      strBuilder.append(hour)
                .append(":")
                .append(mins);
      if (mins < 10) {
        strBuilder.append("0");
      }

      session.setTimeZone(TimeZone.getTimeZone(strBuilder.toString()));
    }
  }

  public final void validateSession(HttpServletRequest request,
                                    HttpServletResponse response) {

    String sessionId = getSessionId(request);
    Session session = mSessionMap.get(sessionId);

    if (session == null) {
      unsetCookie("E",
                  response);
    }

  }

  public final String getLoggedInUser(HttpServletRequest request,
                                      HttpServletResponse response) {
    Session session = getSession(request,
                                 response);
    return session.getUser();
  }

  public final void userLoggedIn(AuthToken authToken,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

    String sessionId = getSessionId(request);
    Session session = mSessionMap.get(sessionId);

    if (session == null) {
      session = createSession(sessionId,
                              authToken,
                              request,
                              response);
    }
    else {
      session.setAuthToken(authToken);
    }

    Long userId = authToken.getUserId();

    if (userId != null) {
      setCookie("E",
                userId.toString(),
                response);
    }

    userLoggedIn(session);

    /*
     * if (mLogger.isLoggable(Level.INFO)) { mLogger.info("User '" +
     * authToken.getUser() .getName() + "' has logged in with session '" +
     * sessionId + "'"); }
     */
  }

  public final void userLoggedOut(HttpServletRequest request,
                                  HttpServletResponse response) {

    String sessionId = getSessionId(request);
    Session session = mSessionMap.remove(sessionId);

    if (session != null) {
      unsetCookie(Session.SESSION_KEY,
                  response);
    }

    unsetCookie("E",
                response);
    userLoggedOut(session);

  }

  private synchronized Session createSession(String sessionId,
                                             AuthToken authToken,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {

    /*
     * if (mLogger.isLoggable(Level.INFO)) { mLogger.info("Create the session '"
     * + sessionId + "' for the user-agent " + request.getHeader("user-agent"));
     * }
     */
    Session session = new Session(sessionId,
                                  authToken,
                                  request.getRemoteHost());
    mSessionMap.put(session.getId(),
                    session);

    setCookie(Session.SESSION_KEY,
              sessionId,
              response);

    return session;
  }

  private String getSessionId(HttpServletRequest request) {

    Cookie sessionKeyCookie = getSessionIdCookie(request);
    if (sessionKeyCookie == null) {

      return mUIDGenerator.getUId();
    }

    return sessionKeyCookie.getValue();
  }

  public void setCookie(String name,
                        String value,
                        HttpServletResponse response) {

    Cookie cookie = new Cookie(name,
                               value);
    cookie.setMaxAge(COOKIE_MAX_AGE);
    cookie.setPath("/");
    // cookie.setSecure(true);
    response.addCookie(cookie);
  }

  public void unsetCookie(String name,
                          HttpServletResponse response) {

    Cookie cookie = new Cookie(name,
                               "");
    cookie.setMaxAge(0);

    cookie.setPath("/");
    response.addCookie(cookie);
  }

  private Cookie getCookie(String name,
                           HttpServletRequest request) {

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName()
                  .equals(name)) {
          return cookie;
        }
      }
    }

    return null;
  }

  public String getCookieValue(String name,
                               HttpServletRequest request) {
    Cookie cookie = getCookie(name,
                              request);
    if (cookie == null) {
      return null;
    }

    return cookie.getValue();
  }

  private Cookie getSessionIdCookie(HttpServletRequest request) {

    return getCookie(Session.SESSION_KEY,
                     request);
  }
}

package meru.app.binding.http;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.AppRequest;
//import kaga.app.session.Session;
import meru.app.session.Session;

public class HttpAppRequest extends AppRequest {

  protected Session mSession;
  protected HttpServletRequest mRequest;
  protected HttpServletResponse mResponse;

  public HttpAppRequest(String resourceURI,
                        HttpServletRequest request,
                        HttpServletResponse response) throws UnsupportedEncodingException {

    super(resourceURI);
    mRequest = request;
    mResponse = response;
    mParameters = request.getParameterMap();
  }

  public void setContext(Session session) {
    mSession = session;
  }

  @Override
  public String getSessionId() {
    return mSession.getId();
  }
  
  @Override
  public Long getLoggedInUserId() {
    return 0L;//mSession.getUserId();
  }
  
  @Override
  public boolean isSSLEnabled() {
    return mRequest.isSecure();
  }

  @Override
  public boolean isAuthenticated() {

    Cookie[] cookies = mRequest.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName()
                  .equals("EID")) {
          return true;
        }
      }
    }

    return false;
  }

  public HttpServletRequest getRequest() {
    return mRequest;
  }

  public HttpServletResponse getResponse() {
    return mResponse;
  }
}

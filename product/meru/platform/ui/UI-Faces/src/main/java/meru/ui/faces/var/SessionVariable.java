package meru.ui.faces.var;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SessionVariable extends ViewVariable {

    public static final String NAME = "session";

    private HttpServletRequest mRequest;

    public SessionVariable(HttpServletRequest request) {
        super(NAME);
        mRequest = request;
    }

    @Override
    public Object getFieldValue(String fieldName) {

        return getCookieValue(fieldName);
    }

    private String getCookieValue(String name) {

        Cookie sessionKeyCookie = getCookie(name);
        if (sessionKeyCookie == null) {

            return null;
        }

        return sessionKeyCookie.getValue();
    }

    private Cookie getCookie(String name) {

        Cookie[] cookies = mRequest.getCookies();

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
}

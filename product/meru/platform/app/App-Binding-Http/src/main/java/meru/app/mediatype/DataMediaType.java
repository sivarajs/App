package meru.app.mediatype;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface DataMediaType {

    String encode(Object object, String... attributes);

    String encode(List<?> objects, String... attributes);

    <T> Object decode(HttpServletRequest request,
                      Class<T> objectClass);

}

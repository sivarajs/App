package meru.app.binding.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.mediatype.DataMediaType;
import meru.app.mediatype.MediaTypeFactory;

public class HttpEntityRequest extends HttpAppRequest {

    static final String RESOURCE_URL_PATH = "/bo/";

    private String mEntityName;
    private Object mEntityId;

    public HttpEntityRequest(HttpServletRequest request,
                             HttpServletResponse response) throws UnsupportedEncodingException {

        super(request.getRequestURI(),
              request,
              response);

        parse(request.getPathInfo());
    }

    public String getEntityName() {

        return this.mEntityName;
    }

    public Object getEntityId() {

        return this.mEntityId;
    }

    public Object getEntity(Class<?> entityClass) throws IOException {

        Object resource = null;

        String contentType = mRequest.getContentType();
        if (contentType == null) {
            throw new RuntimeException("MIME Type can not be null");
        }

        int index = contentType.indexOf(";");
        if (index > 0) {
            contentType = contentType.substring(0,
                                                index);
        }

        DataMediaType mediaType = MediaTypeFactory.getMediaType(contentType);

        resource = mediaType.decode(mRequest,
                                    entityClass);

        return resource;

    }

    private void parse(String path) {

        if (path == null || path.trim()
                                .length() == 0) {
            throw new IllegalArgumentException("Invalid resource path : "
                    + path);
        }

        path = path.substring(1);
        int index = path.indexOf("/");

        if (index >= 0) {

            mEntityName = path.substring(0,
                                         index);
            String id = path.substring(index + 1);

            try {
                mEntityId = new Long(id);
            } catch (Exception ex) {
                mEntityId = id;
            }

        }
        else {
            mEntityName = path;
        }
    }
}

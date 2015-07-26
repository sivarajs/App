package meru.app.mediatype;

import java.util.HashMap;
import java.util.Map;

public class MediaTypeFactory {

    private static Map<String, DataMediaType> MEDIA_TYPES = new HashMap<String, DataMediaType>(1);

    private static final String MIME_JSON = "application/json";
    private static final String MIME_FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String MIME_MULTIPART_FORM_DATA = "multipart/form-data";
    
    static {
        MEDIA_TYPES.put(MIME_JSON,
                        new JsonMediaType());
        MEDIA_TYPES.put(MIME_FORM_URL_ENCODED,
                        new FormURLEncodedMediaType());

        MEDIA_TYPES.put(MIME_MULTIPART_FORM_DATA,
                        new MultipartFormDataMediaType());
    }

    public static DataMediaType getMediaType(String mediaType) {
        DataMediaType dMediaType = MEDIA_TYPES.get(mediaType);
        if (dMediaType == null) {
            throw new RuntimeException("Unknown MediaType : " + mediaType);
        }
        return dMediaType;
    }
}

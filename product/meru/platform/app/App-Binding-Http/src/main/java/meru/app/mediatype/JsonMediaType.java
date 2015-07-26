package meru.app.mediatype;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import meru.data.format.json.JsonBuilder;
import meru.data.format.json.parser.JsonParser;
import meru.sys.IOSystem;


public class JsonMediaType implements DataMediaType {

    @Override
    public String encode(Object object, String... attributes) {

        if (object == null) {
            return null;
        }

        JsonBuilder builder = new JsonBuilder();

        return builder.build(object);
    }

    @Override
    public String encode(List<?> objects, String... attributes) {

        StringBuilder strBuilder = new StringBuilder(objects.size()*20);

        strBuilder.append("{\"items\":[");

        JsonBuilder builder = new JsonBuilder();

        int size = objects.size();
        for (int i = 0; i < size; i++) {

            strBuilder.append(builder.build(objects.get(i)));

            if (i < size - 1) {
                strBuilder.append(",");
            }
        }

        strBuilder.append("]}");

        return strBuilder.toString();
    }

    @Override
    public <T> Object decode(HttpServletRequest request,
                             Class<T> objectClass) {
        
        String data = null;
        try {
            data = IOSystem.read(request.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        
        System.out.println("Posted Data for '"+objectClass+"' : " + data);

        
        if (data == null || data.trim()
                                .length() == 0) {
            throw new RuntimeException("No data provided to post");
        }
        
        JsonParser parser = new JsonParser();
        return parser.parse(data,
                            objectClass);
    }
    
}

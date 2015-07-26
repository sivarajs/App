package meru.app.mediatype;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import meru.sys.lang.ClassHelper;

public class FormURLEncodedMediaType implements DataMediaType {

    @Override
    public String encode(Object object, String... attributes) {

       throw new UnsupportedOperationException();
    }

    @Override
    public String encode(List<?> objects, String... attributes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Object decode(HttpServletRequest request,
                             Class<T> objectClass) {

        T entity = null;
        try {
            entity = objectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
        Map<String, String[]> parameters = request.getParameterMap();

        for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {

            String[] value = parameter.getValue();

            if (value.length > 0) {

                System.out.println("#### "+parameter.getKey()+"  "+value[0]);
                ClassHelper.setFieldValue(entity,
                                          parameter.getKey(),
                                          value[0],
                                          true);
            }
        }


        return entity;

    }
}

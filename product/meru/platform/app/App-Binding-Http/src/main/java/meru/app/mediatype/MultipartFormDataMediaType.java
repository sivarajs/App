package meru.app.mediatype;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import meru.app.config.AppConfig;
import meru.sys.IOSystem;
import meru.sys.lang.ClassHelper;

public class MultipartFormDataMediaType implements DataMediaType {

    @Override
    public String encode(Object object, String... attributes) {

        throw new UnsupportedOperationException();
    }

    @Override
    public String encode(List<?> objects, String... attributes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Object decode(HttpServletRequest request, Class<T> objectClass) {

        T entity = null;
        try {
            entity = objectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            Collection<Part> parts = request.getParts();

            for (Part part : parts) {

                String contentType = part.getContentType();
                String paramName = part.getName();
                paramName = paramName.substring(paramName.indexOf('.') + 1);
                String paramValue = null;
                if (contentType == null) {
                    if (part.getSize() > 0) {

                        paramValue = IOSystem.read(part.getInputStream());
                    }

                }
                else {

                    String imageFileName = getFileName(part);
                    File file = new File(AppConfig.TEMP_FOLDER,
                                         imageFileName);
                    paramValue = file.getAbsolutePath();
                    part.write(paramValue);

                }

                ClassHelper.setFieldValue(entity,
                                          paramName,
                                          paramValue,
                                          false);

            }

        } catch (IllegalStateException | IOException | ServletException e) {
            e.printStackTrace();
        }

        return entity;

    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim()
                     .startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2,
                                       token.length() - 1);
            }
        }
        return null;
    }

}

package meru.sys;

import java.io.InputStream;

public final class JVM {

    public final static class SystemProperty {

        public static final String NEW_LINE = System.getProperty("line.separator");
        public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    }

    public static String readClassPathResource(String resourceName) {

        InputStream inputStream = Thread.currentThread()
                                        .getContextClassLoader()
                                        .getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new RuntimeException(resourceName + " not found");
        }
        return IOSystem.read(inputStream);

    }

}

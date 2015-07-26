package meru.lang.loader;

import java.io.File;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CustomClassLoaderTest extends AppTest {

    public CustomClassLoaderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CustomClassLoaderTest.class);
    }

    public void testLoadClass() throws ClassNotFoundException, IOException {
        CustomClassLoader classLoader = new CustomClassLoader(new File(TEMP_DIR));

        classLoader.loadClass(CLASS_NAME);

        try {
            classLoader.loadClass("empty");
            fail();
        } catch (ClassNotFoundException e) {

        }

    }

}

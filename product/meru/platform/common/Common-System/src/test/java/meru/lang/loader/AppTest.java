package meru.lang.loader;

import java.io.File;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import meru.sys.IOSystem;
import meru.sys.JVM;
import meru.sys.JVM.SystemProperty;
import meru.sys.lang.JCompiler;

public class AppTest extends TestCase {

    protected static final String CLASS_NAME = "Test";
    protected static final String TEMP_DIR = JVM.SystemProperty.TEMP_DIR + "/k";
    protected static final String SRC_DIR = TEMP_DIR + "/src";
    protected static final String CLASSES_DIR = TEMP_DIR + "/classes";
    
    protected static File CLASSES_DIR_FILE;
    
    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(CustomClassLoaderTest.class);
        testSuite.addTestSuite(CachedClassLoaderTest.class);
        return testSuite;
    }

    @Override
    protected void setUp() throws Exception {

        File tempDir = new File(SRC_DIR);
        tempDir.mkdirs();
        CLASSES_DIR_FILE = new File(CLASSES_DIR);
        CLASSES_DIR_FILE.mkdirs();
        
        createClass(CLASS_NAME,
                    tempDir,
                    CLASSES_DIR_FILE);

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        IOSystem.delete(new File(TEMP_DIR));
        super.tearDown();
    }

    private void createClass(String className,
                             File srcDir,
                             File classesDir) throws IOException {

        StringBuilder strBuilder = new StringBuilder("public class ");
        strBuilder.append(className)
                  .append(" {")
                  .append(SystemProperty.NEW_LINE);

        strBuilder.append("public String sayHello() ")
                  .append(" {")
                  .append(SystemProperty.NEW_LINE)
                  .append(" return \"hello\";")
                  .append(SystemProperty.NEW_LINE)
                  .append(" }");

        strBuilder.append(SystemProperty.NEW_LINE)
                  .append(" }");
        
        
        
        File srcFile = new File(srcDir, className+".java");
        
        IOSystem.transfer(strBuilder.toString(), srcFile);
        JCompiler.compile(srcFile, classesDir);
    }

}

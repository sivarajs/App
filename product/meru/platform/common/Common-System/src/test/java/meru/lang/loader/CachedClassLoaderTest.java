package meru.lang.loader;

import java.io.File;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CachedClassLoaderTest extends AppTest {

    public CachedClassLoaderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CachedClassLoaderTest.class);
    }

    public void testRepositoryClassCache() throws ClassNotFoundException, IOException {
        CachedClassLoader classCache = new CachedClassLoader(new File(TEMP_DIR));
        
        classCache.loadClass(CLASS_NAME);
        
        assertEquals(classCache.size(), 1);
        
        classCache.loadClass(CLASS_NAME);
        
        assertEquals(classCache.size(), 1);
        
        try {
          classCache.loadClass("empty");
          fail();
        } catch (ClassNotFoundException e) {
            
        }
       
        classCache.fileModified();
        assertEquals(classCache.size(), 0);
        
        
    }
    
    
}

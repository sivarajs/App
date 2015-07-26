package meru.lang.loader;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import meru.io.watch.FileWatchListener;
import meru.io.watch.FileWatchService;

public class CachedClassLoader extends ClassLoader implements FileWatchListener {

    private CustomClassLoader classLoader;
    private FileWatchService fileWatchService;
    
    private Map<String, Class<?>> classesMap;
    
    CachedClassLoader(File binaryFolder) throws IOException {
        classLoader = new CustomClassLoader(binaryFolder);
        classesMap = new ConcurrentHashMap<String, Class<?>>();
        
        fileWatchService = new FileWatchService(binaryFolder.toPath());
        fileWatchService.addFileWatchListener(this);
    }
    
    public int size() {
        return classesMap.size();
    }
    
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        
        Class<?> claz = classesMap.get(className);
        
        if (claz == null) {
            loadClas(className);
        }
        
        return claz;
    }

    synchronized private Class<?> loadClas(String className) throws ClassNotFoundException {
        
        Class<?> claz = classesMap.get(className);
        
        if (claz == null) {
            claz = classLoader.loadClass(className);
            classesMap.put(className, claz);
        }
        
        return claz;

    }
    
    public void fileModified() {
        classesMap.clear();
    }
}

package meru.lang.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {

    private File classesFolder;

    CustomClassLoader(File binaryFolder) {
        classesFolder = new File(binaryFolder,
                                 "classes");
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        String cName = className.replace('.',
                                         '/') + ".class";
        File classFile = new File(classesFolder,
                                  cName);
        
        try (FileInputStream inputStream = new FileInputStream(classFile);) {

            byte[] bytes = new byte[(int) classFile.length()];
            inputStream.read(bytes);

            return defineClass(className,
                               bytes,
                               0,
                               bytes.length);
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException(className);
        } catch (IOException e) {
            throw new RuntimeException(className,
                                       e);
        }

    }

}

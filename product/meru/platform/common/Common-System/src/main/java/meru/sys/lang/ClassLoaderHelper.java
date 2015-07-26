package meru.sys.lang;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import meru.sys.IOSystem;

public class ClassLoaderHelper {

  private static final FilenameFilter classFilter = new ClassFilter();

  public static <T> List<Class<? extends T>> loadClassesExtending(File dir,
                                                                  Class<T> extendedClass) {

    List<Class<? extends T>> classList = new ArrayList<Class<? extends T>>(1);
    loadClassesExtending(new File(dir,
                                  "classes"),
                         extendedClass,
                         "",
                         classList);

    loadJarClassesExtending(new File(dir,
                                     "lib"),
                            extendedClass,
                            classList);

    return classList;

  }

  private static <T> void loadJarClassesExtending(File libDir,
                                                  Class<T> extendedClass,
                                                  List<Class<? extends T>> classList) {

    File[] files = libDir.listFiles(new JarFilter());

    for (File file : files) {
      JarFile jarFile = null;
      try {

        jarFile = new JarFile(file);

        Enumeration<?> e = jarFile.entries();

        while (e.hasMoreElements()) {

          JarEntry je = (JarEntry) e.nextElement();

          if (je.getName()
                .endsWith(".class")) {

            String className = je.getName()
                                 .substring(0,
                                            je.getName()
                                              .length() - 6);

            className = className.replace('/', '.');
            Class<? extends T> claz = extendsClass(className,
                                                   extendedClass);

            if (claz != null) {
              classList.add(claz);
            }
          }
        }

      } catch (IOException e1) {

      } finally {
        IOSystem.close(jarFile);
      }
    }
  }

  private static <T> void loadClassesExtending(File classesDir,
                                               Class<T> extendedClass,
                                               String packageName,
                                               List<Class<? extends T>> classList) {

    File[] files = classesDir.listFiles(classFilter);

    for (File file : files) {

      if (file.isDirectory()) {
        loadClassesExtending(file,
                             extendedClass,
                             packageName + file.getName() + ".",
                             classList);
      }
      else {

        String fileName = file.getName();

        String className = packageName
            + fileName.substring(0,
                                 fileName.indexOf('.'));
        Class<? extends T> claz = extendsClass(className,
                                               extendedClass);

        if (claz != null) {

          classList.add(claz);
        }

      }

    }

  }

  @SuppressWarnings("unchecked")
  private static <T> Class<? extends T> extendsClass(String className,
                                                     Class<T> extendedClass) {
    Class<?> claz;
    try {

      claz = Class.forName(className);
      if ((claz.getModifiers() & Modifier.ABSTRACT) != Modifier.ABSTRACT
          && extendedClass.isAssignableFrom(claz)) {

        return (Class<? extends T>) claz;
      }
    } catch (Throwable e) {

    }

    return null;
  }

  private static class ClassFilter implements FilenameFilter {

    public static final String CLASS_EXT = ".class";

    @Override
    public boolean accept(File dir,
                          String name) {
      return name.indexOf('.') < 0 || name.endsWith(CLASS_EXT);
    }

  }

  private static class JarFilter implements FilenameFilter {

    public static final String JAR_EXT = ".jar";

    @Override
    public boolean accept(File dir,
                          String name) {
      return name.endsWith(JAR_EXT);
    }

  }
}

package meru.sys.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import meru.sys.JVM;

public class JCompiler {

    public static void compile(List<File> srcClassFiles,
                               File classesDir) {

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null,
                                                                                      null,
                                                                                      null);

        Iterable<? extends JavaFileObject> compilationUnits = javaFileManager.getJavaFileObjectsFromFiles(srcClassFiles);

        List<String> options = new ArrayList<String>(1);
        options.add("-d");
        options.add(classesDir.getAbsolutePath());

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        javaCompiler.getTask(null,
                             javaFileManager,
                             diagnostics,
                             options,
                             null,
                             compilationUnits)
                    .call();

        if (!diagnostics.getDiagnostics()
                        .isEmpty()) {

            StringBuilder strBuilder = new StringBuilder();

            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {

                strBuilder.append("ERROR : ")
                          .append(diagnostic.getMessage(null))
                          .append(JVM.SystemProperty.NEW_LINE);
            }

            throw new RuntimeException(strBuilder.toString());
        }

    }

    public static void compile(File srcClassFile,
                               File classesDir) {
        List<File> srcClassFiles = new ArrayList<File>(1);
        srcClassFiles.add(srcClassFile);
        compile(srcClassFiles,
                classesDir);
    }
}

package meru.io;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import meru.sys.lang.StringHelper;

public class JavaClassWriter extends SrcFileWriter {

    private String mClassName;

    public JavaClassWriter(File file) throws IOException {
        super(file);
    }

    public JavaClassWriter(File file,
                           String indent) throws IOException {
        super(file,
              indent);
    }

    public JavaClassWriter(Writer writer) throws IOException {
        super(writer);
    }

    @Override
    public final JavaClassWriter newLine() throws IOException {
        super.newLine();
        return this;
    }

    public JavaClassWriter addPackage(String pgName) throws IOException {
        mWriter.write("package ");
        mWriter.write(pgName);
        mWriter.write(";");
        newLine().newLine();
        return this;
    }

    public JavaClassWriter addImport(String pcName) throws IOException {
        mWriter.write("import ");
        mWriter.write(pcName);
        mWriter.write(";");
        newLine();
        return this;
    }

    public JavaClassWriter startClass(String className,
                                      String extendedClass) throws IOException {
        mClassName = className;
        mWriter.write("public class ");
        mWriter.write(className);

        if (!StringHelper.isNullOrEmpty(extendedClass)) {
            mWriter.write(" extends ");
            mWriter.write(extendedClass);
        }

        mWriter.write(" {");

        newLine().newLine();
        return this;
    }

    public JavaClassWriter endClass() throws IOException {
        newLine();
        mWriter.write("}");
        return this;
    }
    
    public JavaClassWriter addClassAnnotration(String annotation) throws IOException {
        return addAnnotration(annotation);
    }

    public JavaClassWriter addFieldMethodAnnotration(String annotation) throws IOException {
        indent();
        return addAnnotration(annotation);
    }

    private JavaClassWriter addAnnotration(String annotation) throws IOException {
        mWriter.write("@");
        mWriter.write(annotation);
        newLine();
        return this;
    }

    
    public JavaClassWriter addField(String field,
                                    String type,
                                    String defaultValue) throws IOException {

        indent();
        mWriter.write("private ");
        mWriter.write(type);
        mWriter.write(" ");
        mWriter.write(field);

        if (!StringHelper.isNullOrEmpty(defaultValue)) {
            mWriter.write(" = ");
            mWriter.write(defaultValue);
        }

        mWriter.write(";");
        newLine();
        return this;
    }

    private JavaClassWriter writeArgument(MethodArgument argument) throws IOException {
        mWriter.write(argument.type);
        mWriter.write(" ");
        mWriter.write(argument.name);
        return this;
    }

    private JavaClassWriter writeArguments(MethodArgument[] arguments) throws IOException {
        if (arguments != null) {
            boolean isFirst = true;
            for (MethodArgument argument : arguments) {
                if (!isFirst) {
                    mWriter.write(", ");
                    isFirst = false;
                }
                writeArgument(argument);
            }
        }

        return this;
    }

    public JavaClassWriter startConstructor() throws IOException {

        startConstructor((MethodArgument[]) null);
        return this;

    }

    public JavaClassWriter startConstructor(MethodArgument... arguments) throws IOException {
        indent();
        mWriter.write("public ");
        mWriter.write(mClassName);
        mWriter.write("(");
        writeArguments(arguments);

        mWriter.write(") {");
        newLine();
        return this;
    }

    public JavaClassWriter endConstructor() throws IOException {
        newLine().indent();
        mWriter.write("}");
        newLine().newLine();
        return this;
    }

    public JavaClassWriter startMethod(String name) throws IOException {
        return startMethod(name,
                           (MethodArgument[]) null);
    }

    public JavaClassWriter startMethod(String name,
                                       MethodArgument... arguments) throws IOException {
        newLine().indent();
        mWriter.write("public void ");
        mWriter.write(name);
        mWriter.write("(");

        writeArguments(arguments);

        mWriter.write(") {");
        newLine();
        return this;
    }

    public JavaClassWriter endMethod() throws IOException {
        newLine().indent();
        mWriter.write("}");
        newLine().newLine();
        return this;
    }

    public JavaClassWriter addStatementln(String statement) throws IOException {
        indent().indent();
        mWriter.write(statement);
        newLine();

        return this;

    }
    public JavaClassWriter addStatement(String statement) throws IOException {
        indent().indent();
        mWriter.write(statement);
        newLine();
        return this;
    }

    public JavaClassWriter addComment(String comment) throws IOException {
        indent().indent();
        mWriter.write("//");
        mWriter.write(comment);
        newLine();

        return this;

    }
    
    public static class MethodArgument {
        String type;
        String name;

        public MethodArgument(String type,
                              String name) {
            this.type = type;
            this.name = name;
        }
    }
}

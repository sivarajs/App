package meru.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import meru.sys.JVM;

public class SrcFileWriter {

    private String mIndentStr = "  ";
    protected Writer mWriter;

    public SrcFileWriter(File file) throws IOException {

        mWriter = new FileWriter(file);
    }

    public SrcFileWriter(File file,
                         String indent) throws IOException {

        mIndentStr = indent;
        mWriter = new FileWriter(file);
    }

    public SrcFileWriter(Writer writer) throws IOException {

        mWriter = writer;
    }

    protected SrcFileWriter indent() throws IOException {
        mWriter.write(mIndentStr);
        return this;
    }

    public SrcFileWriter newLine() throws IOException {
        mWriter.write(JVM.SystemProperty.NEW_LINE);
        return this;
    }

}

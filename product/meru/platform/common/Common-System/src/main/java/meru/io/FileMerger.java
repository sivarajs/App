package meru.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import meru.sys.IOSystem;
import meru.sys.JVM;

public class FileMerger {

    private File mTargetFile;
    private static final byte[] NEW_LINE_BYTES = JVM.SystemProperty.NEW_LINE.getBytes();

    public FileMerger(File targetFile) {

        mTargetFile = targetFile;

    }

    public void merge(File[] filesToMerge) throws IOException {
        merge(filesToMerge,
              false);
    }

    public void merge(File[] filesToMerge, boolean append) throws IOException {
        FileOutputStream targetFileStream = null;
        try {
            targetFileStream = new FileOutputStream(mTargetFile,
                                                    append);

            for (File file : filesToMerge) {
                try {
                    IOSystem.transfer(file,
                                      targetFileStream);
                    targetFileStream.write(NEW_LINE_BYTES);
                } catch (Exception e) {
                    throw new IOException("Unable to merge the file '" + file
                                                  + "'.",
                                          e);
                }
            }

        } finally {
            IOSystem.close(targetFileStream);
        }

    }

    public void readAndMerge(File listFile) throws IOException {
        readAndMerge(listFile);
    }

    public void readAndMerge(File listFile, boolean append) throws IOException {
        List<String> fileNames = IOSystem.readLines(listFile);
        File[] filesToMerge = new File[fileNames.size()];
        for (int i = 0; i < fileNames.size(); i++) {
            filesToMerge[i] = new File(fileNames.get(i));
        }
        merge(filesToMerge,
              append);
    }

    public static void mergeJS(String mergeFile, String targetFile) throws IOException {
        mergeJS(mergeFile,
                targetFile,
                false);
    }

    public static void mergeJS(String mergeFile,
                               String targetFile,
                               boolean append) throws IOException {
        File listFile = new File(mergeFile);

        FileMerger fileMerger = new FileMerger(new File(targetFile));
        fileMerger.readAndMerge(listFile,
                                append);
    }

    public static void mergeCSS(String mergeFile, String targetFile) throws IOException {
        mergeCSS(mergeFile,
                 targetFile,
                 false);
    }

    public static void mergeCSS(String mergeFile,
                                String targetFile,
                                boolean append) throws IOException {
        File listFile = new File(mergeFile);

        FileMerger fileMerger = new FileMerger(new File(targetFile));
        fileMerger.readAndMerge(listFile,
                                append);
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            throw new RuntimeException("Tomcat Home and Application Name are required");
        }

        mergeJS(args[0] + "/webapps/" + args[1] + "/st/js/merge/app-merge.txt",
                args[0] + "/webapps/" + args[1] + "/st/js/app.js");
        // mergeJS("C:/application/apache-tomcat-7.0.34/webapps/retail/common/js/merge/dewbag-merge1.js",
        // "C:/application/apache-tomcat-7.0.34/webapps/ROOT/common/js/db1.js");
        // mergeJS("C:/application/apache-tomcat-7.0.34/webapps/retail/common/js/merge/dewbag-merge2.js",
        // "C:/application/apache-tomcat-7.0.34/webapps/ROOT/common/js/db2.js");
        // mergeJS("C:/application/apache-tomcat-7.0.34/webapps/retail/common/js/merge/dewbag-merge.js",
        // "C:/application/apache-tomcat-7.0.34/webapps/ROOT/common/js/db.js");
        // mergeJS("C:/application/apache-tomcat-7.0.34/webapps/retail/common/js/merge2.js",
        // "C:/application/apache-tomcat-7.0.30/webapps/ROOT/common/js/db2.js");
        // mergeCSS("C:/application/apache-tomcat-7.0.34/webapps/retail/common/css/merge/dewbag-merge.css",
        // "C:/application/apache-tomcat-7.0.34/webapps/ROOT/common/css/db.css");
        // mergeCSS("C:/application/apache-tomcat-7.0.34/webapps/retail/common/css/merge/app-merge.css",
        // "C:/application/apache-tomcat-7.0.34/webapps/ROOT/common/css/app.css");

        mergeCSS(args[0] + "/webapps/" + args[1]
                         + "/st/css/merge/app-merge.txt",
                 args[0] + "/webapps/" + args[1] + "/st/css/app.css");

    }

}

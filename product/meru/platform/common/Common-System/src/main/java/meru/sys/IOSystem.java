package meru.sys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class IOSystem {

  public static final File CWD = new File(System.getProperty("user.dir"));
  
  public static void close(Closeable closeable) {

    if (closeable != null) {
      try {
        closeable.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  public static void delete(File file) {

    if (file.isDirectory()) {
      for (File cFile : file.listFiles()) {
        delete(cFile);
      }

    }

    if (!file.delete()) {
      throw new RuntimeException("Unable to delete the file/folder " + file);
    }

  }

  public static String getRelativePath(String root,
                                       String file) {

    int index = file.indexOf(root);
    if (index >= 0) {
      return file.substring(index + root.length() + 1)
                 .replace(File.separatorChar,
                          '/');
    }

    return file;
  }

  public static Map<String, String> readProperties(File file) {

    Properties properties = new Properties();

    InputStream inputStream = null;

    try {
      inputStream = new FileInputStream(file);
      properties.load(inputStream);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Map<String, String> propMap = new HashMap<String, String>(properties.size());

    for (Map.Entry<Object, Object> entry : properties.entrySet()) {

      propMap.put((String) entry.getKey(),
                  (String) entry.getValue());

    }

    return propMap;
  }

  public static String read(InputStream inputStream) {

    String data = null;
    try (StringWriter stringWriter = new StringWriter();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);) {

      char[] dataBuffer = new char[1024];
      int length = 0;
      while ((length = inputStreamReader.read(dataBuffer)) > 0) {
        stringWriter.write(dataBuffer,
                           0,
                           length);
      }

      if (stringWriter.getBuffer()
                      .length() > 0) {

        stringWriter.flush();
        data = stringWriter.toString();
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      close(inputStream);
    }

    return data;
  }

  public static List<String> readLines(File file) throws IOException {
    return readLines(new FileInputStream(file));
  }

  public static List<String> readLines(InputStream inputStream) throws IOException {

    List<String> lines = new ArrayList<String>(1);

    BufferedReader bufferedReader = null;
    InputStreamReader inputStreamReader = null;
    try {
      inputStreamReader = new InputStreamReader(inputStream);
      bufferedReader = new BufferedReader(inputStreamReader);

      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.trim()
                .length() > 0) {
          lines.add(line);
        }
      }
    } finally {
      close(inputStreamReader);
      close(bufferedReader);
      close(inputStream);
    }

    return lines;
  }

  public static void transfer(String content,
                              File targetFile) throws IOException {

    FileOutputStream fileOPStream = null;
    OutputStreamWriter outputStreamWriter = null;
    BufferedWriter bufWriter = null;
    try {
      fileOPStream = new FileOutputStream(targetFile);
      outputStreamWriter = new OutputStreamWriter(fileOPStream);
      bufWriter = new BufferedWriter(outputStreamWriter);
      bufWriter.write(content);
    } finally {
      close(bufWriter);
      close(outputStreamWriter);
      close(fileOPStream);
    }
  }

  public static void transfer(File file,
                              OutputStream outputStream) throws IOException {

    FileInputStream fileIOStream = null;
    try {
      fileIOStream = new FileInputStream(file);

      transferBytes(fileIOStream,
                    outputStream);
    } finally {
      close(fileIOStream);
    }
  }

  public static void transfer(InputStream inputStream,
                              OutputStream outputStream) throws IOException {

    transferBytes(inputStream,
                  outputStream);
  }

  private static void transferBytes(InputStream inputStream,
                                    OutputStream ouputStream) throws IOException {

    byte[] buffer = new byte[1000];
    int length = 0;

    while ((length = inputStream.read(buffer)) >= 0)
      ouputStream.write(buffer,
                        0,
                        length);
  }

}

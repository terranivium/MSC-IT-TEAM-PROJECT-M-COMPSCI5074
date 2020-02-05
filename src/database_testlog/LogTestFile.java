package database_testlog;
//Alternative solution
// in 12.0.2 works with correct output
//in 1.8 results in console
//
//"java.nio.file.AccessDeniedException: logTestFile.txt"

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;

public class LogTestFile {

  public static void main(String[] args) {

    // Convert the string to a
    // byte array.
    String s = "Hello World! ";
    byte data[] = s.getBytes();
    Path p = Paths.get("logTestFile.txt");

    try (OutputStream out = new BufferedOutputStream(
      Files.newOutputStream(p, CREATE, APPEND))) {
      out.write(data, 0, data.length);
    } catch (IOException x) {
      System.err.println(x);
    }
  }
}
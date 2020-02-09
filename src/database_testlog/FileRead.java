package database_testlog;
//alternative solution
// in 12.02 works as expected
// in 1.8 results in console:
//
//"Exception in thread "main" java.io.IOException: Access is denied
//	at java.io.WinNTFileSystem.createFileExclusively(Native Method)
//	at java.io.File.createNewFile(File.java:1012)
//	at FileRead.main(FileRead.java:8)"

import java.io.*;
public class FileRead {

   public static void main(String args[])throws IOException {
      File file = new File("FileRead.txt");
      
      // creates the file
      file.createNewFile();
      
      // creates a FileWriter Object
      FileWriter writer = new FileWriter(file); 
      
      // Writes the content to the file
      writer.write("This\n is\n an\n example\n"); 
      writer.flush();
      writer.close();

      // Creates a FileReader Object
      FileReader fr = new FileReader(file); 
      char [] a = new char[50];
      fr.read(a);   // reads the content to the array
      
      for(char c : a)
         System.out.print(c);   // prints the characters one by one
      fr.close();
   }
}
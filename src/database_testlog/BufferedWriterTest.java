package database_testlog;
//Alternative solution
// in 12.0.2 works as expected
//in 1.8 results in console
//
//"Problem opening file.
//java.nio.file.AccessDeniedException: bufferedWriterOutput.txt
//	at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:83)
//	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:97)
//	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:102)
//	at sun.nio.fs.WindowsFileSystemProvider.newByteChannel(WindowsFileSystemProvider.java:230)
//	at java.nio.file.spi.FileSystemProvider.newOutputStream(FileSystemProvider.java:434)
//	at java.nio.file.Files.newOutputStream(Files.java:216)
//	at java.nio.file.Files.newBufferedWriter(Files.java:2860)
//	at java.nio.file.Files.newBufferedWriter(Files.java:2896)
//	at BufferedWriterTest.main(BufferedWriterTest.java:20)"

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferedWriterTest {

	public BufferedWriterTest() {

	}

	public static void main(String[] args) {


		//Get the file reference
		Path path = Paths.get("bufferedWriterOutput.txt");
		 
		//Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path)) 
		{
		    writer.write("Hello World !!");
		}
		catch (IOException e)
		{
			System.out.println("Problem opening file.");
			e.printStackTrace();
		}
		
	}

}



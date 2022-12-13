package utilities.generic.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextReader {

	public static void main(String[] args) {
		String filePath = "D:\\Users\\stephenhall\\Desktop\\ION Phase 2\\Keys\\redingtc.pub";

		System.out.println(getContent(filePath));
	}

	// Read file content into string with - Files.readAllBytes(Path path)
	public static String getContent(String filePath) {
		String content = "";

		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
	
	/**
	 * Return the contents of a file broken into a list of lines
	 * 
	 * @param filePath
	 * @return
	 */
	public static ArrayList<String> getContentAsArrayList(String filePath) {
		
		// read the lines as a list of strings
		List<String> items = Arrays.asList(getContent(filePath).split("\n"));
		ArrayList<String> trimmedStrings = new ArrayList<String>();
		
		// trim all strings
		for(String s:items) {
			trimmedStrings.add(s.trim());
		}
		
		return trimmedStrings;
	}
	
	/**
	 * Write a string to a text file
	 * 
	 * @param contents
	 * @param fileDirectory - filepath e.g. "C:\\tmp\testFile.txt"
	 */
	public static void writeFile(String contents, String fileDirectory) {
		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new 
					FileWriter(fileDirectory));
			writer.write(contents);
			System.out.println();
		    writer.close();

		    System.out.println("File written: " + fileDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String cutLineFromFile(String filePath) {
		ArrayList<String> fileContents = getContentAsArrayList(filePath);
		String lineToReturn=fileContents.get(0);
		fileContents.remove(lineToReturn);
		
		String newContents="";
		for(String s:fileContents) {
			newContents = newContents + s + "\n";
		}
		
		writeFile(newContents.trim(),filePath);
		
		return lineToReturn;
	}
}

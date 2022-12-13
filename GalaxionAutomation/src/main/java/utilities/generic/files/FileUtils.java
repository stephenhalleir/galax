package utilities.generic.files;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

public class FileUtils {
	public static void copyFile(String sourceFile) {
		File from = new File(sourceFile);
		
		try {
			File to=new File(sourceFile.replace(".", "_."));
			Files.copy(from, to);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
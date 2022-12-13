package framework.maintenance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import utilities.generic.files.TextReader;

/**
 * This utility is used to clear down unwanted files from the automation framework
 * folder structure. It takes a list of target directories from a text file and empties them.
 * 
 * @author stephenhall
 *
 */

public class CleanDownFiles {

	public static void main(String[] args) {

		// read the list of directories to be cleaned from a text file
		ArrayList<String> directories = TextReader.getContentAsArrayList("config/directories_to_clean.txt");

		// for each file path in the file...
		for (String filepath : directories) {

			// if the row is not blank and the row does not start with a # (comments)
			if (!filepath.trim().equals("") && !filepath.startsWith("#")) {

				// create a file object
				File directory = new File(filepath);

				// if the path is a directory (as opposed to a file)
				if (directory.isDirectory()) {

					// clean the directory
					try {
						System.err.println("Cleaning directory: " + directory.getAbsolutePath());
						FileUtils.cleanDirectory(directory);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.err.println(directory.getAbsolutePath() + " is not a directory");
				}
			}
		}
	}
}

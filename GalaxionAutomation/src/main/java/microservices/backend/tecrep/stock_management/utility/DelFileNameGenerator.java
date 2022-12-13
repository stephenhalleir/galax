package microservices.backend.tecrep.stock_management.utility;

import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

/**
 * Generate a new unique DEL file name based on the date and ensure that the name is new
 * @author stephenhall
 *
 */
public class DelFileNameGenerator {
	
	// specify a file that contains the DELYYMMDDXXX counter where XXX is incremental
	private static String fileLocation="files\\tecrep\\stock_management\\del_filename.txt";
	
	/**
	 * Get a new unique DEL file name
	 * @return
	 */
	public static String getDelFilename() {
		
		String filename = "DEL" + Timestamp.getCurrentTimestamp("yyMMdd");
		
		// read in the last used file name from the file
		String lastFilename = TextReader.getContent(fileLocation).trim();
		
		// if the filename is one from today, the file name will be the next one in the sequence
		if(lastFilename.startsWith(filename)) {
			int latestFileNumber = Integer.parseInt(lastFilename.replace(filename, ""));
			filename = filename + String.format("%03d", latestFileNumber+1);
		}
		// or if the filename is from yesterday, generate a new filename as DELYYMMDD001
		else {
			filename=filename + "001";
		}
		
		// write the used filename back to the file
		TextReader.writeFile(filename, fileLocation);
		
		return filename;
	}
	
}

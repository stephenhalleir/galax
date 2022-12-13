package microservices.backend.eir_cdr_repository_backend.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import utilities.config.ConfigReader;
import utilities.galaxion.ftp.IONFileUploader;
import utilities.generic.files.TextReader;

public class NonUsageGenerator {

	// create a non-usage event file and send it to the directory
	public static String processNonUsageFile(String msisdn, int recordType) {

		String s = null;

		// select the template based on the file type
		switch (recordType) {
		case 1:
			s = TextReader.getContent("templates/non_usage/non_usage_type1");
			break;
		case 2:
			s = TextReader.getContent("templates/non_usage/non_usage_type2");
			break;
		case 7:
			s = TextReader.getContent("templates/non_usage/non_usage_type7");
			break;
		case 8:
			s = TextReader.getContent("templates/non_usage/non_usage_type8");
			break;
		case 9:
			s = TextReader.getContent("templates/non_usage/non_usage_type9");
			break;
		default:
			System.err.println("Error: No template found for non usage file type " + recordType);
			return null;
		} 

		// determine the file name based on the date
		String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String filename = date + "_" + System.currentTimeMillis() + "_auto_non_usage_type" + recordType + ".evt";
		System.out.println("Creating non usage event file " + filename);

		// add the header to the file
		date = simpleDateFormat.format(new Date());

		// replace the values in the template
		s = s.replace("$msisdn", msisdn);
		s = s.replace("$timestamp", date);
		s = s.replace("$transactionId", "S" + System.currentTimeMillis() + "000");

		// write the contents to the file
		String fileDirectory = System.getProperty("user.dir") + "\\files\\non_usage\\" + filename;
		System.out.println(s);
		TextReader.writeFile(s, fileDirectory);

		// send the file to the non-usage directory
		IONFileUploader.uploadFile(fileDirectory, "eir-sftp-backend", ConfigReader.getConfigValue("ion_non_usage_directory"));

		return filename;
	}
}

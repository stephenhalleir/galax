package external_systems.mobile_network.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.files.TextReader;

public class NetworkProfileExtract {

	private String msisdn;
	private String templateFileLocation = "templates\\network_extracts\\network_extract_template.html";

	// constructor - takes a MSISDN
	public NetworkProfileExtract(String msisdn) {
		this.msisdn = msisdn;
	}

	// method to build the network profile for a customer and print it to a HTML
	// file
	public String printNetworkProfile() {

		// get the current date and time
		Date date = Calendar.getInstance().getTime();

		// create the service/subscription object
		Service service = new Service(msisdn);

		// read the network profiles from the network (via MMW)
		service.loadNetworkProfile();

		// start to build the content
		String htmlTemplate = TextReader.getContent(templateFileLocation);
		htmlTemplate = htmlTemplate.replace("$msisdn", service.getMsisdn());
		htmlTemplate = htmlTemplate.replace("$billingSource", service.getSprProfile().getBillingSource());
		htmlTemplate = htmlTemplate.replace("$mmwInstance", EnvironmentDirectory.getMMWInstance());

		// write the CVM profile HTML string to the main page, if the profile exists
		if (service.getCvmProfile().isRetrievalOk()) {
			htmlTemplate = htmlTemplate.replace("$cvmProfile", service.getCvmProfile().asHTML());
		} else {
			htmlTemplate = htmlTemplate.replace("$cvmProfile", "CVM profile not found");
		}

		// write the SPR profile HTML string to the main page, if the profile exists
		if (service.getSprProfile().isRetrievalOk()) {
			htmlTemplate = htmlTemplate.replace("$sprProfile", service.getSprProfile().asHTML());
		} else {
			htmlTemplate = htmlTemplate.replace("$sprProfile", "SPR profile not found");
		}

		// write the OCS profile HTML string to the main page, if the profile exists
		if (service.getOCSProfile().isRetrievalOk()) {
			htmlTemplate = htmlTemplate.replace("$ocsProfile", service.getOCSProfile().asHTML());
		} else {
			htmlTemplate = htmlTemplate.replace("$ocsProfile", "OCS profile not found");
		}

		// write the OCS profile HTML string to the main page, if the profile exists
		if (service.getHlrfeProfile().isRetrievalOk()) {
			htmlTemplate = htmlTemplate.replace("$hlrfeProfile", service.getHlrfeProfile().asHTML());
		} else {
			htmlTemplate = htmlTemplate.replace("$hlrfeProfile", "HLR-FE profile not found");
		}

		// determine the date format for the date display on the page
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String strDate = df.format(date);
		htmlTemplate = htmlTemplate.replace("$dateTime", strDate);

		try {
			// determine the date format for the filename
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String timestamp = dateFormat.format(date);

			// write the contents to the HTML file
			return writeToFile(htmlTemplate, "NetworkLookup_" + timestamp + "_" + service.getSprProfile().getBillingSource() + "_" + service.getMsisdn08x() + ".html");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// write the output to the HTML file
	public String writeToFile(String fileContent, String fileName) throws IOException {
		String projectPath = System.getProperty("user.dir") + "\\files\\network_extracts\\";
		String tempFile = projectPath + File.separator + fileName;
		File file = new File(tempFile);

		// if file does exists, then delete and create a new file
		if (file.exists()) {
			try {
				File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();

		// return the directory where the files are created
		System.out.println("File created at " + file.getAbsolutePath());
		return file.getAbsolutePath();
	}

	public static void main(String[] args) {

		String[] msisdns = { "0854460213" };

		// create the extract object
		NetworkProfileExtract netExt;

		// for each MSISDN in the list
		for (int i = 0; i < msisdns.length; i++) {

			// create an extract object for the MSISDN
			netExt = new NetworkProfileExtract(msisdns[i]);

			// generate and print the extract
			netExt.printNetworkProfile();
		}
	}
}

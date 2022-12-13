package microservices.backend.eir_contact_management_backend.utilities.permissions_update;

import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.config.ConfigReader;
import utilities.galaxion.ftp.IONFileUploader;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class UpdatePermissionsMain {

	/**
	 * Mock up/generate a permissions update file from external system and upload to ION for processing
	 * 
	 * @param billingAccountID
	 * @param type
	 * @return the name of the file
	 */
	public static String processPermissionsUpdateFile(int billingAccountID, String type) {
		
		// read the email address for the account
		String uuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		String emailAddress = ContactManagementDAO.getEmailAddressForUUID(uuid);

		// create the file object
		PermissionsUpdate update = new PermissionsUpdate(billingAccountID, emailAddress, type);
		PermissionsUpdateFile file = new PermissionsUpdateFile();
		file.addUpdate(update);
		String timestamp = Timestamp.getCurrentTimestamp("ddMMyy_HHmmss");
		String filename = "gomo_permissions_" + timestamp + ".txt";
		String filepath = "files\\permissions_updates\\" + filename;

		// write the file to the local directory 
		TextReader.writeFile(file.toString(), filepath);

		// upload the file to the server
		IONFileUploader.uploadFile(filepath, "eir-sftp-backend", ConfigReader.getConfigValue("permissions_update_directory"));
		System.out.println("Done");
		return filepath;
	}

	public static void main(String[] args) {
		String filename=processPermissionsUpdateFile(89900060, "email");
		System.out.println(filename);
	}
}

package utilities.test.config_readers;

import java.io.IOException;

import utilities.config.ConfigReader;

public class PasswordUtil {
	private static String passwordFileLocation="test_data//passwords.config";
	private static String DEFAULT_PASSWORD="Password123";
	
	public static String getPassword(int billingAccountID) {
		String savedPassword=ConfigReader.readConfigValue(passwordFileLocation, Integer.toString(billingAccountID));
		if(savedPassword==null) {
			return DEFAULT_PASSWORD;
		}
		else {
			return savedPassword;
		}
	}
	
	/**
	 * Update a password in the passwords file
	 * 
	 * @param billingAccountID
	 * @param newPassword
	 */
	public static void updatePassword(int billingAccountID, String newPassword) {
		try {
			ConfigReader.updateConfigValue(passwordFileLocation, Integer.toString(billingAccountID), newPassword);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

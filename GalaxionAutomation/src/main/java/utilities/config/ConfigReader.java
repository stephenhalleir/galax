package utilities.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Generic config reader and updater class
 * @date 12/07/2021
 * @author stephenhall
 *
 */
public class ConfigReader {
	
	private static String defaultConfigFile="res\\config\\config.properties";
	
	public static String readConfigValue(String fileLocation, String propertyName) {
		
		File src = new File(fileLocation);
		
		try {
			FileInputStream fis = new FileInputStream(src);
			Properties properties = new Properties();
			properties.load(fis);
			
			if (properties.getProperty(propertyName).equalsIgnoreCase("null")) {
				throw new NullPointerException(propertyName + " property not found in properties file " + fileLocation);
			} else {
				return properties.getProperty(propertyName);
			}
		} catch (Exception e) {
			System.out.println("Not able to load the Config File " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Returns the config value from the Config.properties file
	 * @return
	 */
	public static String getConfigValue(String property) {
		return readConfigValue(defaultConfigFile,property);
	}
	
	public static void updateConfigValue(String fileLocation, String propertyName, String newValue) throws IOException {
		FileInputStream in = new FileInputStream(fileLocation);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(fileLocation);
		props.setProperty(propertyName, newValue);
		props.store(out, null);
		out.close();
	}
}

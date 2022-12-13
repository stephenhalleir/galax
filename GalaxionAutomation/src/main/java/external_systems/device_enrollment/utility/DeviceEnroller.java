package external_systems.device_enrollment.utility;

import external_systems.device_enrollment.api.DepAPI;
import external_systems.device_enrollment.enums.Provider;
import external_systems.device_enrollment.file.DEPFile;
import framework.test_data.generic.RandomStringGenerator;
import io.restassured.response.Response;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class DeviceEnroller {
	
	/**
	 * Generate and send a DEP file to the device enrollment platform
	 * @param accountID
	 * @param provider - Apple, Android or Samsung
	 * @return file object
	 */
	public static DEPFile processDEPFile(int accountID,Provider provider) {
		
		String deviceCode;

		// get a sample device code based on the provider specified
		if(provider==Provider.Android) {
			deviceCode="GALC1SESS";
		}
		else if(provider==Provider.Samsung) {
			deviceCode="GSAMA12SS";
		}
		else if(provider==Provider.Apple) {
			deviceCode="GAPP732RG";
		}
		else {
			deviceCode="";
		}
		
		WaitUtil.waitForSeconds(1);
		
		// generate file contents
		String uniqueID=Timestamp.getTimestamp("yyMMddhhmmss");
		deviceCode=deviceCode + RandomStringGenerator.getRandomString("01234567890", 7);
		String imei="35" + System.currentTimeMillis();
		String orderNumber="DE" + uniqueID;
		
		// create the file object
		DEPFile file = new DEPFile();
		file.setAccountID(accountID);
		file.setDeviceCode(deviceCode);
		file.setOrderNumber(orderNumber);
		file.setFutureDate("20062021");
		file.setImei(imei);
		
		System.out.println(file.toString());
		
		// write the file to the local directory
		String filename="files\\device_enrollment\\CNF" + System.currentTimeMillis() + "_" + orderNumber + "_auto_" + provider.toString() + ".csv";//CNF110520211536_DE0000030504_Steve_APPLE.csv
		file.setFilename(filename);
		TextReader.writeFile(file.toString(), filename);
		
		// construct the URL
		String url="https://dep-file-and-email.test.dep.comhar.local/upload?deviceType=$device&orderType=OR";
		url=url.replace("$device",provider.toString());
		System.out.println(url);
		
		// send the file to DEP via the API
		Response r = DepAPI.postDEPFile(url, filename);
		System.out.println(r.statusCode() + ", " + r.asString());
		
		// return the file object
		return file;
	}
	
}

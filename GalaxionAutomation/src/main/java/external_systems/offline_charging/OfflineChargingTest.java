package external_systems.offline_charging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import framework.test_data.generic.RandomStringGenerator;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class OfflineChargingTest {

	public static String getCountryForPlmn(String plmn) {
		ArrayList<String> scenarios = TextReader.getContentAsArrayList("D:\\Users\\stephenhall\\Desktop\\Offline Rating\\files\\rephse.txt");

		for (String line : scenarios) {
			String[] parts = line.split(",", -1);
			if (parts[2].equals(plmn)) {
				return parts[0];
			}
		}

		return null;
	}

	public static String getCountryForBNumber(String bNumber) {
		ArrayList<String> scenarios = TextReader.getContentAsArrayList("D:\\Users\\stephenhall\\Desktop\\Offline Rating\\files\\country_bnumber_mapping.txt");

		for (String line : scenarios) {
			String[] parts = line.split(",", -1);
			if (parts[1].equals(bNumber)) {
				return parts[0];
			}
		}

		return null;
	}

	/** 
	 * Generate a readable report
	 * @param tapFile
	 * @return
	 */
	public static String getReport(String tapFile) {
		ArrayList<String> contents = TextReader.getContentAsArrayList(tapFile);

		String header = "Type:" +  getPadding("Type:", 10) + "IMSI:" + getPadding("IMSI:", 20) + "Roaming in:" + getPadding("Roaming in:", 30) + "Calling number:"
				+ getPadding("Calling number:", 30) + "Calling country:" + getPadding("Calling country:", 30) + "PLMN:" + getPadding("PLMN:", 30) + "Duration:" + getPadding("Duration:", 10) + "Timestamp:";

		String reportContents=header;
		for (String line : contents) {
			String[] parts = line.split(",", -1);
			if (parts.length > 30) {
				String type="";
				
				if(parts[0].equals("30")) {
					type="MT";
				}
				else if(parts[0].equals("20")) {
					type="MO";
				}
				String imsi=parts[2];
				String roamingCountry=getCountryForPlmn(parts[32]);
				String callingNumber=parts[7];
				String callingCountry=getCountryForBNumber(callingNumber);
				String plmn = parts[32];
				String duration=parts[23];
				String timestamp = parts[20].substring(0,2) + "-" +   parts[20].substring(2,4) + "-" +  parts[20].substring(4,6) + " " + parts[21].substring(0,2) + ":" +   parts[21].substring(2,4) + ":" +  parts[21].substring(4,6);
				String reportLine = type + getPadding(type, 10) + imsi + getPadding(imsi, 20) + roamingCountry + getPadding(roamingCountry, 30) + callingNumber
						+ getPadding(callingNumber, 30) + callingCountry + getPadding(callingCountry, 30) + plmn
						+ getPadding(plmn, 30) + duration
						+ getPadding(duration, 10) + timestamp;
				
				reportContents = reportContents + "\n" + reportLine;
			}
		}
		return reportContents;
	}

	public static String getPadding(String s, int length) {
		String padding = "";
		for (int i = s.length(); i < length; i++) {
			padding = padding + " ";
		}
		return padding;
	}

	public static void main(String[] args) {
		
		ArrayList<String> scenarios = TextReader.getContentAsArrayList("D:\\Users\\stephenhall\\Desktop\\Offline Rating\\files\\rephse.txt");

		long startTime = System.currentTimeMillis();
		//String file = "files//tap_files//CDSWEEPIRLME07466..062612";
		String[] imsis = {"272039104417795"};
		String file = "files//tap_files//OFFLINECHARGING."+imsis[0] + "." + Timestamp.getCurrentTimestamp("yyyyMMddHHmmss");
		
		String[] bNumbers = {"12345678900","112345678900","86123456789","61123456789","91123456789","20123456789","5212345678","237123456789","222123456789","47123456789","353851234567","3312345678","3412345678"};
		ArrayList<String> lines = TextReader.getContentAsArrayList("files\\tap_files\\CDSWEEPIRLME07466.2.062613");
		String newContents = "";
		
		Date today = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(today); 
		
		String dateString = Timestamp.getCurrentTimestamp("yyMMdd");
		
		for (String line : lines) {
			String[] parts = line.split(",", -1);
			if (parts.length > 30) {
				String randomString = scenarios.get(RandomStringGenerator.getRandomInteger(0, scenarios.size() - 1));
				String[] randomStringParts = randomString.split(",");
				String imsi = RandomStringGenerator.getRandomItemFromArray(imsis);
				String bPartyNumber = RandomStringGenerator.getRandomItemFromArray(bNumbers);
				String duration = Integer.toString(RandomStringGenerator.getRandomInteger(1, 1200));
				String plmn = randomStringParts[2];
				String mscID = randomStringParts[1];
				
				c.add(Calendar.SECOND, 1);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
				String timeString = simpleDateFormat.format(c.getTime());
				line = line.replace(parts[2], imsi);
				line = line.replace(parts[16], (mscID + "0000000").substring(0,10));
				line = line.replace(parts[32], plmn);
				line = line.replace(parts[23], duration);
				line = line.replace(parts[2], imsi);
				line = line.replace(parts[7], bPartyNumber);
				line=line.replace(parts[21], timeString);
				line=line.replace(parts[20], dateString);
			}

			newContents = newContents + line + "\n";
		}
		TextReader.writeFile(newContents.trim(), file + "." + System.currentTimeMillis());

		long endTime = System.currentTimeMillis();
		System.out.println("Processing time = " + (endTime - startTime) + "ms");
	}
}

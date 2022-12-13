package external_systems.device_enrollment.file;

import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class DEPFile {

	private int accountID;
	private String deviceCode;
	private String imei;
	private String orderNumber;
	private String futureDate;
	private String filename;

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getFutureDate() {
		return futureDate;
	}

	public void setFutureDate(String futureDate) {
		this.futureDate = futureDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String toString() {
		String uniqueID = Timestamp.getTimestamp("yyMMddhhmmss");
		String template = TextReader.getContent("templates\\device_enrollment\\device_enrollment_file");

		template = template.replace("$accountID", Integer.toString(accountID));
		template = template.replace("$deviceCode", deviceCode);
		template = template.replace("$timestamp", Timestamp.getTimestamp("yyMMddhhmmss"));
		template = template.replace("$imei", imei);
		template = template.replace("$orderNumber", orderNumber);
		template = template.replace("$uniqueID", uniqueID);
		template = template.replace("$futureDate", "10062021");
		return template;
	}
}

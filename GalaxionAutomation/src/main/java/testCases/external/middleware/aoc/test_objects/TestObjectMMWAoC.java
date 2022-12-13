package testCases.external.middleware.aoc.test_objects;

public class TestObjectMMWAoC {

	private String testName;
	private int band;
	private String msisdn;
	private int offerID;
	private String notificationType;

	public TestObjectMMWAoC() {
		testName = "";
		band = 0;
		msisdn = "";
		offerID=0;
		notificationType="";
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getBand() {
		return band;
	}

	public void setBand(int band) {
		this.band = band;
	}


	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String toString() {
		return "Band " + band + ", type: " + notificationType + ", offer ID " + offerID;
	}
}

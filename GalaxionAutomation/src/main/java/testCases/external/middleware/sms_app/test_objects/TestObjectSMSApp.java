package testCases.external.middleware.sms_app.test_objects;

public class TestObjectSMSApp {

	private String testName;
	private String keyword;
	private String shortcode;
	private String msisdn;
	private boolean inScope;
	private int topupAmount;
	private boolean reprovision;
	private String responseSubstring;
	
	
	public TestObjectSMSApp() {
		testName="";
		keyword="";
		shortcode="";
		msisdn="";
		inScope=false;
		topupAmount=0;
		reprovision=false;
		responseSubstring="";
	}
	
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public boolean isInScope() {
		return inScope;
	}
	public void setInScope(boolean inScope) {
		this.inScope = inScope;
	}

	public int getTopupAmount() {
		return topupAmount;
	}

	public void setTopupAmount(int topupAmount) {
		this.topupAmount = topupAmount;
	}

	public boolean isReprovision() {
		return reprovision;
	}

	public void setReprovision(boolean reprovision) {
		this.reprovision = reprovision;
	}

	public String getResponseSubstring() {
		return responseSubstring;
	}

	public void setResponseSubstring(String responseSubstring) {
		this.responseSubstring = responseSubstring;
	}
	
	public String toString() {
		return testName;
	}
}

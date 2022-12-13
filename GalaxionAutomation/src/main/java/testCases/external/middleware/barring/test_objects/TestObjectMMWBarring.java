package testCases.external.middleware.barring.test_objects;

public class TestObjectMMWBarring {

	private String testName;
	private String barType;
	private String action;
	private String msisdn;
	private boolean inScope;

	public TestObjectMMWBarring() {
		testName = "";
		barType = "";
		action = "";
		msisdn = "";
		inScope = false;
	}
 
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getBarType() {
		return barType;
	}

	public void setBarType(String barType) {
		this.barType = barType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
	
	public String toString() {
		return testName;
	}

}

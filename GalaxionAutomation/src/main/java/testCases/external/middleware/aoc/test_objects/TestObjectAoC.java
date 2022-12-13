package testCases.external.middleware.aoc.test_objects;

public class TestObjectAoC {

	private String testName;
	private int band;
	private String action;
	private int topupAmount;
	private String msisdn;
	private String bundleName;
	private boolean inScope;
	private boolean reprovision;

	public TestObjectAoC() {
		testName = "";
		band = 0;
		action = "";
		bundleName = "";
		msisdn = "";
		inScope = false;
		topupAmount = 0;
		reprovision = false;
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

	public int getBand() {
		return band;
	}

	public void setBand(int band) {
		this.band = band;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}
	
	public String toString() {
		return testName;
	}
}

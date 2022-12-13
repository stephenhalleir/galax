package testCases.testObjects.tecrep.resources;

public class MsisdnLifecycleTransition {

	private String statusBefore;
	private String statusAfter;
	private String action;

	public MsisdnLifecycleTransition(String testString) {
		String[] testStringPieces = testString.split(",");
		statusBefore = testStringPieces[0];
		statusAfter = testStringPieces[1];
		action = testStringPieces[2];
	}

	public String getStatusBefore() {
		return statusBefore;
	}

	public void setStatusBefore(String statusBefore) {
		this.statusBefore = statusBefore;
	}

	public String getStatusAfter() {
		return statusAfter;
	}

	public void setStatusAfter(String statusAfter) {
		this.statusAfter = statusAfter;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String toString() {
		return statusBefore + " to " + statusAfter + "[" + action + "]";
	}
}

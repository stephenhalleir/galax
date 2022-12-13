package testCases.eir.b2b.responses.get_termination_request;

public class GetPendingTerminationRequestDTO {
	
	private boolean hasPendingTerminationRequest;
	private String scheduledDate;
	
	public GetPendingTerminationRequestDTO() {
		super();
	}
	public boolean isHasPendingTerminationRequest() {
		return hasPendingTerminationRequest;
	}
	public void setHasPendingTerminationRequest(boolean hasPendingTerminationRequest) {
		this.hasPendingTerminationRequest = hasPendingTerminationRequest;
	}
	public String getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
}

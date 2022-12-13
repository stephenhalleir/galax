package microservices.frontend.eir_b2b_crm_ui_frontend.dto.schedule_termination;

public class ScheduleTerminationDTO {
	private String reason;
	private String approvedBy;
	private int earlyCeaseCharge;
	private String terminationDate;

	public ScheduleTerminationDTO(String reason, String approvedBy, int earlyCeaaseCharge, String terminationDate) {
		super();
		this.reason = reason;
		this.approvedBy = approvedBy;
		this.earlyCeaseCharge = earlyCeaaseCharge;
		this.terminationDate = terminationDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public int getEarlyCeaseCharge() {
		return earlyCeaseCharge;
	}

	public void setEarlyCeaseCharge(int earlyCeaseCharge) {
		this.earlyCeaseCharge = earlyCeaseCharge;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
}

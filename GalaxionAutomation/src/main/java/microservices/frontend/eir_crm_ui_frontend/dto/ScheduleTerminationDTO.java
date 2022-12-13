package microservices.frontend.eir_crm_ui_frontend.dto;

import microservices.backend.eir_subscription_management_backend.enums.TerminationReason;

public class ScheduleTerminationDTO {

	private String primaryReason;
	private String secondaryReason;
	private String scheduledAt;
	private int earlyCeaseChargePrice;
	private String source;
	private String comment;

	public ScheduleTerminationDTO(String primaryReason, String secondaryReason, String scheduledAt, int earlyCeaseChargePrice, String source, String comment) {
		super();
		this.primaryReason = primaryReason;
		this.secondaryReason = secondaryReason;
		this.scheduledAt = scheduledAt;
		this.earlyCeaseChargePrice = earlyCeaseChargePrice;
		this.source = source;
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getEarlyCeaseChargePrice() {
		return earlyCeaseChargePrice;
	}

	public void setEarlyCeaseChargePrice(int earlyCeaseChargePrice) {
		this.earlyCeaseChargePrice = earlyCeaseChargePrice;
	}

	public String getPrimaryReason() {
		return primaryReason;
	}

	public void setPrimaryReason(String primaryReason) {
		this.primaryReason = primaryReason;
	}

	public String getScheduledAt() {
		return scheduledAt;
	}

	public void setScheduledAt(String scheduledAt) {
		this.scheduledAt = scheduledAt;
	}

	public String getSecondaryReason() {
		return secondaryReason;
	}

	public void setSecondaryReason(String secondaryReason) {
		this.secondaryReason = secondaryReason;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}

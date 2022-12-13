package microservices.backend.eir_adjustment_backend.dto;

import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import utilities.generic.time.Timestamp;

public class B2BCreateAdjustmentDTO {
	private int amount;
	private String filename;
	private String freeText;
	private boolean notificationRequired;
	private String originalPaymentTransactionUuid;
	private String reason;

	public B2BCreateAdjustmentDTO(int amount, AdjustmentReason reason) {
		this.freeText="Auto Adjustment " + Timestamp.getUniqueTimestamp();
		this.filename="auto-adjustment-len" + Timestamp.getUniqueTimestamp();
		this.originalPaymentTransactionUuid=RandomStringGenerator.getRandomUUID();
		this.amount=amount;
		this.reason = reason.toString();
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public boolean isNotificationRequired() {
		return notificationRequired;
	}

	public void setNotificationRequired(boolean notificationRequired) {
		this.notificationRequired = notificationRequired;
	}

	public String getOriginalPaymentTransactionUuid() {
		return originalPaymentTransactionUuid;
	}

	public void setOriginalPaymentTransactionUuid(String originalPaymentTransactionUuid) {
		this.originalPaymentTransactionUuid = originalPaymentTransactionUuid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}

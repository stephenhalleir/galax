package microservices.backend.eir_adjustment_backend.dto;

import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentType;

public class PaygCrmUiAdjustmentDTO {

	private int amount;
	private String freeText;
	private AdjustmentReason reason;
	private AdjustmentType adjustmentType;
	
	public PaygCrmUiAdjustmentDTO(int amount, AdjustmentReason reason, AdjustmentType adjustmentType) {
		super();
		this.amount = amount;
		this.reason = reason;
		this.adjustmentType = adjustmentType;
		this.freeText="auto_adjustment_" + System.currentTimeMillis();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public AdjustmentReason getReason() {
		return reason;
	}

	public void setReason(AdjustmentReason reason) {
		this.reason = reason;
	}

	public String getAdjustmentType() {
		return adjustmentType.toString();
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
}

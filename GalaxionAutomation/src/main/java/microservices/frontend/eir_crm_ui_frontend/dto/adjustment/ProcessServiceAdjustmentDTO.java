package microservices.frontend.eir_crm_ui_frontend.dto.adjustment;

import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentType;

public class ProcessServiceAdjustmentDTO {

	private AdjustmentReason reason;
	private int amount;
	private String freeText;
	private AdjustmentType adjustmentType;

	public ProcessServiceAdjustmentDTO(AdjustmentReason reason, AdjustmentType adjustmentType, int amount, String freeText) {
		super();
		this.reason = reason;
		this.amount = amount;
		this.freeText = freeText;
		this.adjustmentType = adjustmentType;
	}

	public AdjustmentReason getReason() {
		return reason;
	}

	public void setReason(AdjustmentReason reason) {
		this.reason = reason;
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

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
}

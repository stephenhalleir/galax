package microservices.backend.eir_adjustment_backend.dto;

import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;

public class CreateHardwareFundAdjustmentDTO {
	
	private AdjustmentReason reason;
	private int amount;
	private String freeText;
	
	public CreateHardwareFundAdjustmentDTO(int amount, AdjustmentReason reason, String freeText) {
		this.amount=amount;
		this.reason=reason;
		this.freeText=freeText;
	}
	
	public String getReason() {
		return reason.toString();
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
}

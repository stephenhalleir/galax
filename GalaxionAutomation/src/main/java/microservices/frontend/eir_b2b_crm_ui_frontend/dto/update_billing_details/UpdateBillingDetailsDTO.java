package microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_billing_details;

public class UpdateBillingDetailsDTO {

	private String billDeliveryType;
	private boolean billItemised;
	private int billingCycle;
	private boolean billAnalyserConsent;

	public UpdateBillingDetailsDTO(String billDeliveryType, boolean billItemised, int billingCycle, boolean billAnalyserConsent) {
		super();
		this.billDeliveryType = billDeliveryType;
		this.billItemised = billItemised;
		this.billingCycle = billingCycle;
		this.billAnalyserConsent = billAnalyserConsent;
	}

	public String getBillDeliveryType() {
		return billDeliveryType;
	}

	public void setBillDeliveryType(String billDeliveryType) {
		this.billDeliveryType = billDeliveryType;
	}

	public boolean isBillItemised() {
		return billItemised;
	}

	public void setBillItemised(boolean billItemised) {
		this.billItemised = billItemised;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public boolean isBillAnalyserConsent() {
		return billAnalyserConsent;
	}

	public void setBillAnalyserConsent(boolean billAnalyserConsent) {
		this.billAnalyserConsent = billAnalyserConsent;
	}
}

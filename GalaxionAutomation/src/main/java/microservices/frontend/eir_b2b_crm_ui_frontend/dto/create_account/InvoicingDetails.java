package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

public class InvoicingDetails {
	private String billCycle;
	private boolean billAnalyserConsent;
	private String billDeliveryType;
	private boolean billItemised;

	public InvoicingDetails() {
		super();
	}

	public InvoicingDetails(String billCycle, boolean billAnalyserConsent, String billDeliveryType, boolean billItemised) {
		super();
		this.billCycle = billCycle;
		this.billAnalyserConsent = billAnalyserConsent;
		this.billDeliveryType = billDeliveryType;
		this.billItemised = billItemised;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public boolean isBillAnalyserConsent() {
		return billAnalyserConsent;
	}

	public void setBillAnalyserConsent(boolean billAnalyserConsent) {
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
}

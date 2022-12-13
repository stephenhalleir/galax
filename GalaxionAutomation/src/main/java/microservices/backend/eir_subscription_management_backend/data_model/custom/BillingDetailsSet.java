package microservices.backend.eir_subscription_management_backend.data_model.custom;

public class BillingDetailsSet {

	private int billCycleId;
	private String invoiceDeliveryMethod;
	private boolean billAnalyserConsent;
	private boolean billItemised;

	public BillingDetailsSet() {
		super();
	}

	public int getBillCycleId() {
		return billCycleId;
	}

	public void setBillCycleId(int billCycleId) {
		this.billCycleId = billCycleId;
	}

	public String getInvoiceDeliveryMethod() {
		return invoiceDeliveryMethod;
	}

	public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
		this.invoiceDeliveryMethod = invoiceDeliveryMethod;
	}

	public boolean isBillAnalyserConsent() {
		return billAnalyserConsent;
	}

	public void setBillAnalyserConsent(boolean billAnalyserConsent) {
		this.billAnalyserConsent = billAnalyserConsent;
	}

	public boolean isBillItemised() {
		return billItemised;
	}

	public void setBillItemised(boolean billItemised) {
		this.billItemised = billItemised;
	}
}

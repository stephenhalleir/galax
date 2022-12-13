package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

public class PaymentDetails {

	private int paymentTerm;

	public PaymentDetails(int paymentTerm) {
		super();
		this.paymentTerm = paymentTerm;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}	
}

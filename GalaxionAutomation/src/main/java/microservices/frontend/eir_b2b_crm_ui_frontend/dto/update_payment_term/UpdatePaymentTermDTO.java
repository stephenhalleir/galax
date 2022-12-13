package microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_payment_term;

public class UpdatePaymentTermDTO {

	private int paymentTerm;

	public UpdatePaymentTermDTO(int paymentTerm) {
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

package microservices.frontend.common_ui.response_objects.payment_center;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReturnedPayer {
	private String payerContactUuid;
	private String payerRef;
	private String brand;
	private List<PaymentMethod> paymentMethods;
	private int billingAccountId;

	public ReturnedPayer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@JsonIgnore
	public PaymentMethod getPaymentMethod(int paymentMethod) {
		for(PaymentMethod method:paymentMethods) {
			if(method.getPaymentMethodId()==paymentMethod) {
				return method;
			}
		}
		return null;
	}

	public String getPayerContactUuid() {
		return payerContactUuid;
	}

	public void setPayerContactUuid(String payerContactUuid) {
		this.payerContactUuid = payerContactUuid;
	}

	public String getPayerRef() {
		return payerRef;
	}

	public void setPayerRef(String payerRef) {
		this.payerRef = payerRef;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}
	
	
}

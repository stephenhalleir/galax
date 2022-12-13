package microservices.backend.eir_elavon_facade_backend.api;

import java.util.UUID;

public class GenerateHppRequestDTO {

	private String brand;
	private int amount;
	private String orderId;
	private String paymentSource;
	private boolean anonymous;
	private String callbackUrl;
	private String payerReference;
	private String billingAccountID;
	
	public GenerateHppRequestDTO(String prospectUuid) {
		brand="GOMO";
		amount=1499;
		anonymous=false;
		orderId=UUID.randomUUID().toString();
		paymentSource="ESHOP";
		callbackUrl="https://eir-elavon-api.perf.ion.comhar.local/elavon/callback/prospect/" + prospectUuid + "/validate";
		payerReference=null;
		billingAccountID=null;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentSource() {
		return paymentSource;
	}
	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getPayerReference() {
		return payerReference;
	}
	public void setPayerReference(String payerReference) {
		this.payerReference = payerReference;
	}
	public String getBillingAccountID() {
		return billingAccountID;
	}
	public void setBillingAccountID(String billingAccountID) {
		this.billingAccountID = billingAccountID;
	}
}

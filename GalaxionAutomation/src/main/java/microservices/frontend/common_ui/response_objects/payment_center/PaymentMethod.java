package microservices.frontend.common_ui.response_objects.payment_center;

import java.sql.Date;

public class PaymentMethod {
	private String paymentMethodType;
	private int paymentMethodId;
	private String status;
	private String type;
	private String cardholderName;
	private String expirationDate;
	private String partialDigits;
	private String providerReference;
	private Date suspensionEnd;
	private boolean canceled;
	private boolean IsDefault;
	private Date canceledAt;
	
	public PaymentMethod() {
		super();
		
	}
	public String getPaymentMethodType() {
		return paymentMethodType;
	}
	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
	public int getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getPartialDigits() {
		return partialDigits;
	}
	public void setPartialDigits(String partialDigits) {
		this.partialDigits = partialDigits;
	}
	public String getProviderReference() {
		return providerReference;
	}
	public void setProviderReference(String providerReference) {
		this.providerReference = providerReference;
	}
	public Date getSuspensionEnd() {
		return suspensionEnd;
	}
	public void setSuspensionEnd(Date suspensionEnd) {
		this.suspensionEnd = suspensionEnd;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	public boolean isDefault() {
		return IsDefault;
	}
	public void setDefault(boolean isDefault) {
		IsDefault = isDefault;
	}
	public boolean isIsDefault() {
		return IsDefault;
	}
	public void setIsDefault(boolean isDefault) {
		IsDefault = isDefault;
	}
	public Date getCanceledAt() {
		return canceledAt;
	}
	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}
}

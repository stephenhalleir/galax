package microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_payment_methods;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethodDTO {
	private String paymentMethodType;
	private boolean canceled;
	private int paymentMethodId;
	private String status;
	private boolean defaultMethod;

	private String accountOwner;
	private String bankName;
	private String bic;
	private String branchName;
	private String country;
	private String iban;
	private String type;
	private String mandateSignedAt;
	private String uniqueMandateReference;

	public PaymentMethodDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
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

	public boolean isDefaultMethod() {
		return defaultMethod;
	}

	public void setDefaultMethod(boolean defaultMethod) {
		this.defaultMethod = defaultMethod;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMandateSignedAt() {
		return mandateSignedAt;
	}

	public void setMandateSignedAt(String mandateSignedAt) {
		this.mandateSignedAt = mandateSignedAt;
	}

	public String getUniqueMandateReference() {
		return uniqueMandateReference;
	}

	public void setUniqueMandateReference(String uniqueMandateReference) {
		this.uniqueMandateReference = uniqueMandateReference;
	}
}

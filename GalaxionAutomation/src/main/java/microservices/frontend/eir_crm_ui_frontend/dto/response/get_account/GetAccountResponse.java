package microservices.frontend.eir_crm_ui_frontend.dto.response.get_account;

public class GetAccountResponse {
	private String type;
	private int id;
	private String createdAt;
	private String updatedAt;
	private String status;
	private String orderReference;
	private String brand;
	private String customerType;
	private int billingAccountId;
	private String invoiceDeliveryMethod;
	private String itemisedInvoice;
	private String invoiceSuppression;
	private String billingExclusion;
	private String language;
	private String legacyReference;
	private String legacySystemName;
	private String lastBillIssuedAt;
	private String nextBillTargetAt;
	
	public GetAccountResponse() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getInvoiceDeliveryMethod() {
		return invoiceDeliveryMethod;
	}

	public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
		this.invoiceDeliveryMethod = invoiceDeliveryMethod;
	}

	public String getItemisedInvoice() {
		return itemisedInvoice;
	}

	public void setItemisedInvoice(String itemisedInvoice) {
		this.itemisedInvoice = itemisedInvoice;
	}

	public String getInvoiceSuppression() {
		return invoiceSuppression;
	}

	public void setInvoiceSuppression(String invoiceSuppression) {
		this.invoiceSuppression = invoiceSuppression;
	}

	public String getBillingExclusion() {
		return billingExclusion;
	}

	public void setBillingExclusion(String billingExclusion) {
		this.billingExclusion = billingExclusion;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLegacyReference() {
		return legacyReference;
	}

	public void setLegacyReference(String legacyReference) {
		this.legacyReference = legacyReference;
	}

	public String getLegacySystemName() {
		return legacySystemName;
	}

	public void setLegacySystemName(String legacySystemName) {
		this.legacySystemName = legacySystemName;
	}

	public String getLastBillIssuedAt() {
		return lastBillIssuedAt;
	}

	public void setLastBillIssuedAt(String lastBillIssuedAt) {
		this.lastBillIssuedAt = lastBillIssuedAt;
	}

	public String getNextBillTargetAt() {
		return nextBillTargetAt;
	}

	public void setNextBillTargetAt(String nextBillTargetAt) {
		this.nextBillTargetAt = nextBillTargetAt;
	}
}

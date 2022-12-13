package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountHistoryOld {

	private int id;
	private int rev;
	private int revtype;
	private Date creationDateTime;
	private Date startDateTime;
	private String status;
	private String brand;
	private String orderReference;
	private String ownerUuid;
	private String type;
	private int billingAccountId;
	private int billingExclusion;
	private String invoiceDeliveryMethod;
	private int invoiceSuppression;
	private int itemisedInvoice;
	private String payerUuid;
	private String paymentMethodType;
	private int billingCycleId;
	private String language;

	public AccountHistoryOld() {

	}

	public AccountHistoryOld(ResultSet rs) {
		try {
			id = rs.getInt("id");
			rev = rs.getInt("rev");
			revtype = rs.getInt("revtype");
			creationDateTime = rs.getDate("creation_date_time");
			startDateTime = rs.getDate("start_date_time");
			status = rs.getString("status");
			brand = rs.getString("brand");
			orderReference = rs.getString("order_reference");
			ownerUuid = rs.getString("owner_uuid");
			type = rs.getString("type");
			billingAccountId = rs.getInt("billing_account_id");
			billingExclusion = rs.getInt("billing_exclusion");
			invoiceDeliveryMethod = rs.getString("invoice_delivery_method");
			invoiceSuppression = rs.getInt("invoice_suppression");
			itemisedInvoice = rs.getInt("itemised_invoice");
			payerUuid = rs.getString("payer_uuid");
			paymentMethodType = rs.getString("payment_method_type");
			billingCycleId = rs.getInt("billing_cycle_id");
			language = rs.getString("language");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public int getRevtype() {
		return revtype;
	}

	public void setRevtype(int revtype) {
		this.revtype = revtype;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public String getOwnerUuid() {
		return ownerUuid;
	}

	public void setOwnerUuid(String ownerUuid) {
		this.ownerUuid = ownerUuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public int getBillingExclusion() {
		return billingExclusion;
	}

	public void setBillingExclusion(int billingExclusion) {
		this.billingExclusion = billingExclusion;
	}

	public String getInvoiceDeliveryMethod() {
		return invoiceDeliveryMethod;
	}

	public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
		this.invoiceDeliveryMethod = invoiceDeliveryMethod;
	}

	public int getInvoiceSuppression() {
		return invoiceSuppression;
	}

	public void setInvoiceSuppression(int invoiceSuppression) {
		this.invoiceSuppression = invoiceSuppression;
	}

	public int getItemisedInvoice() {
		return itemisedInvoice;
	}

	public void setItemisedInvoice(int itemisedInvoice) {
		this.itemisedInvoice = itemisedInvoice;
	}

	public String getPayerUuid() {
		return payerUuid;
	}

	public void setPayerUuid(String payerUuid) {
		this.payerUuid = payerUuid;
	}

	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public int getBillingCycleId() {
		return billingCycleId;
	}

	public void setBillingCycleId(int billingCycleId) {
		this.billingCycleId = billingCycleId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
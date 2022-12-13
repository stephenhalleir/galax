package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAccountView {

	private int billingAccountId;
	private String accountType;
	private String paymentMethodType;
	private String invoiceDeliveryMethod;
	private int itemisedInvoice;
	private int invoiceSuppression;
	private int billingExclusion;
	private String billingAccountStatus;
	private Date startTime;

	public BillingAccountView() {

	}

	public BillingAccountView(ResultSet rs) {
		try {
			billingAccountId = rs.getInt("billing_account_id");
			accountType = rs.getString("account_type");
			paymentMethodType = rs.getString("payment_method_type");
			invoiceDeliveryMethod = rs.getString("invoice_delivery_method");
			itemisedInvoice = rs.getInt("itemised_invoice");
			invoiceSuppression = rs.getInt("invoice_suppression");
			billingExclusion = rs.getInt("billing_exclusion");
			billingAccountStatus = rs.getString("billing_account_status");
			startTime = rs.getDate("start_time");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public String getInvoiceDeliveryMethod() {
		return invoiceDeliveryMethod;
	}

	public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
		this.invoiceDeliveryMethod = invoiceDeliveryMethod;
	}

	public int getItemisedInvoice() {
		return itemisedInvoice;
	}

	public void setItemisedInvoice(int itemisedInvoice) {
		this.itemisedInvoice = itemisedInvoice;
	}

	public int getInvoiceSuppression() {
		return invoiceSuppression;
	}

	public void setInvoiceSuppression(int invoiceSuppression) {
		this.invoiceSuppression = invoiceSuppression;
	}

	public int getBillingExclusion() {
		return billingExclusion;
	}

	public void setBillingExclusion(int billingExclusion) {
		this.billingExclusion = billingExclusion;
	}

	public String getBillingAccountStatus() {
		return billingAccountStatus;
	}

	public void setBillingAccountStatus(String billingAccountStatus) {
		this.billingAccountStatus = billingAccountStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
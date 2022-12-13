package pojo_repo.eir_billing_enquiry_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingEnquiry {

	private int invoiceId;
	private int billingAccountId;
	private String billingPeriod;
	private Date effectiveDateTime;
	private Date uploadDateTime;
	private int vatTotalAmount;
	private int recurringAmount;
	private int oneOffAmount;
	private int usageAmount;

	public BillingEnquiry() {

	}

	public BillingEnquiry(ResultSet rs) {
		try {
			invoiceId = rs.getInt("invoice_id");
			billingAccountId = rs.getInt("billing_account_id");
			billingPeriod = rs.getString("billing_period");
			effectiveDateTime = rs.getDate("effective_date_time");
			uploadDateTime = rs.getDate("upload_date_time");
			vatTotalAmount = rs.getInt("vat_total_amount");
			recurringAmount = rs.getInt("recurring_amount");
			oneOffAmount = rs.getInt("one_off_amount");
			usageAmount = rs.getInt("usage_amount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public Date getEffectiveDateTime() {
		return effectiveDateTime;
	}

	public void setEffectiveDateTime(Date effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public Date getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime(Date uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	public int getVatTotalAmount() {
		return vatTotalAmount;
	}

	public void setVatTotalAmount(int vatTotalAmount) {
		this.vatTotalAmount = vatTotalAmount;
	}

	public int getRecurringAmount() {
		return recurringAmount;
	}

	public void setRecurringAmount(int recurringAmount) {
		this.recurringAmount = recurringAmount;
	}

	public int getOneOffAmount() {
		return oneOffAmount;
	}

	public void setOneOffAmount(int oneOffAmount) {
		this.oneOffAmount = oneOffAmount;
	}

	public int getUsageAmount() {
		return usageAmount;
	}

	public void setUsageAmount(int usageAmount) {
		this.usageAmount = usageAmount;
	}

}
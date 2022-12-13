package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigAgreement {

	private String agreement;
	private String accountNo;
	private String billCycle;
	private String billFrequency;
	private String billingMedia;
	private String nextExpectedInvoiceDate;
	private String firstInvoiceDate;
	private String lastInvoiceDate;

	public MigAgreement() {

	}

	public MigAgreement(ResultSet rs) {
		try {
			agreement = rs.getString("agreement");
			accountNo = rs.getString("account_no");
			billCycle = rs.getString("bill_cycle");
			billFrequency = rs.getString("bill_frequency");
			billingMedia = rs.getString("billing_media");
			nextExpectedInvoiceDate = rs.getString("next_expected_invoice_date");
			firstInvoiceDate = rs.getString("first_invoice_date");
			lastInvoiceDate = rs.getString("last_invoice_date");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public String getBillFrequency() {
		return billFrequency;
	}

	public void setBillFrequency(String billFrequency) {
		this.billFrequency = billFrequency;
	}

	public String getBillingMedia() {
		return billingMedia;
	}

	public void setBillingMedia(String billingMedia) {
		this.billingMedia = billingMedia;
	}

	public String getNextExpectedInvoiceDate() {
		return nextExpectedInvoiceDate;
	}

	public void setNextExpectedInvoiceDate(String nextExpectedInvoiceDate) {
		this.nextExpectedInvoiceDate = nextExpectedInvoiceDate;
	}

	public String getFirstInvoiceDate() {
		return firstInvoiceDate;
	}

	public void setFirstInvoiceDate(String firstInvoiceDate) {
		this.firstInvoiceDate = firstInvoiceDate;
	}

	public String getLastInvoiceDate() {
		return lastInvoiceDate;
	}

	public void setLastInvoiceDate(String lastInvoiceDate) {
		this.lastInvoiceDate = lastInvoiceDate;
	}

}
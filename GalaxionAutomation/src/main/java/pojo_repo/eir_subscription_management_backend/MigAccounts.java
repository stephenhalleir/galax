package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigAccounts {

	private String accountNumber;
	private int accountType;
	private String accountTypeDesc;
	private String billingMedia;
	private String paymentType;
	private int lastInvoiceRun;
	private String nextInvoiceExpectedDate;
	private int accountInQuery;
	private int accountBalance;
	private int amountOverdue;
	private int amountInQuery;
	private int ignoreCcProcedures;
	private String accountName;
	private String billingAddressLine1;
	private String billingAddressLine2;
	private String billingAddressLine3;
	private String billingAddressLine4;
	private String billingAddressLine5;
	private String billingAddressPostcode;

	public MigAccounts() {

	}

	public MigAccounts(ResultSet rs) {
		try {
			accountNumber = rs.getString("account_number");
			accountType = rs.getInt("account_type");
			accountTypeDesc = rs.getString("account_type_desc");
			billingMedia = rs.getString("billing_media");
			paymentType = rs.getString("payment_type");
			lastInvoiceRun = rs.getInt("last_invoice_run");
			nextInvoiceExpectedDate = rs.getString("next_invoice_expected_date");
			accountInQuery = rs.getInt("account_in_query");
			accountBalance = rs.getInt("account_balance");
			amountOverdue = rs.getInt("amount_overdue");
			amountInQuery = rs.getInt("amount_in_query");
			ignoreCcProcedures = rs.getInt("ignore_cc_procedures");
			accountName = rs.getString("account_name");
			billingAddressLine1 = rs.getString("billing_address_line_1");
			billingAddressLine2 = rs.getString("billing_address_line_2");
			billingAddressLine3 = rs.getString("billing_address_line_3");
			billingAddressLine4 = rs.getString("billing_address_line_4");
			billingAddressLine5 = rs.getString("billing_address_line_5");
			billingAddressPostcode = rs.getString("billing_address_postcode");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getAccountTypeDesc() {
		return accountTypeDesc;
	}

	public void setAccountTypeDesc(String accountTypeDesc) {
		this.accountTypeDesc = accountTypeDesc;
	}

	public String getBillingMedia() {
		return billingMedia;
	}

	public void setBillingMedia(String billingMedia) {
		this.billingMedia = billingMedia;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getLastInvoiceRun() {
		return lastInvoiceRun;
	}

	public void setLastInvoiceRun(int lastInvoiceRun) {
		this.lastInvoiceRun = lastInvoiceRun;
	}

	public String getNextInvoiceExpectedDate() {
		return nextInvoiceExpectedDate;
	}

	public void setNextInvoiceExpectedDate(String nextInvoiceExpectedDate) {
		this.nextInvoiceExpectedDate = nextInvoiceExpectedDate;
	}

	public int getAccountInQuery() {
		return accountInQuery;
	}

	public void setAccountInQuery(int accountInQuery) {
		this.accountInQuery = accountInQuery;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getAmountOverdue() {
		return amountOverdue;
	}

	public void setAmountOverdue(int amountOverdue) {
		this.amountOverdue = amountOverdue;
	}

	public int getAmountInQuery() {
		return amountInQuery;
	}

	public void setAmountInQuery(int amountInQuery) {
		this.amountInQuery = amountInQuery;
	}

	public int getIgnoreCcProcedures() {
		return ignoreCcProcedures;
	}

	public void setIgnoreCcProcedures(int ignoreCcProcedures) {
		this.ignoreCcProcedures = ignoreCcProcedures;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBillingAddressLine1() {
		return billingAddressLine1;
	}

	public void setBillingAddressLine1(String billingAddressLine1) {
		this.billingAddressLine1 = billingAddressLine1;
	}

	public String getBillingAddressLine2() {
		return billingAddressLine2;
	}

	public void setBillingAddressLine2(String billingAddressLine2) {
		this.billingAddressLine2 = billingAddressLine2;
	}

	public String getBillingAddressLine3() {
		return billingAddressLine3;
	}

	public void setBillingAddressLine3(String billingAddressLine3) {
		this.billingAddressLine3 = billingAddressLine3;
	}

	public String getBillingAddressLine4() {
		return billingAddressLine4;
	}

	public void setBillingAddressLine4(String billingAddressLine4) {
		this.billingAddressLine4 = billingAddressLine4;
	}

	public String getBillingAddressLine5() {
		return billingAddressLine5;
	}

	public void setBillingAddressLine5(String billingAddressLine5) {
		this.billingAddressLine5 = billingAddressLine5;
	}

	public String getBillingAddressPostcode() {
		return billingAddressPostcode;
	}

	public void setBillingAddressPostcode(String billingAddressPostcode) {
		this.billingAddressPostcode = billingAddressPostcode;
	}

}
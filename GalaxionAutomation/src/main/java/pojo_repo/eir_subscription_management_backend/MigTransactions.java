package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigTransactions {

	private int accountNumber;
	private int invoiceNumber;
	private int amount;
	private int paymentItemNumber;
	private Date invoiceDate;
	private String transactionType;
	private String transactionTypeDesc;
	private Date transactionDate;

	public MigTransactions() {

	}

	public MigTransactions(ResultSet rs) {
		try {
			accountNumber = rs.getInt("account_number");
			invoiceNumber = rs.getInt("invoice_number");
			amount = rs.getInt("amount");
			paymentItemNumber = rs.getInt("payment_item_number");
			invoiceDate = rs.getDate("invoice_date");
			transactionType = rs.getString("transaction_type");
			transactionTypeDesc = rs.getString("transaction_type_desc");
			transactionDate = rs.getDate("transaction_date");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPaymentItemNumber() {
		return paymentItemNumber;
	}

	public void setPaymentItemNumber(int paymentItemNumber) {
		this.paymentItemNumber = paymentItemNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionTypeDesc() {
		return transactionTypeDesc;
	}

	public void setTransactionTypeDesc(String transactionTypeDesc) {
		this.transactionTypeDesc = transactionTypeDesc;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
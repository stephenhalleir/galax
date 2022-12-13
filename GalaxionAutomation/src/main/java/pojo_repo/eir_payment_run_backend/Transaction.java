package pojo_repo.eir_payment_run_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {

	private int id;
	private int amount;
	private int billingAccountId;
	private Date dueDate;
	private String errorCode;
	private String errorMessage;
	private Date lastModifiedDateTime;
	private String status;
	private Date transactionDateTime;
	private int version;

	public Transaction() {

	}

	public Transaction(ResultSet rs) {
		try {
			id = rs.getInt("id");
			amount = rs.getInt("amount");
			billingAccountId = rs.getInt("billing_account_id");
			dueDate = rs.getDate("due_date");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			lastModifiedDateTime = rs.getDate("last_modified_date_time");
			status = rs.getString("status");
			transactionDateTime = rs.getDate("transaction_date_time");
			version = rs.getInt("version");
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
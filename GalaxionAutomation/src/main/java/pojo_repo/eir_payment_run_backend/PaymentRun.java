package pojo_repo.eir_payment_run_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRun {

	private int id;
	private int accountCount;
	private Date dueDate;
	private Date endDateTime;
	private int processedAccountCount;
	private Date startDateTime;
	private String status;
	private int totalAmount;
	private String errorCode;
	private String errorMessage;

	public PaymentRun() {

	}

	public PaymentRun(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountCount = rs.getInt("account_count");
			dueDate = rs.getDate("due_date");
			endDateTime = rs.getDate("end_date_time");
			processedAccountCount = rs.getInt("processed_account_count");
			startDateTime = rs.getDate("start_date_time");
			status = rs.getString("status");
			totalAmount = rs.getInt("total_amount");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
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

	public int getAccountCount() {
		return accountCount;
	}

	public void setAccountCount(int accountCount) {
		this.accountCount = accountCount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public int getProcessedAccountCount() {
		return processedAccountCount;
	}

	public void setProcessedAccountCount(int processedAccountCount) {
		this.processedAccountCount = processedAccountCount;
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

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
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

}
package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRun {

	private int id;
	private String brand;
	private String accountType;
	private String paymentMethod;
	private String status;
	private Date dueDate;
	private String approver;
	private Date scheduleDateTime;
	private Date startDateTime;
	private Date endDateTime;
	private Date createdAt;
	private Date updatedAt;
	private String errorCode;
	private String errorMessage;
	private int originalPaymentRunId;
	private String type;

	public PaymentRun() {

	}

	public PaymentRun(ResultSet rs) {
		try {
			id = rs.getInt("id");
			brand = rs.getString("brand");
			accountType = rs.getString("account_type");
			paymentMethod = rs.getString("payment_method");
			status = rs.getString("status");
			dueDate = rs.getDate("due_date");
			approver = rs.getString("approver");
			scheduleDateTime = rs.getDate("schedule_date_time");
			startDateTime = rs.getDate("start_date_time");
			endDateTime = rs.getDate("end_date_time");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			originalPaymentRunId = rs.getInt("original_payment_run_id");
			type = rs.getString("type");
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getScheduleDateTime() {
		return scheduleDateTime;
	}

	public void setScheduleDateTime(Date scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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

	public int getOriginalPaymentRunId() {
		return originalPaymentRunId;
	}

	public void setOriginalPaymentRunId(int originalPaymentRunId) {
		this.originalPaymentRunId = originalPaymentRunId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
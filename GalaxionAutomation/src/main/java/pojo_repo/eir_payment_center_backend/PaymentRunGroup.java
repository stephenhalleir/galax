package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRunGroup {

	private int id;
	private int paymentRunId;
	private int size;
	private String status;
	private Date startDateTime;
	private Date endDateTime;
	private Date createdAt;
	private Date updatedAt;
	private String errorCode;

	public PaymentRunGroup() {

	}

	public PaymentRunGroup(ResultSet rs) {
		try {
			id = rs.getInt("id");
			paymentRunId = rs.getInt("payment_run_id");
			size = rs.getInt("size");
			status = rs.getString("status");
			startDateTime = rs.getDate("start_date_time");
			endDateTime = rs.getDate("end_date_time");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			errorCode = rs.getString("error_code");
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

	public int getPaymentRunId() {
		return paymentRunId;
	}

	public void setPaymentRunId(int paymentRunId) {
		this.paymentRunId = paymentRunId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
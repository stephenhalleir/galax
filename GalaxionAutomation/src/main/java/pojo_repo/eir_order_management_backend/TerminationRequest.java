package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TerminationRequest {

	private int orderServiceId;
	private Date scheduledDateTime;
	private String reasonCode;
	private String description;
	private String errorCode;
	private String errorMessage;

	public TerminationRequest() {

	}

	public TerminationRequest(ResultSet rs) {
		try {
			orderServiceId = rs.getInt("order_service_id");
			scheduledDateTime = rs.getDate("scheduled_date_time");
			reasonCode = rs.getString("reason_code");
			description = rs.getString("description");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(int orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

	public Date getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(Date scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
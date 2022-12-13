package microservices.backend.eir_order_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TerminationRequest {
	private int orderServiceID;
	private Date scheduledDateTime;
	private String reasonCode;
	private String description;
	private String errorCode;
	private String errorMessage;
	private String approvedBy;
	
	public TerminationRequest() {
		
	}
	
	public TerminationRequest(ResultSet rs) {
		try {
			orderServiceID=rs.getInt("order_service_id");
			scheduledDateTime=rs.getDate("scheduled_date_time");
			reasonCode=rs.getString("reason_code");
			description=rs.getString("description");
			errorCode=rs.getString("error_code");
			errorMessage=rs.getString("error_message");
			approvedBy=rs.getString("approved_by");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getOrderServiceID() {
		return orderServiceID;
	}

	public void setOrderServiceID(int orderServiceID) {
		this.orderServiceID = orderServiceID;
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

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
}

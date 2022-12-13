package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefPortRetry {

	private int id;
	private String rejectCode;
	private String errorMessage;
	private int isRetryAllowed;
	private int isNotificationRequired;

	public RefPortRetry() {

	}

	public RefPortRetry(ResultSet rs) {
		try {
			id = rs.getInt("id");
			rejectCode = rs.getString("reject_code");
			errorMessage = rs.getString("error_message");
			isRetryAllowed = rs.getInt("is_retry_allowed");
			isNotificationRequired = rs.getInt("is_notification_required");
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

	public String getRejectCode() {
		return rejectCode;
	}

	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getIsRetryAllowed() {
		return isRetryAllowed;
	}

	public void setIsRetryAllowed(int isRetryAllowed) {
		this.isRetryAllowed = isRetryAllowed;
	}

	public int getIsNotificationRequired() {
		return isNotificationRequired;
	}

	public void setIsNotificationRequired(int isNotificationRequired) {
		this.isNotificationRequired = isNotificationRequired;
	}

}
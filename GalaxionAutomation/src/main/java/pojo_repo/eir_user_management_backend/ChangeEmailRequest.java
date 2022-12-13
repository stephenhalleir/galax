package pojo_repo.eir_user_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeEmailRequest {

	private int id;
	private String userUuid;
	private String oldEmail;
	private String newEmail;
	private String token;
	private String status;
	private Date expirationDateTime;
	private Date processedDateTime;

	public ChangeEmailRequest() {

	}

	public ChangeEmailRequest(ResultSet rs) {
		try {
			id = rs.getInt("id");
			userUuid = rs.getString("user_uuid");
			oldEmail = rs.getString("old_email");
			newEmail = rs.getString("new_email");
			token = rs.getString("token");
			status = rs.getString("status");
			expirationDateTime = rs.getDate("expiration_date_time");
			processedDateTime = rs.getDate("processed_date_time");
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

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(Date expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	public Date getProcessedDateTime() {
		return processedDateTime;
	}

	public void setProcessedDateTime(Date processedDateTime) {
		this.processedDateTime = processedDateTime;
	}

}
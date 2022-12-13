package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adjustment {

	private int id;
	private int accountId;
	private String reason;
	private int amount;
	private String freeText;
	private int isNotificationRequired;

	public Adjustment() {

	}

	public Adjustment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			reason = rs.getString("reason");
			amount = rs.getInt("amount");
			freeText = rs.getString("free_text");
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public int getIsNotificationRequired() {
		return isNotificationRequired;
	}

	public void setIsNotificationRequired(int isNotificationRequired) {
		this.isNotificationRequired = isNotificationRequired;
	}

}
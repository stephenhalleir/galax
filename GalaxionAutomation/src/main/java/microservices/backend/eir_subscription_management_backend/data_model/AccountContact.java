package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountContact {

	private int id;
	private int accountId;
	private String uuid;
	private int typeId;
	private Date createdAt;
	private Date endedAt;
	private String name;
	private String description;
	
	public AccountContact() {

	}

	public AccountContact(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			uuid = rs.getString("uuid");
			typeId = rs.getInt("type_id");
			createdAt = rs.getDate("created_at");
			endedAt = rs.getDate("ended_at");
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

	public Date getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(Date endedAt) {
		this.endedAt = endedAt;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
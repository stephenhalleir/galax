package pojo_repo.eir_contact_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Email {

	private int id;
	private String contactUuid;
	private String email;
	private int validated;
	private String type;
	private Date updatedAt;

	public Email() {

	}

	public Email(ResultSet rs) {
		try {
			id = rs.getInt("id");
			contactUuid = rs.getString("contact_uuid");
			email = rs.getString("email");
			validated = rs.getInt("validated");
			type = rs.getString("type");
			updatedAt = rs.getDate("updated_at");
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

	public String getContactUuid() {
		return contactUuid;
	}

	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getValidated() {
		return validated;
	}

	public void setValidated(int validated) {
		this.validated = validated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
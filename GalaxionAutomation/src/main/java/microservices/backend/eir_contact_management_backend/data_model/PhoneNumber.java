package microservices.backend.eir_contact_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneNumber {

	private int id;
	private String contactUuid;
	private String phoneNumber;
	private String type;
	private Date updatedAt;

	public PhoneNumber() {

	}

	public PhoneNumber(ResultSet rs) {
		try {
			id = rs.getInt("id");
			contactUuid = rs.getString("contact_uuid");
			phoneNumber = rs.getString("phone_number");
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
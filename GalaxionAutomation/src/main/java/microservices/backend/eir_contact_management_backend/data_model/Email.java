package microservices.backend.eir_contact_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Email {
	private int id;
	private String contactUuid;
	private String email;

	public Email() {

	}

	public Email(ResultSet rs) {
		try {
			id = rs.getInt("id");
			contactUuid = rs.getString("contact_uuid");
			email = rs.getString("email");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

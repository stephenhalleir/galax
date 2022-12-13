package pojo_repo.eir_contact_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdentityDocument {

	private int id;
	private Date expirationDate;
	private String identifier;
	private String nationality;
	private String type;
	private String contactUuid;

	public IdentityDocument() {

	}

	public IdentityDocument(ResultSet rs) {
		try {
			id = rs.getInt("id");
			expirationDate = rs.getDate("expiration_date");
			identifier = rs.getString("identifier");
			nationality = rs.getString("nationality");
			type = rs.getString("type");
			contactUuid = rs.getString("contact_uuid");
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContactUuid() {
		return contactUuid;
	}

	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}

}
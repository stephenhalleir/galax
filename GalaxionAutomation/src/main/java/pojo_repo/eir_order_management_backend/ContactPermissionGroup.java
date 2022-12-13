package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactPermissionGroup {

	private int id;
	private int contactId;

	public ContactPermissionGroup() {

	}

	public ContactPermissionGroup(ResultSet rs) {
		try {
			id = rs.getInt("id");
			contactId = rs.getInt("contact_id");
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

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

}
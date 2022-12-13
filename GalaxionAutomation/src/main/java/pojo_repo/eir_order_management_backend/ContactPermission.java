package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactPermission {

	private int id;
	private int contactPermissionGroupId;

	public ContactPermission() {

	}

	public ContactPermission(ResultSet rs) {
		try {
			id = rs.getInt("id");
			contactPermissionGroupId = rs.getInt("contact_permission_group_id");
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

	public int getContactPermissionGroupId() {
		return contactPermissionGroupId;
	}

	public void setContactPermissionGroupId(int contactPermissionGroupId) {
		this.contactPermissionGroupId = contactPermissionGroupId;
	}

}
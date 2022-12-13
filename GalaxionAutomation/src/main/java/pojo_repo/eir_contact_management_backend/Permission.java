package pojo_repo.eir_contact_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Permission {

	private String contactUuid;
	private String permissionCode;

	public Permission() {

	}

	public Permission(ResultSet rs) {
		try {
			contactUuid = rs.getString("contact_uuid");
			permissionCode = rs.getString("permission_code");
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

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

}
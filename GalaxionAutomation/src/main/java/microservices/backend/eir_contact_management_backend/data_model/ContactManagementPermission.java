package microservices.backend.eir_contact_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactManagementPermission {
	private String permissionCode;
	private String permissionGroupCode;
	private String contactUuid;
	
	public ContactManagementPermission() {
		
	}
	
	public ContactManagementPermission(ResultSet rs) {
		try {
			permissionCode=rs.getString("permission_code");
			permissionGroupCode=rs.getString("permission_group_code");
			contactUuid=rs.getString("contact_uuid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionGroupCode() {
		return permissionGroupCode;
	}

	public void setPermissionGroupCode(String permissionGroupCode) {
		this.permissionGroupCode = permissionGroupCode;
	}
	
	public String getContactUuid() {
		return contactUuid;
	}

	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}

	public String toString() {
		return permissionCode + "\t" + permissionGroupCode;
	}
}

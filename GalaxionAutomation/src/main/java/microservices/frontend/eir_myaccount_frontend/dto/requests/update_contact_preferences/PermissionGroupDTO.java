package microservices.frontend.eir_myaccount_frontend.dto.requests.update_contact_preferences;

import java.util.ArrayList;
import java.util.List;

import microservices.backend.eir_contact_management_backend.enums.PermissionCode;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.frontend.common_ui.dto.permissions.Permission;

public class PermissionGroupDTO {

	private PermissionGroupCode permissionGroup;
	private List<Permission> permissions;
	private String name;

	public PermissionGroupDTO(PermissionGroupCode permissionGroup) {
		this.permissionGroup = permissionGroup;
		permissions = new ArrayList<Permission>();
		permissions.add(new Permission(PermissionCode.ALLOW_DIRECT_MAIL_CONTACT, "Direct mail", false));
		permissions.add(new Permission(PermissionCode.ALLOW_PHONE_CONTACT, "Phone", false));
		permissions.add(new Permission(PermissionCode.ALLOW_EMAIL_CONTACT, "Email", false));
		permissions.add(new Permission(PermissionCode.ALLOW_FOTS_CONTACT, "FOTS", false));
		permissions.add(new Permission(PermissionCode.ALLOW_SMS_CONTACT, "SMS", false));
	}

	public PermissionGroupDTO() {

	}

	public PermissionGroupCode getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroupCode permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setPermission(PermissionCode permissionName, boolean enabled) {
		for (Permission permission : permissions) {
			if (PermissionCode.valueOf(permission.getPermission()) == permissionName) {
				permission.setEnabled(enabled);
				break;
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}

package microservices.frontend.common_ui.dto;

import java.util.ArrayList;

import microservices.backend.eir_contact_management_backend.enums.PermissionCode;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.frontend.common_ui.dto.permissions.Permission;

public class PermissionGroupDTO {
	private PermissionGroupCode permissionGroup;
	private String name;
	private ArrayList<Permission> permissions;
	
	public PermissionGroupDTO(PermissionGroupCode permissionGroup) {
		this.permissionGroup=permissionGroup;
		permissions = new ArrayList<Permission>();
		permissions.add(new Permission(PermissionCode.ALLOW_EMAIL_CONTACT));
		permissions.add(new Permission(PermissionCode.ALLOW_DIRECT_MAIL_CONTACT));
		permissions.add(new Permission(PermissionCode.ALLOW_FOTS_CONTACT));
		permissions.add(new Permission(PermissionCode.ALLOW_PHONE_CONTACT));
		permissions.add(new Permission(PermissionCode.ALLOW_SMS_CONTACT));
	}

	public PermissionGroupCode getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroupCode permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public ArrayList<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

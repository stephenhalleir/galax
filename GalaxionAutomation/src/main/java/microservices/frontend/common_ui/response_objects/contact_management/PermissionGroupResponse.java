package microservices.frontend.common_ui.response_objects.contact_management;

import java.util.List;

import microservices.frontend.common_ui.dto.permissions.Permission;

public class PermissionGroupResponse {
	private String name;
	private String permissionGroup;
	private List<Permission> permissions;

	public PermissionGroupResponse() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(String permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public void setPermission(String permissionName, boolean enabled) {
		for(Permission permission:permissions) {
			if(permission.getPermission().equals(permissionName)) {
				permission.setEnabled(enabled);
				break;
			}
		}
	}

}

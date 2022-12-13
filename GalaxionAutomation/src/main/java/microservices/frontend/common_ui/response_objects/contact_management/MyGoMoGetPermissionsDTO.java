package microservices.frontend.common_ui.response_objects.contact_management;

import java.util.List;

public class MyGoMoGetPermissionsDTO {
	private boolean allowThirdParty;
	private List<PermissionGroupResponse> permissionGroupResponse;

	public MyGoMoGetPermissionsDTO() {
		super();
	}

	public boolean isAllowThirdParty() {
		return allowThirdParty;
	}

	public void setAllowThirdParty(boolean allowThirdParty) {
		this.allowThirdParty = allowThirdParty;
	}

	public List<PermissionGroupResponse> getPermissionGroupResponse() {
		return permissionGroupResponse;
	}

	public void setPermissionGroupResponse(List<PermissionGroupResponse> permissionGroupResponse) {
		this.permissionGroupResponse = permissionGroupResponse;
	}

	public void setPermission(String permissionGroup, String permissionName, boolean enabled) {
		for (PermissionGroupResponse response : permissionGroupResponse) {
			if (response.getPermissionGroup().equals(permissionGroup)) {
				response.setPermission(permissionName, enabled);
				break;
			}
		}
	}
}

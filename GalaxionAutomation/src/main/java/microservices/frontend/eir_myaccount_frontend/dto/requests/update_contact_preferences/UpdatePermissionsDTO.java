package microservices.frontend.eir_myaccount_frontend.dto.requests.update_contact_preferences;

import java.util.ArrayList;
import java.util.List;

import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;

public class UpdatePermissionsDTO {

	private List<PermissionGroupDTO> permissionGroups;
	
	public UpdatePermissionsDTO(PermissionGroupCode groupCode) {
		permissionGroups = new ArrayList<PermissionGroupDTO>();
		permissionGroups.add(new PermissionGroupDTO(groupCode));
	}

	public List<PermissionGroupDTO> getPermissionGroups() {
		return permissionGroups;
	}

	public void setPermissionGroups(List<PermissionGroupDTO> permissionGroups) {
		this.permissionGroups = permissionGroups;
	}
}

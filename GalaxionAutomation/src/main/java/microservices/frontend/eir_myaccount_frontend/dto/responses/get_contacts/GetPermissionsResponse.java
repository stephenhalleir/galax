package microservices.frontend.eir_myaccount_frontend.dto.responses.get_contacts;

import java.util.ArrayList;
import java.util.List;

import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.frontend.eir_myaccount_frontend.dto.requests.update_contact_preferences.PermissionGroupDTO;

public class GetPermissionsResponse {

	private List<PermissionGroupDTO> permissionGroupResponse;
	private boolean allowThirdParty;

	public GetPermissionsResponse() {

	}
	
	public GetPermissionsResponse(PermissionGroupCode groupCode) {
		permissionGroupResponse = new ArrayList<PermissionGroupDTO>();
		permissionGroupResponse.add(new PermissionGroupDTO(groupCode));
	}

	public List<PermissionGroupDTO> getPermissionGroupResponse() {
		return permissionGroupResponse;
	}

	public void setPermissionGroupResponse(List<PermissionGroupDTO> permissionGroupResponse) {
		this.permissionGroupResponse = permissionGroupResponse;
	}

	public boolean isAllowThirdParty() {
		return allowThirdParty;
	}

	public void setAllowThirdParty(boolean allowThirdParty) {
		this.allowThirdParty = allowThirdParty;
	}
}

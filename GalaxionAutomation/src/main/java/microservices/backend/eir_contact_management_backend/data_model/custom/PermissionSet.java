package microservices.backend.eir_contact_management_backend.data_model.custom;

import java.util.ArrayList;

import microservices.backend.eir_contact_management_backend.data_model.ContactManagementPermission;

public class PermissionSet {
	private ArrayList<ContactManagementPermission> permissions;

	
	public PermissionSet(ArrayList<ContactManagementPermission> permissions) {
		super();
		this.permissions = permissions;
	}

	public ArrayList<ContactManagementPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<ContactManagementPermission> permissions) {
		this.permissions = permissions;
	}
	
	public boolean hasPermission(String p) {
		for(ContactManagementPermission permission:permissions) {
			if(permission.getPermissionCode().toString().equals(p)) {
				return true;
			}
		}
		return false;
	}
}

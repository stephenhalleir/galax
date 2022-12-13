package microservices.frontend.common_ui.dto.permissions;

import microservices.backend.eir_contact_management_backend.enums.PermissionCode;

public class Permission {
	private String permission;
	private String name;
	private boolean enabled;
	
	public Permission(PermissionCode permission, String name, boolean enabled) {
		super();
		this.permission = permission.toString();
		this.name = name;
		this.enabled = enabled;
	}

	public Permission() {
		super();
	}

	public Permission(PermissionCode code) {
		this.enabled = false;
		this.permission = code.toString();
		System.err.println("Code = " + code);
		switch (code) {
		case ALLOW_EMAIL_CONTACT:
			name = "Email";
			break;
		case ALLOW_DIRECT_MAIL_CONTACT:
			name = "Direct mail";
			break;
		case ALLOW_FOTS_CONTACT:
			name = "FOTS";
			break;
		case ALLOW_PHONE_CONTACT:
			name = "Phone";
			break;
		case ALLOW_SMS_CONTACT:
			name = "SMS";
			break;
		}
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}

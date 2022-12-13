package microservices.backend.eir_contact_management_backend.utilities.permissions_update;

public class PermissionsUpdate {
	
	private int billingAccountID;
	private String emailAddress;
	private String type;
	
	public PermissionsUpdate() {
		
	}
	
	public PermissionsUpdate(int billingAccountID, String emailAddress, String type) {
		super();
		this.billingAccountID = billingAccountID;
		this.emailAddress = emailAddress;
		this.type = type;
	}



	public int getBillingAccountID() {
		return billingAccountID;
	}
	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return billingAccountID + "," + emailAddress + "," + type;
	}
}

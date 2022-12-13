package microservices.frontend.eir_crm_ui_frontend.dto.contact_management;

public class AddAuthorizedUserDTO {
	
	private String firstName;
	private String lastName;
	private int accountId;
	private String contactType;

	public AddAuthorizedUserDTO(String firstName, String lastName, int accountId, String contactType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountId = accountId;
		this.contactType = contactType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
}

package microservices.frontend.eir_crm_ui_frontend.dto.contact_management;

public class EditAuthorizedUserDTO {
	private String firstName;
	private String lastName;

	public EditAuthorizedUserDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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
}

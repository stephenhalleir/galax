package microservices.frontend.eir_crm_ui_frontend.dto.requests.update_contact;

import java.util.ArrayList;
import java.util.List;

public class UpdateContactDTO {
	
	private String firstName;
	private String lastName;
	private String birthDate;

	
	public UpdateContactDTO(String firstName, String lastName, String birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
}

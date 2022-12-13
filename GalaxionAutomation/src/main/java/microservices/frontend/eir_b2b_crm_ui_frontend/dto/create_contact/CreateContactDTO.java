package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_contact;

import json.common.AddressDTO;
import microservices.backend.eir_contact_management_backend.enums.ContactType;

public class CreateContactDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private ContactType type;
	private AddressDTO address;
	
	public CreateContactDTO(String firstName, String lastName, String email, String phoneNumber, ContactType type, AddressDTO address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ContactType getType() {
		return type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}
}

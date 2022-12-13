package json.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Contact {

	private String firstName;
	private String lastName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;
	
	private String contactPhoneNumber;

	public Contact(String firstName, String lastName, String email, String contactPhoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactPhoneNumber = contactPhoneNumber;
	}
	
	public Contact(String firstName, String lastName, String contactPhoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactPhoneNumber = contactPhoneNumber;
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

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}
}

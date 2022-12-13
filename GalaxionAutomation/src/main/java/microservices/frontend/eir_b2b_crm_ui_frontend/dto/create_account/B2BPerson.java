package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

public class B2BPerson {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private B2BAddress address;
	
	public B2BPerson(String firstName, String lastName, String email, String phoneNumber, B2BAddress address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
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
	public B2BAddress getAddress() {
		return address;
	}
	public void setAddress(B2BAddress address) {
		this.address = address;
	}
}

package microservices.frontend.eir_crm_ui_frontend.dto.requests.update_contact;

public class PhoneNumber {
	
	private String phoneNumber;
	
	public PhoneNumber(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}

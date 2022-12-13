package microservices.frontend.eir_crm_ui_frontend.dto.requests.update_contact_number;

public class UpdateContactNumberDTO {

	private String phoneNumber;
	private String type;
	
	public UpdateContactNumberDTO(String phoneNumber, String type) {
		super();
		this.phoneNumber = phoneNumber;
		this.type = type;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
}

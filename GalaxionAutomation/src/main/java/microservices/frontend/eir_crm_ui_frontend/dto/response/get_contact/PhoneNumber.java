package microservices.frontend.eir_crm_ui_frontend.dto.response.get_contact;

public class PhoneNumber {
	private int id;
	private String phoneNumber;
	private String type;

	public PhoneNumber() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

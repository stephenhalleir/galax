package microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_account;

public class CompanyDTO {
	private String name;
	private String registrationNumber;
	private int id;

	public CompanyDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

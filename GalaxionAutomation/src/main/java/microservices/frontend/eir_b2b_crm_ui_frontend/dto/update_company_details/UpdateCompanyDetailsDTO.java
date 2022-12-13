package microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_company_details;

public class UpdateCompanyDetailsDTO {

	private String name;
	private String registrationNumber;

	public UpdateCompanyDetailsDTO(String name, String registrationNumber) {
		super();
		this.name = name;
		this.registrationNumber = registrationNumber;
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
}

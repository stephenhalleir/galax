package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

public class CompanyDetails {
	private String name;
	
	public CompanyDetails(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

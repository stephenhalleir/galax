package microservices.frontend.eir_crm_ui_frontend.dto.requests.update_email;

import microservices.backend.eir_catalog_core_backend.enums.Brand;

public class UpdateEmailAddressDTO {

	private String email;
	private Brand brand;
	
	public UpdateEmailAddressDTO(String email,Brand brand) {
		this.email=email;
		this.brand=brand;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}	
}

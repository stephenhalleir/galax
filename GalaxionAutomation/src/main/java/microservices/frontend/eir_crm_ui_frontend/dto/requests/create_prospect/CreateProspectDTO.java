package microservices.frontend.eir_crm_ui_frontend.dto.requests.create_prospect;

import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;

public class CreateProspectDTO {

	private Brand brand;
	private OfferType offerType;

	public CreateProspectDTO() {
		super();
	}

	public CreateProspectDTO(Brand brand, OfferType offerType) {
		super();
		this.brand = brand;
		this.offerType = offerType;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}
}

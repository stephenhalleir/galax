package microservices.frontend.eir_crm_ui_frontend.dto.acquisitions;

import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;

public class CreateProspectDTO {

	private Brand brand;
	private ChannelCode channelCode;
	private OfferType offerType;

	public CreateProspectDTO(Brand brand, ChannelCode channelCode, OfferType offerType) {
		super();
		this.brand = brand;
		this.channelCode = channelCode;
		this.offerType = offerType;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ChannelCode getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(ChannelCode channelCode) {
		this.channelCode = channelCode;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}
}

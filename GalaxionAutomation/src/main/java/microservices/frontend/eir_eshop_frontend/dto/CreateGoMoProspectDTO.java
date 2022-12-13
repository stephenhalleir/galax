package microservices.frontend.eir_eshop_frontend.dto;

import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_order_management_backend.enums.ChannelType;

public class CreateGoMoProspectDTO {
	private Brand brand;
	private ChannelType channelType;
	private OfferType offerType;
	
	public CreateGoMoProspectDTO(Brand brand, ChannelType channelType, OfferType offerType) {
		super();
		this.brand = brand;
		this.channelType = channelType;
		this.offerType = offerType;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public ChannelType getChannelType() {
		return channelType;
	}
	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}
	public OfferType getOfferType() {
		return offerType;
	}
	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}
	
	
}

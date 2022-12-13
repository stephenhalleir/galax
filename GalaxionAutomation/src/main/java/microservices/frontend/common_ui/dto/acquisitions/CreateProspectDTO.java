package microservices.frontend.common_ui.dto.acquisitions;

import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_order_management_backend.enums.ChannelType;

/**
 * Prospect DTO used by PAYG CRM UI API
 * 
 * @author stephenhall
 *
 */

public class CreateProspectDTO {

	private String brand;
	private String channelCode;
	private String offerType;

	public CreateProspectDTO() {
		super();
	}

	public CreateProspectDTO(Brand brand, ChannelCode channelCode, OfferType offerType) {
		super();
		this.brand = brand.toString();
		this.channelCode = channelCode.toString();
		this.offerType = offerType.toString();
	}
	
	// Use this constructor for GoMo eShop as ChannelCode will not be relevant
	public CreateProspectDTO(Brand brand, ChannelType channelType, OfferType offerType) {
		super();
		this.brand = brand.toString();
		this.channelCode = null;
		this.offerType = offerType.toString();
	}
	
	public CreateProspectDTO(String brand, String channelCode, String channelType, String offerType) {
		super();
		this.brand = brand;
		this.channelCode = channelCode;
		this.offerType = offerType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	
	
}

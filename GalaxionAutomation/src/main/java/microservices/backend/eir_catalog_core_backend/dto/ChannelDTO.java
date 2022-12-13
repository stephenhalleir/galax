package microservices.backend.eir_catalog_core_backend.dto;

import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_order_management_backend.enums.ChannelName;
import microservices.backend.eir_order_management_backend.enums.ChannelType;

public class ChannelDTO {
	private Brand brand;
	private ChannelName channelName;
	private ChannelType channelType;

	public ChannelDTO() {
		brand = Brand.EIR;
		channelName = ChannelName.TELESALES;
		channelType = ChannelType.INDIRECT;
	}

	public ChannelDTO(Brand brand, ChannelName channelName, ChannelType channelType) {
		this.brand = brand;
		this.channelName = channelName;
		this.channelType = channelType;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ChannelName getChannelName() {
		return channelName;
	}

	public void setChannelName(ChannelName channelName) {
		this.channelName = channelName;
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}	
}

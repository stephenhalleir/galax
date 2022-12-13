package microservices.backend.eir_prospect_backend.objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_prospect_backend.enums.ProspectType;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class Prospect {

	private String brand;
	private String offerType;
	private String customerUuid;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String channelCode;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String uuid;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String orderNumber;

	public Prospect() {
		channelCode = null;
		offerType = null;
		brand = null;
	}

	public Prospect(String brand, String channelCode, String offerType) {
		this.brand = brand; 
		this.channelCode = channelCode;
		this.offerType = offerType;
	}

	// constructor using enums
	public Prospect(Brand brand, ChannelCode channelCode, OfferType offerType) {
		this.brand = brand.toString(); 
		this.channelCode = channelCode.toString();
		this.offerType = offerType.toString();
	}

	public Prospect(ProspectType prospectType) {
		if(prospectType==ProspectType.GOMO) {
			brand="GOMO";
			offerType="POSTPAY";
		}
		else if(prospectType==ProspectType.EIR_PREPAY_TELESALES) {
			brand="EIR";
			channelCode="TELESALES";
			offerType="PREPAY";
		}
		else if(prospectType==ProspectType.EIR_PREPAY_RETAIL){
			brand="EIR";
			channelCode="RETAIL";
			offerType="PREPAY";
		}
		else if(prospectType==ProspectType.EIR_POSTPAY_RETAIL){
			brand="EIR";
			channelCode="RETAIL";
			offerType="POSTPAY";
		}
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String toString() {
		return brand + ", " + channelCode + ", " + offerType + ", " + uuid;
	}
	
	public String toJSONString() {
		return PojoToJsonConverter.getJSON(this);
	}
}

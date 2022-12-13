package microservices.backend.eir_otp_verification_backend.dto;

import microservices.backend.eir_catalog_core_backend.enums.Brand;

public class TriggerOtpDTO {
	
	private String msisdn;
	private String brand;
	
	public TriggerOtpDTO(String msisdn, Brand brand) {
		this.msisdn=msisdn;
		this.brand=brand.toString();
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}

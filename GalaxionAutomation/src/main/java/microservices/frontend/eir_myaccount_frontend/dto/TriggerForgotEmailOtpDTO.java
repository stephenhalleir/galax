package microservices.frontend.eir_myaccount_frontend.dto;

import microservices.backend.eir_catalog_core_backend.enums.Brand;

public class TriggerForgotEmailOtpDTO {

	private String msisdn;
	private Brand brand;
	
	public TriggerForgotEmailOtpDTO(String msisdn) {
		this.msisdn=msisdn;
		this.brand=Brand.GOMO;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}	
}

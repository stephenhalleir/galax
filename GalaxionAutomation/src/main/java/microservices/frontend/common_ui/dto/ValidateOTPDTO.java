package microservices.frontend.common_ui.dto;

import microservices.backend.eir_catalog_core_backend.enums.Brand;

public class ValidateOTPDTO {
	private Brand brand;
	private String otpCode;
	private String otpUuid;
	
	
	public ValidateOTPDTO(String otpCode, String otpUuid) {
		super();
		this.otpCode = otpCode;
		this.otpUuid = otpUuid;
		this.brand=Brand.GOMO;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getOtpCode() {
		return otpCode;
	}
	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
	public String getOtpUuid() {
		return otpUuid;
	}
	public void setOtpUuid(String otpUuid) {
		this.otpUuid = otpUuid;
	}
}

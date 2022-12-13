package microservices.frontend.eir_myaccount_frontend.dto.requests.update_address;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UpdateAddressDTO {
	private String addressLine1;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String addressLine2;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String addressLine3;
	private String town;
	private String county;
	private String code;
	private String type;
	
	public UpdateAddressDTO(String addressLine1, String addressLine2, String addressLine3, String town, String county, String code, String type) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.town = town;
		this.county = county;
		this.code = code;
		this.type = type;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

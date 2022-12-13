package json.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AddressDTO {
	private String addressLine1;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String addressLine2;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String addressLine3;

	private String town;
	private String county;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String city;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String country;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String eircode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String code;

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

	public String getEircode() {
		return eircode;
	}

	public void setEircode(String eircode) {
		this.eircode = eircode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}	
}

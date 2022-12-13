package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

public class B2BAddress {
	private String code;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String county;
	private String country;
	
	public B2BAddress(String code, String addressLine1, String addressLine2, String addressLine3, String city, String county, String country) {
		super();
		this.code = code;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.county = county;
		this.country = country;
	}
	
	public B2BAddress() {
		super();
		this.code = "D24YX53";
		this.addressLine1 = "UNIT 4050";
		this.addressLine2 = "KINGSWOOD AVENUE";
		this.addressLine3 = "CITYWEST BUSINESS CAMPUS";
		this.city = "DUBLIN 24";
		this.county = "CO_DUBLIN";
		this.country = "Ireland";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}

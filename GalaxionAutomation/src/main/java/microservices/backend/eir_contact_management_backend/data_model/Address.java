package microservices.backend.eir_contact_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import microservices.backend.eir_contact_management_backend.enums.AddressType;

public class Address {
	private int id;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String town;
	private String county;
	private String eircode;
	private AddressType addressType;
	
	public Address(ResultSet rs) {
		try {
			id=rs.getInt("id");
			addressLine1=rs.getString("address_line1");
			addressLine2=rs.getString("address_line2");
			addressLine3=rs.getString("address_line3");
			town=rs.getString("town");
			county=rs.getString("county");
			eircode=rs.getString("code");
			addressType=AddressType.valueOf(rs.getString("type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public Address() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEircode() {
		return eircode;
	}
	public void setEircode(String eircode) {
		this.eircode = eircode;
	}
	public AddressType getAddressType() {
		return addressType;
	}
	
	@JsonIgnore
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	@JsonIgnore
	public String getAddressLine2Str() {
		if(addressLine2==null) {
			return "";
		}
		else {
			return addressLine2;
		}
	}
	
	@JsonIgnore
	public String getAddressLine3Str() {
		if(addressLine3==null) {
			return "";
		}
		else {
			return addressLine3;
		}
	}
}

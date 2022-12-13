package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {

	private int id;
	private int contactAddressId;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String town;
	private String county;
	private String country;

	public Address() {

	}

	public Address(ResultSet rs) {
		try {
			id = rs.getInt("id");
			contactAddressId = rs.getInt("contact_address_id");
			addressLine1 = rs.getString("address_line1");
			addressLine2 = rs.getString("address_line2");
			addressLine3 = rs.getString("address_line3");
			town = rs.getString("town");
			county = rs.getString("county");
			country = rs.getString("country");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContactAddressId() {
		return contactAddressId;
	}

	public void setContactAddressId(int contactAddressId) {
		this.contactAddressId = contactAddressId;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
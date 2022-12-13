package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDetails {

	private String changeOfferUuid;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String town;
	private String county;
	private String eircode;

	public CustomerDetails() {

	}

	public CustomerDetails(ResultSet rs) {
		try {
			changeOfferUuid = rs.getString("change_offer_uuid");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			email = rs.getString("email");
			phoneNumber = rs.getString("phone_number");
			addressLine1 = rs.getString("address_line_1");
			addressLine2 = rs.getString("address_line_2");
			addressLine3 = rs.getString("address_line_3");
			town = rs.getString("town");
			county = rs.getString("county");
			eircode = rs.getString("eircode");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getChangeOfferUuid() {
		return changeOfferUuid;
	}

	public void setChangeOfferUuid(String changeOfferUuid) {
		this.changeOfferUuid = changeOfferUuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

}
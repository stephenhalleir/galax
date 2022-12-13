package microservices.backend.eir_contact_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import microservices.backend.eir_contact_management_backend.enums.AddressType;

public class Contact {
	private String uuid;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String emailAddress;
	private String phoneNumber;
	private String title;
	private String nationality;
	private int vip;
	private int allowThirdParties;
	private ArrayList<Address> addresses;
	
	public Contact(ResultSet rs) {
		try {
			uuid=rs.getString("uuid");
			firstName=rs.getString("first_name");
			lastName=rs.getString("last_name");
			birthDate=rs.getDate("birth_date");
			allowThirdParties=rs.getInt("allow_third_parties");
			title=rs.getString("title");
			vip = rs.getInt("is_vip");
			nationality=rs.getString("nationality");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public ArrayList<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public Date getBirthDate() {
		return birthDate;
	}
	
	public String getBirthDate(String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(birthDate);
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Return an address of a particular type - i.e. BILLING or DELIVERY
	 * @param addressType
	 * @return address object
	 */
	public Address getAddress(AddressType addressType) {
		for(Address address:addresses) {
			if(address.getAddressType()==addressType) {
				return address;
			}
		}
		return null;
	}
	
	public Address getBillingAddress() {
		return getAddress(AddressType.BILLING);
	}
	
	public Address getDeliveryAddress() {
		return getAddress(AddressType.DELIVERY);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAllowThirdParties() {
		return allowThirdParties;
	}

	public void setAllowThirdParties(int allowThirdParties) {
		this.allowThirdParties = allowThirdParties;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}
}

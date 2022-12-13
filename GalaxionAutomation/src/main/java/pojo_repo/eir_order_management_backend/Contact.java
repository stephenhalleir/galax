package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact {

	private int id;
	private String email;
	private String contactPhoneNumber;
	private String contactMobileNumber;
	private String title;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String position;
	private int deliveryAddressId;
	private int billingAddressId;

	public Contact() {

	}

	public Contact(ResultSet rs) {
		try {
			id = rs.getInt("id");
			email = rs.getString("email");
			contactPhoneNumber = rs.getString("contact_phone_number");
			contactMobileNumber = rs.getString("contact_mobile_number");
			title = rs.getString("title");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			birthDate = rs.getDate("birth_date");
			position = rs.getString("position");
			deliveryAddressId = rs.getInt("delivery_address_id");
			billingAddressId = rs.getInt("billing_address_id");
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getContactMobileNumber() {
		return contactMobileNumber;
	}

	public void setContactMobileNumber(String contactMobileNumber) {
		this.contactMobileNumber = contactMobileNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(int deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public int getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(int billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

}
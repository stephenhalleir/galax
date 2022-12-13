package framework.test_data.generic;

import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;

public class Person {

	private String firstName;
	private String lastName;
	private String contactPhoneNumber;
	private String mobilePhoneNumber;
	private AddressFinderAddress billingAddress;
	private AddressFinderAddress deliveryAddress;
	private String emailAddress;
	private String dateOfBirth;
	private String creditCardNumber;

	public Person() {

	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
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

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getEmailPrefix() {
		return (firstName + "." + lastName).toLowerCase();
	}

	public AddressFinderAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressFinderAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public AddressFinderAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressFinderAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

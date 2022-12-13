package microservices.frontend.eir_myaccount_frontend.dto.requests.order_replacement_sim;

import json.common.AddressDTO;

public class GoMoOrderReplacementSimDTO {

	private AddressDTO address;
	private boolean barServices;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private int serviceId;

	public GoMoOrderReplacementSimDTO() {
		address = new AddressDTO();
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public boolean isBarServices() {
		return barServices;
	}

	public void setBarServices(boolean barServices) {
		this.barServices = barServices;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}

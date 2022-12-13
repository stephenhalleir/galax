package microservices.frontend.eir_crm_ui_frontend.dto;

import json.common.AddressDTO;
import json.common.Contact;
import microservices.backend.eir_catalog_core_backend.dto.ChannelDTO;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_order_management_backend.enums.ChannelName;
import microservices.backend.eir_order_management_backend.enums.ChannelType;

public class PAYGOrderReplacementSimDTO {
	private AddressDTO address;
	private boolean barServices;
	private int serviceId;
	private boolean waived;
	private String email;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	
	
	
	public PAYGOrderReplacementSimDTO() {
		super();
		address = new AddressDTO();
		this.waived=false;
		this.barServices=false;
	}
	public PAYGOrderReplacementSimDTO(AddressDTO address, boolean barServices, int serviceId, boolean waived, String email, String phoneNumber,
			String firstName, String lastName) {
		super();
		this.address = address;
		this.barServices = barServices;
		this.serviceId = serviceId;
		this.waived = waived;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
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
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public boolean isWaived() {
		return waived;
	}
	public void setWaived(boolean waived) {
		this.waived = waived;
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
}

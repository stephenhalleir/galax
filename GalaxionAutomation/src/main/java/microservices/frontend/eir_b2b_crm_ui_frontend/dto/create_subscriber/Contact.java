package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import json.common.AddressDTO;

public class Contact {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String mobileNumber;
	private String position;
	private AddressDTO address;
	private String uuid;
	private List<ContactType> types;
	
	public Contact() {
		firstName="Steve";
		lastName="Test";
		email="steve_auto_" + System.currentTimeMillis() + "@eirb2b.ie";
		phoneNumber="0872897487";
		address=new AddressDTO();
		address.setAddressLine1("12 Test Road");
		address.setAddressLine2("Testville");
		address.setAddressLine3("Test town");
		address.setCity("WATERFORD");
		address.setCounty("CO_KILKENNY");
		address.setCountry("Ireland");
		address.setCode("X91Y40X");
		position="Owner";
		types=new ArrayList<ContactType>();
		types.add(new ContactType("Billing Contact","PAYER"));
		types.add(new ContactType("Primary Contact","OWNER"));
		uuid="8fe4b29e-1106-49ef-93e6-9be78e7693b6";
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

	public String getMobileNumber() {
		return phoneNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<ContactType> getTypes() {
		return types;
	}

	public void setTypes(List<ContactType> types) {
		this.types = types;
	}
}

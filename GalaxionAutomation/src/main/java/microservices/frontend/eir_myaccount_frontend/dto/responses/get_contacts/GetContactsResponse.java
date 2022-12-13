package microservices.frontend.eir_myaccount_frontend.dto.responses.get_contacts;

import java.util.List;

import microservices.frontend.common_ui.response_objects.contact_management.ReturnedContactAddress;

public class GetContactsResponse {

	private String uuid;
	private String firstName;
	private String lastName;
	private String title;
	private String birthDate;
	private String nationality;
	private String companyPosition;
	private boolean allowThirdParties;
	private String gdprValidatedAt;
	private String gdprLanguage;
	private boolean vip;
	private String isLastCheckedAt;
	private List<Email> emails;
	private List<PhoneNumber> phoneNumbers;
	private List<ReturnedContactAddress> addresses;
	private List<String> identityDocuments;
	private String communicationPreference;
	private String createdAt;
	private String idLastCheckedAt;
	private GetPermissionsResponse permissions;

	public GetContactsResponse() {
		super();
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCompanyPosition() {
		return companyPosition;
	}

	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}

	public boolean isAllowThirdParties() {
		return allowThirdParties;
	}

	public void setAllowThirdParties(boolean allowThirdParties) {
		this.allowThirdParties = allowThirdParties;
	}

	public String getGdprValidatedAt() {
		return gdprValidatedAt;
	}

	public void setGdprValidatedAt(String gdprValidatedAt) {
		this.gdprValidatedAt = gdprValidatedAt;
	}

	public String getGdprLanguage() {
		return gdprLanguage;
	}

	public void setGdprLanguage(String gdprLanguage) {
		this.gdprLanguage = gdprLanguage;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public String getIsLastCheckedAt() {
		return isLastCheckedAt;
	}

	public void setIsLastCheckedAt(String isLastCheckedAt) {
		this.isLastCheckedAt = isLastCheckedAt;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<ReturnedContactAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<ReturnedContactAddress> addresses) {
		this.addresses = addresses;
	}

	public List<String> getIdentityDocuments() {
		return identityDocuments;
	}

	public void setIdentityDocuments(List<String> identityDocuments) {
		this.identityDocuments = identityDocuments;
	}

	public String getCommunicationPreference() {
		return communicationPreference;
	}

	public void setCommunicationPreference(String communicationPreference) {
		this.communicationPreference = communicationPreference;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getIdLastCheckedAt() {
		return idLastCheckedAt;
	}

	public void setIdLastCheckedAt(String idLastCheckedAt) {
		this.idLastCheckedAt = idLastCheckedAt;
	}

	public GetPermissionsResponse getPermissions() {
		return permissions;
	}

	public void setPermissions(GetPermissionsResponse permissions) {
		this.permissions = permissions;
	}
}

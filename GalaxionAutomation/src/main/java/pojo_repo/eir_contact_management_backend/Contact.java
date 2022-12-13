package pojo_repo.eir_contact_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact {

	private String uuid;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int communicationPreferenceId;
	private String title;
	private String nationality;
	private Date gdprValidationDate;
	private String gdprLanguage;
	private Date createdAt;
	private Date updatedAt;
	private int allowThirdParties;
	private String companyPosition;
	private int isVip;

	public Contact() {

	}

	public Contact(ResultSet rs) {
		try {
			uuid = rs.getString("uuid");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			birthDate = rs.getDate("birth_date");
			communicationPreferenceId = rs.getInt("communication_preference_id");
			title = rs.getString("title");
			nationality = rs.getString("nationality");
			gdprValidationDate = rs.getDate("gdpr_validation_date");
			gdprLanguage = rs.getString("gdpr_language");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			allowThirdParties = rs.getInt("allow_third_parties");
			companyPosition = rs.getString("company_position");
			isVip = rs.getInt("is_vip");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getCommunicationPreferenceId() {
		return communicationPreferenceId;
	}

	public void setCommunicationPreferenceId(int communicationPreferenceId) {
		this.communicationPreferenceId = communicationPreferenceId;
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

	public Date getGdprValidationDate() {
		return gdprValidationDate;
	}

	public void setGdprValidationDate(Date gdprValidationDate) {
		this.gdprValidationDate = gdprValidationDate;
	}

	public String getGdprLanguage() {
		return gdprLanguage;
	}

	public void setGdprLanguage(String gdprLanguage) {
		this.gdprLanguage = gdprLanguage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getAllowThirdParties() {
		return allowThirdParties;
	}

	public void setAllowThirdParties(int allowThirdParties) {
		this.allowThirdParties = allowThirdParties;
	}

	public String getCompanyPosition() {
		return companyPosition;
	}

	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

}
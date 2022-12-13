package microservices.frontend.eir_crm_ui_frontend.dto.response.get_credit_score_response;

public class GetCreditScoreResponse {
	
	private String creditScore;
	private String occupation;
	private String residentialStatus;
	private String prospectUuid;
	private String contactUuid;
	private String createdAt;
	private String updatedAt;
	
	public GetCreditScoreResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getResidentialStatus() {
		return residentialStatus;
	}
	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}
	public String getProspectUuid() {
		return prospectUuid;
	}
	public void setProspectUuid(String prospectUuid) {
		this.prospectUuid = prospectUuid;
	}
	public String getContactUuid() {
		return contactUuid;
	}
	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}

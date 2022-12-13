package microservices.frontend.eir_myaccount_frontend.dto;

public class UpdateExpiryDateDTO {

	private String expirationDate;
	
	public UpdateExpiryDateDTO(String expirationDate) {
		super();
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}	
}

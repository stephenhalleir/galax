package microservices.frontend.eir_myaccount_frontend.dto;

public class UpdateEmailDTO {

	private String email;

	public UpdateEmailDTO(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}

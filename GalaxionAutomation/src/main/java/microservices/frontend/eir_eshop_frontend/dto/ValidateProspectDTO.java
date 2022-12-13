package microservices.frontend.eir_eshop_frontend.dto;

public class ValidateProspectDTO {

	private String hppResponse;
	
	public ValidateProspectDTO(String hppResponse) {
		super();
		this.hppResponse = hppResponse;
	}

	public String getHppResponse() {
		return hppResponse;
	}

	public void setHppResponse(String hppResponse) {
		this.hppResponse = hppResponse;
	}
	
}

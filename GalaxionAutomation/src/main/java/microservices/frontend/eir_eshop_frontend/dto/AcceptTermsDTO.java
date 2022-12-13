package microservices.frontend.eir_eshop_frontend.dto;

public class AcceptTermsDTO {

	private boolean hasAgreedTermsAndConditions;

	public AcceptTermsDTO() {
		hasAgreedTermsAndConditions=true;
	}
	
	public boolean isHasAgreedTermsAndConditions() {
		return hasAgreedTermsAndConditions;
	}

	public void setHasAgreedTermsAndConditions(boolean hasAgreedTermsAndConditions) {
		this.hasAgreedTermsAndConditions = hasAgreedTermsAndConditions;
	}	
}

package microservices.backend.eir_subscription_management_backend.dto;

public class SubsMgmtMsisdnSwapDTO {
	
	private String msisdn;

	public SubsMgmtMsisdnSwapDTO(String msisdn) {
		this.msisdn=msisdn;
	}
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
}

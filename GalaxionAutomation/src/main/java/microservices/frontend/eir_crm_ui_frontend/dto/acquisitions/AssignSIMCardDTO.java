package microservices.frontend.eir_crm_ui_frontend.dto.acquisitions;

public class AssignSIMCardDTO {

	private String iccid;
	private String msisdn;

	public AssignSIMCardDTO(String iccid, String msisdn) {
		super();
		this.iccid = iccid;
		this.msisdn = msisdn;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
}

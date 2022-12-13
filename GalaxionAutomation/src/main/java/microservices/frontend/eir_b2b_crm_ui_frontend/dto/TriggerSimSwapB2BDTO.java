package microservices.frontend.eir_b2b_crm_ui_frontend.dto;

public class TriggerSimSwapB2BDTO {
	
	public String iccid;

	public TriggerSimSwapB2BDTO() {

	}

	public TriggerSimSwapB2BDTO(String iccid) {
		this.iccid = iccid;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
}

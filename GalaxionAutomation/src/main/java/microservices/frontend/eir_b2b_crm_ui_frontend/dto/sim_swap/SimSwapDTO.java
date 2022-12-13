package microservices.frontend.eir_b2b_crm_ui_frontend.dto.sim_swap;

public class SimSwapDTO {
	
	private String iccid;

	public SimSwapDTO(String iccid) {
		super();
		this.iccid = iccid;
	}

	public SimSwapDTO() {
		super();
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
}

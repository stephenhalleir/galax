package microservices.frontend.eir_crm_ui_frontend.dto.requests.activate_replacement_sim;

public class ActivateReplacementSIMDTO {

	private String puk;
	private String serviceId;

	public ActivateReplacementSIMDTO(int serviceId, String puk) {
		super();
		this.puk = puk;
		this.serviceId = Integer.toString(serviceId);
	}

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
}

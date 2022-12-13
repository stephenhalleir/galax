package microservices.frontend.eir_myaccount_frontend.dto;

public class ActivateSimSwapMyGoMoDTO {
	
	private String puk;
	private int serviceId;
	
	public ActivateSimSwapMyGoMoDTO(String puk, int serviceId) {
		super();
		this.puk = puk;
		this.serviceId = serviceId;
	}
	public String getPuk() {
		return puk;
	}
	public void setPuk(String puk) {
		this.puk = puk;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}

package microservices.frontend.common_ui.response_objects.subs_management;

public class GetOfferResponse {
	private CustomerOfferDTO customerOfferDTO;
	private String portStartDateTime;
	private String pin;
	private String puk;
	private String imsi;
	

	public GetOfferResponse() {
		super();
	}
	public CustomerOfferDTO getCustomerOfferDTO() {
		return customerOfferDTO;
	}
	public void setCustomerOfferDTO(CustomerOfferDTO customerOfferDTO) {
		this.customerOfferDTO = customerOfferDTO;
	}
	public String getPortStartDateTime() {
		return portStartDateTime;
	}
	public void setPortStartDateTime(String portStartDateTime) {
		this.portStartDateTime = portStartDateTime;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPuk() {
		return puk;
	}
	public void setPuk(String puk) {
		this.puk = puk;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
}

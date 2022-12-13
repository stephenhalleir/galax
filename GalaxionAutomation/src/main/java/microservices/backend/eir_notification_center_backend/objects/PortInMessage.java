package microservices.backend.eir_notification_center_backend.objects;

public class PortInMessage extends Message {
	private String portStartDate;
	private String msisdn;
	
	public PortInMessage(String portStartDate, String msisdn) {
		this.portStartDate=portStartDate;
		this.msisdn=msisdn;
	}
	
	public String getPortStartDate() {
		return portStartDate;
	}
	public void setPortStartDate(String portStartDate) {
		this.portStartDate = portStartDate;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
}

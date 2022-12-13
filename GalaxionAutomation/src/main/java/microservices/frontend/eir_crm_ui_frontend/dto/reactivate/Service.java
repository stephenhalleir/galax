package microservices.frontend.eir_crm_ui_frontend.dto.reactivate;

public class Service {
	private int subscriptionServiceId;
	private String serviceName;
	private String item;
	private String reason;
	
	public Service(int subscriptionServiceId) {
		this.subscriptionServiceId=subscriptionServiceId;
	}

	public int getSubscriptionServiceId() {
		return subscriptionServiceId;
	}

	public void setSubscriptionServiceId(int subscriptionServiceId) {
		this.subscriptionServiceId = subscriptionServiceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}

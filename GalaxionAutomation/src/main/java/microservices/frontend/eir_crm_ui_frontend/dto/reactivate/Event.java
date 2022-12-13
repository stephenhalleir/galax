package microservices.frontend.eir_crm_ui_frontend.dto.reactivate;

public class Event {

	private Service service;
	
	public Event(int subscriptionServiceId) {
		service=new Service(subscriptionServiceId);
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}

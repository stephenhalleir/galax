package microservices.frontend.eir_crm_ui_frontend.dto.reactivate;

import utilities.generic.time.Timestamp;

public class ReactivateServiceDTO {
	private String eventType;
	private String eventSourceType;
	private Event event;
	private String eventTime;
	
	public ReactivateServiceDTO(int subscriptionServiceId) {
		eventType="SERVICE_FULFILLMENT_REACTIVATE_SERVICE";
		eventSourceType="CUSTOMER_OFFER";
		eventTime=Timestamp.getCurrentTimestamp("yyyy-MM-dd'T'HH:mm:ss");
		event=new Event(subscriptionServiceId);
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventSourceType() {
		return eventSourceType;
	}

	public void setEventSourceType(String eventSourceType) {
		this.eventSourceType = eventSourceType;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
}

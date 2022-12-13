package microservices.backend.tecrep.resource_management.dto;

public class ChangeStateDTO {

	private String activityService;
	private String orderId;
	private int serviceId;

	public String getActivityService() {
		return activityService;
	}

	public void setActivityService(String activityService) {
		this.activityService = activityService;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}

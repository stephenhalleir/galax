package microservices.frontend.common_ui.response_objects.subs_management.b2b_crm_get_subscription;

public class Contract {
	
	private String startAt;
	private String endAt;

	public Contract() {
		super();
	}

	public String getStartAt() {
		return startAt;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}
}

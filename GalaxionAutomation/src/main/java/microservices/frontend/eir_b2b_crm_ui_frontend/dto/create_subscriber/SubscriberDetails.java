package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

public class SubscriberDetails {
	
	private String name;
	private boolean vip;
	
	public SubscriberDetails(String name, boolean vip) {
		super();
		this.name = name;
		this.vip = vip;
	}

	public SubscriberDetails() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
}

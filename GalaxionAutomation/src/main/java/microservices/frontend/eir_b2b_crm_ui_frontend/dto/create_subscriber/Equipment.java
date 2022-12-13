package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import java.util.List;

public class Equipment {
	
	private int catalogItemId;
	private boolean deviceEnrolled;
	private List<PricePlan> pricePlans;

	public int getCatalogItemId() {
		return catalogItemId;
	}

	public void setCatalogItemId(int catalogItemId) {
		this.catalogItemId = catalogItemId;
	}

	public boolean isDeviceEnrolled() {
		return deviceEnrolled;
	}

	public void setDeviceEnrolled(boolean deviceEnrolled) {
		this.deviceEnrolled = deviceEnrolled;
	}

	public List<PricePlan> getPricePlans() {
		return pricePlans;
	}

	public void setPricePlans(List<PricePlan> pricePlans) {
		this.pricePlans = pricePlans;
	}
}

package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import java.util.List;

public class SimCard {
	private String code;
	private int id;
	private List<PricePlan> pricePlans;
	
	public SimCard() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<PricePlan> getPricePlans() {
		return pricePlans;
	}

	public void setPricePlans(List<PricePlan> pricePlans) {
		this.pricePlans = pricePlans;
	}
}

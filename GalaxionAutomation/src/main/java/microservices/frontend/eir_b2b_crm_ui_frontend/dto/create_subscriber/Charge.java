package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Charge {

	private List<PricePlan> pricePlans;
	private int id;
	private String code;
	private String billingType;
	private String status;

	public Charge() {
		super();
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public List<PricePlan> getPricePlans() {
		return pricePlans;
	}
	
	public PricePlan getPricePlan() {
		return pricePlans.get(0);
	}

	public void setPricePlans(List<PricePlan> pricePlans) {
		this.pricePlans = pricePlans;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public int getCatalogItemId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

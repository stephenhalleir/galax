package microservices.frontend.eir_myaccount_frontend.dto.responses.get_replacement_sim_charge;

public class Charge {
	private String catalogChargeCode;
	private String catalogPricePlanCode;
	private String description;
	private String billingType;
	private int defaultPriceVatIncluded;

	public Charge() {
		super();
	}

	public String getCatalogChargeCode() {
		return catalogChargeCode;
	}

	public void setCatalogChargeCode(String catalogChargeCode) {
		this.catalogChargeCode = catalogChargeCode;
	}

	public String getCatalogPricePlanCode() {
		return catalogPricePlanCode;
	}

	public void setCatalogPricePlanCode(String catalogPricePlanCode) {
		this.catalogPricePlanCode = catalogPricePlanCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public int getDefaultPriceVatIncluded() {
		return defaultPriceVatIncluded;
	}

	public void setDefaultPriceVatIncluded(int defaultPriceVatIncluded) {
		this.defaultPriceVatIncluded = defaultPriceVatIncluded;
	}

}

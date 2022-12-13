package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PricePlan {

	@JsonIgnore
	private String validFrom;
	
	@JsonIgnore
	private String validTo;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int defaultPriceVatExcluded;
	
	@JsonIgnore
	private int maxPriceVatExcluded;
	
	@JsonIgnore
	private int minPriceVatExcluded;
	
	private int id;
	
	@JsonIgnore
	private String code;

	public PricePlan() {
		super();
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public int getDefaultPriceVatExcluded() {
		return defaultPriceVatExcluded;
	}

	public void setDefaultPriceVatExcluded(int defaultPriceVatExcluded) {
		this.defaultPriceVatExcluded = defaultPriceVatExcluded;
	}

	public int getMaxPriceVatExcluded() {
		return maxPriceVatExcluded;
	}

	public void setMaxPriceVatExcluded(int maxPriceVatExcluded) {
		this.maxPriceVatExcluded = maxPriceVatExcluded;
	}

	public int getMinPriceVatExcluded() {
		return minPriceVatExcluded;
	}

	public void setMinPriceVatExcluded(int minPriceVatExcluded) {
		this.minPriceVatExcluded = minPriceVatExcluded;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public int getPrice() {
		return defaultPriceVatExcluded;
	}
	
	public int getCatalogItemId() {
		return id;
	}
}

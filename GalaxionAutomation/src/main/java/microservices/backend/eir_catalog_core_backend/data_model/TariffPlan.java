package microservices.backend.eir_catalog_core_backend.data_model;

/**
 * This class represents a row in the catalog-core.tariff_plan table
 * 
 * @author stephenhall
 *
 */

public class TariffPlan {

	private String code;
	private String description;
	private String offerCode;
	private String commitmentDuration;
	private String channelCode;
	private String discountCode;
	private int defaultSubsidy;
	private int simOnly;
	private String customerTypeCode;

	public TariffPlan() {
		code = "";
		description = "";
		offerCode = "";
		commitmentDuration = "";
		channelCode = "";
		discountCode = "";
		defaultSubsidy = -1;
		simOnly = -1;
	}
	
	public TariffPlan(String code) {
		this.code=code;
		description = "";
		offerCode = "";
		commitmentDuration = "";
		channelCode = "";
		discountCode = "";
		defaultSubsidy = -1;
		simOnly = -1;
	}
	
	public TariffPlan(String offerCode, String tariffCode) {
		this.code=tariffCode;
		description = "";
		this.offerCode = offerCode;
		commitmentDuration = "";
		channelCode = "";
		discountCode = "";
		defaultSubsidy = -1;
		simOnly = -1;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getCommitmentDuration() {
		return commitmentDuration;
	}

	public void setCommitmentDuration(String commitmentDuration) {
		this.commitmentDuration = commitmentDuration;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public int getDefaultSubsidy() {
		return defaultSubsidy;
	}

	public void setDefaultSubsidy(int defaultSubsidy) {
		this.defaultSubsidy = defaultSubsidy;
	}

	public int getSimOnly() {
		return simOnly;
	}

	public void setSimOnly(int simOnly) {
		this.simOnly = simOnly;
	}
	
	public String getCustomerTypeCode() {
		return customerTypeCode;
	}

	public void setCustomerTypeCode(String customerTypeCode) {
		this.customerTypeCode = customerTypeCode;
	}

	public String toString() {
		return code + ", " + offerCode;
	}
}

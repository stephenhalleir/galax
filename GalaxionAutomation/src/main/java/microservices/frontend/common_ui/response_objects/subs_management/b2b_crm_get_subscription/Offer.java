package microservices.frontend.common_ui.response_objects.subs_management.b2b_crm_get_subscription;

public class Offer {
	private String description;
	private String offerSummary;
	private String offerRecurringPrice;
	private String offerType;
	private String pricePlanDescription;
	private boolean simOnly;
	private String commitmentDuration;
	private String msisdn;
	private String directoryPreference;
	private String serviceGroupCode;

	public Offer() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOfferSummary() {
		return offerSummary;
	}

	public void setOfferSummary(String offerSummary) {
		this.offerSummary = offerSummary;
	}

	public String getOfferRecurringPrice() {
		return offerRecurringPrice;
	}

	public void setOfferRecurringPrice(String offerRecurringPrice) {
		this.offerRecurringPrice = offerRecurringPrice;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getPricePlanDescription() {
		return pricePlanDescription;
	}

	public void setPricePlanDescription(String pricePlanDescription) {
		this.pricePlanDescription = pricePlanDescription;
	}

	public boolean isSimOnly() {
		return simOnly;
	}

	public void setSimOnly(boolean simOnly) {
		this.simOnly = simOnly;
	}

	public String getCommitmentDuration() {
		return commitmentDuration;
	}

	public void setCommitmentDuration(String commitmentDuration) {
		this.commitmentDuration = commitmentDuration;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getDirectoryPreference() {
		return directoryPreference;
	}

	public void setDirectoryPreference(String directoryPreference) {
		this.directoryPreference = directoryPreference;
	}

	public String getServiceGroupCode() {
		return serviceGroupCode;
	}

	public void setServiceGroupCode(String serviceGroupCode) {
		this.serviceGroupCode = serviceGroupCode;
	}
}

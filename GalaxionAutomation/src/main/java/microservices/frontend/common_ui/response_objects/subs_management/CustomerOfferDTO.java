package microservices.frontend.common_ui.response_objects.subs_management;

import java.util.List;

public class CustomerOfferDTO {
	private int catalogOfferId;
	private String name;
	private String description;
	private String status;
	private String offerType;
	private int priority;
	private List<Usage> usages;
	private int monthlyAmount;
	private int onceOffAmount;
	private int upfrontAmount;
	private String activationDate;
	private String nddPreference;
	private String msisdn;
	private String offerName;
	private String offerStatus;
	private String offerExpiryDate;
	
	public CustomerOfferDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<Usage> getUsages() {
		return usages;
	}

	public void setUsages(List<Usage> usages) {
		this.usages = usages;
	}

	public int getMonthlyAmount() {
		return monthlyAmount;
	}

	public void setMonthlyAmount(int monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}

	public int getOnceOffAmount() {
		return onceOffAmount;
	}

	public void setOnceOffAmount(int onceOffAmount) {
		this.onceOffAmount = onceOffAmount;
	}

	public int getUpfrontAmount() {
		return upfrontAmount;
	}

	public void setUpfrontAmount(int upfrontAmount) {
		this.upfrontAmount = upfrontAmount;
	}

	public String getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	public String getNddPreference() {
		return nddPreference;
	}

	public void setNddPreference(String nddPreference) {
		this.nddPreference = nddPreference;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferStatus() { 
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public String getOfferExpiryDate() {
		return offerExpiryDate;
	}

	public void setOfferExpiryDate(String offerExpiryDate) {
		this.offerExpiryDate = offerExpiryDate;
	}

	@Override
	public String toString() {
		return "CustomerOfferDTO [catalogOfferId=" + catalogOfferId + ", name=" + name + ", description=" + description + ", status=" + status + ", offerType="
				+ offerType + ", priority=" + priority + ", monthlyAmount=" + monthlyAmount + ", onceOffAmount=" + onceOffAmount + ", upfrontAmount="
				+ upfrontAmount + ", activationDate=" + activationDate + ", nddPreference=" + nddPreference + ", msisdn=" + msisdn + ", offerName=" + offerName
				+ ", offerStatus=" + offerStatus + ", offerExpiryDate=" + offerExpiryDate + "]";
	}
}

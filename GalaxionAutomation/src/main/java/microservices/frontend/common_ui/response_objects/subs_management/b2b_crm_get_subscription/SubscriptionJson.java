package microservices.frontend.common_ui.response_objects.subs_management.b2b_crm_get_subscription;

public class SubscriptionJson {
	private int id;
	private String offerCode;
	private String type;
	private String terminationDateTime;
	private String activationDateTime;
	private String subscriberName;
	private String costCenter;
	private boolean vip;
	private Offer offer;
	private String status;
	private String iccid;
	private Contract contract;
	private String creationDateTime;
	private int serviceId;
	private boolean canBeReactivated;
	private int offerId;

	public SubscriptionJson() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTerminationDateTime() {
		return terminationDateTime;
	}

	public void setTerminationDateTime(String terminationDateTime) {
		this.terminationDateTime = terminationDateTime;
	}

	public String getActivationDateTime() {
		return activationDateTime;
	}

	public void setActivationDateTime(String activationDateTime) {
		this.activationDateTime = activationDateTime;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContact(Contract contract) {
		this.contract = contract;
	}

	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	

	public boolean isCanBeReactivated() {
		return canBeReactivated;
	}

	public void setCanBeReactivated(boolean canBeReactivated) {
		this.canBeReactivated = canBeReactivated;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
}

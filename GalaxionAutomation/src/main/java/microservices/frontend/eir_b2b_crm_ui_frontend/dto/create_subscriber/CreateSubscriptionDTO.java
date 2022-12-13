package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

public class CreateSubscriptionDTO {
	private int billingAccountId;
	private boolean debitHardwareFund;
	private CatalogOffer offer;
	private SubscriberDetails subscriberDetails;
	private OfferDetails offerDetails;
	private Contact contact;
	
	public CreateSubscriptionDTO(int billingAccountID, CatalogOffer offer) {
		this.billingAccountId=billingAccountID;
		this.offer=offer;
		this.debitHardwareFund=false;
		this.subscriberDetails=new SubscriberDetails();
		this.offerDetails=new OfferDetails(offer);
		this.contact=new Contact();
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public OfferDetails getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(OfferDetails offerDetails) {
		this.offerDetails = offerDetails;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public boolean isDebitHardwareFund() {
		return debitHardwareFund;
	}

	public void setDebitHardwareFund(boolean debitHardwareFund) {
		this.debitHardwareFund = debitHardwareFund;
	}

	public CatalogOffer getOffer() {
		return offer;
	}

	public void setOffer(CatalogOffer offer) {
		this.offer = offer;
	}

	public SubscriberDetails getSubscriberDetails() {
		return subscriberDetails;
	}

	public void setSubscriberDetails(SubscriberDetails subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}

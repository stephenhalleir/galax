package testCases.testObjects.orders;

import java.util.ArrayList;

import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_topup_backend.enums.TopupAmount;

public class OrderSubscription {
	private OfferCode offerCode;
	private ArrayList<SubscriptionAddonBundle> addons;
	private String handsetCode;
	private TopupAmount topupAmount;
	
	public OrderSubscription() {
		
	}
	
	public OrderSubscription(OfferCode offerCode, ArrayList<SubscriptionAddonBundle> addons,TopupAmount topupAmount, String handsetCode) {
		this.offerCode=offerCode;
		this.topupAmount=topupAmount;
		this.handsetCode=handsetCode;
		this.addons=addons;
	}

	public OfferCode getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(OfferCode offerCode) {
		this.offerCode = offerCode;
	}

	public ArrayList<SubscriptionAddonBundle> getAddons() {
		return addons;
	}

	public void setAddons(ArrayList<SubscriptionAddonBundle> addons) {
		this.addons = addons;
	}

	public String getHandsetCode() {
		return handsetCode;
	}

	public void setHandsetCode(String handsetCode) {
		this.handsetCode = handsetCode;
	}

	public TopupAmount getTopupAmount() {
		return topupAmount;
	}

	public void setTopupAmount(TopupAmount topupAmount) {
		this.topupAmount = topupAmount;
	}
}

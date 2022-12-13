package testCases.eir.b2c.testObjects;

import java.util.ArrayList;

import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_catalog_core_backend.enums.SalesChannel;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_order_management_backend.enums.RegistrationStatus;
import microservices.backend.eir_topup_backend.enums.TopupAmount;
import testCases.testObjects.orders.PrepayOrder;
import testCases.testObjects.orders.OrderSubscription;

public class PrepaySingleLineOrder {
	private SalesChannel channel;
	private RegistrationStatus registration;
	private String deviceCode;
	private OfferCode offerCode;
	private TopupAmount topupAmount;
	private SubscriptionAddonBundle bundle;
	
	public PrepaySingleLineOrder() {
		
	}
	
	public PrepaySingleLineOrder(SalesChannel channel, RegistrationStatus registration, String deviceCode, OfferCode offerCode, TopupAmount topupAmount,
			SubscriptionAddonBundle bundle) {
		super();
		this.channel = channel;
		this.registration = registration;
		this.deviceCode = deviceCode;
		this.offerCode = offerCode;
		this.topupAmount = topupAmount;
		this.bundle = bundle;
	}
	
	public PrepayOrder toPrepayOrder() {
		PrepayOrder order = new PrepayOrder(channel, registration);

		ArrayList<SubscriptionAddonBundle> addons1 = new ArrayList<SubscriptionAddonBundle>();
		if(bundle!=null) {
			addons1.add(bundle);
		}

		// create the subscription
		OrderSubscription subscription1 = new OrderSubscription(offerCode, addons1, topupAmount, deviceCode);
		
		// add the subscriptions to the order
		ArrayList<OrderSubscription> subscriptions = new ArrayList<OrderSubscription>();
		subscriptions.add(subscription1);

		order.setSubscriptions(subscriptions);
		return order;
	}


	public SalesChannel getChannel() {
		return channel;
	}
	public void setChannel(SalesChannel channel) {
		this.channel = channel;
	}
	public RegistrationStatus getRegistration() {
		return registration;
	}
	public void setRegistration(RegistrationStatus registration) {
		this.registration = registration;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public OfferCode getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(OfferCode offerCode) {
		this.offerCode = offerCode;
	}
	public TopupAmount getTopupAmount() {
		return topupAmount;
	}
	public void setTopupAmount(TopupAmount topupAmount) {
		this.topupAmount = topupAmount;
	}
	public SubscriptionAddonBundle getBundle() {
		return bundle;
	}
	public void setBundle(SubscriptionAddonBundle bundle) {
		this.bundle = bundle;
	}
}

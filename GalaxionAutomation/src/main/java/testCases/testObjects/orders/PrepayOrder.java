package testCases.testObjects.orders;

import java.util.ArrayList;

import microservices.backend.eir_catalog_core_backend.enums.SalesChannel;
import microservices.backend.eir_order_management_backend.enums.RegistrationStatus;

public class PrepayOrder {
	private SalesChannel channel;
	private ArrayList<OrderSubscription> subscriptions;
	private RegistrationStatus registrationStatus;
	
	public PrepayOrder() {
		
	}
	
	public PrepayOrder(SalesChannel channel, RegistrationStatus registrationStatus) {
		this.channel=channel;
		this.registrationStatus=registrationStatus;
	}

	public SalesChannel getChannel() {
		return channel;
	}

	public void setChannel(SalesChannel channel) {
		this.channel = channel;
	}

	public ArrayList<OrderSubscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(ArrayList<OrderSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
}

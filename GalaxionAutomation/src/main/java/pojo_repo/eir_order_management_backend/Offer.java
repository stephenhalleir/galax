package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Offer {

	private int id;
	private int offerId;
	private String offerType;
	private String domain;
	private int orderId;
	private int orderServiceId;
	private String orderSubscriptionStatus;
	private int subscriptionId;

	public Offer() {

	}

	public Offer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			offerId = rs.getInt("offer_id");
			offerType = rs.getString("offer_type");
			domain = rs.getString("domain");
			orderId = rs.getInt("order_id");
			orderServiceId = rs.getInt("order_service_id");
			orderSubscriptionStatus = rs.getString("order_subscription_status");
			subscriptionId = rs.getInt("subscription_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(int orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

	public String getOrderSubscriptionStatus() {
		return orderSubscriptionStatus;
	}

	public void setOrderSubscriptionStatus(String orderSubscriptionStatus) {
		this.orderSubscriptionStatus = orderSubscriptionStatus;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}
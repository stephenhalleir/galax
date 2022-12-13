package microservices.backend.eir_order_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents the OFFER table within order management
 * @author stephenhall
 *
 */

public class Offer {
	private int id;
	private int offerID;
	private String offerType;
	private String domain;
	private int orderID;
	private int orderServiceID;
	private String orderSubscriptionStatus;
	private int subscriptionID;
	private String tariffPlanCode;
	
	public Offer() {
		
	}
		
	public Offer(ResultSet rs) {
		try {
			id=rs.getInt("id");
			offerID=rs.getInt("id");
			offerType=rs.getString("offer_type");
			domain=rs.getString("domain");
			orderID=rs.getInt("order_id");
			orderServiceID=rs.getInt("order_service_id");
			orderSubscriptionStatus=rs.getString("order_subscription_status");
			subscriptionID=rs.getInt("subscription_id");
			tariffPlanCode=rs.getString("tariff_plan_code");
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

	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
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

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getOrderServiceID() {
		return orderServiceID;
	}

	public void setOrderServiceID(int orderServiceID) {
		this.orderServiceID = orderServiceID;
	}

	public String getOrderSubscriptionStatus() {
		return orderSubscriptionStatus;
	}

	public void setOrderSubscriptionStatus(String orderSubscriptionStatus) {
		this.orderSubscriptionStatus = orderSubscriptionStatus;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getTariffPlanCode() {
		return tariffPlanCode;
	}

	public void setTariffPlanCode(String tariffPlanCode) {
		this.tariffPlanCode = tariffPlanCode;
	}
}

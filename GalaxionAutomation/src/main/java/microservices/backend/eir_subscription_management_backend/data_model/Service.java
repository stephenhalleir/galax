package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
	private int id;
	private int catalogSubOfferID;
	private String domain;
	private String name;
	private int orderID;
	private int subscriptionID;
	private int networkID;
	private int simCardId;
	
	public Service() {
		
	}
	
	/**
	 * Populate the Service based on a result set
	 * @param rs
	 */
	public Service(ResultSet rs) {
		try {
			id=rs.getInt("id");
			catalogSubOfferID=rs.getInt("catalog_sub_offer_id");
			domain=rs.getString("domain");
			name=rs.getString("name");
			orderID=rs.getInt("order_id");
			subscriptionID=rs.getInt("subscription_id");
			networkID=rs.getInt("network_id");
			simCardId=rs.getInt("sim_card_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCatalogSubOfferID() {
		return catalogSubOfferID;
	}

	public void setCatalogSubOfferID(int catalogSubOfferID) {
		this.catalogSubOfferID = catalogSubOfferID;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public int getNetworkID() {
		return networkID;
	}

	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSimCardId() {
		return simCardId;
	}

	public void setSimCardId(int simCardId) {
		this.simCardId = simCardId;
	}
	
	
}

package microservices.backend.eir_subscription_management_backend.data_model.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionServicePair {

	private int subscriptionID;
	private int catalogOfferID;
	private String msisdn;
	private String status;
	private String domain;
	private int serviceID;
	private String nddPreference;
	private int networkID;

	public SubscriptionServicePair(ResultSet rs) {
		try {
			subscriptionID = rs.getInt("subscription_id");
			serviceID = rs.getInt("service_id");
			catalogOfferID = rs.getInt("catalog_offer_id");
			status = rs.getString("status");
			msisdn = rs.getString("name");
			domain = rs.getString("domain");
			networkID=rs.getInt("network_id");
			nddPreference=rs.getString("ndd_preference");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getNddPreference() {
		return nddPreference;
	}



	public void setNddPreference(String nddPreference) {
		this.nddPreference = nddPreference;
	}



	public int getNetworkID() {
		return networkID;
	}



	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}



	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public int getCatalogOfferID() {
		return catalogOfferID;
	}

	public void setCatalogOfferID(int catalogOfferID) {
		this.catalogOfferID = catalogOfferID;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
}

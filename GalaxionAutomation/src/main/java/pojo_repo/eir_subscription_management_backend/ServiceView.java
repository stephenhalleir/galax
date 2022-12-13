package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceView {

private int serviceId;
private String msisdn;
private int catalogSubOfferId;
private String domain;
private int orderId;
private int subscriptionId;
private Date activatedAt;
private Date terminatedAt;
private int networkId;
private int simCardId;

public ServiceView() {

}

public ServiceView(ResultSet rs) {
try {
	serviceId = rs.getInt("service_id");
	msisdn = rs.getString("msisdn");
	catalogSubOfferId = rs.getInt("catalog_sub_offer_id");
	domain = rs.getString("domain");
	orderId = rs.getInt("order_id");
	subscriptionId = rs.getInt("subscription_id");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	networkId = rs.getInt("network_id");
	simCardId = rs.getInt("sim_card_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}
public String getMsisdn() {
 	return msisdn;
}
public void setMsisdn(String msisdn) {
 	 this.msisdn=msisdn;
}
public int getCatalogSubOfferId() {
 	return catalogSubOfferId;
}
public void setCatalogSubOfferId(int catalogSubOfferId) {
 	 this.catalogSubOfferId=catalogSubOfferId;
}
public String getDomain() {
 	return domain;
}
public void setDomain(String domain) {
 	 this.domain=domain;
}
public int getOrderId() {
 	return orderId;
}
public void setOrderId(int orderId) {
 	 this.orderId=orderId;
}
public int getSubscriptionId() {
 	return subscriptionId;
}
public void setSubscriptionId(int subscriptionId) {
 	 this.subscriptionId=subscriptionId;
}
public Date getActivatedAt() {
 	return activatedAt;
}
public void setActivatedAt(Date activatedAt) {
 	 this.activatedAt=activatedAt;
}
public Date getTerminatedAt() {
 	return terminatedAt;
}
public void setTerminatedAt(Date terminatedAt) {
 	 this.terminatedAt=terminatedAt;
}
public int getNetworkId() {
 	return networkId;
}
public void setNetworkId(int networkId) {
 	 this.networkId=networkId;
}
public int getSimCardId() {
 	return simCardId;
}
public void setSimCardId(int simCardId) {
 	 this.simCardId=simCardId;
}

}
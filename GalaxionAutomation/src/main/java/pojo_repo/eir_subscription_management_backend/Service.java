package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {

private int id;
private Date createdAt;
private Date updatedAt;
private int catalogSubOfferId;
private String domain;
private String name;
private int orderId;
private int subscriptionId;
private Date activatedAt;
private Date terminatedAt;
private int networkId;

public Service() {

}

public Service(ResultSet rs) {
try {
	id = rs.getInt("id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	catalogSubOfferId = rs.getInt("catalog_sub_offer_id");
	domain = rs.getString("domain");
	name = rs.getString("name");
	orderId = rs.getInt("order_id");
	subscriptionId = rs.getInt("subscription_id");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	networkId = rs.getInt("network_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}
public Date getUpdatedAt() {
 	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
 	 this.updatedAt=updatedAt;
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
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
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

}
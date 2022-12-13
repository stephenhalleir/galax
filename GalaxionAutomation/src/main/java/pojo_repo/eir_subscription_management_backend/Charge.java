package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Charge {

private int id;
private int orderId;
private int catalogChargeId;
private Date createdAt;
private Date updatedAt;
private Date activatedAt;
private Date terminatedAt;
private String catalogCode;
private String billingType;
private String description;
private String pricePlanCatalogCode;
private int price;
private int coolingOffPeriod;
private String advanceArrears;
private int addonBundleId;
private int serviceId;

public Charge() {

}

public Charge(ResultSet rs) {
try {
	id = rs.getInt("id");
	orderId = rs.getInt("order_id");
	catalogChargeId = rs.getInt("catalog_charge_id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	catalogCode = rs.getString("catalog_code");
	billingType = rs.getString("billing_type");
	description = rs.getString("description");
	pricePlanCatalogCode = rs.getString("price_plan_catalog_code");
	price = rs.getInt("price");
	coolingOffPeriod = rs.getInt("cooling_off_period");
	advanceArrears = rs.getString("advance_arrears");
	addonBundleId = rs.getInt("addon_bundle_id");
	serviceId = rs.getInt("service_id");
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
public int getOrderId() {
 	return orderId;
}
public void setOrderId(int orderId) {
 	 this.orderId=orderId;
}
public int getCatalogChargeId() {
 	return catalogChargeId;
}
public void setCatalogChargeId(int catalogChargeId) {
 	 this.catalogChargeId=catalogChargeId;
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
public String getCatalogCode() {
 	return catalogCode;
}
public void setCatalogCode(String catalogCode) {
 	 this.catalogCode=catalogCode;
}
public String getBillingType() {
 	return billingType;
}
public void setBillingType(String billingType) {
 	 this.billingType=billingType;
}
public String getDescription() {
 	return description;
}
public void setDescription(String description) {
 	 this.description=description;
}
public String getPricePlanCatalogCode() {
 	return pricePlanCatalogCode;
}
public void setPricePlanCatalogCode(String pricePlanCatalogCode) {
 	 this.pricePlanCatalogCode=pricePlanCatalogCode;
}
public int getPrice() {
 	return price;
}
public void setPrice(int price) {
 	 this.price=price;
}
public int getCoolingOffPeriod() {
 	return coolingOffPeriod;
}
public void setCoolingOffPeriod(int coolingOffPeriod) {
 	 this.coolingOffPeriod=coolingOffPeriod;
}
public String getAdvanceArrears() {
 	return advanceArrears;
}
public void setAdvanceArrears(String advanceArrears) {
 	 this.advanceArrears=advanceArrears;
}
public int getAddonBundleId() {
 	return addonBundleId;
}
public void setAddonBundleId(int addonBundleId) {
 	 this.addonBundleId=addonBundleId;
}
public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}

}
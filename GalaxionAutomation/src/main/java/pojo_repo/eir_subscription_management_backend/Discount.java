package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {

private int id;
private int catalogDiscountId;
private Date createdAt;
private Date updatedAt;
private Date activatedAt;
private Date terminatedAt;
private String catalogCode;
private int addonBundleId;
private int subscriptionId;
private int serviceId;
private int amountVatIncluded;
private int amountVatExcluded;
private String catalogItemType;
private String billingType;

public Discount() {

}

public Discount(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogDiscountId = rs.getInt("catalog_discount_id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	catalogCode = rs.getString("catalog_code");
	addonBundleId = rs.getInt("addon_bundle_id");
	subscriptionId = rs.getInt("subscription_id");
	serviceId = rs.getInt("service_id");
	amountVatIncluded = rs.getInt("amount_vat_included");
	amountVatExcluded = rs.getInt("amount_vat_excluded");
	catalogItemType = rs.getString("catalog_item_type");
	billingType = rs.getString("billing_type");
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
public int getCatalogDiscountId() {
 	return catalogDiscountId;
}
public void setCatalogDiscountId(int catalogDiscountId) {
 	 this.catalogDiscountId=catalogDiscountId;
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
public int getAddonBundleId() {
 	return addonBundleId;
}
public void setAddonBundleId(int addonBundleId) {
 	 this.addonBundleId=addonBundleId;
}
public int getSubscriptionId() {
 	return subscriptionId;
}
public void setSubscriptionId(int subscriptionId) {
 	 this.subscriptionId=subscriptionId;
}
public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}
public int getAmountVatIncluded() {
 	return amountVatIncluded;
}
public void setAmountVatIncluded(int amountVatIncluded) {
 	 this.amountVatIncluded=amountVatIncluded;
}
public int getAmountVatExcluded() {
 	return amountVatExcluded;
}
public void setAmountVatExcluded(int amountVatExcluded) {
 	 this.amountVatExcluded=amountVatExcluded;
}
public String getCatalogItemType() {
 	return catalogItemType;
}
public void setCatalogItemType(String catalogItemType) {
 	 this.catalogItemType=catalogItemType;
}
public String getBillingType() {
 	return billingType;
}
public void setBillingType(String billingType) {
 	 this.billingType=billingType;
}

}
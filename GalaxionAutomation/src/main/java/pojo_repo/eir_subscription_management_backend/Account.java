package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

private int id;
private Date createdAt;
private Date updatedAt;
private String status;
private String brand;
private String orderReference;
private String type;
private int billingAccountId;
private int billingExclusion;
private String invoiceDeliveryMethod;
private int invoiceSuppression;
private int itemisedInvoice;
private int billingCycleId;
private String language;
private String customerType;
private Date lastBillIssuedAt;
private String legacyReference;

public Account() {

}

public Account(ResultSet rs) {
try {
	id = rs.getInt("id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	status = rs.getString("status");
	brand = rs.getString("brand");
	orderReference = rs.getString("order_reference");
	type = rs.getString("type");
	billingAccountId = rs.getInt("billing_account_id");
	billingExclusion = rs.getInt("billing_exclusion");
	invoiceDeliveryMethod = rs.getString("invoice_delivery_method");
	invoiceSuppression = rs.getInt("invoice_suppression");
	itemisedInvoice = rs.getInt("itemised_invoice");
	billingCycleId = rs.getInt("billing_cycle_id");
	language = rs.getString("language");
	customerType = rs.getString("customer_type");
	lastBillIssuedAt = rs.getDate("last_bill_issued_at");
	legacyReference = rs.getString("legacy_reference");
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
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public String getBrand() {
 	return brand;
}
public void setBrand(String brand) {
 	 this.brand=brand;
}
public String getOrderReference() {
 	return orderReference;
}
public void setOrderReference(String orderReference) {
 	 this.orderReference=orderReference;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public int getBillingAccountId() {
 	return billingAccountId;
}
public void setBillingAccountId(int billingAccountId) {
 	 this.billingAccountId=billingAccountId;
}
public int getBillingExclusion() {
 	return billingExclusion;
}
public void setBillingExclusion(int billingExclusion) {
 	 this.billingExclusion=billingExclusion;
}
public String getInvoiceDeliveryMethod() {
 	return invoiceDeliveryMethod;
}
public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
 	 this.invoiceDeliveryMethod=invoiceDeliveryMethod;
}
public int getInvoiceSuppression() {
 	return invoiceSuppression;
}
public void setInvoiceSuppression(int invoiceSuppression) {
 	 this.invoiceSuppression=invoiceSuppression;
}
public int getItemisedInvoice() {
 	return itemisedInvoice;
}
public void setItemisedInvoice(int itemisedInvoice) {
 	 this.itemisedInvoice=itemisedInvoice;
}
public int getBillingCycleId() {
 	return billingCycleId;
}
public void setBillingCycleId(int billingCycleId) {
 	 this.billingCycleId=billingCycleId;
}
public String getLanguage() {
 	return language;
}
public void setLanguage(String language) {
 	 this.language=language;
}
public String getCustomerType() {
 	return customerType;
}
public void setCustomerType(String customerType) {
 	 this.customerType=customerType;
}
public Date getLastBillIssuedAt() {
 	return lastBillIssuedAt;
}
public void setLastBillIssuedAt(Date lastBillIssuedAt) {
 	 this.lastBillIssuedAt=lastBillIssuedAt;
}
public String getLegacyReference() {
 	return legacyReference;
}
public void setLegacyReference(String legacyReference) {
 	 this.legacyReference=legacyReference;
}

}
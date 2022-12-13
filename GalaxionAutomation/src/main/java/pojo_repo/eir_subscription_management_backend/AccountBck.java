package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBck {

private int id;
private Date creationDateTime;
private Date startDateTime;
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

public AccountBck() {

}

public AccountBck(ResultSet rs) {
try {
	id = rs.getInt("id");
	creationDateTime = rs.getDate("creation_date_time");
	startDateTime = rs.getDate("start_date_time");
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
public Date getCreationDateTime() {
 	return creationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
 	 this.creationDateTime=creationDateTime;
}
public Date getStartDateTime() {
 	return startDateTime;
}
public void setStartDateTime(Date startDateTime) {
 	 this.startDateTime=startDateTime;
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

}
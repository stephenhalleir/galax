package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAccountHistory {

private int id;
private int rev;
private int revtype;
private Date createdAt;
private Date updatedAt;
private int lockedByProvisioning;
private String status;
private int billingExclusion;
private String invoiceDeliveryMethod;
private int invoiceSuppression;
private int itemisedInvoice;
private String payerUuid;
private String paymentMethodType;

public BillingAccountHistory() {

}

public BillingAccountHistory(ResultSet rs) {
try {
	id = rs.getInt("id");
	rev = rs.getInt("rev");
	revtype = rs.getInt("revtype");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	lockedByProvisioning = rs.getInt("locked_by_provisioning");
	status = rs.getString("status");
	billingExclusion = rs.getInt("billing_exclusion");
	invoiceDeliveryMethod = rs.getString("invoice_delivery_method");
	invoiceSuppression = rs.getInt("invoice_suppression");
	itemisedInvoice = rs.getInt("itemised_invoice");
	payerUuid = rs.getString("payer_uuid");
	paymentMethodType = rs.getString("payment_method_type");
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
public int getRev() {
 	return rev;
}
public void setRev(int rev) {
 	 this.rev=rev;
}
public int getRevtype() {
 	return revtype;
}
public void setRevtype(int revtype) {
 	 this.revtype=revtype;
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
public int getLockedByProvisioning() {
 	return lockedByProvisioning;
}
public void setLockedByProvisioning(int lockedByProvisioning) {
 	 this.lockedByProvisioning=lockedByProvisioning;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
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
public String getPayerUuid() {
 	return payerUuid;
}
public void setPayerUuid(String payerUuid) {
 	 this.payerUuid=payerUuid;
}
public String getPaymentMethodType() {
 	return paymentMethodType;
}
public void setPaymentMethodType(String paymentMethodType) {
 	 this.paymentMethodType=paymentMethodType;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAccount {

private int id;
private Date createdAt;
private Date updatedAt;
private int version;
private int lockedByProvisioning;
private String status;
private int billingExclusion;
private String invoiceDeliveryMethod;
private int invoiceSuppression;
private int itemisedInvoice;
private String payerUuid;
private String paymentMethodType;

public BillingAccount() {

}

public BillingAccount(ResultSet rs) {
try {
	id = rs.getInt("id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	version = rs.getInt("version");
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
public int getVersion() {
 	return version;
}
public void setVersion(int version) {
 	 this.version=version;
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
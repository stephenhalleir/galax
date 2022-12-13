package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAccountHistoryOld {

private int id;
private int rev;
private int revtype;
private Date creationDateTime;
private Date startDateTime;
private int lockedByProvisioning;
private String status;
private int billingExclusion;
private String invoiceDeliveryMethod;
private int invoiceSuppression;
private int itemisedInvoice;
private String payerUuid;
private String paymentMethodType;

public BillingAccountHistoryOld() {

}

public BillingAccountHistoryOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	rev = rs.getInt("rev");
	revtype = rs.getInt("revtype");
	creationDateTime = rs.getDate("creation_date_time");
	startDateTime = rs.getDate("start_date_time");
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
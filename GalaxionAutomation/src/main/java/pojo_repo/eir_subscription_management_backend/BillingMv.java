package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingMv {

private int billingAccountId;
private int billingCycleId;
private Date accountActivatedAt;
private String billingAccountStatus;
private int itemisedInvoice;
private String invoiceDeliveryMethod;
private String paymentMethodType;
private String payerUuid;
private int accountId;
private String brand;
private String accountType;
private Date createdAt;
private Date accountUpdatedAt;
private int subscriptionId;
private int catalogOfferId;
private String subscriptionStatus;
private String userUuid;
private Date subscriptionUpdatedAt;
private Date subscriptionEndedAt;
private String subscriptionType;
private int catalogItemId;
private Date itemUpdatedAt;
private Date itemEndedAt;
private String itemStatus;
private int serviceId;
private String serviceName;
private int catalogSubOfferId;
private Date serviceUpdatedAt;
private Date serviceEndedAt;
private String serviceStatus;
private String domain;

public BillingMv() {

}

public BillingMv(ResultSet rs) {
try {
	billingAccountId = rs.getInt("billing_account_id");
	billingCycleId = rs.getInt("billing_cycle_id");
	accountActivatedAt = rs.getDate("account_activated_at");
	billingAccountStatus = rs.getString("billing_account_status");
	itemisedInvoice = rs.getInt("itemised_invoice");
	invoiceDeliveryMethod = rs.getString("invoice_delivery_method");
	paymentMethodType = rs.getString("payment_method_type");
	payerUuid = rs.getString("payer_uuid");
	accountId = rs.getInt("account_id");
	brand = rs.getString("brand");
	accountType = rs.getString("account_type");
	createdAt = rs.getDate("created_at");
	accountUpdatedAt = rs.getDate("account_updated_at");
	subscriptionId = rs.getInt("subscription_id");
	catalogOfferId = rs.getInt("catalog_offer_id");
	subscriptionStatus = rs.getString("subscription_status");
	userUuid = rs.getString("user_uuid");
	subscriptionUpdatedAt = rs.getDate("subscription_updated_at");
	subscriptionEndedAt = rs.getDate("subscription_ended_at");
	subscriptionType = rs.getString("subscription_type");
	catalogItemId = rs.getInt("catalog_item_id");
	itemUpdatedAt = rs.getDate("item_updated_at");
	itemEndedAt = rs.getDate("item_ended_at");
	itemStatus = rs.getString("item_status");
	serviceId = rs.getInt("service_id");
	serviceName = rs.getString("service_name");
	catalogSubOfferId = rs.getInt("catalog_sub_offer_id");
	serviceUpdatedAt = rs.getDate("service_updated_at");
	serviceEndedAt = rs.getDate("service_ended_at");
	serviceStatus = rs.getString("service_status");
	domain = rs.getString("domain");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getBillingAccountId() {
 	return billingAccountId;
}
public void setBillingAccountId(int billingAccountId) {
 	 this.billingAccountId=billingAccountId;
}
public int getBillingCycleId() {
 	return billingCycleId;
}
public void setBillingCycleId(int billingCycleId) {
 	 this.billingCycleId=billingCycleId;
}
public Date getAccountActivatedAt() {
 	return accountActivatedAt;
}
public void setAccountActivatedAt(Date accountActivatedAt) {
 	 this.accountActivatedAt=accountActivatedAt;
}
public String getBillingAccountStatus() {
 	return billingAccountStatus;
}
public void setBillingAccountStatus(String billingAccountStatus) {
 	 this.billingAccountStatus=billingAccountStatus;
}
public int getItemisedInvoice() {
 	return itemisedInvoice;
}
public void setItemisedInvoice(int itemisedInvoice) {
 	 this.itemisedInvoice=itemisedInvoice;
}
public String getInvoiceDeliveryMethod() {
 	return invoiceDeliveryMethod;
}
public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
 	 this.invoiceDeliveryMethod=invoiceDeliveryMethod;
}
public String getPaymentMethodType() {
 	return paymentMethodType;
}
public void setPaymentMethodType(String paymentMethodType) {
 	 this.paymentMethodType=paymentMethodType;
}
public String getPayerUuid() {
 	return payerUuid;
}
public void setPayerUuid(String payerUuid) {
 	 this.payerUuid=payerUuid;
}
public int getAccountId() {
 	return accountId;
}
public void setAccountId(int accountId) {
 	 this.accountId=accountId;
}
public String getBrand() {
 	return brand;
}
public void setBrand(String brand) {
 	 this.brand=brand;
}
public String getAccountType() {
 	return accountType;
}
public void setAccountType(String accountType) {
 	 this.accountType=accountType;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}
public Date getAccountUpdatedAt() {
 	return accountUpdatedAt;
}
public void setAccountUpdatedAt(Date accountUpdatedAt) {
 	 this.accountUpdatedAt=accountUpdatedAt;
}
public int getSubscriptionId() {
 	return subscriptionId;
}
public void setSubscriptionId(int subscriptionId) {
 	 this.subscriptionId=subscriptionId;
}
public int getCatalogOfferId() {
 	return catalogOfferId;
}
public void setCatalogOfferId(int catalogOfferId) {
 	 this.catalogOfferId=catalogOfferId;
}
public String getSubscriptionStatus() {
 	return subscriptionStatus;
}
public void setSubscriptionStatus(String subscriptionStatus) {
 	 this.subscriptionStatus=subscriptionStatus;
}
public String getUserUuid() {
 	return userUuid;
}
public void setUserUuid(String userUuid) {
 	 this.userUuid=userUuid;
}
public Date getSubscriptionUpdatedAt() {
 	return subscriptionUpdatedAt;
}
public void setSubscriptionUpdatedAt(Date subscriptionUpdatedAt) {
 	 this.subscriptionUpdatedAt=subscriptionUpdatedAt;
}
public Date getSubscriptionEndedAt() {
 	return subscriptionEndedAt;
}
public void setSubscriptionEndedAt(Date subscriptionEndedAt) {
 	 this.subscriptionEndedAt=subscriptionEndedAt;
}
public String getSubscriptionType() {
 	return subscriptionType;
}
public void setSubscriptionType(String subscriptionType) {
 	 this.subscriptionType=subscriptionType;
}
public int getCatalogItemId() {
 	return catalogItemId;
}
public void setCatalogItemId(int catalogItemId) {
 	 this.catalogItemId=catalogItemId;
}
public Date getItemUpdatedAt() {
 	return itemUpdatedAt;
}
public void setItemUpdatedAt(Date itemUpdatedAt) {
 	 this.itemUpdatedAt=itemUpdatedAt;
}
public Date getItemEndedAt() {
 	return itemEndedAt;
}
public void setItemEndedAt(Date itemEndedAt) {
 	 this.itemEndedAt=itemEndedAt;
}
public String getItemStatus() {
 	return itemStatus;
}
public void setItemStatus(String itemStatus) {
 	 this.itemStatus=itemStatus;
}
public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}
public String getServiceName() {
 	return serviceName;
}
public void setServiceName(String serviceName) {
 	 this.serviceName=serviceName;
}
public int getCatalogSubOfferId() {
 	return catalogSubOfferId;
}
public void setCatalogSubOfferId(int catalogSubOfferId) {
 	 this.catalogSubOfferId=catalogSubOfferId;
}
public Date getServiceUpdatedAt() {
 	return serviceUpdatedAt;
}
public void setServiceUpdatedAt(Date serviceUpdatedAt) {
 	 this.serviceUpdatedAt=serviceUpdatedAt;
}
public Date getServiceEndedAt() {
 	return serviceEndedAt;
}
public void setServiceEndedAt(Date serviceEndedAt) {
 	 this.serviceEndedAt=serviceEndedAt;
}
public String getServiceStatus() {
 	return serviceStatus;
}
public void setServiceStatus(String serviceStatus) {
 	 this.serviceStatus=serviceStatus;
}
public String getDomain() {
 	return domain;
}
public void setDomain(String domain) {
 	 this.domain=domain;
}

}
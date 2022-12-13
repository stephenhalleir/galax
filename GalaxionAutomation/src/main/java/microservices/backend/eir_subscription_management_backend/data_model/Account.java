package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Account {
	private int id;
	private String brand;
	private Date createdAt;
	private String orderReference;
	private String status;
	private String type;
	private int billingCycleID;
	private int billingAccountID;
	private String invoiceDeliveryMethod;
	private String customerType;
	private int itemisedInvoice;

	public Account() {

	}

	public Account(ResultSet rs) {
		try {
			id = rs.getInt("id");
			brand = rs.getString("brand");
			orderReference = rs.getString("order_reference");
			status = rs.getString("status");
			type = rs.getString("type");
			billingAccountID = rs.getInt("billing_account_id");
			invoiceDeliveryMethod = rs.getString("invoice_delivery_method");
			customerType = rs.getString("customer_type");
			billingCycleID = rs.getInt("billing_cycle_id");
			itemisedInvoice = rs.getInt("itemised_invoice");
			createdAt = rs.getDate("created_at");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public String getInvoiceDeliveryMethod() {
		return invoiceDeliveryMethod;
	}

	public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
		this.invoiceDeliveryMethod = invoiceDeliveryMethod;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBillingCycleID() {
		return billingCycleID;
	}

	public void setBillingCycleID(int billingCycleID) {
		this.billingCycleID = billingCycleID;
	}

	public int getItemisedInvoice() {
		return itemisedInvoice;
	}

	public void setItemisedInvoice(int itemisedInvoice) {
		this.itemisedInvoice = itemisedInvoice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}

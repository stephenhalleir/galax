package microservices.backend.eir_payment_center_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Payer {
	private int id;
	private int billingAccountId;
	private String brand;
	private String contactUuid;
	private String providerPayerRef;
	private int defaultPaymentMethodId;
	
	public Payer() {
		
	}
	
	public Payer(ResultSet rs) {
		try {
			id=rs.getInt("id");
			billingAccountId=rs.getInt("billing_account_id");
			brand=rs.getString("brand");
			contactUuid=rs.getString("contact_uuid");
			providerPayerRef=rs.getString("provider_payer_ref");
			defaultPaymentMethodId=rs.getInt("default_payment_method_id");
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
	public int getBillingAccountId() {
		return billingAccountId;
	}
	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getContactUuid() {
		return contactUuid;
	}
	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}
	public String getProviderPayerRef() {
		return providerPayerRef;
	}
	public void setProviderPayerRef(String providerPayerRef) {
		this.providerPayerRef = providerPayerRef;
	}
	public int getDefaultPaymentMethodId() {
		return defaultPaymentMethodId;
	}
	public void setDefaultPaymentMethodId(int defaultPaymentMethodId) {
		this.defaultPaymentMethodId = defaultPaymentMethodId;
	}
}

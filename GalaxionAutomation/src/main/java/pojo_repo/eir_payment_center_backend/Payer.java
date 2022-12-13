package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payer {

	private int id;
	private int billingAccountId;
	private String brand;
	private String contactUuid;
	private String providerPayerRef;
	private int defaultPaymentMethodId;
	private Date createdAt;

	public Payer() {

	}

	public Payer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			billingAccountId = rs.getInt("billing_account_id");
			brand = rs.getString("brand");
			contactUuid = rs.getString("contact_uuid");
			providerPayerRef = rs.getString("provider_payer_ref");
			defaultPaymentMethodId = rs.getInt("default_payment_method_id");
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
package microservices.backend.eir_sim_swap_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Charge {

	private int id;
	private int simSwapRequestId;
	private String catalogPricePlanCode;
	private String catalogChargeCode;
	private String description;
	private String billingType;
	private int defaultPriceVatIncluded;
	private Date createdAt;
	private Date updatedAt;

	public Charge() {

	}

	public Charge(ResultSet rs) {
		try {
			id = rs.getInt("id");
			simSwapRequestId = rs.getInt("sim_swap_request_id");
			catalogPricePlanCode = rs.getString("catalog_price_plan_code");
			catalogChargeCode = rs.getString("catalog_charge_code");
			description = rs.getString("description");
			billingType = rs.getString("billing_type");
			defaultPriceVatIncluded = rs.getInt("default_price_vat_included");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
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

	public int getSimSwapRequestId() {
		return simSwapRequestId;
	}

	public void setSimSwapRequestId(int simSwapRequestId) {
		this.simSwapRequestId = simSwapRequestId;
	}

	public String getCatalogChargeCode() {
		return catalogChargeCode;
	}

	public void setCatalogChargeCode(String catalogChargeCode) {
		this.catalogChargeCode = catalogChargeCode;
	}

	public String getCatalogPricePlanCode() {
		return catalogPricePlanCode;
	}

	public void setCatalogPricePlanCode(String catalogPricePlanCode) {
		this.catalogPricePlanCode = catalogPricePlanCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public int getDefaultPriceVatIncluded() {
		return defaultPriceVatIncluded;
	}

	public void setDefaultPriceVatIncluded(int defaultPriceVatIncluded) {
		this.defaultPriceVatIncluded = defaultPriceVatIncluded;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
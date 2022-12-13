package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundle {

	private int id;
	private int orderId;
	private int catalogAddonBundleId;
	private Date createdAt;
	private Date updatedAt;
	private Date activatedAt;
	private Date terminatedAt;
	private Date endScheduledAt;
	private int contractId;
	private String catalogCode;
	private String orderReference;
	private int accountId;
	private int serviceId;
	private int simCardId;

	public AddonBundle() {

	}

	public AddonBundle(ResultSet rs) {
		try {
			id = rs.getInt("id");
			orderId = rs.getInt("order_id");
			catalogAddonBundleId = rs.getInt("catalog_addon_bundle_id");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			activatedAt = rs.getDate("activated_at");
			terminatedAt = rs.getDate("terminated_at");
			endScheduledAt = rs.getDate("end_scheduled_at");
			contractId = rs.getInt("contract_id");
			catalogCode = rs.getString("catalog_code");
			orderReference = rs.getString("order_reference");
			accountId = rs.getInt("account_id");
			serviceId = rs.getInt("service_id");
			simCardId=rs.getInt("sim_card_id");
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCatalogAddonBundleId() {
		return catalogAddonBundleId;
	}

	public void setCatalogAddonBundleId(int catalogAddonBundleId) {
		this.catalogAddonBundleId = catalogAddonBundleId;
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

	public Date getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(Date activatedAt) {
		this.activatedAt = activatedAt;
	}

	public Date getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(Date terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public Date getEndScheduledAt() {
		return endScheduledAt;
	}

	public void setEndScheduledAt(Date endScheduledAt) {
		this.endScheduledAt = endScheduledAt;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getSimCardId() {
		return simCardId;
	}

	public void setSimCardId(int simCardId) {
		this.simCardId = simCardId;
	}
	
	

}
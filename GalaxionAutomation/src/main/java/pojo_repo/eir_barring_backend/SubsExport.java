package pojo_repo.eir_barring_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubsExport {

	private int id;
	private int serviceId;
	private int billingAccountId;
	private String provisioningAction;
	private String status;
	private int lockedByProvisioning;
	private Date createdDateTime;
	private Date updatedDateTime;
	private int barringItemId;
	private String requestorId;
	private String itemName;

	public SubsExport() {

	}

	public SubsExport(ResultSet rs) {
		try {
			id = rs.getInt("id");
			serviceId = rs.getInt("service_id");
			billingAccountId = rs.getInt("billing_account_id");
			provisioningAction = rs.getString("provisioning_action");
			status = rs.getString("status");
			lockedByProvisioning = rs.getInt("locked_by_provisioning");
			createdDateTime = rs.getDate("created_date_time");
			updatedDateTime = rs.getDate("updated_date_time");
			barringItemId = rs.getInt("barring_item_id");
			requestorId = rs.getString("requestor_id");
			itemName = rs.getString("item_name");
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

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getProvisioningAction() {
		return provisioningAction;
	}

	public void setProvisioningAction(String provisioningAction) {
		this.provisioningAction = provisioningAction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLockedByProvisioning() {
		return lockedByProvisioning;
	}

	public void setLockedByProvisioning(int lockedByProvisioning) {
		this.lockedByProvisioning = lockedByProvisioning;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public int getBarringItemId() {
		return barringItemId;
	}

	public void setBarringItemId(int barringItemId) {
		this.barringItemId = barringItemId;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
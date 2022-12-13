package pojo_repo.eir_barring_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Barring {

	private int id;
	private int serviceId;
	private int accountId;
	private int billingAccountId;
	private String provisioningAction;
	private String status;
	private Date createdDateTime;

	public Barring() {

	}

	public Barring(ResultSet rs) {
		try {
			id = rs.getInt("id");
			serviceId = rs.getInt("service_id");
			accountId = rs.getInt("account_id");
			billingAccountId = rs.getInt("billing_account_id");
			provisioningAction = rs.getString("provisioning_action");
			status = rs.getString("status");
			createdDateTime = rs.getDate("created_date_time");
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
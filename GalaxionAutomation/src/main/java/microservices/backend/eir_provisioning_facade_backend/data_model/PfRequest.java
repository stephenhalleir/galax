package microservices.backend.eir_provisioning_facade_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PfRequest {
	private int id;
	private String externalID;
	private int networkID;
	private int accountID;
	private int billingAccountID;
	private String requestAction;
	private String requestStatus;
	private int subscriptionID;
	private String billCycle;
	private String requestor;
	private int isProcessed;
	private Date createdDateTime;
	private Date updatedDateTime;

	public PfRequest() {

	}

	public PfRequest(ResultSet rs) {
		try {
			id = rs.getInt("id");
			externalID = rs.getString("external_id");
			networkID = rs.getInt("network_id");
			accountID = rs.getInt("account_id");
			billingAccountID = rs.getInt("billing_account_id");
			requestAction = rs.getString("request_action");
			requestStatus = rs.getString("request_status");
			subscriptionID = rs.getInt("subscription_id");
			billCycle = rs.getString("bill_cycle");
			requestor = rs.getString("requestor");
			isProcessed = rs.getInt("is_processed");
			createdDateTime = rs.getDate("created_date_time");
			updatedDateTime = rs.getDate("updated_date_time");
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

	public String getExternalID() {
		return externalID;
	}

	public void setExternalID(String externalID) {
		this.externalID = externalID;
	}

	public int getNetworkID() {
		return networkID;
	}

	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public String getRequestAction() {
		return requestAction;
	}

	public void setRequestAction(String requestAction) {
		this.requestAction = requestAction;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public int getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(int isProcessed) {
		this.isProcessed = isProcessed;
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
}

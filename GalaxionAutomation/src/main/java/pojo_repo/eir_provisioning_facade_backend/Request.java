package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {

	private int id;
	private String externalId;
	private int networkId;
	private int accountId;
	private int billingAccountId;
	private int catalogOfferId;
	private String requestAction;
	private String requestStatus;
	private int subscriptionId;
	private String billCycle;
	private String requestor;
	private int isProcessed;
	private Date createdDateTime;

	public Request() {

	}

	public Request(ResultSet rs) {
		try {
			id = rs.getInt("id");
			externalId = rs.getString("external_id");
			networkId = rs.getInt("network_id");
			accountId = rs.getInt("account_id");
			billingAccountId = rs.getInt("billing_account_id");
			catalogOfferId = rs.getInt("catalog_offer_id");
			requestAction = rs.getString("request_action");
			requestStatus = rs.getString("request_status");
			subscriptionId = rs.getInt("subscription_id");
			billCycle = rs.getString("bill_cycle");
			requestor = rs.getString("requestor");
			isProcessed = rs.getInt("is_processed");
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

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
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

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
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

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
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

}
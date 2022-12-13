package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivateSubscription {

	private int id;
	private int accountId;
	private String subscriptionName;
	private String currentMsisdn;
	private String iccid;
	private String newMsisdn;
	private String networkName;
	private String networkAccountId;
	private String accountType;
	private String action;
	private int scheduledPort;

	public ActivateSubscription() {

	}

	public ActivateSubscription(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			subscriptionName = rs.getString("subscription_name");
			currentMsisdn = rs.getString("current_msisdn");
			iccid = rs.getString("iccid");
			newMsisdn = rs.getString("new_msisdn");
			networkName = rs.getString("network_name");
			networkAccountId = rs.getString("network_account_id");
			accountType = rs.getString("account_type");
			action = rs.getString("action");
			scheduledPort = rs.getInt("scheduled_port");
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}

	public String getCurrentMsisdn() {
		return currentMsisdn;
	}

	public void setCurrentMsisdn(String currentMsisdn) {
		this.currentMsisdn = currentMsisdn;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getNewMsisdn() {
		return newMsisdn;
	}

	public void setNewMsisdn(String newMsisdn) {
		this.newMsisdn = newMsisdn;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getNetworkAccountId() {
		return networkAccountId;
	}

	public void setNetworkAccountId(String networkAccountId) {
		this.networkAccountId = networkAccountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getScheduledPort() {
		return scheduledPort;
	}

	public void setScheduledPort(int scheduledPort) {
		this.scheduledPort = scheduledPort;
	}

}
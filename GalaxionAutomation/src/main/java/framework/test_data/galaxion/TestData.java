package framework.test_data.galaxion;

/**
 * This class represents a piece of mobile test data retrieved from the Galaxion database
 */

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestData {

	/**
	 * Refactoring steps:
	 * 
	 * Remove field billing account ID
	 * Remove account_id getter/setters
	 * Update billing account ID getter/setters to return/set the account_id
	 * Refactor/rename getBillingAccountID setBillingAccountID
	 */
	private int accountId;
	private int billingAccountId;
	private int serviceId;
	private String msisdn;
	private int subscriptionId;
	private String contactUuid;
	private String brand;

	public TestData(ResultSet rs) {
		try {
			accountId = rs.getInt("account_id");
			
			// TODO remove
			billingAccountId = rs.getInt("billing_account_id");
			subscriptionId = rs.getInt("subscription_id");
			serviceId = rs.getInt("service_id");
			msisdn = rs.getString("msisdn");
			contactUuid = rs.getString("contact_uuid");
			brand = rs.getString("brand");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getContactUuid() {
		return contactUuid;
	}

	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "TestData [accountId=" + accountId + ", billingAccountId=" + billingAccountId + ", serviceId=" + serviceId + ", msisdn=" + msisdn
				+ ", subscriptionId=" + subscriptionId + ", contactUuid=" + contactUuid + ", brand=" + brand + "]";
	}
}

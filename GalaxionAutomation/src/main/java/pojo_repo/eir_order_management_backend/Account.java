package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

	private int id;
	private String customerAccountName;
	private int subscriptionParentAccountId;
	private int subscriptionAccountId;
	private int billingAccountId;
	private String creditClass;
	private String marketSegment;
	private int isBillable;
	private String accountType;

	public Account() {

	}

	public Account(ResultSet rs) {
		try {
			id = rs.getInt("id");
			customerAccountName = rs.getString("customer_account_name");
			subscriptionParentAccountId = rs.getInt("subscription_parent_account_id");
			subscriptionAccountId = rs.getInt("subscription_account_id");
			billingAccountId = rs.getInt("billing_account_id");
			creditClass = rs.getString("credit_class");
			marketSegment = rs.getString("market_segment");
			isBillable = rs.getInt("is_billable");
			accountType = rs.getString("account_type");
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

	public String getCustomerAccountName() {
		return customerAccountName;
	}

	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}

	public int getSubscriptionParentAccountId() {
		return subscriptionParentAccountId;
	}

	public void setSubscriptionParentAccountId(int subscriptionParentAccountId) {
		this.subscriptionParentAccountId = subscriptionParentAccountId;
	}

	public int getSubscriptionAccountId() {
		return subscriptionAccountId;
	}

	public void setSubscriptionAccountId(int subscriptionAccountId) {
		this.subscriptionAccountId = subscriptionAccountId;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getCreditClass() {
		return creditClass;
	}

	public void setCreditClass(String creditClass) {
		this.creditClass = creditClass;
	}

	public String getMarketSegment() {
		return marketSegment;
	}

	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}

	public int getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(int isBillable) {
		this.isBillable = isBillable;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
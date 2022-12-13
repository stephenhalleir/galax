package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSubscription {

	private int id;
	private int accountID;
	private int subscriptionID;

	public AccountSubscription(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountID = rs.getInt("account_id");
			subscriptionID = rs.getInt("subscription_id");
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

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	
}

package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigSubStatus {

	private int subscription;

	public MigSubStatus() {

	}

	public MigSubStatus(ResultSet rs) {
		try {
			subscription = rs.getInt("subscription");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getSubscription() {
		return subscription;
	}

	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}

}
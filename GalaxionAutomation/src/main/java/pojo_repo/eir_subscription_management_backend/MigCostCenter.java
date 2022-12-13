package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigCostCenter {

	private int subscription;
	private String msisdn;

	public MigCostCenter() {

	}

	public MigCostCenter(ResultSet rs) {
		try {
			subscription = rs.getInt("subscription");
			msisdn = rs.getString("msisdn");
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

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

}
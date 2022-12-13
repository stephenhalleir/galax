package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigSprReconcile {

	private String subscriberId;
	private String customerAccountId;
	private String msisdn;
	private String imsi;
	private String tariffPlan;

	public MigSprReconcile() {

	}

	public MigSprReconcile(ResultSet rs) {
		try {
			subscriberId = rs.getString("subscriber_id");
			customerAccountId = rs.getString("customer_account_id");
			msisdn = rs.getString("msisdn");
			imsi = rs.getString("imsi");
			tariffPlan = rs.getString("tariff_plan");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getTariffPlan() {
		return tariffPlan;
	}

	public void setTariffPlan(String tariffPlan) {
		this.tariffPlan = tariffPlan;
	}

}
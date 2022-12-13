package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDetail {

	private String serviceName;
	private int billingAccountId;
	private int subscriptionId;
	private int serviceId;
	private int accountId;
	private String serviceType;
	private String billCycle;
	private Date effectiveStartDateTime;

	public ServiceDetail() {

	}

	public ServiceDetail(ResultSet rs) {
		try {
			serviceName = rs.getString("service_name");
			billingAccountId = rs.getInt("billing_account_id");
			subscriptionId = rs.getInt("subscription_id");
			serviceId = rs.getInt("service_id");
			accountId = rs.getInt("account_id");
			serviceType = rs.getString("service_type");
			billCycle = rs.getString("bill_cycle");
			effectiveStartDateTime = rs.getDate("effective_start_date_time");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public Date getEffectiveStartDateTime() {
		return effectiveStartDateTime;
	}

	public void setEffectiveStartDateTime(Date effectiveStartDateTime) {
		this.effectiveStartDateTime = effectiveStartDateTime;
	}

}
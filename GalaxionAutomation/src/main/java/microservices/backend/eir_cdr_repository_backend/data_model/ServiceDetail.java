package microservices.backend.eir_cdr_repository_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDetail {

	private String serviceName;
	private int billingAccountID;
	private int subscriptionID;
	private int serviceID;
	private String serviceType;
	private String billCycle;
	private Date effectiveStartDateTime;
	private Date effectiveEndDateTime;
	
	public ServiceDetail() {
		
	}
	
	// populate the service detail based on a resultset object
	public ServiceDetail(ResultSet rs) {
		try {
			serviceName=rs.getString("service_name");
			billingAccountID=rs.getInt("billing_account_id");
			subscriptionID=rs.getInt("subscription_id");
			serviceID=rs.getInt("service_id");
			serviceType=rs.getString("service_type");
			billCycle=rs.getString("service_type");
			effectiveStartDateTime=rs.getDate("effective_start_date_time");
			effectiveEndDateTime=rs.getDate("effective_start_date_time");
			billCycle=rs.getString("bill_cycle");
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

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
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

	public Date getEffectiveEndDateTime() {
		return effectiveEndDateTime;
	}

	public void setEffectiveEndDateTime(Date effectiveEndDateTime) {
		this.effectiveEndDateTime = effectiveEndDateTime;
	}
}

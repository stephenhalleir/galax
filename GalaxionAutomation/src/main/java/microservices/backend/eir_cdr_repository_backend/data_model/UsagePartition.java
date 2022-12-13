package microservices.backend.eir_cdr_repository_backend.data_model;

import java.sql.Date;
import java.sql.SQLException;

import java.sql.ResultSet;

public class UsagePartition {

	private int partitionNumber;
	private String billCycle;
	private String billingPeriod;
	private Date usageFromDateTime;
	private Date usageToDateTime;
	private String partitionStatus;
	private Date lastMoifiedDateTime;
	
	public UsagePartition() {
		
	}
	
	public UsagePartition(ResultSet rs) {
		try {
			partitionNumber=rs.getInt("partition_number");
			billCycle=rs.getString("bill_cycle");
			billingPeriod=rs.getString("billing_period");
			usageFromDateTime=rs.getDate("usage_from_date_time");
			usageToDateTime=rs.getDate("usage_to_date_time");
			partitionStatus=rs.getString("partition_status");
			lastMoifiedDateTime=rs.getDate("last_modified_date_time");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPartitionNumber() {
		return partitionNumber;
	}

	public void setPartitionNumber(int partitionNumber) {
		this.partitionNumber = partitionNumber;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public String getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public Date getUsageFromDateTime() {
		return usageFromDateTime;
	}

	public void setUsageFromDateTime(Date usageFromDateTime) {
		this.usageFromDateTime = usageFromDateTime;
	}

	public Date getUsageToDateTime() {
		return usageToDateTime;
	}

	public void setUsageToDateTime(Date usageToDateTime) {
		this.usageToDateTime = usageToDateTime;
	}

	public String getPartitionStatus() {
		return partitionStatus;
	}

	public void setPartitionStatus(String partitionStatus) {
		this.partitionStatus = partitionStatus;
	}

	public Date getLastMoifiedDateTime() {
		return lastMoifiedDateTime;
	}

	public void setLastMoifiedDateTime(Date lastMoifiedDateTime) {
		this.lastMoifiedDateTime = lastMoifiedDateTime;
	}
}

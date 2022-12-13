package microservices.backend.eir_cdr_repository_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileNonUsage {

	private int id;
	private int mobileNonUsageFileID;
	private int recordType;
	private String serviceName;
	private String transactionID;
	private int status;
	private String serviceStatus;

	public MobileNonUsage() {

	}
	
	public MobileNonUsage(ResultSet rs) {
		try {
			id=rs.getInt("id");
			mobileNonUsageFileID=rs.getInt("mobile_non_usage_file_id");
			recordType=rs.getInt("record_type");
			status=rs.getInt("status");
			serviceName=rs.getString("service_name");
			transactionID=rs.getString("transaction_id");
			serviceStatus=rs.getString("service_status");
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

	public int getMobileNonUsageFileID() {
		return mobileNonUsageFileID;
	}

	public void setMobileNonUsageFileID(int mobileNonUsageFileID) {
		this.mobileNonUsageFileID = mobileNonUsageFileID;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
}

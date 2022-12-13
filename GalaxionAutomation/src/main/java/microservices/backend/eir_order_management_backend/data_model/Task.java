package microservices.backend.eir_order_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {
	private int id;
	private String type;
	private int sequence;
	private int isOptional;
	private int isBlocking;
	private int hasBreakPoint;
	private String hierarchyLevel;
	private int processID;
	private int offerID;
	private int serviceID;
	private int accountID;
	private String status;
	private String errorCode;
	private String errorMessage;
	private int retryCount;
	private Date createdDateTime;
	private Date updatedDateTime;
	
	public Task() {
		
	}
	
	public Task(ResultSet rs) {
		try {
			id=rs.getInt("id");
			type=rs.getString("type");
			sequence=rs.getInt("sequence");
			isOptional=rs.getInt("is_optional");
			isBlocking=rs.getInt("is_blocking");
			hasBreakPoint=rs.getInt("has_break_point");
			hierarchyLevel=rs.getString("hierarchy_level");
			processID=rs.getInt("process_id");
			offerID=rs.getInt("offer_id");
			serviceID=rs.getInt("service_id");
			accountID=rs.getInt("account_id");
			status=rs.getString("status");
			errorCode=rs.getString("error_code");
			errorMessage=rs.getString("error_message");
			retryCount=rs.getInt("retry_count");
			createdDateTime=rs.getDate("created_date_time");
			updatedDateTime=rs.getDate("updated_date_time");
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(int isOptional) {
		this.isOptional = isOptional;
	}

	public int getIsBlocking() {
		return isBlocking;
	}

	public void setIsBlocking(int isBlocking) {
		this.isBlocking = isBlocking;
	}

	public int getHasBreakPoint() {
		return hasBreakPoint;
	}

	public void setHasBreakPoint(int hasBreakPoint) {
		this.hasBreakPoint = hasBreakPoint;
	}

	public String getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
}

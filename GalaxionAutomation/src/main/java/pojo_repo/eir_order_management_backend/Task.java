package pojo_repo.eir_order_management_backend;

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
	private int processId;
	private int offerId;
	private int serviceId;
	private int accountId;
	private String status;
	private String errorCode;
	private String errorMessage;
	private int retryCount;
	private Date createdDateTime;

	public Task() {

	}

	public Task(ResultSet rs) {
		try {
			id = rs.getInt("id");
			type = rs.getString("type");
			sequence = rs.getInt("sequence");
			isOptional = rs.getInt("is_optional");
			isBlocking = rs.getInt("is_blocking");
			hasBreakPoint = rs.getInt("has_break_point");
			hierarchyLevel = rs.getString("hierarchy_level");
			processId = rs.getInt("process_id");
			offerId = rs.getInt("offer_id");
			serviceId = rs.getInt("service_id");
			accountId = rs.getInt("account_id");
			status = rs.getString("status");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			retryCount = rs.getInt("retry_count");
			createdDateTime = rs.getDate("created_date_time");
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

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
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

}
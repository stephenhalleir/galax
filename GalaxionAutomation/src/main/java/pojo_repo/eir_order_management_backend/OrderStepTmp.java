package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStepTmp {

	private int id;
	private int isBlocking;
	private Date createdDate;
	private String errorCode;
	private String errorMessage;
	private String event;
	private int isOptional;
	private int ordering;
	private String protocolType;
	private int retryCount;
	private String status;
	private Date updatedDate;

	public OrderStepTmp() {

	}

	public OrderStepTmp(ResultSet rs) {
		try {
			id = rs.getInt("id");
			isBlocking = rs.getInt("is_blocking");
			createdDate = rs.getDate("CREATEd_date");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			event = rs.getString("event");
			isOptional = rs.getInt("is_optional");
			ordering = rs.getInt("ordering");
			protocolType = rs.getString("protocol_type");
			retryCount = rs.getInt("retry_count");
			status = rs.getString("status");
			updatedDate = rs.getDate("updated_date");
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

	public int getIsBlocking() {
		return isBlocking;
	}

	public void setIsBlocking(int isBlocking) {
		this.isBlocking = isBlocking;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(int isOptional) {
		this.isOptional = isOptional;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Port {

	private int id;
	private int fmnpId;
	private int serviceId;
	private String orderNumber;
	private String phoneNumber;
	private int otherNetworkId;
	private String otherNetworkAccountNumber;
	private Date portStartDate;
	private int batchId;
	private String type;
	private String fmnpStatus;
	private String state;
	private String direction;
	private String brand;
	private int rejectCode;
	private int parentPortId;
	private int isPortReversalEnabled;
	private String errorDescription;
	private Date createdDateTime;

	public Port() {

	}

	public Port(ResultSet rs) {
		try {
			id = rs.getInt("id");
			fmnpId = rs.getInt("fmnp_id");
			serviceId = rs.getInt("service_id");
			orderNumber = rs.getString("order_number");
			phoneNumber = rs.getString("phone_number");
			otherNetworkId = rs.getInt("other_network_id");
			otherNetworkAccountNumber = rs.getString("other_network_account_number");
			portStartDate = rs.getDate("port_start_date");
			batchId = rs.getInt("batch_id");
			type = rs.getString("type");
			fmnpStatus = rs.getString("fmnp_status");
			state = rs.getString("state");
			direction = rs.getString("direction");
			brand = rs.getString("brand");
			rejectCode = rs.getInt("reject_code");
			parentPortId = rs.getInt("parent_port_id");
			isPortReversalEnabled = rs.getInt("is_port_reversal_enabled");
			errorDescription = rs.getString("error_description");
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

	public int getFmnpId() {
		return fmnpId;
	}

	public void setFmnpId(int fmnpId) {
		this.fmnpId = fmnpId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getOtherNetworkId() {
		return otherNetworkId;
	}

	public void setOtherNetworkId(int otherNetworkId) {
		this.otherNetworkId = otherNetworkId;
	}

	public String getOtherNetworkAccountNumber() {
		return otherNetworkAccountNumber;
	}

	public void setOtherNetworkAccountNumber(String otherNetworkAccountNumber) {
		this.otherNetworkAccountNumber = otherNetworkAccountNumber;
	}

	public Date getPortStartDate() {
		return portStartDate;
	}

	public void setPortStartDate(Date portStartDate) {
		this.portStartDate = portStartDate;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFmnpStatus() {
		return fmnpStatus;
	}

	public void setFmnpStatus(String fmnpStatus) {
		this.fmnpStatus = fmnpStatus;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getRejectCode() {
		return rejectCode;
	}

	public void setRejectCode(int rejectCode) {
		this.rejectCode = rejectCode;
	}

	public int getParentPortId() {
		return parentPortId;
	}

	public void setParentPortId(int parentPortId) {
		this.parentPortId = parentPortId;
	}

	public int getIsPortReversalEnabled() {
		return isPortReversalEnabled;
	}

	public void setIsPortReversalEnabled(int isPortReversalEnabled) {
		this.isPortReversalEnabled = isPortReversalEnabled;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
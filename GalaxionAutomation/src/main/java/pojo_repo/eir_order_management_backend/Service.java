package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {

	private int id;
	private int serviceId;
	private int networkId;
	private String serviceName;
	private String orderServiceStatus;
	private int oldServiceId;
	private int orderPortId;
	private int orderId;
	private int eventId;
	private int lockedByProvisioning;
	private String provisioningAction;
	private Date createdDateTime;

	public Service() {

	}

	public Service(ResultSet rs) {
		try {
			id = rs.getInt("id");
			serviceId = rs.getInt("service_id");
			networkId = rs.getInt("network_id");
			serviceName = rs.getString("service_name");
			orderServiceStatus = rs.getString("order_service_status");
			oldServiceId = rs.getInt("old_service_id");
			orderPortId = rs.getInt("order_port_id");
			orderId = rs.getInt("order_id");
			eventId = rs.getInt("event_id");
			lockedByProvisioning = rs.getInt("locked_by_provisioning");
			provisioningAction = rs.getString("provisioning_action");
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

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getOrderServiceStatus() {
		return orderServiceStatus;
	}

	public void setOrderServiceStatus(String orderServiceStatus) {
		this.orderServiceStatus = orderServiceStatus;
	}

	public int getOldServiceId() {
		return oldServiceId;
	}

	public void setOldServiceId(int oldServiceId) {
		this.oldServiceId = oldServiceId;
	}

	public int getOrderPortId() {
		return orderPortId;
	}

	public void setOrderPortId(int orderPortId) {
		this.orderPortId = orderPortId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getLockedByProvisioning() {
		return lockedByProvisioning;
	}

	public void setLockedByProvisioning(int lockedByProvisioning) {
		this.lockedByProvisioning = lockedByProvisioning;
	}

	public String getProvisioningAction() {
		return provisioningAction;
	}

	public void setProvisioningAction(String provisioningAction) {
		this.provisioningAction = provisioningAction;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
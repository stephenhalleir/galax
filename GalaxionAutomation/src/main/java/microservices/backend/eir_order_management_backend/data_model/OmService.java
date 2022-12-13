package microservices.backend.eir_order_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents the order-management.SERVICE table
 * @author stephenhall
 *
 */
public class OmService {

	private int id;
	private int serviceID;
	private int networkID;
	private String serviceName;
	private String orderServiceStatus;
	private int oldServiceID;
	private int orderPortID;
	private int orderID;
	private int eventID;
	private int requestID;
	private int lockedByProvisioning;
	private String provisioningAction;
	private Date createdDateTime;
	private Date updatedDateTime;
	
	public OmService() {
		
	}
	
	/**
	 * Populate from a result set
	 * @param rs
	 */
	public OmService(ResultSet rs) {
		try {
			id=rs.getInt("id");
			serviceID=rs.getInt("service_id");
			networkID=rs.getInt("network_id");
			serviceName=rs.getString("service_name");
			orderServiceStatus=rs.getString("order_service_status");
			provisioningAction=rs.getString("provisioning_action");
			oldServiceID=rs.getInt("old_service_id");
			orderPortID=rs.getInt("order_port_id");
			orderID=rs.getInt("order_id");
			eventID=rs.getInt("event_id");
			requestID=rs.getInt("request_id");
			lockedByProvisioning=rs.getInt("locked_by_provisioning");
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

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getNetworkID() {
		return networkID;
	}

	public void setNetworkID(int networkID) {
		this.networkID = networkID;
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

	public int getOldServiceID() {
		return oldServiceID;
	}

	public void setOldServiceID(int oldServiceID) {
		this.oldServiceID = oldServiceID;
	}

	public int getOrderPortID() {
		return orderPortID;
	}

	public void setOrderPortID(int orderPortID) {
		this.orderPortID = orderPortID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
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

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
}

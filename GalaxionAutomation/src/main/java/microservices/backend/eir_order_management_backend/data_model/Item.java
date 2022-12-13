package microservices.backend.eir_order_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {

	private int id;
	private int itemId;
	private String catalogItemCode;
	private String itemType;
	private String orderItemStatus;
	private int addonItemId;
	private int subscriptionItemId;
	private int deviceEnrolled;
	private int lockedByProvisioning;
	private int orderId;
	private int orderOfferId;
	private int orderServiceId;

	public Item() {

	}

	public Item(ResultSet rs) {
		try {
			id = rs.getInt("id");
			itemId = rs.getInt("item_id");
			catalogItemCode = rs.getString("catalog_item_code");
			itemType = rs.getString("item_type");
			orderItemStatus = rs.getString("order_item_status");
			addonItemId = rs.getInt("addon_item_id");
			subscriptionItemId = rs.getInt("subscription_item_id");
			deviceEnrolled = rs.getInt("device_enrolled");
			lockedByProvisioning = rs.getInt("locked_by_provisioning");
			orderId = rs.getInt("order_id");
			orderOfferId = rs.getInt("order_offer_id");
			orderServiceId = rs.getInt("order_service_id");
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

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getCatalogItemCode() {
		return catalogItemCode;
	}

	public void setCatalogItemCode(String catalogItemCode) {
		this.catalogItemCode = catalogItemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getOrderItemStatus() {
		return orderItemStatus;
	}

	public void setOrderItemStatus(String orderItemStatus) {
		this.orderItemStatus = orderItemStatus;
	}

	public int getAddonItemId() {
		return addonItemId;
	}

	public void setAddonItemId(int addonItemId) {
		this.addonItemId = addonItemId;
	}

	public int getSubscriptionItemId() {
		return subscriptionItemId;
	}

	public void setSubscriptionItemId(int subscriptionItemId) {
		this.subscriptionItemId = subscriptionItemId;
	}

	public int getDeviceEnrolled() {
		return deviceEnrolled;
	}

	public void setDeviceEnrolled(int deviceEnrolled) {
		this.deviceEnrolled = deviceEnrolled;
	}

	public int getLockedByProvisioning() {
		return lockedByProvisioning;
	}

	public void setLockedByProvisioning(int lockedByProvisioning) {
		this.lockedByProvisioning = lockedByProvisioning;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderOfferId() {
		return orderOfferId;
	}

	public void setOrderOfferId(int orderOfferId) {
		this.orderOfferId = orderOfferId;
	}

	public int getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(int orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

}
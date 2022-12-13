package microservices.backend.tecrep.resource_management.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Number {
	private int id;
	private String vanityCategory;
	private String nature;
	private String phoneNumber;
	private String status;
	private int rangeID;
	private int intervalID;
	private int serviceID;
	private String orderID;
	private String inventoryPoolCode;
	private String swapReason;
	private String packID;
	private String batchNumber;
	
	public Number() {
		
	}
	
	public Number(ResultSet rs) {
		try {
			id=rs.getInt("id");
			vanityCategory=rs.getString("vanity_category");
			nature=rs.getString("nature");
			phoneNumber=rs.getString("phone_number");
			status=rs.getString("status");
			intervalID=rs.getInt("interval_id");
			serviceID=rs.getInt("service_id");
			orderID=rs.getString("order_id");
			inventoryPoolCode=rs.getString("inventory_pool_code");
			swapReason=rs.getString("swap_reason");
			packID=rs.getString("pack_id");
			batchNumber=rs.getString("batch_number");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVanityCategory() {
		return vanityCategory;
	}

	public void setVanityCategory(String vanityCategory) {
		this.vanityCategory = vanityCategory;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRangeID() {
		return rangeID;
	}

	public void setRangeID(int rangeID) {
		this.rangeID = rangeID;
	}

	public int getIntervalID() {
		return intervalID;
	}

	public void setIntervalID(int intervalID) {
		this.intervalID = intervalID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getInventoryPoolCode() {
		return inventoryPoolCode;
	}

	public void setInventoryPoolCode(String inventoryPoolCode) {
		this.inventoryPoolCode = inventoryPoolCode;
	}

	public String getSwapReason() {
		return swapReason;
	}

	public void setSwapReason(String swapReason) {
		this.swapReason = swapReason;
	}

	public String getPackID() {
		return packID;
	}

	public void setPackID(String packID) {
		this.packID = packID;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
}

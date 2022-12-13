package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingSubtotalPostpay {

	private int id;
	private int billingAccountId;
	private int subscriptionId;
	private int serviceId;
	private int partitionNumber;
	private int duration;
	private int quantity;
	private int amount;
	private int eventCount;
	private String measuringUnit;

	public RatingSubtotalPostpay() {

	}

	public RatingSubtotalPostpay(ResultSet rs) {
		try {
			id = rs.getInt("id");
			billingAccountId = rs.getInt("billing_account_id");
			subscriptionId = rs.getInt("subscription_id");
			serviceId = rs.getInt("service_id");
			partitionNumber = rs.getInt("partition_number");
			duration = rs.getInt("duration");
			quantity = rs.getInt("quantity");
			amount = rs.getInt("amount");
			eventCount = rs.getInt("event_count");
			measuringUnit = rs.getString("measuring_unit");
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

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getPartitionNumber() {
		return partitionNumber;
	}

	public void setPartitionNumber(int partitionNumber) {
		this.partitionNumber = partitionNumber;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

}
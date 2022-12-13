package microservices.backend.eir_cdr_repository_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

import framework.test_data.generic.StringUtil;

public class RatingSubtotal {
	// fields from the RATING_SUBTOTAL_POSTPAY table
	private int id;
	private int billingAccountID;
	private int subscriptionID;
	private int serviceID;
	private int partitionNumber;
	private int duration;
	private int quantity;
	private int amount;
	private int eventCount;
	private String measuringUnit;
	private int ratingSubtotalTypeID;
	private RefRatingSubtotalType ratingSubtotalType;
	
	public RatingSubtotal() {
		
	}
	
	public RatingSubtotal(ResultSet rs) {
		try {
			id=rs.getInt("id");
			billingAccountID=rs.getInt("billing_account_id");
			subscriptionID=rs.getInt("subscription_id");
			serviceID=rs.getInt("service_id");
			partitionNumber=rs.getInt("partition_number");
			duration=rs.getInt("duration");
			quantity=rs.getInt("quantity");
			amount=rs.getInt("amount");
			eventCount=rs.getInt("event_count");
			measuringUnit=rs.getString("measuring_unit");
			ratingSubtotalTypeID=rs.getInt("rating_subtotal_type_id");
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

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
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

	public int getRatingSubtotalTypeID() {
		return ratingSubtotalTypeID;
	}

	public void setRatingSubtotalTypeID(int ratingSubtotalTypeID) {
		this.ratingSubtotalTypeID = ratingSubtotalTypeID;
	}

	public RefRatingSubtotalType getRatingSubtotalType() {
		return ratingSubtotalType;
	}

	public void setRatingSubtotalType(RefRatingSubtotalType ratingSubtotalType) {
		this.ratingSubtotalType = ratingSubtotalType;
	}
	
	public String getDisplayName() {
		return ratingSubtotalType.getRatingSubtotalDisplayName();
	}
	
	public String getDisplayCost() {		
		return StringUtil.toCurrency(amount,"€");
	}
	
	public String getDisplayTime() {		
		if(measuringUnit.equals("EVENT")) {
			return Integer.toString(eventCount);
		}
		else if(measuringUnit.equals("KB")) {
			double bytes=(double)quantity/1024/1024;
			return String.format("%.3f", bytes) + "GB";
		}
		else if(measuringUnit.equals("B")) {
			double bytes=(double)quantity/1024/1024/1024;
			return String.format("%.3f", bytes) + "GB";
		}
		else if(measuringUnit.equals("SECOND")) {
			return StringUtil.getHoursMinutesSeconds(duration);
		}
		else {
			System.err.println("Error in RatingSubtotal.getDisplayTime(): Unknown measuring unit " + measuringUnit);
			return null;
		}
	}
}

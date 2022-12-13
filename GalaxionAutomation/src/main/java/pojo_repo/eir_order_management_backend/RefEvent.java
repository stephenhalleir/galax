package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefEvent {

	private int id;
	private String eventType;
	private String brandName;
	private String channelName;
	private String subscriptionType;

	public RefEvent() {

	}

	public RefEvent(ResultSet rs) {
		try {
			id = rs.getInt("id");
			eventType = rs.getString("event_type");
			brandName = rs.getString("brand_name");
			channelName = rs.getString("channel_name");
			subscriptionType = rs.getString("subscription_type");
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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

}
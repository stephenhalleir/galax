package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestSubscriptionTerminationOld {

private int id;
private int subscriptionId;
private int isInsideCoolingOffPeriod;
private Date creationDateTime;
private String reason;

public RequestSubscriptionTerminationOld() {

}

public RequestSubscriptionTerminationOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	subscriptionId = rs.getInt("subscription_id");
	isInsideCoolingOffPeriod = rs.getInt("is_inside_cooling_off_period");
	creationDateTime = rs.getDate("creation_date_time");
	reason = rs.getString("reason");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}
public int getSubscriptionId() {
 	return subscriptionId;
}
public void setSubscriptionId(int subscriptionId) {
 	 this.subscriptionId=subscriptionId;
}
public int getIsInsideCoolingOffPeriod() {
 	return isInsideCoolingOffPeriod;
}
public void setIsInsideCoolingOffPeriod(int isInsideCoolingOffPeriod) {
 	 this.isInsideCoolingOffPeriod=isInsideCoolingOffPeriod;
}
public Date getCreationDateTime() {
 	return creationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
 	 this.creationDateTime=creationDateTime;
}
public String getReason() {
 	return reason;
}
public void setReason(String reason) {
 	 this.reason=reason;
}

}
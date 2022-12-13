package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestSubscriptionChangeOld {

private int id;
private int subscriptionId;
private int catalogOfferId;
private Date scheduledDateTime;

public RequestSubscriptionChangeOld() {

}

public RequestSubscriptionChangeOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	subscriptionId = rs.getInt("subscription_id");
	catalogOfferId = rs.getInt("catalog_offer_id");
	scheduledDateTime = rs.getDate("scheduled_date_time");
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
public int getCatalogOfferId() {
 	return catalogOfferId;
}
public void setCatalogOfferId(int catalogOfferId) {
 	 this.catalogOfferId=catalogOfferId;
}
public Date getScheduledDateTime() {
 	return scheduledDateTime;
}
public void setScheduledDateTime(Date scheduledDateTime) {
 	 this.scheduledDateTime=scheduledDateTime;
}

}
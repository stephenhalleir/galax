package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestSubscriptionChange {

private int id;
private int subscriptionId;
private int catalogOfferId;
private Date scheduledAt;

public RequestSubscriptionChange() {

}

public RequestSubscriptionChange(ResultSet rs) {
try {
	id = rs.getInt("id");
	subscriptionId = rs.getInt("subscription_id");
	catalogOfferId = rs.getInt("catalog_offer_id");
	scheduledAt = rs.getDate("scheduled_at");
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
public Date getScheduledAt() {
 	return scheduledAt;
}
public void setScheduledAt(Date scheduledAt) {
 	 this.scheduledAt=scheduledAt;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeOfferRequest {

private int id;
private int subscriptionId;
private String reason;
private Date changeOfferAt;
private String newOfferCode;
private String oldOfferCode;
private String newOrderReference;

public ChangeOfferRequest() {

}

public ChangeOfferRequest(ResultSet rs) {
try {
	id = rs.getInt("id");
	subscriptionId = rs.getInt("subscription_id");
	reason = rs.getString("reason");
	changeOfferAt = rs.getDate("change_offer_at");
	newOfferCode = rs.getString("new_offer_code");
	oldOfferCode = rs.getString("old_offer_code");
	newOrderReference = rs.getString("new_order_reference");
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
public String getReason() {
 	return reason;
}
public void setReason(String reason) {
 	 this.reason=reason;
}
public Date getChangeOfferAt() {
 	return changeOfferAt;
}
public void setChangeOfferAt(Date changeOfferAt) {
 	 this.changeOfferAt=changeOfferAt;
}
public String getNewOfferCode() {
 	return newOfferCode;
}
public void setNewOfferCode(String newOfferCode) {
 	 this.newOfferCode=newOfferCode;
}
public String getOldOfferCode() {
 	return oldOfferCode;
}
public void setOldOfferCode(String oldOfferCode) {
 	 this.oldOfferCode=oldOfferCode;
}
public String getNewOrderReference() {
 	return newOrderReference;
}
public void setNewOrderReference(String newOrderReference) {
 	 this.newOrderReference=newOrderReference;
}

}
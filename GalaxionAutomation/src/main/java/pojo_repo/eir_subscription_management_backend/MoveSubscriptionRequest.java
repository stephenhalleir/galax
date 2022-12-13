package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveSubscriptionRequest {

private int id;
private int subscriptionId;
private int oldAccountId;
private int newAccountId;

public MoveSubscriptionRequest() {

}

public MoveSubscriptionRequest(ResultSet rs) {
try {
	id = rs.getInt("id");
	subscriptionId = rs.getInt("subscription_id");
	oldAccountId = rs.getInt("old_account_id");
	newAccountId = rs.getInt("new_account_id");
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
public int getOldAccountId() {
 	return oldAccountId;
}
public void setOldAccountId(int oldAccountId) {
 	 this.oldAccountId=oldAccountId;
}
public int getNewAccountId() {
 	return newAccountId;
}
public void setNewAccountId(int newAccountId) {
 	 this.newAccountId=newAccountId;
}

}
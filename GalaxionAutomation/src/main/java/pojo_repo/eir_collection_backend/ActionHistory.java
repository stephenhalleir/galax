package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionHistory {

private int id;
private String type;
private String userName;
private int flowId;
private int billingAccountId;
private Date createdAt;

public ActionHistory() {

}

public ActionHistory(ResultSet rs) {
try {
	id = rs.getInt("id");
	type = rs.getString("type");
	userName = rs.getString("user_name");
	flowId = rs.getInt("flow_id");
	billingAccountId = rs.getInt("billing_account_id");
	createdAt = rs.getDate("created_at");
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
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public String getUserName() {
 	return userName;
}
public void setUserName(String userName) {
 	 this.userName=userName;
}
public int getFlowId() {
 	return flowId;
}
public void setFlowId(int flowId) {
 	 this.flowId=flowId;
}
public int getBillingAccountId() {
 	return billingAccountId;
}
public void setBillingAccountId(int billingAccountId) {
 	 this.billingAccountId=billingAccountId;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
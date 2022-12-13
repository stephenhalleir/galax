package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefSubscriptionTerminationReason {

private int id;
private String primaryReason;
private String secondaryReason;
private String accountType;
private int displayable;
private int displayableOrder;
private int eccWaived;
private int fromSystem;
private Date createdAt;

public RefSubscriptionTerminationReason() {

}

public RefSubscriptionTerminationReason(ResultSet rs) {
try {
	id = rs.getInt("id");
	primaryReason = rs.getString("primary_reason");
	secondaryReason = rs.getString("secondary_reason");
	accountType = rs.getString("account_type");
	displayable = rs.getInt("displayable");
	displayableOrder = rs.getInt("displayable_order");
	eccWaived = rs.getInt("ecc_waived");
	fromSystem = rs.getInt("from_system");
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
public String getPrimaryReason() {
 	return primaryReason;
}
public void setPrimaryReason(String primaryReason) {
 	 this.primaryReason=primaryReason;
}
public String getSecondaryReason() {
 	return secondaryReason;
}
public void setSecondaryReason(String secondaryReason) {
 	 this.secondaryReason=secondaryReason;
}
public String getAccountType() {
 	return accountType;
}
public void setAccountType(String accountType) {
 	 this.accountType=accountType;
}
public int getDisplayable() {
 	return displayable;
}
public void setDisplayable(int displayable) {
 	 this.displayable=displayable;
}
public int getDisplayableOrder() {
 	return displayableOrder;
}
public void setDisplayableOrder(int displayableOrder) {
 	 this.displayableOrder=displayableOrder;
}
public int getEccWaived() {
 	return eccWaived;
}
public void setEccWaived(int eccWaived) {
 	 this.eccWaived=eccWaived;
}
public int getFromSystem() {
 	return fromSystem;
}
public void setFromSystem(int fromSystem) {
 	 this.fromSystem=fromSystem;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
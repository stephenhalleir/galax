package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionItemOld {

private int id;
private Date creationDateTime;
private Date startDateTime;
private int itemId;

public SubscriptionItemOld() {

}

public SubscriptionItemOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	creationDateTime = rs.getDate("creation_date_time");
	startDateTime = rs.getDate("start_date_time");
	itemId = rs.getInt("item_id");
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
public Date getCreationDateTime() {
 	return creationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
 	 this.creationDateTime=creationDateTime;
}
public Date getStartDateTime() {
 	return startDateTime;
}
public void setStartDateTime(Date startDateTime) {
 	 this.startDateTime=startDateTime;
}
public int getItemId() {
 	return itemId;
}
public void setItemId(int itemId) {
 	 this.itemId=itemId;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemTerminationRequestOld {

private int id;
private int serviceId;
private int itemId;
private Date scheduledDateTime;

public ItemTerminationRequestOld() {

}

public ItemTerminationRequestOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	serviceId = rs.getInt("service_id");
	itemId = rs.getInt("item_id");
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
public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}
public int getItemId() {
 	return itemId;
}
public void setItemId(int itemId) {
 	 this.itemId=itemId;
}
public Date getScheduledDateTime() {
 	return scheduledDateTime;
}
public void setScheduledDateTime(Date scheduledDateTime) {
 	 this.scheduledDateTime=scheduledDateTime;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestServiceTermination {

private int id;
private int serviceId;
private Date creationDateTime;
private String reason;
private String otherReason;

public RequestServiceTermination() {

}

public RequestServiceTermination(ResultSet rs) {
try {
	id = rs.getInt("id");
	serviceId = rs.getInt("service_id");
	creationDateTime = rs.getDate("creation_date_time");
	reason = rs.getString("reason");
	otherReason = rs.getString("other_reason");
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
public String getOtherReason() {
 	return otherReason;
}
public void setOtherReason(String otherReason) {
 	 this.otherReason=otherReason;
}

}
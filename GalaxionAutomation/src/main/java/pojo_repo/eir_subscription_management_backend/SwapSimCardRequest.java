package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SwapSimCardRequest {

private int id;
private int serviceId;
private int oldSimCardId;
private int newSimCardId;
private Date createdAt;

public SwapSimCardRequest() {

}

public SwapSimCardRequest(ResultSet rs) {
try {
	id = rs.getInt("id");
	serviceId = rs.getInt("service_id");
	oldSimCardId = rs.getInt("old_sim_card_id");
	newSimCardId = rs.getInt("new_sim_card_id");
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
public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}
public int getOldSimCardId() {
 	return oldSimCardId;
}
public void setOldSimCardId(int oldSimCardId) {
 	 this.oldSimCardId=oldSimCardId;
}
public int getNewSimCardId() {
 	return newSimCardId;
}
public void setNewSimCardId(int newSimCardId) {
 	 this.newSimCardId=newSimCardId;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
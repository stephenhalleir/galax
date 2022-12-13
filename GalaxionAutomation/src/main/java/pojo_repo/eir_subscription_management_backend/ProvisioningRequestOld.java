package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningRequestOld {

private int id;
private Date startDateTime;
private String status;

public ProvisioningRequestOld() {

}

public ProvisioningRequestOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	startDateTime = rs.getDate("start_date_time");
	status = rs.getString("status");
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
public Date getStartDateTime() {
 	return startDateTime;
}
public void setStartDateTime(Date startDateTime) {
 	 this.startDateTime=startDateTime;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}

}
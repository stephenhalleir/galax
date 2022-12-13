package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoiceMailInitial {

private int serviceid;
private String name;
private String status;
private Date createDate;

public VoiceMailInitial() {

}

public VoiceMailInitial(ResultSet rs) {
try {
	serviceid = rs.getInt("serviceId");
	name = rs.getString("name");
	status = rs.getString("status");
	createDate = rs.getDate("create_date");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getServiceid() {
 	return serviceid;
}
public void setServiceid(int serviceid) {
 	 this.serviceid=serviceid;
}
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public Date getCreateDate() {
 	return createDate;
}
public void setCreateDate(Date createDate) {
 	 this.createDate=createDate;
}

}
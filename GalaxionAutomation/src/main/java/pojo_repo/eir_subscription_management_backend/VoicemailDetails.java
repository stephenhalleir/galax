package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoicemailDetails {

private int mnpid;
private int serviceId;
private String phoneNumber;
private String state;
private Date portStartDate;

public VoicemailDetails() {

}

public VoicemailDetails(ResultSet rs) {
try {
	mnpid = rs.getInt("mnpId");
	serviceId = rs.getInt("service_id");
	phoneNumber = rs.getString("phone_number");
	state = rs.getString("state");
	portStartDate = rs.getDate("port_start_Date");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getMnpid() {
 	return mnpid;
}
public void setMnpid(int mnpid) {
 	 this.mnpid=mnpid;
}
public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}
public String getPhoneNumber() {
 	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
 	 this.phoneNumber=phoneNumber;
}
public String getState() {
 	return state;
}
public void setState(String state) {
 	 this.state=state;
}
public Date getPortStartDate() {
 	return portStartDate;
}
public void setPortStartDate(Date portStartDate) {
 	 this.portStartDate=portStartDate;
}

}
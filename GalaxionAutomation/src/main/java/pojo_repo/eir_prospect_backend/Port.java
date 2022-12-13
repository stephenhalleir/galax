package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Port {

private int oldId;
private String phoneNumber;
private int otherNetworkId;
private String otherNetworkName;
private String otherNetworkAccountNumber;
private Date portStartDateTime;
private String type;

public Port() {

}

public Port(ResultSet rs) {
try {
	oldId = rs.getInt("old_id");
	phoneNumber = rs.getString("phone_number");
	otherNetworkId = rs.getInt("other_network_id");
	otherNetworkName = rs.getString("other_network_name");
	otherNetworkAccountNumber = rs.getString("other_network_account_number");
	portStartDateTime = rs.getDate("port_start_date_time");
	type = rs.getString("type");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getOldId() {
 	return oldId;
}
public void setOldId(int oldId) {
 	 this.oldId=oldId;
}
public String getPhoneNumber() {
 	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
 	 this.phoneNumber=phoneNumber;
}
public int getOtherNetworkId() {
 	return otherNetworkId;
}
public void setOtherNetworkId(int otherNetworkId) {
 	 this.otherNetworkId=otherNetworkId;
}
public String getOtherNetworkName() {
 	return otherNetworkName;
}
public void setOtherNetworkName(String otherNetworkName) {
 	 this.otherNetworkName=otherNetworkName;
}
public String getOtherNetworkAccountNumber() {
 	return otherNetworkAccountNumber;
}
public void setOtherNetworkAccountNumber(String otherNetworkAccountNumber) {
 	 this.otherNetworkAccountNumber=otherNetworkAccountNumber;
}
public Date getPortStartDateTime() {
 	return portStartDateTime;
}
public void setPortStartDateTime(Date portStartDateTime) {
 	 this.portStartDateTime=portStartDateTime;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}

}
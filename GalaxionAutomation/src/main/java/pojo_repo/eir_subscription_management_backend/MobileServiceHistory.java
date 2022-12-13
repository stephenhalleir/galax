package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileServiceHistory {

private int id;
private int mobileServiceId;
private String msisdn;

public MobileServiceHistory() {

}

public MobileServiceHistory(ResultSet rs) {
try {
	id = rs.getInt("id");
	mobileServiceId = rs.getInt("mobile_service_id");
	msisdn = rs.getString("msisdn");
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
public int getMobileServiceId() {
 	return mobileServiceId;
}
public void setMobileServiceId(int mobileServiceId) {
 	 this.mobileServiceId=mobileServiceId;
}
public String getMsisdn() {
 	return msisdn;
}
public void setMsisdn(String msisdn) {
 	 this.msisdn=msisdn;
}

}
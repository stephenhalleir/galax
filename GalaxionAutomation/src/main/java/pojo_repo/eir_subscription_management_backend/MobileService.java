package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileService {

private int id;
private String msisdn;

public MobileService() {

}

public MobileService(ResultSet rs) {
try {
	id = rs.getInt("id");
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
public String getMsisdn() {
 	return msisdn;
}
public void setMsisdn(String msisdn) {
 	 this.msisdn=msisdn;
}

}
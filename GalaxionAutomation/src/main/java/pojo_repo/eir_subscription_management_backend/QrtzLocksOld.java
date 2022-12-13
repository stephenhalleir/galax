package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzLocksOld {

private String schedName;

public QrtzLocksOld() {

}

public QrtzLocksOld(ResultSet rs) {
try {
	schedName = rs.getString("SCHED_NAME");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getSchedName() {
 	return schedName;
}
public void setSchedName(String schedName) {
 	 this.schedName=schedName;
}

}
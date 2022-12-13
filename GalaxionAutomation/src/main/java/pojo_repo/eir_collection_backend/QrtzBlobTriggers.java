package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzBlobTriggers {

private String schedName;
private String triggerName;
private String triggerGroup;

public QrtzBlobTriggers() {

}

public QrtzBlobTriggers(ResultSet rs) {
try {
	schedName = rs.getString("SCHED_NAME");
	triggerName = rs.getString("TRIGGER_NAME");
	triggerGroup = rs.getString("TRIGGER_GROUP");
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
public String getTriggerName() {
 	return triggerName;
}
public void setTriggerName(String triggerName) {
 	 this.triggerName=triggerName;
}
public String getTriggerGroup() {
 	return triggerGroup;
}
public void setTriggerGroup(String triggerGroup) {
 	 this.triggerGroup=triggerGroup;
}

}
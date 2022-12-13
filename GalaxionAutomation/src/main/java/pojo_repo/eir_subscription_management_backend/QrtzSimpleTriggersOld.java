package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzSimpleTriggersOld {

private String schedName;
private String triggerName;
private String triggerGroup;
private int repeatCount;
private int repeatInterval;

public QrtzSimpleTriggersOld() {

}

public QrtzSimpleTriggersOld(ResultSet rs) {
try {
	schedName = rs.getString("SCHED_NAME");
	triggerName = rs.getString("TRIGGER_NAME");
	triggerGroup = rs.getString("TRIGGER_GROUP");
	repeatCount = rs.getInt("REPEAT_COUNT");
	repeatInterval = rs.getInt("REPEAT_INTERVAL");
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
public int getRepeatCount() {
 	return repeatCount;
}
public void setRepeatCount(int repeatCount) {
 	 this.repeatCount=repeatCount;
}
public int getRepeatInterval() {
 	return repeatInterval;
}
public void setRepeatInterval(int repeatInterval) {
 	 this.repeatInterval=repeatInterval;
}

}
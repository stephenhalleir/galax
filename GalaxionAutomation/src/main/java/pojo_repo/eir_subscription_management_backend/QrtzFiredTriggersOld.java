package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzFiredTriggersOld {

private String schedName;
private String entryId;
private String triggerName;
private String triggerGroup;
private String instanceName;
private int firedTime;
private int schedTime;
private int priority;
private String state;
private String jobName;
private String jobGroup;
private int isNonconcurrent;

public QrtzFiredTriggersOld() {

}

public QrtzFiredTriggersOld(ResultSet rs) {
try {
	schedName = rs.getString("SCHED_NAME");
	entryId = rs.getString("ENTRY_ID");
	triggerName = rs.getString("TRIGGER_NAME");
	triggerGroup = rs.getString("TRIGGER_GROUP");
	instanceName = rs.getString("INSTANCE_NAME");
	firedTime = rs.getInt("FIRED_TIME");
	schedTime = rs.getInt("SCHED_TIME");
	priority = rs.getInt("PRIORITY");
	state = rs.getString("STATE");
	jobName = rs.getString("JOB_NAME");
	jobGroup = rs.getString("JOB_GROUP");
	isNonconcurrent = rs.getInt("IS_NONCONCURRENT");
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
public String getEntryId() {
 	return entryId;
}
public void setEntryId(String entryId) {
 	 this.entryId=entryId;
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
public String getInstanceName() {
 	return instanceName;
}
public void setInstanceName(String instanceName) {
 	 this.instanceName=instanceName;
}
public int getFiredTime() {
 	return firedTime;
}
public void setFiredTime(int firedTime) {
 	 this.firedTime=firedTime;
}
public int getSchedTime() {
 	return schedTime;
}
public void setSchedTime(int schedTime) {
 	 this.schedTime=schedTime;
}
public int getPriority() {
 	return priority;
}
public void setPriority(int priority) {
 	 this.priority=priority;
}
public String getState() {
 	return state;
}
public void setState(String state) {
 	 this.state=state;
}
public String getJobName() {
 	return jobName;
}
public void setJobName(String jobName) {
 	 this.jobName=jobName;
}
public String getJobGroup() {
 	return jobGroup;
}
public void setJobGroup(String jobGroup) {
 	 this.jobGroup=jobGroup;
}
public int getIsNonconcurrent() {
 	return isNonconcurrent;
}
public void setIsNonconcurrent(int isNonconcurrent) {
 	 this.isNonconcurrent=isNonconcurrent;
}

}
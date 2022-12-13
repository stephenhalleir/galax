package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzJobDetailsOld {

private String schedName;
private String jobName;
private String jobGroup;
private String description;
private String jobClassName;
private int isDurable;
private int isNonconcurrent;
private int isUpdateData;
private int requestsRecovery;

public QrtzJobDetailsOld() {

}

public QrtzJobDetailsOld(ResultSet rs) {
try {
	schedName = rs.getString("SCHED_NAME");
	jobName = rs.getString("JOB_NAME");
	jobGroup = rs.getString("JOB_GROUP");
	description = rs.getString("DESCRIPTION");
	jobClassName = rs.getString("JOB_CLASS_NAME");
	isDurable = rs.getInt("IS_DURABLE");
	isNonconcurrent = rs.getInt("IS_NONCONCURRENT");
	isUpdateData = rs.getInt("IS_UPDATE_DATA");
	requestsRecovery = rs.getInt("REQUESTS_RECOVERY");
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
public String getDescription() {
 	return description;
}
public void setDescription(String description) {
 	 this.description=description;
}
public String getJobClassName() {
 	return jobClassName;
}
public void setJobClassName(String jobClassName) {
 	 this.jobClassName=jobClassName;
}
public int getIsDurable() {
 	return isDurable;
}
public void setIsDurable(int isDurable) {
 	 this.isDurable=isDurable;
}
public int getIsNonconcurrent() {
 	return isNonconcurrent;
}
public void setIsNonconcurrent(int isNonconcurrent) {
 	 this.isNonconcurrent=isNonconcurrent;
}
public int getIsUpdateData() {
 	return isUpdateData;
}
public void setIsUpdateData(int isUpdateData) {
 	 this.isUpdateData=isUpdateData;
}
public int getRequestsRecovery() {
 	return requestsRecovery;
}
public void setRequestsRecovery(int requestsRecovery) {
 	 this.requestsRecovery=requestsRecovery;
}

}
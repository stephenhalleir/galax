package microservices.backend.eir_collection_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Step {

private int id;
private String errorCode;
private String errorMessage;
private Date lastProcessedAt;
private String name;
private int ordering;
private String status;
private int tryCount;
private int waitTime;
private int flowId;
private String definition;
private Date nextProcessingTime;
private String preErrorStatus;
private Date createdAt;

public Step() {

}

public Step(ResultSet rs) {
try {
	id = rs.getInt("id");
	errorCode = rs.getString("error_code");
	errorMessage = rs.getString("error_message");
	lastProcessedAt = rs.getDate("last_processed_at");
	name = rs.getString("name");
	ordering = rs.getInt("ordering");
	status = rs.getString("status");
	tryCount = rs.getInt("try_count");
	waitTime = rs.getInt("wait_time");
	flowId = rs.getInt("flow_id");
	definition = rs.getString("definition");
	nextProcessingTime = rs.getDate("next_processing_time");
	preErrorStatus = rs.getString("pre_error_status");
	createdAt = rs.getDate("created_at");
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
public String getErrorCode() {
 	return errorCode;
}
public void setErrorCode(String errorCode) {
 	 this.errorCode=errorCode;
}
public String getErrorMessage() {
 	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
 	 this.errorMessage=errorMessage;
}
public Date getLastProcessedAt() {
 	return lastProcessedAt;
}
public void setLastProcessedAt(Date lastProcessedAt) {
 	 this.lastProcessedAt=lastProcessedAt;
}
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public int getOrdering() {
 	return ordering;
}
public void setOrdering(int ordering) {
 	 this.ordering=ordering;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public int getTryCount() {
 	return tryCount;
}
public void setTryCount(int tryCount) {
 	 this.tryCount=tryCount;
}
public int getWaitTime() {
 	return waitTime;
}
public void setWaitTime(int waitTime) {
 	 this.waitTime=waitTime;
}
public int getFlowId() {
 	return flowId;
}
public void setFlowId(int flowId) {
 	 this.flowId=flowId;
}
public String getDefinition() {
 	return definition;
}
public void setDefinition(String definition) {
 	 this.definition=definition;
}
public Date getNextProcessingTime() {
 	return nextProcessingTime;
}
public void setNextProcessingTime(Date nextProcessingTime) {
 	 this.nextProcessingTime=nextProcessingTime;
}
public String getPreErrorStatus() {
 	return preErrorStatus;
}
public void setPreErrorStatus(String preErrorStatus) {
 	 this.preErrorStatus=preErrorStatus;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
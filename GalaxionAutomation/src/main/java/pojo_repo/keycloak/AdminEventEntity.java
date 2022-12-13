package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminEventEntity {

private String id;
private int adminEventTime;
private String realmId;
private String operationType;
private String authRealmId;
private String authClientId;
private String authUserId;
private String ipAddress;
private String resourcePath;
private String representation;
private String error;

public AdminEventEntity() {

}

public AdminEventEntity(ResultSet rs) {
try {
	id = rs.getString("ID");
	adminEventTime = rs.getInt("ADMIN_EVENT_TIME");
	realmId = rs.getString("REALM_ID");
	operationType = rs.getString("OPERATION_TYPE");
	authRealmId = rs.getString("AUTH_REALM_ID");
	authClientId = rs.getString("AUTH_CLIENT_ID");
	authUserId = rs.getString("AUTH_USER_ID");
	ipAddress = rs.getString("IP_ADDRESS");
	resourcePath = rs.getString("RESOURCE_PATH");
	representation = rs.getString("REPRESENTATION");
	error = rs.getString("ERROR");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getId() {
 	return id;
}
public void setId(String id) {
 	 this.id=id;
}
public int getAdminEventTime() {
 	return adminEventTime;
}
public void setAdminEventTime(int adminEventTime) {
 	 this.adminEventTime=adminEventTime;
}
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}
public String getOperationType() {
 	return operationType;
}
public void setOperationType(String operationType) {
 	 this.operationType=operationType;
}
public String getAuthRealmId() {
 	return authRealmId;
}
public void setAuthRealmId(String authRealmId) {
 	 this.authRealmId=authRealmId;
}
public String getAuthClientId() {
 	return authClientId;
}
public void setAuthClientId(String authClientId) {
 	 this.authClientId=authClientId;
}
public String getAuthUserId() {
 	return authUserId;
}
public void setAuthUserId(String authUserId) {
 	 this.authUserId=authUserId;
}
public String getIpAddress() {
 	return ipAddress;
}
public void setIpAddress(String ipAddress) {
 	 this.ipAddress=ipAddress;
}
public String getResourcePath() {
 	return resourcePath;
}
public void setResourcePath(String resourcePath) {
 	 this.resourcePath=resourcePath;
}
public String getRepresentation() {
 	return representation;
}
public void setRepresentation(String representation) {
 	 this.representation=representation;
}
public String getError() {
 	return error;
}
public void setError(String error) {
 	 this.error=error;
}

}
package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientSession {

private String id;
private String clientId;
private String redirectUri;
private String state;
private int timestamp;
private String sessionId;
private String authMethod;
private String realmId;
private String authUserId;

public ClientSession() {

}

public ClientSession(ResultSet rs) {
try {
	id = rs.getString("ID");
	clientId = rs.getString("CLIENT_ID");
	redirectUri = rs.getString("REDIRECT_URI");
	state = rs.getString("STATE");
	timestamp = rs.getInt("TIMESTAMP");
	sessionId = rs.getString("SESSION_ID");
	authMethod = rs.getString("AUTH_METHOD");
	realmId = rs.getString("REALM_ID");
	authUserId = rs.getString("AUTH_USER_ID");
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
public String getClientId() {
 	return clientId;
}
public void setClientId(String clientId) {
 	 this.clientId=clientId;
}
public String getRedirectUri() {
 	return redirectUri;
}
public void setRedirectUri(String redirectUri) {
 	 this.redirectUri=redirectUri;
}
public String getState() {
 	return state;
}
public void setState(String state) {
 	 this.state=state;
}
public int getTimestamp() {
 	return timestamp;
}
public void setTimestamp(int timestamp) {
 	 this.timestamp=timestamp;
}
public String getSessionId() {
 	return sessionId;
}
public void setSessionId(String sessionId) {
 	 this.sessionId=sessionId;
}
public String getAuthMethod() {
 	return authMethod;
}
public void setAuthMethod(String authMethod) {
 	 this.authMethod=authMethod;
}
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}
public String getAuthUserId() {
 	return authUserId;
}
public void setAuthUserId(String authUserId) {
 	 this.authUserId=authUserId;
}

}
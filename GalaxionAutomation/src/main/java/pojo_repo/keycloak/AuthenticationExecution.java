package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationExecution {

private String id;
private String alias;
private String authenticator;
private String realmId;
private String flowId;
private int requirement;
private int priority;
private int authenticatorFlow;
private String authFlowId;

public AuthenticationExecution() {

}

public AuthenticationExecution(ResultSet rs) {
try {
	id = rs.getString("ID");
	alias = rs.getString("ALIAS");
	authenticator = rs.getString("AUTHENTICATOR");
	realmId = rs.getString("REALM_ID");
	flowId = rs.getString("FLOW_ID");
	requirement = rs.getInt("REQUIREMENT");
	priority = rs.getInt("PRIORITY");
	authenticatorFlow = rs.getInt("AUTHENTICATOR_FLOW");
	authFlowId = rs.getString("AUTH_FLOW_ID");
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
public String getAlias() {
 	return alias;
}
public void setAlias(String alias) {
 	 this.alias=alias;
}
public String getAuthenticator() {
 	return authenticator;
}
public void setAuthenticator(String authenticator) {
 	 this.authenticator=authenticator;
}
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}
public String getFlowId() {
 	return flowId;
}
public void setFlowId(String flowId) {
 	 this.flowId=flowId;
}
public int getRequirement() {
 	return requirement;
}
public void setRequirement(int requirement) {
 	 this.requirement=requirement;
}
public int getPriority() {
 	return priority;
}
public void setPriority(int priority) {
 	 this.priority=priority;
}
public int getAuthenticatorFlow() {
 	return authenticatorFlow;
}
public void setAuthenticatorFlow(int authenticatorFlow) {
 	 this.authenticatorFlow=authenticatorFlow;
}
public String getAuthFlowId() {
 	return authFlowId;
}
public void setAuthFlowId(String authFlowId) {
 	 this.authFlowId=authFlowId;
}

}
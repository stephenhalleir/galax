package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientScopeClient {

private String clientId;
private String scopeId;

public ClientScopeClient() {

}

public ClientScopeClient(ResultSet rs) {
try {
	clientId = rs.getString("CLIENT_ID");
	scopeId = rs.getString("SCOPE_ID");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getClientId() {
 	return clientId;
}
public void setClientId(String clientId) {
 	 this.clientId=clientId;
}
public String getScopeId() {
 	return scopeId;
}
public void setScopeId(String scopeId) {
 	 this.scopeId=scopeId;
}

}
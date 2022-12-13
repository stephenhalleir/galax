package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAuthFlowBindings {

private String clientId;
private String flowId;

public ClientAuthFlowBindings() {

}

public ClientAuthFlowBindings(ResultSet rs) {
try {
	clientId = rs.getString("CLIENT_ID");
	flowId = rs.getString("FLOW_ID");
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
public String getFlowId() {
 	return flowId;
}
public void setFlowId(String flowId) {
 	 this.flowId=flowId;
}

}
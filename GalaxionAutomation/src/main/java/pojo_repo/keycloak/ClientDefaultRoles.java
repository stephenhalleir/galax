package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDefaultRoles {

private String clientId;

public ClientDefaultRoles() {

}

public ClientDefaultRoles(ResultSet rs) {
try {
	clientId = rs.getString("CLIENT_ID");
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

}
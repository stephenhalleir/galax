package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAttributes {

private String clientId;
private String value;

public ClientAttributes() {

}

public ClientAttributes(ResultSet rs) {
try {
	clientId = rs.getString("CLIENT_ID");
	value = rs.getString("VALUE");
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
public String getValue() {
 	return value;
}
public void setValue(String value) {
 	 this.value=value;
}

}
package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientNodeRegistrations {

private String clientId;
private int value;

public ClientNodeRegistrations() {

}

public ClientNodeRegistrations(ResultSet rs) {
try {
	clientId = rs.getString("CLIENT_ID");
	value = rs.getInt("VALUE");
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
public int getValue() {
 	return value;
}
public void setValue(int value) {
 	 this.value=value;
}

}
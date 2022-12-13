package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientSessionAuthStatus {

private String authenticator;
private int status;

public ClientSessionAuthStatus() {

}

public ClientSessionAuthStatus(ResultSet rs) {
try {
	authenticator = rs.getString("AUTHENTICATOR");
	status = rs.getInt("STATUS");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getAuthenticator() {
 	return authenticator;
}
public void setAuthenticator(String authenticator) {
 	 this.authenticator=authenticator;
}
public int getStatus() {
 	return status;
}
public void setStatus(int status) {
 	 this.status=status;
}

}
package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticatorConfigEntry {

private String authenticatorId;
private String value;

public AuthenticatorConfigEntry() {

}

public AuthenticatorConfigEntry(ResultSet rs) {
try {
	authenticatorId = rs.getString("AUTHENTICATOR_ID");
	value = rs.getString("VALUE");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getAuthenticatorId() {
 	return authenticatorId;
}
public void setAuthenticatorId(String authenticatorId) {
 	 this.authenticatorId=authenticatorId;
}
public String getValue() {
 	return value;
}
public void setValue(String value) {
 	 this.value=value;
}

}
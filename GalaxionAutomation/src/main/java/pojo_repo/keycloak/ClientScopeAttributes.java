package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientScopeAttributes {

private String scopeId;
private String value;

public ClientScopeAttributes() {

}

public ClientScopeAttributes(ResultSet rs) {
try {
	scopeId = rs.getString("SCOPE_ID");
	value = rs.getString("VALUE");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getScopeId() {
 	return scopeId;
}
public void setScopeId(String scopeId) {
 	 this.scopeId=scopeId;
}
public String getValue() {
 	return value;
}
public void setValue(String value) {
 	 this.value=value;
}

}
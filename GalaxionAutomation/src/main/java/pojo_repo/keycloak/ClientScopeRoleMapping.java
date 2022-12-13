package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientScopeRoleMapping {

private String scopeId;

public ClientScopeRoleMapping() {

}

public ClientScopeRoleMapping(ResultSet rs) {
try {
	scopeId = rs.getString("SCOPE_ID");
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

}
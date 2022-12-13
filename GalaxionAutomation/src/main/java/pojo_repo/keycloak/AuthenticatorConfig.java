package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticatorConfig {

private String id;
private String alias;

public AuthenticatorConfig() {

}

public AuthenticatorConfig(ResultSet rs) {
try {
	id = rs.getString("ID");
	alias = rs.getString("ALIAS");
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

}
package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationFlow {

private String id;
private String alias;
private String description;
private String realmId;
private String providerId;
private int topLevel;

public AuthenticationFlow() {

}

public AuthenticationFlow(ResultSet rs) {
try {
	id = rs.getString("ID");
	alias = rs.getString("ALIAS");
	description = rs.getString("DESCRIPTION");
	realmId = rs.getString("REALM_ID");
	providerId = rs.getString("PROVIDER_ID");
	topLevel = rs.getInt("TOP_LEVEL");
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
public String getDescription() {
 	return description;
}
public void setDescription(String description) {
 	 this.description=description;
}
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}
public String getProviderId() {
 	return providerId;
}
public void setProviderId(String providerId) {
 	 this.providerId=providerId;
}
public int getTopLevel() {
 	return topLevel;
}
public void setTopLevel(int topLevel) {
 	 this.topLevel=topLevel;
}

}
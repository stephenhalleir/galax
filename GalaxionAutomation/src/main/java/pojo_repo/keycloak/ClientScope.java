package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientScope {

private String id;
private String name;
private String realmId;
private String description;

public ClientScope() {

}

public ClientScope(ResultSet rs) {
try {
	id = rs.getString("ID");
	name = rs.getString("NAME");
	realmId = rs.getString("REALM_ID");
	description = rs.getString("DESCRIPTION");
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
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}
public String getDescription() {
 	return description;
}
public void setDescription(String description) {
 	 this.description=description;
}

}
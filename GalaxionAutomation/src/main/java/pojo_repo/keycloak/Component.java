package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Component {

private String id;
private String name;
private String parentId;
private String providerId;
private String providerType;
private String realmId;

public Component() {

}

public Component(ResultSet rs) {
try {
	id = rs.getString("ID");
	name = rs.getString("NAME");
	parentId = rs.getString("PARENT_ID");
	providerId = rs.getString("PROVIDER_ID");
	providerType = rs.getString("PROVIDER_TYPE");
	realmId = rs.getString("REALM_ID");
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
public String getParentId() {
 	return parentId;
}
public void setParentId(String parentId) {
 	 this.parentId=parentId;
}
public String getProviderId() {
 	return providerId;
}
public void setProviderId(String providerId) {
 	 this.providerId=providerId;
}
public String getProviderType() {
 	return providerType;
}
public void setProviderType(String providerType) {
 	 this.providerType=providerType;
}
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}

}
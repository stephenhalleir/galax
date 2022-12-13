package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrokerLink {

private String identityProvider;
private String storageProviderId;
private String realmId;
private String brokerUserId;
private String brokerUsername;
private String token;

public BrokerLink() {

}

public BrokerLink(ResultSet rs) {
try {
	identityProvider = rs.getString("IDENTITY_PROVIDER");
	storageProviderId = rs.getString("STORAGE_PROVIDER_ID");
	realmId = rs.getString("REALM_ID");
	brokerUserId = rs.getString("BROKER_USER_ID");
	brokerUsername = rs.getString("BROKER_USERNAME");
	token = rs.getString("TOKEN");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getIdentityProvider() {
 	return identityProvider;
}
public void setIdentityProvider(String identityProvider) {
 	 this.identityProvider=identityProvider;
}
public String getStorageProviderId() {
 	return storageProviderId;
}
public void setStorageProviderId(String storageProviderId) {
 	 this.storageProviderId=storageProviderId;
}
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}
public String getBrokerUserId() {
 	return brokerUserId;
}
public void setBrokerUserId(String brokerUserId) {
 	 this.brokerUserId=brokerUserId;
}
public String getBrokerUsername() {
 	return brokerUsername;
}
public void setBrokerUsername(String brokerUsername) {
 	 this.brokerUsername=brokerUsername;
}
public String getToken() {
 	return token;
}
public void setToken(String token) {
 	 this.token=token;
}

}
package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientInitialAccess {

private String id;
private String realmId;
private int timestamp;
private int expiration;
private int count;

public ClientInitialAccess() {

}

public ClientInitialAccess(ResultSet rs) {
try {
	id = rs.getString("ID");
	realmId = rs.getString("REALM_ID");
	timestamp = rs.getInt("TIMESTAMP");
	expiration = rs.getInt("EXPIRATION");
	count = rs.getInt("COUNT");
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
public String getRealmId() {
 	return realmId;
}
public void setRealmId(String realmId) {
 	 this.realmId=realmId;
}
public int getTimestamp() {
 	return timestamp;
}
public void setTimestamp(int timestamp) {
 	 this.timestamp=timestamp;
}
public int getExpiration() {
 	return expiration;
}
public void setExpiration(int expiration) {
 	 this.expiration=expiration;
}
public int getCount() {
 	return count;
}
public void setCount(int count) {
 	 this.count=count;
}

}
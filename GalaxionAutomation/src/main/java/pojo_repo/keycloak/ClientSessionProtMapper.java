package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientSessionProtMapper {

private String protocolMapperId;

public ClientSessionProtMapper() {

}

public ClientSessionProtMapper(ResultSet rs) {
try {
	protocolMapperId = rs.getString("PROTOCOL_MAPPER_ID");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getProtocolMapperId() {
 	return protocolMapperId;
}
public void setProtocolMapperId(String protocolMapperId) {
 	 this.protocolMapperId=protocolMapperId;
}

}
package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientSessionRole {

private String roleId;

public ClientSessionRole() {

}

public ClientSessionRole(ResultSet rs) {
try {
	roleId = rs.getString("ROLE_ID");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getRoleId() {
 	return roleId;
}
public void setRoleId(String roleId) {
 	 this.roleId=roleId;
}

}
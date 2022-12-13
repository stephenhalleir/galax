package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Permission {

private int prospectId;
private String permissionCode;

public Permission() {

}

public Permission(ResultSet rs) {
try {
	prospectId = rs.getInt("prospect_id");
	permissionCode = rs.getString("permission_code");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getProspectId() {
 	return prospectId;
}
public void setProspectId(int prospectId) {
 	 this.prospectId=prospectId;
}
public String getPermissionCode() {
 	return permissionCode;
}
public void setPermissionCode(String permissionCode) {
 	 this.permissionCode=permissionCode;
}

}
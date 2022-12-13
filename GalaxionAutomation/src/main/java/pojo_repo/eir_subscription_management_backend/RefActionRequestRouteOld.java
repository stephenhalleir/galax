package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefActionRequestRouteOld {

private String action;
private String hierarchyLevel;
private int isNeedProvisioning;
private int isSetLock;

public RefActionRequestRouteOld() {

}

public RefActionRequestRouteOld(ResultSet rs) {
try {
	action = rs.getString("action");
	hierarchyLevel = rs.getString("hierarchy_level");
	isNeedProvisioning = rs.getInt("is_need_provisioning");
	isSetLock = rs.getInt("is_set_lock");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getAction() {
 	return action;
}
public void setAction(String action) {
 	 this.action=action;
}
public String getHierarchyLevel() {
 	return hierarchyLevel;
}
public void setHierarchyLevel(String hierarchyLevel) {
 	 this.hierarchyLevel=hierarchyLevel;
}
public int getIsNeedProvisioning() {
 	return isNeedProvisioning;
}
public void setIsNeedProvisioning(int isNeedProvisioning) {
 	 this.isNeedProvisioning=isNeedProvisioning;
}
public int getIsSetLock() {
 	return isSetLock;
}
public void setIsSetLock(int isSetLock) {
 	 this.isSetLock=isSetLock;
}

}
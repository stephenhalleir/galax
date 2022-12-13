package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefActionResponseRouteOld {

private String action;
private String hierarchyLevel;

public RefActionResponseRouteOld() {

}

public RefActionResponseRouteOld(ResultSet rs) {
try {
	action = rs.getString("action");
	hierarchyLevel = rs.getString("hierarchy_level");
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

}
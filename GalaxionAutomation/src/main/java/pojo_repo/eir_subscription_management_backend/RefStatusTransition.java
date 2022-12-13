package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefStatusTransition {

private String action;
private String fromStatus;
private String hierarchyLevel;

public RefStatusTransition() {

}

public RefStatusTransition(ResultSet rs) {
try {
	action = rs.getString("action");
	fromStatus = rs.getString("from_status");
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
public String getFromStatus() {
 	return fromStatus;
}
public void setFromStatus(String fromStatus) {
 	 this.fromStatus=fromStatus;
}
public String getHierarchyLevel() {
 	return hierarchyLevel;
}
public void setHierarchyLevel(String hierarchyLevel) {
 	 this.hierarchyLevel=hierarchyLevel;
}

}
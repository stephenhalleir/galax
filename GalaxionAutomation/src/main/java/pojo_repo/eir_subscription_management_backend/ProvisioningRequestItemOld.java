package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningRequestItemOld {

private int id;
private int hierarchicalEntityId;
private String newStatus;
private String actionType;
private String hierarchyLevel;
private int newCatalogItemId;
private int oldCatalogItemId;

public ProvisioningRequestItemOld() {

}

public ProvisioningRequestItemOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	hierarchicalEntityId = rs.getInt("hierarchical_entity_id");
	newStatus = rs.getString("new_status");
	actionType = rs.getString("action_type");
	hierarchyLevel = rs.getString("hierarchy_level");
	newCatalogItemId = rs.getInt("new_catalog_item_id");
	oldCatalogItemId = rs.getInt("old_catalog_item_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}
public int getHierarchicalEntityId() {
 	return hierarchicalEntityId;
}
public void setHierarchicalEntityId(int hierarchicalEntityId) {
 	 this.hierarchicalEntityId=hierarchicalEntityId;
}
public String getNewStatus() {
 	return newStatus;
}
public void setNewStatus(String newStatus) {
 	 this.newStatus=newStatus;
}
public String getActionType() {
 	return actionType;
}
public void setActionType(String actionType) {
 	 this.actionType=actionType;
}
public String getHierarchyLevel() {
 	return hierarchyLevel;
}
public void setHierarchyLevel(String hierarchyLevel) {
 	 this.hierarchyLevel=hierarchyLevel;
}
public int getNewCatalogItemId() {
 	return newCatalogItemId;
}
public void setNewCatalogItemId(int newCatalogItemId) {
 	 this.newCatalogItemId=newCatalogItemId;
}
public int getOldCatalogItemId() {
 	return oldCatalogItemId;
}
public void setOldCatalogItemId(int oldCatalogItemId) {
 	 this.oldCatalogItemId=oldCatalogItemId;
}

}
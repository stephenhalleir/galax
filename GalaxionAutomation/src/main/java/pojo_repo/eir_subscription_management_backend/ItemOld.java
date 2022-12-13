package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemOld {

private int id;
private Date creationDateTime;
private Date startDateTime;
private int lockedByProvisioning;
private String status;
private int catalogItemId;
private String itemHierarchyLevel;
private int orderItemId;
private Date activationDateTime;

public ItemOld() {

}

public ItemOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	creationDateTime = rs.getDate("creation_date_time");
	startDateTime = rs.getDate("start_date_time");
	lockedByProvisioning = rs.getInt("locked_by_provisioning");
	status = rs.getString("status");
	catalogItemId = rs.getInt("catalog_item_id");
	itemHierarchyLevel = rs.getString("item_hierarchy_level");
	orderItemId = rs.getInt("order_item_id");
	activationDateTime = rs.getDate("activation_date_time");
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
public Date getCreationDateTime() {
 	return creationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
 	 this.creationDateTime=creationDateTime;
}
public Date getStartDateTime() {
 	return startDateTime;
}
public void setStartDateTime(Date startDateTime) {
 	 this.startDateTime=startDateTime;
}
public int getLockedByProvisioning() {
 	return lockedByProvisioning;
}
public void setLockedByProvisioning(int lockedByProvisioning) {
 	 this.lockedByProvisioning=lockedByProvisioning;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public int getCatalogItemId() {
 	return catalogItemId;
}
public void setCatalogItemId(int catalogItemId) {
 	 this.catalogItemId=catalogItemId;
}
public String getItemHierarchyLevel() {
 	return itemHierarchyLevel;
}
public void setItemHierarchyLevel(String itemHierarchyLevel) {
 	 this.itemHierarchyLevel=itemHierarchyLevel;
}
public int getOrderItemId() {
 	return orderItemId;
}
public void setOrderItemId(int orderItemId) {
 	 this.orderItemId=orderItemId;
}
public Date getActivationDateTime() {
 	return activationDateTime;
}
public void setActivationDateTime(Date activationDateTime) {
 	 this.activationDateTime=activationDateTime;
}

}
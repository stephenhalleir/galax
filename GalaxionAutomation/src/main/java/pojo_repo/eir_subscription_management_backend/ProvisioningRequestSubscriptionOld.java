package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningRequestSubscriptionOld {

private int id;
private int hierarchicalEntityId;
private String newStatus;
private int newCatalogOfferId;
private String newType;
private int oldCatalogOfferId;
private String oldType;

public ProvisioningRequestSubscriptionOld() {

}

public ProvisioningRequestSubscriptionOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	hierarchicalEntityId = rs.getInt("hierarchical_entity_id");
	newStatus = rs.getString("new_status");
	newCatalogOfferId = rs.getInt("new_catalog_offer_id");
	newType = rs.getString("new_type");
	oldCatalogOfferId = rs.getInt("old_catalog_offer_id");
	oldType = rs.getString("old_type");
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
public int getNewCatalogOfferId() {
 	return newCatalogOfferId;
}
public void setNewCatalogOfferId(int newCatalogOfferId) {
 	 this.newCatalogOfferId=newCatalogOfferId;
}
public String getNewType() {
 	return newType;
}
public void setNewType(String newType) {
 	 this.newType=newType;
}
public int getOldCatalogOfferId() {
 	return oldCatalogOfferId;
}
public void setOldCatalogOfferId(int oldCatalogOfferId) {
 	 this.oldCatalogOfferId=oldCatalogOfferId;
}
public String getOldType() {
 	return oldType;
}
public void setOldType(String oldType) {
 	 this.oldType=oldType;
}

}
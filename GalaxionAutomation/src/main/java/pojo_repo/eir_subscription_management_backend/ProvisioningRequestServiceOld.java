package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningRequestServiceOld {

private int id;
private int hierarchicalEntityId;
private String newStatus;
private int newCatalogSubOfferId;
private String newDomain;
private String newName;
private int oldCatalogSubOfferId;
private String oldDomain;
private String oldName;

public ProvisioningRequestServiceOld() {

}

public ProvisioningRequestServiceOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	hierarchicalEntityId = rs.getInt("hierarchical_entity_id");
	newStatus = rs.getString("new_status");
	newCatalogSubOfferId = rs.getInt("new_catalog_sub_offer_id");
	newDomain = rs.getString("new_domain");
	newName = rs.getString("new_name");
	oldCatalogSubOfferId = rs.getInt("old_catalog_sub_offer_id");
	oldDomain = rs.getString("old_domain");
	oldName = rs.getString("old_name");
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
public int getNewCatalogSubOfferId() {
 	return newCatalogSubOfferId;
}
public void setNewCatalogSubOfferId(int newCatalogSubOfferId) {
 	 this.newCatalogSubOfferId=newCatalogSubOfferId;
}
public String getNewDomain() {
 	return newDomain;
}
public void setNewDomain(String newDomain) {
 	 this.newDomain=newDomain;
}
public String getNewName() {
 	return newName;
}
public void setNewName(String newName) {
 	 this.newName=newName;
}
public int getOldCatalogSubOfferId() {
 	return oldCatalogSubOfferId;
}
public void setOldCatalogSubOfferId(int oldCatalogSubOfferId) {
 	 this.oldCatalogSubOfferId=oldCatalogSubOfferId;
}
public String getOldDomain() {
 	return oldDomain;
}
public void setOldDomain(String oldDomain) {
 	 this.oldDomain=oldDomain;
}
public String getOldName() {
 	return oldName;
}
public void setOldName(String oldName) {
 	 this.oldName=oldName;
}

}
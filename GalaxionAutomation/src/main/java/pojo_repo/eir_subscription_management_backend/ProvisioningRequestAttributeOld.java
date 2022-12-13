package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningRequestAttributeOld {

private int id;
private String actionType;
private int attributeId;
private int catalogDynamicAttributeId;
private String name;
private String type;
private int provisioningItemId;
private int provisioningServiceId;

public ProvisioningRequestAttributeOld() {

}

public ProvisioningRequestAttributeOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	actionType = rs.getString("action_type");
	attributeId = rs.getInt("attribute_id");
	catalogDynamicAttributeId = rs.getInt("catalog_dynamic_attribute_id");
	name = rs.getString("name");
	type = rs.getString("type");
	provisioningItemId = rs.getInt("provisioning_item_id");
	provisioningServiceId = rs.getInt("provisioning_service_id");
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
public String getActionType() {
 	return actionType;
}
public void setActionType(String actionType) {
 	 this.actionType=actionType;
}
public int getAttributeId() {
 	return attributeId;
}
public void setAttributeId(int attributeId) {
 	 this.attributeId=attributeId;
}
public int getCatalogDynamicAttributeId() {
 	return catalogDynamicAttributeId;
}
public void setCatalogDynamicAttributeId(int catalogDynamicAttributeId) {
 	 this.catalogDynamicAttributeId=catalogDynamicAttributeId;
}
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public int getProvisioningItemId() {
 	return provisioningItemId;
}
public void setProvisioningItemId(int provisioningItemId) {
 	 this.provisioningItemId=provisioningItemId;
}
public int getProvisioningServiceId() {
 	return provisioningServiceId;
}
public void setProvisioningServiceId(int provisioningServiceId) {
 	 this.provisioningServiceId=provisioningServiceId;
}

}
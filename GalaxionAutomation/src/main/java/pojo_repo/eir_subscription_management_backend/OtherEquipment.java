package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OtherEquipment {

private int id;
private int orderId;
private int catalogEquipmentId;
private Date createdAt;
private Date updatedAt;
private Date activatedAt;
private Date terminatedAt;
private int version;
private String catalogCode;
private String type;
private String pricePlanCatalogCode;
private int price;
private int addonBundleId;

public OtherEquipment() {

}

public OtherEquipment(ResultSet rs) {
try {
	id = rs.getInt("id");
	orderId = rs.getInt("order_id");
	catalogEquipmentId = rs.getInt("catalog_equipment_id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	version = rs.getInt("version");
	catalogCode = rs.getString("catalog_code");
	type = rs.getString("type");
	pricePlanCatalogCode = rs.getString("price_plan_catalog_code");
	price = rs.getInt("price");
	addonBundleId = rs.getInt("addon_bundle_id");
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
public int getOrderId() {
 	return orderId;
}
public void setOrderId(int orderId) {
 	 this.orderId=orderId;
}
public int getCatalogEquipmentId() {
 	return catalogEquipmentId;
}
public void setCatalogEquipmentId(int catalogEquipmentId) {
 	 this.catalogEquipmentId=catalogEquipmentId;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}
public Date getUpdatedAt() {
 	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
 	 this.updatedAt=updatedAt;
}
public Date getActivatedAt() {
 	return activatedAt;
}
public void setActivatedAt(Date activatedAt) {
 	 this.activatedAt=activatedAt;
}
public Date getTerminatedAt() {
 	return terminatedAt;
}
public void setTerminatedAt(Date terminatedAt) {
 	 this.terminatedAt=terminatedAt;
}
public int getVersion() {
 	return version;
}
public void setVersion(int version) {
 	 this.version=version;
}
public String getCatalogCode() {
 	return catalogCode;
}
public void setCatalogCode(String catalogCode) {
 	 this.catalogCode=catalogCode;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public String getPricePlanCatalogCode() {
 	return pricePlanCatalogCode;
}
public void setPricePlanCatalogCode(String pricePlanCatalogCode) {
 	 this.pricePlanCatalogCode=pricePlanCatalogCode;
}
public int getPrice() {
 	return price;
}
public void setPrice(int price) {
 	 this.price=price;
}
public int getAddonBundleId() {
 	return addonBundleId;
}
public void setAddonBundleId(int addonBundleId) {
 	 this.addonBundleId=addonBundleId;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimCard {

private int id;
private int orderId;
private int catalogEquipmentId;
private Date createdAt;
private Date updatedAt;
private Date activatedAt;
private Date terminatedAt;
private String iccid;
private int version;
private String imsi;
private String catalogCode;
private String pricePlanCatalogCode;

public SimCard() {

}

public SimCard(ResultSet rs) {
try {
	id = rs.getInt("id");
	orderId = rs.getInt("order_id");
	catalogEquipmentId = rs.getInt("catalog_equipment_id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	iccid = rs.getString("iccid");
	version = rs.getInt("version");
	imsi = rs.getString("imsi");
	catalogCode = rs.getString("catalog_code");
	pricePlanCatalogCode = rs.getString("price_plan_catalog_code");
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
public String getIccid() {
 	return iccid;
}
public void setIccid(String iccid) {
 	 this.iccid=iccid;
}
public int getVersion() {
 	return version;
}
public void setVersion(int version) {
 	 this.version=version;
}
public String getImsi() {
 	return imsi;
}
public void setImsi(String imsi) {
 	 this.imsi=imsi;
}
public String getCatalogCode() {
 	return catalogCode;
}
public void setCatalogCode(String catalogCode) {
 	 this.catalogCode=catalogCode;
}
public String getPricePlanCatalogCode() {
 	return pricePlanCatalogCode;
}
public void setPricePlanCatalogCode(String pricePlanCatalogCode) {
 	 this.pricePlanCatalogCode=pricePlanCatalogCode;
}

}
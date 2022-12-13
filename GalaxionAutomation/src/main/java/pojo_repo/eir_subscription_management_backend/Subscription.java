package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subscription {

private int id;
private Date createdAt;
private Date updatedAt;
private String status;
private int catalogOfferId;
private int orderId;
private String type;
private String userUuid;
private Date activatedAt;
private Date terminatedAt;
private int contractId;
private String tariffPlanCode;
private String costCenter;
private String name;
private int isVip;
private String catalogOfferCode;
private String orderReference;
private Date coolOffAt;

public Subscription() {

}

public Subscription(ResultSet rs) {
try {
	id = rs.getInt("id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	status = rs.getString("status");
	catalogOfferId = rs.getInt("catalog_offer_id");
	orderId = rs.getInt("order_id");
	type = rs.getString("type");
	userUuid = rs.getString("user_uuid");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	contractId = rs.getInt("contract_id");
	tariffPlanCode = rs.getString("tariff_plan_code");
	costCenter = rs.getString("cost_center");
	name = rs.getString("name");
	isVip = rs.getInt("is_vip");
	catalogOfferCode = rs.getString("catalog_offer_code");
	orderReference = rs.getString("order_reference");
	coolOffAt = rs.getDate("cool_off_at");
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
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public int getCatalogOfferId() {
 	return catalogOfferId;
}
public void setCatalogOfferId(int catalogOfferId) {
 	 this.catalogOfferId=catalogOfferId;
}
public int getOrderId() {
 	return orderId;
}
public void setOrderId(int orderId) {
 	 this.orderId=orderId;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public String getUserUuid() {
 	return userUuid;
}
public void setUserUuid(String userUuid) {
 	 this.userUuid=userUuid;
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
public int getContractId() {
 	return contractId;
}
public void setContractId(int contractId) {
 	 this.contractId=contractId;
}
public String getTariffPlanCode() {
 	return tariffPlanCode;
}
public void setTariffPlanCode(String tariffPlanCode) {
 	 this.tariffPlanCode=tariffPlanCode;
}
public String getCostCenter() {
 	return costCenter;
}
public void setCostCenter(String costCenter) {
 	 this.costCenter=costCenter;
}
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public int getIsVip() {
 	return isVip;
}
public void setIsVip(int isVip) {
 	 this.isVip=isVip;
}
public String getCatalogOfferCode() {
 	return catalogOfferCode;
}
public void setCatalogOfferCode(String catalogOfferCode) {
 	 this.catalogOfferCode=catalogOfferCode;
}
public String getOrderReference() {
 	return orderReference;
}
public void setOrderReference(String orderReference) {
 	 this.orderReference=orderReference;
}
public Date getCoolOffAt() {
 	return coolOffAt;
}
public void setCoolOffAt(Date coolOffAt) {
 	 this.coolOffAt=coolOffAt;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsageQuota {

private int id;
private int orderId;
private int catalogUsageQuotaId;
private Date createdAt;
private Date updatedAt;
private Date activatedAt;
private Date terminatedAt;
private String catalogCode;
private int addonBundleId;

public UsageQuota() {

}

public UsageQuota(ResultSet rs) {
try {
	id = rs.getInt("id");
	orderId = rs.getInt("order_id");
	catalogUsageQuotaId = rs.getInt("catalog_usage_quota_id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	catalogCode = rs.getString("catalog_code");
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
public int getCatalogUsageQuotaId() {
 	return catalogUsageQuotaId;
}
public void setCatalogUsageQuotaId(int catalogUsageQuotaId) {
 	 this.catalogUsageQuotaId=catalogUsageQuotaId;
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
public String getCatalogCode() {
 	return catalogCode;
}
public void setCatalogCode(String catalogCode) {
 	 this.catalogCode=catalogCode;
}
public int getAddonBundleId() {
 	return addonBundleId;
}
public void setAddonBundleId(int addonBundleId) {
 	 this.addonBundleId=addonBundleId;
}

}
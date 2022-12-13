package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NetworkElement {

private int id;
private int catalogNetworkElementId;
private Date createdAt;
private Date updatedAt;
private Date activatedAt;
private Date terminatedAt;
private String catalogCode;
private int addonBundleId;
private int usageQuotaId;
private int serviceId;

public NetworkElement() {

}

public NetworkElement(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogNetworkElementId = rs.getInt("catalog_network_element_id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	activatedAt = rs.getDate("activated_at");
	terminatedAt = rs.getDate("terminated_at");
	catalogCode = rs.getString("catalog_code");
	addonBundleId = rs.getInt("addon_bundle_id");
	usageQuotaId = rs.getInt("usage_quota_id");
	serviceId = rs.getInt("service_id");
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
public int getCatalogNetworkElementId() {
 	return catalogNetworkElementId;
}
public void setCatalogNetworkElementId(int catalogNetworkElementId) {
 	 this.catalogNetworkElementId=catalogNetworkElementId;
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
public int getUsageQuotaId() {
 	return usageQuotaId;
}
public void setUsageQuotaId(int usageQuotaId) {
 	 this.usageQuotaId=usageQuotaId;
}
public int getServiceId() {
 	return serviceId;
}
public void setServiceId(int serviceId) {
 	 this.serviceId=serviceId;
}

}
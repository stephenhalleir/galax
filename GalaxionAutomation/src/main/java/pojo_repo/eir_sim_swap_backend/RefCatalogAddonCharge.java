package pojo_repo.eir_sim_swap_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefCatalogAddonCharge {

private int id;
private String accountBrand;
private String accountType;
private String subscriptionType;
private String catalogAddonCode;
private String feeCatalogChargeCode;
private String freeCatalogChargeCode;
private Date createdAt;

public RefCatalogAddonCharge() {

}

public RefCatalogAddonCharge(ResultSet rs) {
try {
	id = rs.getInt("id");
	accountBrand = rs.getString("account_brand");
	accountType = rs.getString("account_type");
	subscriptionType = rs.getString("subscription_type");
	catalogAddonCode = rs.getString("catalog_addon_code");
	feeCatalogChargeCode = rs.getString("fee_catalog_charge_code");
	freeCatalogChargeCode = rs.getString("free_catalog_charge_code");
	createdAt = rs.getDate("created_at");
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
public String getAccountBrand() {
 	return accountBrand;
}
public void setAccountBrand(String accountBrand) {
 	 this.accountBrand=accountBrand;
}
public String getAccountType() {
 	return accountType;
}
public void setAccountType(String accountType) {
 	 this.accountType=accountType;
}
public String getSubscriptionType() {
 	return subscriptionType;
}
public void setSubscriptionType(String subscriptionType) {
 	 this.subscriptionType=subscriptionType;
}
public String getCatalogAddonCode() {
 	return catalogAddonCode;
}
public void setCatalogAddonCode(String catalogAddonCode) {
 	 this.catalogAddonCode=catalogAddonCode;
}
public String getFeeCatalogChargeCode() {
 	return feeCatalogChargeCode;
}
public void setFeeCatalogChargeCode(String feeCatalogChargeCode) {
 	 this.feeCatalogChargeCode=feeCatalogChargeCode;
}
public String getFreeCatalogChargeCode() {
 	return freeCatalogChargeCode;
}
public void setFreeCatalogChargeCode(String freeCatalogChargeCode) {
 	 this.freeCatalogChargeCode=freeCatalogChargeCode;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
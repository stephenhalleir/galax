package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PricePlan {

private int id;
private int catalogPricePlanId;
private Date createdAt;
private Date updatedAt;
private int overridenPrice;

public PricePlan() {

}

public PricePlan(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogPricePlanId = rs.getInt("catalog_price_plan_id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	overridenPrice = rs.getInt("overriden_price");
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
public int getCatalogPricePlanId() {
 	return catalogPricePlanId;
}
public void setCatalogPricePlanId(int catalogPricePlanId) {
 	 this.catalogPricePlanId=catalogPricePlanId;
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
public int getOverridenPrice() {
 	return overridenPrice;
}
public void setOverridenPrice(int overridenPrice) {
 	 this.overridenPrice=overridenPrice;
}

}
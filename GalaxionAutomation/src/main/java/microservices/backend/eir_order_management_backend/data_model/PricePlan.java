package microservices.backend.eir_order_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PricePlan {

	private int itemId;
	private int pricePlanCatalogId;
	private int price;

	public PricePlan() {

	}

	public PricePlan(ResultSet rs) {
		try {
			itemId = rs.getInt("item_id");
			pricePlanCatalogId = rs.getInt("price_plan_catalog_id");
			price = rs.getInt("price");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getPricePlanCatalogId() {
		return pricePlanCatalogId;
	}

	public void setPricePlanCatalogId(int pricePlanCatalogId) {
		this.pricePlanCatalogId = pricePlanCatalogId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
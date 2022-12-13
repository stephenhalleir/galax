package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemTypeDynamicAttribute {

	private int itemTypeId;

	public ItemTypeDynamicAttribute() {

	}

	public ItemTypeDynamicAttribute(ResultSet rs) {
		try {
			itemTypeId = rs.getInt("item_type_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

}
package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemStaticAttribute {

	private int itemId;

	public ItemStaticAttribute() {

	}

	public ItemStaticAttribute(ResultSet rs) {
		try {
			itemId = rs.getInt("item_id");
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

}
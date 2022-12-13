package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemAttribute {

	private int id;
	private int itemId;

	public ItemAttribute() {

	}

	public ItemAttribute(ResultSet rs) {
		try {
			id = rs.getInt("id");
			itemId = rs.getInt("item_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
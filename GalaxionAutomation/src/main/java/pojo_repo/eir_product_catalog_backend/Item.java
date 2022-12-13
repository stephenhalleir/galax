package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {

	private int id;
	private String name;
	private int priority;
	private int itemTypeId;

	public Item() {

	}

	public Item(ResultSet rs) {
		try {
			id = rs.getInt("id");
			name = rs.getString("name");
			priority = rs.getInt("priority");
			itemTypeId = rs.getInt("item_type_id");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

}
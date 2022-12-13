package pojo_repo.eir_barring_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {

	private int id;
	private int barringId;
	private int barringItemId;

	public Item() {

	}

	public Item(ResultSet rs) {
		try {
			id = rs.getInt("id");
			barringId = rs.getInt("barring_id");
			barringItemId = rs.getInt("barring_item_id");
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

	public int getBarringId() {
		return barringId;
	}

	public void setBarringId(int barringId) {
		this.barringId = barringId;
	}

	public int getBarringItemId() {
		return barringItemId;
	}

	public void setBarringItemId(int barringItemId) {
		this.barringItemId = barringItemId;
	}

}
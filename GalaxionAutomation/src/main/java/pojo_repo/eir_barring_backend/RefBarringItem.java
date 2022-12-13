package pojo_repo.eir_barring_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefBarringItem {

	private int id;
	private String barringCode;
	private String name;

	public RefBarringItem() {

	}

	public RefBarringItem(ResultSet rs) {
		try {
			id = rs.getInt("id");
			barringCode = rs.getString("barring_code");
			name = rs.getString("name");
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

	public String getBarringCode() {
		return barringCode;
	}

	public void setBarringCode(String barringCode) {
		this.barringCode = barringCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
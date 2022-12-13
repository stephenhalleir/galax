package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerType {

	private int id;
	private String displayName;

	public CustomerType() {

	}

	public CustomerType(ResultSet rs) {
		try {
			id = rs.getInt("id");
			displayName = rs.getString("display_name");
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessoryExtraInfo {

	private int id;
	private String label;
	private String value;

	public AccessoryExtraInfo() {

	}

	public AccessoryExtraInfo(ResultSet rs) {
		try {
			id = rs.getInt("id");
			label = rs.getString("label");
			value = rs.getString("value");
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
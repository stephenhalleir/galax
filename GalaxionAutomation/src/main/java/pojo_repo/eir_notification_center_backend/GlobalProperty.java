package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GlobalProperty {

	private String name;

	public GlobalProperty() {

	}

	public GlobalProperty(ResultSet rs) {
		try {
			name = rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
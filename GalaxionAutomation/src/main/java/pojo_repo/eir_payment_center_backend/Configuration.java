package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Configuration {

	private String property;

	public Configuration() {

	}

	public Configuration(ResultSet rs) {
		try {
			property = rs.getString("property");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
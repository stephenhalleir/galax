package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefAccountCategory {

	private String type;
	private String value;
	private String label;

	public RefAccountCategory() {

	}

	public RefAccountCategory(ResultSet rs) {
		try {
			type = rs.getString("type");
			value = rs.getString("value");
			label = rs.getString("label");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
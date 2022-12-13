package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefFlow {

	private String name;
	private String description;
	private String role;

	public RefFlow() {

	}

	public RefFlow(ResultSet rs) {
		try {
			name = rs.getString("name");
			description = rs.getString("description");
			role = rs.getString("role");
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
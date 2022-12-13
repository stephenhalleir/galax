package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Attribute {

	private int id;

	public Attribute() {

	}

	public Attribute(ResultSet rs) {
		try {
			id = rs.getInt("id");
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

}
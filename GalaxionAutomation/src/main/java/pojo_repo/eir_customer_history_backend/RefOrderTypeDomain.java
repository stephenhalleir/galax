package pojo_repo.eir_customer_history_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefOrderTypeDomain {

	private int id;
	private String name;

	public RefOrderTypeDomain() {

	}

	public RefOrderTypeDomain(ResultSet rs) {
		try {
			id = rs.getInt("id");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
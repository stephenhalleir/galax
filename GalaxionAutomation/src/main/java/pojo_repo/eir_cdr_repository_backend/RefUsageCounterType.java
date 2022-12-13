package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefUsageCounterType {

	private int id;
	private String key;

	public RefUsageCounterType() {

	}

	public RefUsageCounterType(ResultSet rs) {
		try {
			id = rs.getInt("id");
			key = rs.getString("key");
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
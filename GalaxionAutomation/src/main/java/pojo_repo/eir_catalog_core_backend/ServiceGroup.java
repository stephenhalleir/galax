package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceGroup {

	private int id;
	private String name;
	private String code;

	public ServiceGroup() {

	}

	public ServiceGroup(ResultSet rs) {
		try {
			id = rs.getInt("id");
			name = rs.getString("name");
			code = rs.getString("code");
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
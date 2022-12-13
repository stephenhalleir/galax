package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountingCode {

	private int id;
	private String code;
	private String description;

	public AccountingCode() {

	}

	public AccountingCode(ResultSet rs) {
		try {
			id = rs.getInt("id");
			code = rs.getString("code");
			description = rs.getString("description");
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
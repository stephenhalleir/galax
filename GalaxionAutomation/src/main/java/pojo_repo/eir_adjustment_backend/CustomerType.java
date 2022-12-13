package pojo_repo.eir_adjustment_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerType {

	private String code;

	public CustomerType() {

	}

	public CustomerType(ResultSet rs) {
		try {
			code = rs.getString("code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
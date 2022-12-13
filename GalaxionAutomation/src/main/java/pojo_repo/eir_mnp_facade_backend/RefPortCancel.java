package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefPortCancel {

	private int id;
	private String fmnpStatus;

	public RefPortCancel() {

	}

	public RefPortCancel(ResultSet rs) {
		try {
			id = rs.getInt("id");
			fmnpStatus = rs.getString("fmnp_status");
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

	public String getFmnpStatus() {
		return fmnpStatus;
	}

	public void setFmnpStatus(String fmnpStatus) {
		this.fmnpStatus = fmnpStatus;
	}

}
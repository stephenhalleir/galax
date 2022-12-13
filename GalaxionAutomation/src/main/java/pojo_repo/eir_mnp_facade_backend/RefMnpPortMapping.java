package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefMnpPortMapping {

	private int id;
	private String fmnpStatus;
	private String state;

	public RefMnpPortMapping() {

	}

	public RefMnpPortMapping(ResultSet rs) {
		try {
			id = rs.getInt("id");
			fmnpStatus = rs.getString("fmnp_status");
			state = rs.getString("state");
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
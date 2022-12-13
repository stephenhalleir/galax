package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefPortRetryRule {

	private int id;
	private String rejectCode;
	private String currentPortType;

	public RefPortRetryRule() {

	}

	public RefPortRetryRule(ResultSet rs) {
		try {
			id = rs.getInt("id");
			rejectCode = rs.getString("reject_code");
			currentPortType = rs.getString("current_port_type");
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

	public String getRejectCode() {
		return rejectCode;
	}

	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}

	public String getCurrentPortType() {
		return currentPortType;
	}

	public void setCurrentPortType(String currentPortType) {
		this.currentPortType = currentPortType;
	}

}
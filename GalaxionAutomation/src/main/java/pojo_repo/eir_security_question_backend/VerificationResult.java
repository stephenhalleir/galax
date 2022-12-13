package pojo_repo.eir_security_question_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificationResult {

	private int id;
	private String uuid;
	private String status;

	public VerificationResult() {

	}

	public VerificationResult(ResultSet rs) {
		try {
			id = rs.getInt("id");
			uuid = rs.getString("uuid");
			status = rs.getString("status");
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
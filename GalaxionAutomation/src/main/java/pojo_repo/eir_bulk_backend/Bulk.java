package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bulk {

	private int id;
	private String filename;
	private String status;
	private Date createdAt;
	private String refFlowName;
	private String username;

	public Bulk() {

	}

	public Bulk(ResultSet rs) {
		try {
			id = rs.getInt("id");
			filename = rs.getString("filename");
			status = rs.getString("status");
			createdAt = rs.getDate("created_at");
			refFlowName = rs.getString("ref_flow_name");
			username = rs.getString("username");
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getRefFlowName() {
		return refFlowName;
	}

	public void setRefFlowName(String refFlowName) {
		this.refFlowName = refFlowName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
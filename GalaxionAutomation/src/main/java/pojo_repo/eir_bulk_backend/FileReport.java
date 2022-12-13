package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileReport {

	private int id;
	private String fileName;
	private Date createdAt;

	public FileReport() {

	}

	public FileReport(ResultSet rs) {
		try {
			id = rs.getInt("id");
			fileName = rs.getString("file_name");
			createdAt = rs.getDate("created_at");
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Flow {

	private int id;
	private int lineNumber;
	private int bulkId;
	private String status;
	private String errorCode;

	public Flow() {

	}

	public Flow(ResultSet rs) {
		try {
			id = rs.getInt("id");
			lineNumber = rs.getInt("line_number");
			bulkId = rs.getInt("bulk_id");
			status = rs.getString("status");
			errorCode = rs.getString("error_code");
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

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getBulkId() {
		return bulkId;
	}

	public void setBulkId(int bulkId) {
		this.bulkId = bulkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
package microservices.backend.eir_bulk_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Flow {
	private int id;
	private int lineNumber;
	private int bulkID;
	private String status;
	private String errorCode;
	private String errorMessage;

	public Flow() {
		
	}
	
	public Flow(ResultSet rs) {
		try {
			id=rs.getInt("id");
			lineNumber=rs.getInt("line_number");
			bulkID=rs.getInt("bulk_id");
			status=rs.getString("status");
			errorCode=rs.getString("error_code");
			errorMessage=rs.getString("error_message");
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

	public int getBulkID() {
		return bulkID;
	}

	public void setBulkID(int bulkID) {
		this.bulkID = bulkID;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

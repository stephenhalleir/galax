package microservices.backend.eir_bulk_backend.data_model;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import framework.test_data.generic.StringUtil;

/**
 * This class represents a row in the eir-bulk-backend BULK table
 * @author stephenhall
 *
 */
public class Bulk {
	private int id;
	private String filename;
	private String status;
	private String refFlowName;
	private String username;
	private Blob csv;
	
	public Bulk() {
		
	}
	
	public Bulk(ResultSet rs) {
		try {
			id=rs.getInt("id");
			filename=rs.getString("filename");
			status=rs.getString("status");
			refFlowName=rs.getString("ref_flow_name");
			username=rs.getString("username");
			csv=rs.getBlob("csv");
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

	public Blob getCsv() {
		return csv;
	}

	public void setCsv(Blob csv) {
		this.csv = csv;
	}
	
	public String getCsvString() {
		return StringUtil.getStringFromBlob(csv);
	}
}

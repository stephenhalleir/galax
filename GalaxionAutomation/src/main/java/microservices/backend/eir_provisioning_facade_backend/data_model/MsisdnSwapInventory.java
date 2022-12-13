package microservices.backend.eir_provisioning_facade_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MsisdnSwapInventory {
	private int id;
	private String msisdn;
	private String status;
	private Date createdDateTime;
	private Date updatedDateTime;
	
	public MsisdnSwapInventory() {
		
	}
	
	public MsisdnSwapInventory(ResultSet rs) {
		try {
			id=rs.getInt("id");
			msisdn=rs.getString("msisdn");
			status=rs.getString("status");
			createdDateTime=rs.getDate("created_date_time");
			updatedDateTime=rs.getDate("updated_date_time");
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

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
}

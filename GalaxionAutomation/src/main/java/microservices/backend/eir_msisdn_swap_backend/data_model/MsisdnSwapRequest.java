package microservices.backend.eir_msisdn_swap_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MsisdnSwapRequest {

	private int id;
	private String status;
	private String errorMessage;
	private String source;
	private String newMsisdn;
	private int serviceId;
	private Date createdAt;
	private Date updatedAt;

	public MsisdnSwapRequest() {

	}

	public MsisdnSwapRequest(ResultSet rs) {
		try {
			id = rs.getInt("id");
			status = rs.getString("status");
			errorMessage = rs.getString("error_message");
			source = rs.getString("source");
			newMsisdn = rs.getString("new_msisdn");
			serviceId = rs.getInt("service_id");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNewMsisdn() {
		return newMsisdn;
	}

	public void setNewMsisdn(String newMsisdn) {
		this.newMsisdn = newMsisdn;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
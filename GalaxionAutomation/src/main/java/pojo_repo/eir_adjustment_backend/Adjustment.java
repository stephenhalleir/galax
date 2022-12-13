package pojo_repo.eir_adjustment_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adjustment {

	private int id;
	private String fileName;
	private String reference;
	private String text;
	private Date appliedDatetime;
	private Date creationDatetime;
	private Date lastModifiedDatetime;
	private String status;
	private int amount;
	private String agentUsername;
	private int billingAccountId;
	private String errorCode;
	private String errorMessage;
	private int serviceId;
	private int hardwareFundId;

	public Adjustment() {

	}

	public Adjustment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			fileName = rs.getString("file_name");
			reference = rs.getString("reference");
			text = rs.getString("text");
			appliedDatetime = rs.getDate("applied_datetime");
			creationDatetime = rs.getDate("creation_datetime");
			lastModifiedDatetime = rs.getDate("last_modified_datetime");
			status = rs.getString("status");
			amount = rs.getInt("amount");
			agentUsername = rs.getString("agent_username");
			billingAccountId = rs.getInt("billing_account_id");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			serviceId = rs.getInt("service_id");
			hardwareFundId = rs.getInt("hardware_fund_id");
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getAppliedDatetime() {
		return appliedDatetime;
	}

	public void setAppliedDatetime(Date appliedDatetime) {
		this.appliedDatetime = appliedDatetime;
	}

	public Date getCreationDatetime() {
		return creationDatetime;
	}

	public void setCreationDatetime(Date creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

	public Date getLastModifiedDatetime() {
		return lastModifiedDatetime;
	}

	public void setLastModifiedDatetime(Date lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getAgentUsername() {
		return agentUsername;
	}

	public void setAgentUsername(String agentUsername) {
		this.agentUsername = agentUsername;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
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

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getHardwareFundId() {
		return hardwareFundId;
	}

	public void setHardwareFundId(int hardwareFundId) {
		this.hardwareFundId = hardwareFundId;
	}

}
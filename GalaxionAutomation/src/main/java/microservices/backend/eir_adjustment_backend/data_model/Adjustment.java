package microservices.backend.eir_adjustment_backend.data_model;

import java.sql.SQLException;

import java.sql.ResultSet;

public class Adjustment {

	private int id;
	private String filename;
	private String text;
	private String status;
	private String reference;
	private int amount;
	private String agentUsername;
	private int billingAccountID;
	private String errorCode;
	private String errorMessage;
	private int serviceID;
	private int hardwareFundID;
	private String adjustmentReasonKey;
	
	public Adjustment() {
		
	}
	
	public Adjustment(ResultSet rs) {
		try {
			id=rs.getInt("id");
			amount=rs.getInt("amount");
			serviceID=rs.getInt("service_id");
			hardwareFundID=rs.getInt("hardware_fund_id");
			billingAccountID=rs.getInt("billing_account_id");
			filename=rs.getString("file_name");
			reference=rs.getString("reference");
			text=rs.getString("text");
			status=rs.getString("status");
			agentUsername=rs.getString("agent_username");
			errorCode=rs.getString("error_code");
			errorMessage=rs.getString("error_message");
			adjustmentReasonKey=rs.getString("adjustment_reason_key");
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
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

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getHardwareFundID() {
		return hardwareFundID;
	}

	public void setHardwareFundID(int hardwareFundID) {
		this.hardwareFundID = hardwareFundID;
	}

	public String getAdjustmentReasonKey() {
		return adjustmentReasonKey;
	}

	public void setAdjustmentReasonKey(String adjustmentReasonKey) {
		this.adjustmentReasonKey = adjustmentReasonKey;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}	
}

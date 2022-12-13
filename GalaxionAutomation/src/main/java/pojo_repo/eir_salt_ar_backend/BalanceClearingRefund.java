package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceClearingRefund {

	private String freeText;
	private String agentUsername;
	private int sourceBillingAccountId;
	private int id;
	private Date createTs;

	public BalanceClearingRefund() {

	}

	public BalanceClearingRefund(ResultSet rs) {
		try {
			freeText = rs.getString("free_text");
			agentUsername = rs.getString("agent_username");
			sourceBillingAccountId = rs.getInt("source_billing_account_id");
			id = rs.getInt("id");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public String getAgentUsername() {
		return agentUsername;
	}

	public void setAgentUsername(String agentUsername) {
		this.agentUsername = agentUsername;
	}

	public int getSourceBillingAccountId() {
		return sourceBillingAccountId;
	}

	public void setSourceBillingAccountId(int sourceBillingAccountId) {
		this.sourceBillingAccountId = sourceBillingAccountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}
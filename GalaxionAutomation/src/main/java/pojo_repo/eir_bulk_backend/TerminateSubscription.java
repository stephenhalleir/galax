package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TerminateSubscription {

	private int id;
	private int accountId;
	private String msisdn;
	private String reason;
	private Date terminatedAt;
	private int earlyCeaseCharge;

	public TerminateSubscription() {

	}

	public TerminateSubscription(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			msisdn = rs.getString("msisdn");
			reason = rs.getString("reason");
			terminatedAt = rs.getDate("terminated_at");
			earlyCeaseCharge = rs.getInt("early_cease_charge");
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(Date terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public int getEarlyCeaseCharge() {
		return earlyCeaseCharge;
	}

	public void setEarlyCeaseCharge(int earlyCeaseCharge) {
		this.earlyCeaseCharge = earlyCeaseCharge;
	}

}
package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceTransfer {

	private int destinationBillingAccountId;
	private String reasonCode;
	private int sourceBillingAccountId;
	private int id;
	private Date createTs;

	public BalanceTransfer() {

	}

	public BalanceTransfer(ResultSet rs) {
		try {
			destinationBillingAccountId = rs.getInt("destination_billing_account_id");
			reasonCode = rs.getString("reason_code");
			sourceBillingAccountId = rs.getInt("source_billing_account_id");
			id = rs.getInt("id");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getDestinationBillingAccountId() {
		return destinationBillingAccountId;
	}

	public void setDestinationBillingAccountId(int destinationBillingAccountId) {
		this.destinationBillingAccountId = destinationBillingAccountId;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
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
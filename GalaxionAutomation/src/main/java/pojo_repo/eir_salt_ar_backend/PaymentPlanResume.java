package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentPlanResume {

	private Date cancellationDate;
	private String reasonCode;
	private int id;
	private String csrUserName;
	private int appliedToNonOverdue;
	private int remainingAmountAftCancel;
	private Date createTs;

	public PaymentPlanResume() {

	}

	public PaymentPlanResume(ResultSet rs) {
		try {
			cancellationDate = rs.getDate("cancellation_date");
			reasonCode = rs.getString("reason_code");
			id = rs.getInt("id");
			csrUserName = rs.getString("csr_user_name");
			appliedToNonOverdue = rs.getInt("applied_to_non_overdue");
			remainingAmountAftCancel = rs.getInt("remaining_amount_aft_cancel");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCsrUserName() {
		return csrUserName;
	}

	public void setCsrUserName(String csrUserName) {
		this.csrUserName = csrUserName;
	}

	public int getAppliedToNonOverdue() {
		return appliedToNonOverdue;
	}

	public void setAppliedToNonOverdue(int appliedToNonOverdue) {
		this.appliedToNonOverdue = appliedToNonOverdue;
	}

	public int getRemainingAmountAftCancel() {
		return remainingAmountAftCancel;
	}

	public void setRemainingAmountAftCancel(int remainingAmountAftCancel) {
		this.remainingAmountAftCancel = remainingAmountAftCancel;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}
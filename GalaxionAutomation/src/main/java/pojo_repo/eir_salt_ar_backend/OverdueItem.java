package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OverdueItem {

	private int id;
	private int billingAccount;
	private Date overdueDate;
	private Date lastModifiedDate;
	private int overdueAmount;
	private int maxDebtAmount;
	private int balanceChangeId;
	private Date createTs;

	public OverdueItem() {

	}

	public OverdueItem(ResultSet rs) {
		try {
			id = rs.getInt("id");
			billingAccount = rs.getInt("billing_account");
			overdueDate = rs.getDate("overdue_date");
			lastModifiedDate = rs.getDate("last_modified_date");
			overdueAmount = rs.getInt("overdue_amount");
			maxDebtAmount = rs.getInt("max_debt_amount");
			balanceChangeId = rs.getInt("balance_change_id");
			createTs = rs.getDate("create_ts");
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

	public int getBillingAccount() {
		return billingAccount;
	}

	public void setBillingAccount(int billingAccount) {
		this.billingAccount = billingAccount;
	}

	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(int overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public int getMaxDebtAmount() {
		return maxDebtAmount;
	}

	public void setMaxDebtAmount(int maxDebtAmount) {
		this.maxDebtAmount = maxDebtAmount;
	}

	public int getBalanceChangeId() {
		return balanceChangeId;
	}

	public void setBalanceChangeId(int balanceChangeId) {
		this.balanceChangeId = balanceChangeId;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}
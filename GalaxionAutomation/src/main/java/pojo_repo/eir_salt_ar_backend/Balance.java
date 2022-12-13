package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Balance {

	private int lastChange;
	private int prevChange;
	private int billingAccountId;
	private String domain;
	private int nonOverdueAmount;
	private int overdueAmount;
	private Date date;
	private Date delinquentDate;
	private String delinquentState;
	private Date lastBillOverdueDate;
	private Date overdueDate;
	private int receiverFlag;
	private Date createTs;
	private Date modifTs;
	private int delinquentEndAmount;

	public Balance() {

	}

	public Balance(ResultSet rs) {
		try {
			lastChange = rs.getInt("last_change");
			prevChange = rs.getInt("prev_change");
			billingAccountId = rs.getInt("billing_account_id");
			domain = rs.getString("domain");
			nonOverdueAmount = rs.getInt("non_overdue_amount");
			overdueAmount = rs.getInt("overdue_amount");
			date = rs.getDate("date");
			delinquentDate = rs.getDate("delinquent_date");
			delinquentState = rs.getString("delinquent_state");
			lastBillOverdueDate = rs.getDate("last_bill_overdue_date");
			overdueDate = rs.getDate("overdue_date");
			receiverFlag = rs.getInt("receiver_flag");
			createTs = rs.getDate("create_ts");
			modifTs = rs.getDate("modif_ts");
			delinquentEndAmount = rs.getInt("delinquent_end_amount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getLastChange() {
		return lastChange;
	}

	public void setLastChange(int lastChange) {
		this.lastChange = lastChange;
	}

	public int getPrevChange() {
		return prevChange;
	}

	public void setPrevChange(int prevChange) {
		this.prevChange = prevChange;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getNonOverdueAmount() {
		return nonOverdueAmount;
	}

	public void setNonOverdueAmount(int nonOverdueAmount) {
		this.nonOverdueAmount = nonOverdueAmount;
	}

	public int getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(int overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDelinquentDate() {
		return delinquentDate;
	}

	public void setDelinquentDate(Date delinquentDate) {
		this.delinquentDate = delinquentDate;
	}

	public String getDelinquentState() {
		return delinquentState;
	}

	public void setDelinquentState(String delinquentState) {
		this.delinquentState = delinquentState;
	}

	public Date getLastBillOverdueDate() {
		return lastBillOverdueDate;
	}

	public void setLastBillOverdueDate(Date lastBillOverdueDate) {
		this.lastBillOverdueDate = lastBillOverdueDate;
	}

	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	public int getReceiverFlag() {
		return receiverFlag;
	}

	public void setReceiverFlag(int receiverFlag) {
		this.receiverFlag = receiverFlag;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Date getModifTs() {
		return modifTs;
	}

	public void setModifTs(Date modifTs) {
		this.modifTs = modifTs;
	}

	public int getDelinquentEndAmount() {
		return delinquentEndAmount;
	}

	public void setDelinquentEndAmount(int delinquentEndAmount) {
		this.delinquentEndAmount = delinquentEndAmount;
	}

}
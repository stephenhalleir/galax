package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceChange {

	private int nonOverdueAmount;
	private int overdueAmount;
	private Date delinquentDate;
	private int delinquentState;
	private Date lastBillOverdueDate;
	private Date overdueDate;
	private int receiverFlag;
	private int nextChange;
	private int prevChange;
	private String changeType;
	private int id;
	private int amount;
	private int billingAccountId;
	private Date changeDate;
	private Date dueDate;
	private String entryType;
	private String eventRefId;
	private Date createTs;

	public BalanceChange() {

	}

	public BalanceChange(ResultSet rs) {
		try {
			nonOverdueAmount = rs.getInt("non_overdue_amount");
			overdueAmount = rs.getInt("overdue_amount");
			delinquentDate = rs.getDate("delinquent_date");
			delinquentState = rs.getInt("delinquent_state");
			lastBillOverdueDate = rs.getDate("last_bill_overdue_date");
			overdueDate = rs.getDate("overdue_date");
			receiverFlag = rs.getInt("receiver_flag");
			nextChange = rs.getInt("next_change");
			prevChange = rs.getInt("prev_change");
			changeType = rs.getString("change_type");
			id = rs.getInt("id");
			amount = rs.getInt("amount");
			billingAccountId = rs.getInt("billing_account_id");
			changeDate = rs.getDate("change_date");
			dueDate = rs.getDate("due_date");
			entryType = rs.getString("entry_type");
			eventRefId = rs.getString("event_ref_id");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public Date getDelinquentDate() {
		return delinquentDate;
	}

	public void setDelinquentDate(Date delinquentDate) {
		this.delinquentDate = delinquentDate;
	}

	public int getDelinquentState() {
		return delinquentState;
	}

	public void setDelinquentState(int delinquentState) {
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

	public int getNextChange() {
		return nextChange;
	}

	public void setNextChange(int nextChange) {
		this.nextChange = nextChange;
	}

	public int getPrevChange() {
		return prevChange;
	}

	public void setPrevChange(int prevChange) {
		this.prevChange = prevChange;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getEventRefId() {
		return eventRefId;
	}

	public void setEventRefId(String eventRefId) {
		this.eventRefId = eventRefId;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}
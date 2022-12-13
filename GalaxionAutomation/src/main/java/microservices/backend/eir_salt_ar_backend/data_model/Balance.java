package microservices.backend.eir_salt_ar_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import framework.test_data.generic.StringUtil;

/**
 * This class represents the salt-at-backend BALANCE table
 * @author stephenhall
 *
 */
public class Balance {
	private int billingAccountID;
	private long nonOverdueAmount;
	private long overdueAmount;
	private Date overdueDate;
	private int lastChange;
	private Date date;
	private Date delinquentDate;
	private int delinquentState;
	private Date lastBillOverdueDate;
	private int receiverFlag;
	private int delinquentStartAmount;
	private int delinquentEndAmount;	
	
	public Balance() {
		
	}
	
	public Balance(ResultSet rs) {
		try {
			billingAccountID=rs.getInt("billing_account_id");
			nonOverdueAmount=rs.getLong("non_overdue_amount");
			overdueAmount=rs.getLong("overdue_amount");
			overdueDate=rs.getDate("overdue_date");
			lastChange=rs.getInt("last_change");
			date=rs.getDate("date");
			delinquentDate=rs.getDate("delinquent_date");
			delinquentState=rs.getInt("delinquent_state");
			lastBillOverdueDate=rs.getDate("last_bill_overdue_date");
			receiverFlag=rs.getInt("receiver_flag");
			delinquentStartAmount=rs.getInt("delinquent_start_amount");
			delinquentEndAmount=rs.getInt("delinquent_end_amount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public long getNonOverdueAmount() {
		return nonOverdueAmount;
	}

	public void setNonOverdueAmount(int nonOverdueAmount) {
		this.nonOverdueAmount = nonOverdueAmount;
	}

	public long getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(int overdueAmount) {
		this.overdueAmount = overdueAmount;
	}
	
	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	public String getNonOverDueAmountAsEuro() {
		if(nonOverdueAmount==0) {
			return "€0";
		}
		else {
			return StringUtil.toCurrency(nonOverdueAmount, "€");
		}
	}
	
	public long getTotalBalanceDue() {
		return overdueAmount+nonOverdueAmount;
	}

	public int getLastChange() {
		return lastChange;
	}

	public void setLastChange(int lastChange) {
		this.lastChange = lastChange;
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

	public int getReceiverFlag() {
		return receiverFlag;
	}

	public void setReceiverFlag(int receiverFlag) {
		this.receiverFlag = receiverFlag;
	}

	public int getDelinquentStartAmount() {
		return delinquentStartAmount;
	}

	public void setDelinquentStartAmount(int delinquentStartAmount) {
		this.delinquentStartAmount = delinquentStartAmount;
	}

	public int getDelinquentEndAmount() {
		return delinquentEndAmount;
	}

	public void setDelinquentEndAmount(int delinquentEndAmount) {
		this.delinquentEndAmount = delinquentEndAmount;
	}
}

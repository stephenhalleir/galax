package microservices.backend.eir_salt_ar_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * This class represents the eir-salt-ar-backend table BALANCE_CHANGE
 * @author stephenhall
 *
 */
public class BalanceChange {
	private int id;
	private int nonOverdueAmount;
	private int overdueAmount;
	private int nextChange;
	private int prevChange;
	private String changeType;
	private int billingAccountID;
	private int amount;
	private Date changeDate;
	private Date dueDate;
	private String entryType;
	private String eventRefID;
	
	private BalanceChange() {
		
	}
	
	/**
	 * Populate the object based on a result set object
	 * @param rs
	 */
	public BalanceChange(ResultSet rs) {
		try {
			id=rs.getInt("id");
			nonOverdueAmount=rs.getInt("non_overdue_amount");
			overdueAmount=rs.getInt("overdue_amount");
			amount=rs.getInt("amount");
			billingAccountID=rs.getInt("billing_account_id");
			nextChange=rs.getInt("next_change");
			prevChange=rs.getInt("prev_change");
			changeType=rs.getString("change_type");
			changeDate=rs.getDate("change_date");
			dueDate=rs.getDate("due_date");
			entryType=rs.getString("entry_type");
			eventRefID=rs.getString("event_ref_id");
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

	public int getNonOverdueAmount() {
		return nonOverdueAmount;
	}

	public void setNonOverdueAmount(int nonOverdueAmount) {
		this.nonOverdueAmount = nonOverdueAmount;
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

	public String getEventRefID() {
		return eventRefID;
	}

	public void setEventRefID(String eventRefID) {
		this.eventRefID = eventRefID;
	}

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	// =================================================================================================================
		// Other getter & setter methods
		// =================================================================================================================

		public String toString() {
			return getStrDate() + ", " + getStrType() + ", " + getStrAdjustmentAmount() + ", " + getStrBalance();
		}

		// return the adjustment date in format dd/mm/yyyy
		public String getStrDate() {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.format(changeDate);
		}

		// return the translated adjustment type
		public String getStrType() {

			switch (changeType) {
			case ("BALANCE_CLEARING_REFUND"):
				return "REFUND";
			case ("IMMEDIATE_ADJUSTMENT"):
				return "ADJUSTMENT";
			default:
				return changeType;
			}
		}

		// return the adjustment amount in format €X.XX
		public String getStrAdjustmentAmount() {

			DecimalFormat df2 = new DecimalFormat("0.00");
			double bal = (double) amount / 100;
			return "€" + df2.format(bal);
		}

		// return the post-adjustment balance in format €X.XX
		public String getStrBalance() {

			int totalBalance = nonOverdueAmount + overdueAmount;
			
			DecimalFormat df2 = new DecimalFormat("0.00");
			double bal = (double) totalBalance / 100;
			return "€" + df2.format(bal);
		}
}

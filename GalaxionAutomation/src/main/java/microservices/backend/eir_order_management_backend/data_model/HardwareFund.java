package microservices.backend.eir_order_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HardwareFund {
	private int id;
	private int hardwareFundID;
	private int dueAmountTaxExcl;
	private int upfrontTaxInclAmount;
	private int upfrontTaxExclAmount;
	private int initialAmount;
	private int term;
	private String comment;
	private int accountID;
	
	public HardwareFund() {
		
	}
	
	public HardwareFund(ResultSet rs) {
		try {
			id=rs.getInt("id");
			hardwareFundID=rs.getInt("hardware_fund_id");
			dueAmountTaxExcl=rs.getInt("due_amount_tax_excl");
			upfrontTaxInclAmount=rs.getInt("upfront_tax_incl_amount");
			upfrontTaxExclAmount=rs.getInt("upfront_tax_excl_amount");
			initialAmount=rs.getInt("initial_amount");
			term=rs.getInt("term");
			comment=rs.getString("comment");
			accountID=rs.getInt("account_id");
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

	public int getHardwareFundID() {
		return hardwareFundID;
	}

	public void setHardwareFundID(int hardwareFundID) {
		this.hardwareFundID = hardwareFundID;
	}

	public int getDueAmountTaxExcl() {
		return dueAmountTaxExcl;
	}

	public void setDueAmountTaxExcl(int dueAmountTaxExcl) {
		this.dueAmountTaxExcl = dueAmountTaxExcl;
	}

	public int getUpfrontTaxInclAmount() {
		return upfrontTaxInclAmount;
	}

	public void setUpfrontTaxInclAmount(int upfrontTaxInclAmount) {
		this.upfrontTaxInclAmount = upfrontTaxInclAmount;
	}

	public int getUpfrontTaxExclAmount() {
		return upfrontTaxExclAmount;
	}

	public void setUpfrontTaxExclAmount(int upfrontTaxExclAmount) {
		this.upfrontTaxExclAmount = upfrontTaxExclAmount;
	}

	public int getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(int initialAmount) {
		this.initialAmount = initialAmount;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
}

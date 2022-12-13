package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HardwareFund {

	private int id;
	private int hardwareFundId;
	private int dueAmountTaxExcl;
	private int upfrontTaxInclAmount;
	private int upfrontTaxExclAmount;
	private int initialAmount;
	private int term;
	private String comment;

	public HardwareFund() {

	}

	public HardwareFund(ResultSet rs) {
		try {
			id = rs.getInt("id");
			hardwareFundId = rs.getInt("hardware_fund_id");
			dueAmountTaxExcl = rs.getInt("due_amount_tax_excl");
			upfrontTaxInclAmount = rs.getInt("upfront_tax_incl_amount");
			upfrontTaxExclAmount = rs.getInt("upfront_tax_excl_amount");
			initialAmount = rs.getInt("initial_amount");
			term = rs.getInt("term");
			comment = rs.getString("comment");
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

	public int getHardwareFundId() {
		return hardwareFundId;
	}

	public void setHardwareFundId(int hardwareFundId) {
		this.hardwareFundId = hardwareFundId;
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

}
package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

	private int id;
	private int accountId;
	private int amount;
	private Date payedAt;
	private String remark;

	public Payment() {

	}

	public Payment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			amount = rs.getInt("amount");
			payedAt = rs.getDate("payed_at");
			remark = rs.getString("remark");
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getPayedAt() {
		return payedAt;
	}

	public void setPayedAt(Date payedAt) {
		this.payedAt = payedAt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
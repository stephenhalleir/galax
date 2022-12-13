package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopUp {

	private int id;
	private int amount;

	public TopUp() {

	}

	public TopUp(ResultSet rs) {
		try {
			id = rs.getInt("id");
			amount = rs.getInt("amount");
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
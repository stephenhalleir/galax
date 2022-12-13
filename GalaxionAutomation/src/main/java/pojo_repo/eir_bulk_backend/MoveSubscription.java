package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveSubscription {

	private int id;
	private int newAccountId;

	public MoveSubscription() {

	}

	public MoveSubscription(ResultSet rs) {
		try {
			id = rs.getInt("id");
			newAccountId = rs.getInt("new_account_id");
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

	public int getNewAccountId() {
		return newAccountId;
	}

	public void setNewAccountId(int newAccountId) {
		this.newAccountId = newAccountId;
	}

}
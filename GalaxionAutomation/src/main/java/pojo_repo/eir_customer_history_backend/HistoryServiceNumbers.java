package pojo_repo.eir_customer_history_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryServiceNumbers {

	private int id;

	public HistoryServiceNumbers() {

	}

	public HistoryServiceNumbers(ResultSet rs) {
		try {
			id = rs.getInt("id");
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

}
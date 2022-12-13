package pojo_repo.eir_customer_history_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryToServiceNumbers {

	private int historyFk;

	public HistoryToServiceNumbers() {

	}

	public HistoryToServiceNumbers(ResultSet rs) {
		try {
			historyFk = rs.getInt("history_fk");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getHistoryFk() {
		return historyFk;
	}

	public void setHistoryFk(int historyFk) {
		this.historyFk = historyFk;
	}

}
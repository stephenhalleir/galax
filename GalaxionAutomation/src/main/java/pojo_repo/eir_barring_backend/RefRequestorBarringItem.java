package pojo_repo.eir_barring_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefRequestorBarringItem {

	private int barringItemId;
	private int requestorId;

	public RefRequestorBarringItem() {

	}

	public RefRequestorBarringItem(ResultSet rs) {
		try {
			barringItemId = rs.getInt("barring_item_id");
			requestorId = rs.getInt("requestor_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getBarringItemId() {
		return barringItemId;
	}

	public void setBarringItemId(int barringItemId) {
		this.barringItemId = barringItemId;
	}

	public int getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(int requestorId) {
		this.requestorId = requestorId;
	}

}
package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleType {

	private int id;
	private String startAt;
	private String endAt;

	public ScheduleType() {

	}

	public ScheduleType(ResultSet rs) {
		try {
			id = rs.getInt("id");
			startAt = rs.getString("start_at");
			endAt = rs.getString("end_at");
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

	public String getStartAt() {
		return startAt;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}

}
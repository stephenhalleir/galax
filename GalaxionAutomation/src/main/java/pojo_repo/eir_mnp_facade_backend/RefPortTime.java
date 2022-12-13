package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefPortTime {

	private int id;
	private String dateType;
	private Date startTime;

	public RefPortTime() {

	}

	public RefPortTime(ResultSet rs) {
		try {
			id = rs.getInt("id");
			dateType = rs.getString("date_type");
			startTime = rs.getDate("start_time");
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

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
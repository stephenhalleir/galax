package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzCalendars {

	private String schedName;
	private String calendarName;

	public QrtzCalendars() {

	}

	public QrtzCalendars(ResultSet rs) {
		try {
			schedName = rs.getString("SCHED_NAME");
			calendarName = rs.getString("CALENDAR_NAME");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

}
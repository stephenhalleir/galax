package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzCalendarsOld {

private String schedName;
private String calendarName;

public QrtzCalendarsOld() {

}

public QrtzCalendarsOld(ResultSet rs) {
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
 	 this.schedName=schedName;
}
public String getCalendarName() {
 	return calendarName;
}
public void setCalendarName(String calendarName) {
 	 this.calendarName=calendarName;
}

}
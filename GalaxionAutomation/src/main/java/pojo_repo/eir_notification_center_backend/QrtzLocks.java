package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzLocks {

	private String schedName;

	public QrtzLocks() {

	}

	public QrtzLocks(ResultSet rs) {
		try {
			schedName = rs.getString("SCHED_NAME");
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

}
package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzSchedulerState {

	private String schedName;
	private String instanceName;
	private int lastCheckinTime;

	public QrtzSchedulerState() {

	}

	public QrtzSchedulerState(ResultSet rs) {
		try {
			schedName = rs.getString("SCHED_NAME");
			instanceName = rs.getString("INSTANCE_NAME");
			lastCheckinTime = rs.getInt("LAST_CHECKIN_TIME");
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

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public int getLastCheckinTime() {
		return lastCheckinTime;
	}

	public void setLastCheckinTime(int lastCheckinTime) {
		this.lastCheckinTime = lastCheckinTime;
	}

}
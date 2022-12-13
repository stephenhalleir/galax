package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzPausedTriggerGrps {

	private String schedName;

	public QrtzPausedTriggerGrps() {

	}

	public QrtzPausedTriggerGrps(ResultSet rs) {
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
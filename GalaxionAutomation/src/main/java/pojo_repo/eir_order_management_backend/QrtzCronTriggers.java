package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzCronTriggers {

	private String schedName;
	private String triggerName;
	private String triggerGroup;
	private String cronExpression;

	public QrtzCronTriggers() {

	}

	public QrtzCronTriggers(ResultSet rs) {
		try {
			schedName = rs.getString("SCHED_NAME");
			triggerName = rs.getString("TRIGGER_NAME");
			triggerGroup = rs.getString("TRIGGER_GROUP");
			cronExpression = rs.getString("CRON_EXPRESSION");
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

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

}
package microservices.backend.eir_order_management_backend.data_model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;

import java.sql.ResultSet;

public class QuartzTrigger {
	private String triggerName;
	private String schedName;
	private String triggerGroup;
	private String jobName;
	private String jobGroup;
	private String description;
	private BigDecimal nextFireTime;
	private BigDecimal startTime;
	
	public QuartzTrigger(ResultSet rs) {
		try {
			triggerName=rs.getString("trigger_name");
			schedName=rs.getString("sched_name");
			triggerGroup=rs.getString("trigger_group");
			jobName=rs.getString("job_name");
			jobGroup=rs.getString("job_group");
			description=rs.getString("description");
			nextFireTime=rs.getBigDecimal("next_fire_time");
			startTime=rs.getBigDecimal("start_time");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(BigDecimal nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public BigDecimal getStartTime() {
		return startTime;
	}

	public void setStartTime(BigDecimal startTime) {
		this.startTime = startTime;
	}
}

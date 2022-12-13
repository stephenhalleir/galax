package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QrtzTriggers {

	private String schedName;
	private String triggerName;
	private String triggerGroup;
	private String jobName;
	private String jobGroup;
	private String description;
	private int nextFireTime;
	private int prevFireTime;
	private int priority;
	private String triggerState;
	private String triggerType;
	private int startTime;
	private int endTime;
	private String calendarName;
	private int misfireInstr;

	public QrtzTriggers() {

	}

	public QrtzTriggers(ResultSet rs) {
		try {
			schedName = rs.getString("SCHED_NAME");
			triggerName = rs.getString("TRIGGER_NAME");
			triggerGroup = rs.getString("TRIGGER_GROUP");
			jobName = rs.getString("JOB_NAME");
			jobGroup = rs.getString("JOB_GROUP");
			description = rs.getString("DESCRIPTION");
			nextFireTime = rs.getInt("NEXT_FIRE_TIME");
			prevFireTime = rs.getInt("PREV_FIRE_TIME");
			priority = rs.getInt("PRIORITY");
			triggerState = rs.getString("TRIGGER_STATE");
			triggerType = rs.getString("TRIGGER_TYPE");
			startTime = rs.getInt("START_TIME");
			endTime = rs.getInt("END_TIME");
			calendarName = rs.getString("CALENDAR_NAME");
			misfireInstr = rs.getInt("MISFIRE_INSTR");
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

	public int getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(int nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public int getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(int prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public int getMisfireInstr() {
		return misfireInstr;
	}

	public void setMisfireInstr(int misfireInstr) {
		this.misfireInstr = misfireInstr;
	}

}
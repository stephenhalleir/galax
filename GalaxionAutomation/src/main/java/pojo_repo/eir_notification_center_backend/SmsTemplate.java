package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SmsTemplate {

	private String reference;
	private int isPersisted;
	private int isPriority;
	private int scheduleTypeId;
	private String smsContentPath;

	public SmsTemplate() {

	}

	public SmsTemplate(ResultSet rs) {
		try {
			reference = rs.getString("reference");
			isPersisted = rs.getInt("is_persisted");
			isPriority = rs.getInt("is_priority");
			scheduleTypeId = rs.getInt("schedule_type_id");
			smsContentPath = rs.getString("sms_content_path");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getIsPersisted() {
		return isPersisted;
	}

	public void setIsPersisted(int isPersisted) {
		this.isPersisted = isPersisted;
	}

	public int getIsPriority() {
		return isPriority;
	}

	public void setIsPriority(int isPriority) {
		this.isPriority = isPriority;
	}

	public int getScheduleTypeId() {
		return scheduleTypeId;
	}

	public void setScheduleTypeId(int scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}

	public String getSmsContentPath() {
		return smsContentPath;
	}

	public void setSmsContentPath(String smsContentPath) {
		this.smsContentPath = smsContentPath;
	}

}
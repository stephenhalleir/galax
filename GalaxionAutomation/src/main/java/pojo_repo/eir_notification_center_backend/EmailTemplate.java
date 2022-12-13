package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailTemplate {

	private String reference;
	private int isPersisted;
	private int isPriority;
	private int scheduleTypeId;
	private String fromField;
	private String replyToField;
	private String emailSubjectPath;
	private String emailTxtBodyPath;
	private String emailHtmlBodyPath;

	public EmailTemplate() {

	}

	public EmailTemplate(ResultSet rs) {
		try {
			reference = rs.getString("reference");
			isPersisted = rs.getInt("is_persisted");
			isPriority = rs.getInt("is_priority");
			scheduleTypeId = rs.getInt("schedule_type_id");
			fromField = rs.getString("from_field");
			replyToField = rs.getString("reply_to_field");
			emailSubjectPath = rs.getString("email_subject_path");
			emailTxtBodyPath = rs.getString("email_txt_body_path");
			emailHtmlBodyPath = rs.getString("email_html_body_path");
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

	public String getFromField() {
		return fromField;
	}

	public void setFromField(String fromField) {
		this.fromField = fromField;
	}

	public String getReplyToField() {
		return replyToField;
	}

	public void setReplyToField(String replyToField) {
		this.replyToField = replyToField;
	}

	public String getEmailSubjectPath() {
		return emailSubjectPath;
	}

	public void setEmailSubjectPath(String emailSubjectPath) {
		this.emailSubjectPath = emailSubjectPath;
	}

	public String getEmailTxtBodyPath() {
		return emailTxtBodyPath;
	}

	public void setEmailTxtBodyPath(String emailTxtBodyPath) {
		this.emailTxtBodyPath = emailTxtBodyPath;
	}

	public String getEmailHtmlBodyPath() {
		return emailHtmlBodyPath;
	}

	public void setEmailHtmlBodyPath(String emailHtmlBodyPath) {
		this.emailHtmlBodyPath = emailHtmlBodyPath;
	}

}
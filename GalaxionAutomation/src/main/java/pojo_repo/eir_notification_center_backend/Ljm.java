package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ljm {

	private int id;
	private Date scheduledAt;
	private Date sentAt;
	private String msisdn;
	private String content;
	private String templateReference;
	private String smscId;
	private String smscStatus;
	private Date smscDoneDate;
	private Date createdAt;
	private Date updatedAt;

	public Ljm() {

	}

	public Ljm(ResultSet rs) {
		try {
			id = rs.getInt("id");
			scheduledAt = rs.getDate("scheduled_at");
			sentAt = rs.getDate("sent_at");
			msisdn = rs.getString("msisdn");
			content = rs.getString("content");
			templateReference = rs.getString("template_reference");
			smscId = rs.getString("smsc_id");
			smscStatus = rs.getString("smsc_status");
			smscDoneDate = rs.getDate("smsc_done_date");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getScheduledAt() {
		return scheduledAt;
	}

	public void setScheduledAt(Date scheduledAt) {
		this.scheduledAt = scheduledAt;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTemplateReference() {
		return templateReference;
	}

	public void setTemplateReference(String templateReference) {
		this.templateReference = templateReference;
	}

	public String getSmscId() {
		return smscId;
	}

	public void setSmscId(String smscId) {
		this.smscId = smscId;
	}

	public String getSmscStatus() {
		return smscStatus;
	}

	public void setSmscStatus(String smscStatus) {
		this.smscStatus = smscStatus;
	}

	public Date getSmscDoneDate() {
		return smscDoneDate;
	}

	public void setSmscDoneDate(Date smscDoneDate) {
		this.smscDoneDate = smscDoneDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
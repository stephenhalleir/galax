package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailMessage {

	private int id;
	private Date scheduledAt;
	private Date sentAt;
	private String bcc;
	private String cc;
	private String htmlBodyContent;
	private String subjectContent;
	private String to;
	private String txtBodyContent;
	private String templateReference;
	private Date createdAt;
	private Date updatedAt;

	public EmailMessage() {

	}

	public EmailMessage(ResultSet rs) {
		try {
			id = rs.getInt("id");
			scheduledAt = rs.getDate("scheduled_at");
			sentAt = rs.getDate("sent_at");
			bcc = rs.getString("bcc");
			cc = rs.getString("cc");
			htmlBodyContent = rs.getString("html_body_content");
			subjectContent = rs.getString("subject_content");
			to = rs.getString("to");
			txtBodyContent = rs.getString("txt_body_content");
			templateReference = rs.getString("template_reference");
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

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getHtmlBodyContent() {
		return htmlBodyContent;
	}

	public void setHtmlBodyContent(String htmlBodyContent) {
		this.htmlBodyContent = htmlBodyContent;
	}

	public String getSubjectContent() {
		return subjectContent;
	}

	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTxtBodyContent() {
		return txtBodyContent;
	}

	public void setTxtBodyContent(String txtBodyContent) {
		this.txtBodyContent = txtBodyContent;
	}

	public String getTemplateReference() {
		return templateReference;
	}

	public void setTemplateReference(String templateReference) {
		this.templateReference = templateReference;
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
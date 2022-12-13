package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WebMessage {

	private int id;
	private Date scheduledAt;
	private Date sentAt;
	private String contactUuid;
	private String content;
	private Date createdAt;
	private int isDisplayable;
	private String navLink;
	private Date updatedAt;
	private String templateReference;

	public WebMessage() {

	}

	public WebMessage(ResultSet rs) {
		try {
			id = rs.getInt("id");
			scheduledAt = rs.getDate("scheduled_at");
			sentAt = rs.getDate("sent_at");
			contactUuid = rs.getString("contact_uuid");
			content = rs.getString("content");
			createdAt = rs.getDate("created_at");
			isDisplayable = rs.getInt("is_displayable");
			navLink = rs.getString("nav_link");
			updatedAt = rs.getDate("updated_at");
			templateReference = rs.getString("template_reference");
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

	public String getContactUuid() {
		return contactUuid;
	}

	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getIsDisplayable() {
		return isDisplayable;
	}

	public void setIsDisplayable(int isDisplayable) {
		this.isDisplayable = isDisplayable;
	}

	public String getNavLink() {
		return navLink;
	}

	public void setNavLink(String navLink) {
		this.navLink = navLink;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getTemplateReference() {
		return templateReference;
	}

	public void setTemplateReference(String templateReference) {
		this.templateReference = templateReference;
	}

}
package microservices.backend.eir_notification_center_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NCWebMessage {
	private int id;
	private Date scheduledAt;
	private Date sentAt;
	private Date createdAt;
	private Date updatedAt;
	private String contactUuid;
	private String content;
	private int isDisplayable;
	private String navLink;
	private String templateReference;
	private int accountID;
	
	public NCWebMessage() {
		
	}
	
	public NCWebMessage(ResultSet rs) {
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
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTemplateReference() {
		return templateReference;
	}

	public void setTemplateReference(String templateReference) {
		this.templateReference = templateReference;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
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

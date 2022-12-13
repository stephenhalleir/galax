package microservices.frontend.common_ui.response_objects.notification_center;

public class WebMessage {

	private int id;
	private String scheduledAt;
	private String sentAt;
	private String createdAt;
	private String updatedAt;
	private String accountId;
	private String notificationRef;
	private Content content;
	private boolean displayable;
	private String iconName;
	
	public WebMessage() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScheduledAt() {
		return scheduledAt;
	}
	public void setScheduledAt(String scheduledAt) {
		this.scheduledAt = scheduledAt;
	}
	public String getSentAt() {
		return sentAt;
	}
	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getNotificationRef() {
		return notificationRef;
	}
	public void setNotificationRef(String notificationRef) {
		this.notificationRef = notificationRef;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public boolean isDisplayable() {
		return displayable;
	}
	public void setDisplayable(boolean displayable) {
		this.displayable = displayable;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
}

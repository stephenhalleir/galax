package microservices.frontend.common_ui.response_objects.notification_center;

public class Content {
	private String message;
	private String navLink;
	private String navLinkLabel;
	private String toolTip;
	
	public Content() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavLink() {
		return navLink;
	}
	public void setNavLink(String navLink) {
		this.navLink = navLink;
	}
	public String getNavLinkLabel() {
		return navLinkLabel;
	}
	public void setNavLinkLabel(String navLinkLabel) {
		this.navLinkLabel = navLinkLabel;
	}
	public String getToolTip() {
		return toolTip;
	}
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
}

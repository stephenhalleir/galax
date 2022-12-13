package pojo_repo.eir_notification_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WebTemplate {

	private String reference;
	private int isPersisted;
	private int isPriority;
	private int scheduleTypeId;
	private String txtPath;
	private String navLinkLabel;
	private String navLinkPath;
	private String tooltip;
	private int isDisplayable;

	public WebTemplate() {

	}

	public WebTemplate(ResultSet rs) {
		try {
			reference = rs.getString("reference");
			isPersisted = rs.getInt("is_persisted");
			isPriority = rs.getInt("is_priority");
			scheduleTypeId = rs.getInt("schedule_type_id");
			txtPath = rs.getString("txt_path");
			navLinkLabel = rs.getString("nav_link_label");
			navLinkPath = rs.getString("nav_link_path");
			tooltip = rs.getString("tooltip");
			isDisplayable = rs.getInt("is_displayable");
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

	public String getTxtPath() {
		return txtPath;
	}

	public void setTxtPath(String txtPath) {
		this.txtPath = txtPath;
	}

	public String getNavLinkLabel() {
		return navLinkLabel;
	}

	public void setNavLinkLabel(String navLinkLabel) {
		this.navLinkLabel = navLinkLabel;
	}

	public String getNavLinkPath() {
		return navLinkPath;
	}

	public void setNavLinkPath(String navLinkPath) {
		this.navLinkPath = navLinkPath;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public int getIsDisplayable() {
		return isDisplayable;
	}

	public void setIsDisplayable(int isDisplayable) {
		this.isDisplayable = isDisplayable;
	}

}
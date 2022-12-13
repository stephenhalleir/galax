package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemHistoryOld {

	private int id;
	private int rev;
	private int revtype;
	private Date creationDateTime;
	private Date startDateTime;
	private int lockedByProvisioning;
	private String status;
	private int catalogItemId;
	private String itemHierarchyLevel;

	public ItemHistoryOld() {

	}

	public ItemHistoryOld(ResultSet rs) {
		try {
			id = rs.getInt("id");
			rev = rs.getInt("rev");
			revtype = rs.getInt("revtype");
			creationDateTime = rs.getDate("creation_date_time");
			startDateTime = rs.getDate("start_date_time");
			lockedByProvisioning = rs.getInt("locked_by_provisioning");
			status = rs.getString("status");
			catalogItemId = rs.getInt("catalog_item_id");
			itemHierarchyLevel = rs.getString("item_hierarchy_level");
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

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public int getRevtype() {
		return revtype;
	}

	public void setRevtype(int revtype) {
		this.revtype = revtype;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public int getLockedByProvisioning() {
		return lockedByProvisioning;
	}

	public void setLockedByProvisioning(int lockedByProvisioning) {
		this.lockedByProvisioning = lockedByProvisioning;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCatalogItemId() {
		return catalogItemId;
	}

	public void setCatalogItemId(int catalogItemId) {
		this.catalogItemId = catalogItemId;
	}

	public String getItemHierarchyLevel() {
		return itemHierarchyLevel;
	}

	public void setItemHierarchyLevel(String itemHierarchyLevel) {
		this.itemHierarchyLevel = itemHierarchyLevel;
	}

}
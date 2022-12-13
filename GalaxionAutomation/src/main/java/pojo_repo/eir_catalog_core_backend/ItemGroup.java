package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemGroup {

	private int id;
	private int maxPerSubscription;
	private int minPerSubscription;
	private String displayName;
	private int displayOrder;

	public ItemGroup() {

	}

	public ItemGroup(ResultSet rs) {
		try {
			id = rs.getInt("id");
			maxPerSubscription = rs.getInt("max_per_subscription");
			minPerSubscription = rs.getInt("min_per_subscription");
			displayName = rs.getString("display_name");
			displayOrder = rs.getInt("display_order");
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

	public int getMaxPerSubscription() {
		return maxPerSubscription;
	}

	public void setMaxPerSubscription(int maxPerSubscription) {
		this.maxPerSubscription = maxPerSubscription;
	}

	public int getMinPerSubscription() {
		return minPerSubscription;
	}

	public void setMinPerSubscription(int minPerSubscription) {
		this.minPerSubscription = minPerSubscription;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

}
package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferNetworkElement {

	private String offerCode;
	private String networkElementCode;
	private int displayOrder;
	private int itemGroupId;

	public OfferNetworkElement() {

	}

	public OfferNetworkElement(ResultSet rs) {
		try {
			offerCode = rs.getString("offer_code");
			networkElementCode = rs.getString("network_element_code");
			displayOrder = rs.getInt("display_order");
			itemGroupId = rs.getInt("item_group_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getNetworkElementCode() {
		return networkElementCode;
	}

	public void setNetworkElementCode(String networkElementCode) {
		this.networkElementCode = networkElementCode;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public int getItemGroupId() {
		return itemGroupId;
	}

	public void setItemGroupId(int itemGroupId) {
		this.itemGroupId = itemGroupId;
	}

}
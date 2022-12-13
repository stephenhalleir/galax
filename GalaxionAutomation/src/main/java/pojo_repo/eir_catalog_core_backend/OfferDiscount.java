package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferDiscount {

	private String offerCode;
	private String discountCode;
	private int displayOrder;
	private int itemGroupId;

	public OfferDiscount() {

	}

	public OfferDiscount(ResultSet rs) {
		try {
			offerCode = rs.getString("offer_code");
			discountCode = rs.getString("discount_code");
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

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
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
package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubOfferItemGroup {

	private int subOfferId;

	public SubOfferItemGroup() {

	}

	public SubOfferItemGroup(ResultSet rs) {
		try {
			subOfferId = rs.getInt("sub_offer_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getSubOfferId() {
		return subOfferId;
	}

	public void setSubOfferId(int subOfferId) {
		this.subOfferId = subOfferId;
	}

}
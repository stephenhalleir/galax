package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferIncompatibilityGroups {

	private int offerId;

	public OfferIncompatibilityGroups() {

	}

	public OfferIncompatibilityGroups(ResultSet rs) {
		try {
			offerId = rs.getInt("offer_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

}
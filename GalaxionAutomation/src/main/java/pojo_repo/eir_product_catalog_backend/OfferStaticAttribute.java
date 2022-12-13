package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferStaticAttribute {

	private int offerId;

	public OfferStaticAttribute() {

	}

	public OfferStaticAttribute(ResultSet rs) {
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
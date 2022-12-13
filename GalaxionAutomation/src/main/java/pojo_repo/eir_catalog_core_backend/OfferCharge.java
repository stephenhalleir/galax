package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferCharge {

	private String offerCode;
	private String chargeCode;
	private int displayOrder;
	private String serviceGroupCode;

	public OfferCharge() {

	}

	public OfferCharge(ResultSet rs) {
		try {
			offerCode = rs.getString("offer_code");
			chargeCode = rs.getString("charge_code");
			displayOrder = rs.getInt("display_order");
			serviceGroupCode = rs.getString("service_group_code");
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

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getServiceGroupCode() {
		return serviceGroupCode;
	}

	public void setServiceGroupCode(String serviceGroupCode) {
		this.serviceGroupCode = serviceGroupCode;
	}

}
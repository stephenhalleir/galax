package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferSimCard {

	private String offerCode;
	private String simCardInventoryCode;
	private String serviceGroupCode;

	public OfferSimCard() {

	}

	public OfferSimCard(ResultSet rs) {
		try {
			offerCode = rs.getString("offer_code");
			simCardInventoryCode = rs.getString("sim_card_inventory_code");
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

	public String getSimCardInventoryCode() {
		return simCardInventoryCode;
	}

	public void setSimCardInventoryCode(String simCardInventoryCode) {
		this.simCardInventoryCode = simCardInventoryCode;
	}

	public String getServiceGroupCode() {
		return serviceGroupCode;
	}

	public void setServiceGroupCode(String serviceGroupCode) {
		this.serviceGroupCode = serviceGroupCode;
	}

}
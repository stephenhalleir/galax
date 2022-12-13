package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferEquipment {

	private String offerCode;
	private String equipmentCode;
	private int displayOrder;
	private String serviceGroupCode;

	public OfferEquipment() {

	}

	public OfferEquipment(ResultSet rs) {
		try {
			offerCode = rs.getString("offer_code");
			equipmentCode = rs.getString("equipment_code");
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

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
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
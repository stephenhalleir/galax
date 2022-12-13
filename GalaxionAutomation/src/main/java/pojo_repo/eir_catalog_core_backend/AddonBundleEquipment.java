package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundleEquipment {

	private String addonBundleCode;
	private String equipmentCode;

	public AddonBundleEquipment() {

	}

	public AddonBundleEquipment(ResultSet rs) {
		try {
			addonBundleCode = rs.getString("addon_bundle_code");
			equipmentCode = rs.getString("equipment_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getAddonBundleCode() {
		return addonBundleCode;
	}

	public void setAddonBundleCode(String addonBundleCode) {
		this.addonBundleCode = addonBundleCode;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

}
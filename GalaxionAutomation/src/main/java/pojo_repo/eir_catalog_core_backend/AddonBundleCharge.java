package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundleCharge {

	private String addonBundleCode;
	private String chargeCode;

	public AddonBundleCharge() {

	}

	public AddonBundleCharge(ResultSet rs) {
		try {
			addonBundleCode = rs.getString("addon_bundle_code");
			chargeCode = rs.getString("charge_code");
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

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

}
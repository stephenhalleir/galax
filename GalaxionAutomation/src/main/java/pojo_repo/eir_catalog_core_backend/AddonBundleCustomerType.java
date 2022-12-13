package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundleCustomerType {

	private String addonBundleCode;

	public AddonBundleCustomerType() {

	}

	public AddonBundleCustomerType(ResultSet rs) {
		try {
			addonBundleCode = rs.getString("addon_bundle_code");
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

}
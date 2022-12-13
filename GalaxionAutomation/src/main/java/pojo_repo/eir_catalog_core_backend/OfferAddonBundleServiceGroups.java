package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferAddonBundleServiceGroups {

	private String offerCode;
	private String addonBundleCode;

	public OfferAddonBundleServiceGroups() {

	}

	public OfferAddonBundleServiceGroups(ResultSet rs) {
		try {
			offerCode = rs.getString("offer_code");
			addonBundleCode = rs.getString("addon_bundle_code");
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

	public String getAddonBundleCode() {
		return addonBundleCode;
	}

	public void setAddonBundleCode(String addonBundleCode) {
		this.addonBundleCode = addonBundleCode;
	}

}
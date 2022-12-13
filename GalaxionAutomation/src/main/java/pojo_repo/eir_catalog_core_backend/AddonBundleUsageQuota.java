package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundleUsageQuota {

	private String addonBundleCode;
	private String usageQuotaCode;

	public AddonBundleUsageQuota() {

	}

	public AddonBundleUsageQuota(ResultSet rs) {
		try {
			addonBundleCode = rs.getString("addon_bundle_code");
			usageQuotaCode = rs.getString("usage_quota_code");
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

	public String getUsageQuotaCode() {
		return usageQuotaCode;
	}

	public void setUsageQuotaCode(String usageQuotaCode) {
		this.usageQuotaCode = usageQuotaCode;
	}

}
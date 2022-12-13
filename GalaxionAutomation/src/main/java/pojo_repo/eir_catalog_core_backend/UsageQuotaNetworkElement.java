package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsageQuotaNetworkElement {

	private String usageQuotaCode;

	public UsageQuotaNetworkElement() {

	}

	public UsageQuotaNetworkElement(ResultSet rs) {
		try {
			usageQuotaCode = rs.getString("usage_quota_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUsageQuotaCode() {
		return usageQuotaCode;
	}

	public void setUsageQuotaCode(String usageQuotaCode) {
		this.usageQuotaCode = usageQuotaCode;
	}

}
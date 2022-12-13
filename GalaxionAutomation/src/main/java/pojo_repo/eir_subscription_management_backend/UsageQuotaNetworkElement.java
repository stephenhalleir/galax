package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsageQuotaNetworkElement {

private int usageQuotaId;

public UsageQuotaNetworkElement() {

}

public UsageQuotaNetworkElement(ResultSet rs) {
try {
	usageQuotaId = rs.getInt("usage_quota_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getUsageQuotaId() {
 	return usageQuotaId;
}
public void setUsageQuotaId(int usageQuotaId) {
 	 this.usageQuotaId=usageQuotaId;
}

}
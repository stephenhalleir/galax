package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferUsageQuota {

	private String offerCode;
	private String usageQuotaCode;
	private int displayOrder;
	private String serviceGroupCode;

	public OfferUsageQuota() {

	}

	public OfferUsageQuota(ResultSet rs) {
		try {
			offerCode = rs.getString("offer_code");
			usageQuotaCode = rs.getString("usage_quota_code");
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

	public String getUsageQuotaCode() {
		return usageQuotaCode;
	}

	public void setUsageQuotaCode(String usageQuotaCode) {
		this.usageQuotaCode = usageQuotaCode;
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
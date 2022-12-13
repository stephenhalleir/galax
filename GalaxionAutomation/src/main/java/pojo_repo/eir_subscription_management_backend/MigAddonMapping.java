package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigAddonMapping {

	private String cmpServiceCode;
	private String addonCode;
	private int addonId;
	private String charge;
	private int chargeId;
	private String pricePlan;
	private int pricePlanId;
	private String networkElement;
	private int networkElementId;
	private String usageQuota;

	public MigAddonMapping() {

	}

	public MigAddonMapping(ResultSet rs) {
		try {
			cmpServiceCode = rs.getString("cmp_service_code");
			addonCode = rs.getString("addon_code");
			addonId = rs.getInt("addon_id");
			charge = rs.getString("charge");
			chargeId = rs.getInt("charge_id");
			pricePlan = rs.getString("price_plan");
			pricePlanId = rs.getInt("price_plan_id");
			networkElement = rs.getString("network_element");
			networkElementId = rs.getInt("network_element_id");
			usageQuota = rs.getString("usage_quota");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCmpServiceCode() {
		return cmpServiceCode;
	}

	public void setCmpServiceCode(String cmpServiceCode) {
		this.cmpServiceCode = cmpServiceCode;
	}

	public String getAddonCode() {
		return addonCode;
	}

	public void setAddonCode(String addonCode) {
		this.addonCode = addonCode;
	}

	public int getAddonId() {
		return addonId;
	}

	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	public String getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(String pricePlan) {
		this.pricePlan = pricePlan;
	}

	public int getPricePlanId() {
		return pricePlanId;
	}

	public void setPricePlanId(int pricePlanId) {
		this.pricePlanId = pricePlanId;
	}

	public String getNetworkElement() {
		return networkElement;
	}

	public void setNetworkElement(String networkElement) {
		this.networkElement = networkElement;
	}

	public int getNetworkElementId() {
		return networkElementId;
	}

	public void setNetworkElementId(int networkElementId) {
		this.networkElementId = networkElementId;
	}

	public String getUsageQuota() {
		return usageQuota;
	}

	public void setUsageQuota(String usageQuota) {
		this.usageQuota = usageQuota;
	}

}
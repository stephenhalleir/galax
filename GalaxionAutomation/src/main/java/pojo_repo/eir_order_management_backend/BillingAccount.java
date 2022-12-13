package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAccount {

	private int id;
	private String billDeliveryType;
	private int billCycleId;
	private int isItemised;
	private int hasBillAnalyserConsent;

	public BillingAccount() {

	}

	public BillingAccount(ResultSet rs) {
		try {
			id = rs.getInt("id");
			billDeliveryType = rs.getString("bill_delivery_type");
			billCycleId = rs.getInt("bill_cycle_id");
			isItemised = rs.getInt("is_itemised");
			hasBillAnalyserConsent = rs.getInt("has_bill_analyser_consent");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBillDeliveryType() {
		return billDeliveryType;
	}

	public void setBillDeliveryType(String billDeliveryType) {
		this.billDeliveryType = billDeliveryType;
	}

	public int getBillCycleId() {
		return billCycleId;
	}

	public void setBillCycleId(int billCycleId) {
		this.billCycleId = billCycleId;
	}

	public int getIsItemised() {
		return isItemised;
	}

	public void setIsItemised(int isItemised) {
		this.isItemised = isItemised;
	}

	public int getHasBillAnalyserConsent() {
		return hasBillAnalyserConsent;
	}

	public void setHasBillAnalyserConsent(int hasBillAnalyserConsent) {
		this.hasBillAnalyserConsent = hasBillAnalyserConsent;
	}

}
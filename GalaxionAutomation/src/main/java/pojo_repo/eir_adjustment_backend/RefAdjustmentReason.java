package pojo_repo.eir_adjustment_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefAdjustmentReason {

	private String reasonKey;
	private String displayName;
	private String status;
	private String vatCode;
	private String adjustmentType;

	public RefAdjustmentReason() {

	}

	public RefAdjustmentReason(ResultSet rs) {
		try {
			reasonKey = rs.getString("reason_key");
			displayName = rs.getString("display_name");
			status = rs.getString("status");
			vatCode = rs.getString("vat_code");
			adjustmentType = rs.getString("adjustment_type");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getReasonKey() {
		return reasonKey;
	}

	public void setReasonKey(String reasonKey) {
		this.reasonKey = reasonKey;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

}
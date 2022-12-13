package microservices.backend.eir_adjustment_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;

/**
 * This class represents the 'eir-adjustment-backend'.ref_adjustment_reason table
 * @author stephenhall
 *
 */
public class RefAdjustmentReason {

	private String reasonKey;
	private String displayName;
	private String status;
	private String vatCode;
	private String adjustmentType;
	private String adjustmentFinancialType;
	
	public RefAdjustmentReason() {
		
	}
	
	public RefAdjustmentReason(ResultSet rs) {
		try {
			reasonKey=rs.getString("reason_key");
			displayName=rs.getString("display_name");
			status=rs.getString("status");
			vatCode=rs.getString("vat_code");
			adjustmentType=rs.getString("adjustment_type");
			adjustmentFinancialType=rs.getString("adjustment_financial_type");
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

	public String getAdjustmentFinancialType() {
		return adjustmentFinancialType;
	}

	public void setAdjustmentFinancialType(String adjustmentFinancialType) {
		this.adjustmentFinancialType = adjustmentFinancialType;
	}
	
	public AdjustmentReason asReason() {
		return AdjustmentReason.valueOf(reasonKey);
	}
}

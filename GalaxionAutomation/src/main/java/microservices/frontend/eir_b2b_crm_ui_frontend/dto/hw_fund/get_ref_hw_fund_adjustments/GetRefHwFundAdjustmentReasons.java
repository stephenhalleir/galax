package microservices.frontend.eir_b2b_crm_ui_frontend.dto.hw_fund.get_ref_hw_fund_adjustments;

public class GetRefHwFundAdjustmentReasons {
	private String displayName;
	private String value;
	private String adjustmentFinancialType;

	public GetRefHwFundAdjustmentReasons() {
		super();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAdjustmentFinancialType() {
		return adjustmentFinancialType;
	}

	public void setAdjustmentFinancialType(String adjustmentFinancialType) {
		this.adjustmentFinancialType = adjustmentFinancialType;
	}
}

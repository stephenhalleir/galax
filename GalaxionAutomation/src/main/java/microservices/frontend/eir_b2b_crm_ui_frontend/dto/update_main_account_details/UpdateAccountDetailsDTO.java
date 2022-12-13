package microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_main_account_details;

import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_subscription_management_backend.enums.CreditClass;
import microservices.backend.eir_subscription_management_backend.enums.MarketSegment;

public class UpdateAccountDetailsDTO {

	private CreditClass creditClass;
	private MarketSegment marketSegment;
	private String customerAccountName;
	private B2BAccountType accountType;
	
	public UpdateAccountDetailsDTO(CreditClass creditClass, MarketSegment marketSegment, String customerAccountName, B2BAccountType accountType) {
		super();
		this.creditClass = creditClass;
		this.marketSegment = marketSegment;
		this.customerAccountName = customerAccountName;
		this.accountType = accountType;
	}
	public CreditClass getCreditClass() {
		return creditClass;
	}
	public void setCreditClass(CreditClass creditClass) {
		this.creditClass = creditClass;
	}
	public MarketSegment getMarketSegment() {
		return marketSegment;
	}
	public void setMarketSegment(MarketSegment marketSegment) {
		this.marketSegment = marketSegment;
	}
	public String getCustomerAccountName() {
		return customerAccountName;
	}
	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}
	public B2BAccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(B2BAccountType accountType) {
		this.accountType = accountType;
	}	
}

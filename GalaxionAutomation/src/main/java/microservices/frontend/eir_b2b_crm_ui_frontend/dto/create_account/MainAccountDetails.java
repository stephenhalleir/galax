package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

public class MainAccountDetails {
	private String customerAccountName;
	private String accountType;
	private String marketSegment;
	private String creditClass;
	
	
	public MainAccountDetails(String customerAccountName, String accountType, String marketSegment, String creditClass) {
		super();
		this.customerAccountName = customerAccountName;
		this.accountType = accountType;
		this.marketSegment = marketSegment;
		this.creditClass = creditClass;
	}


	public String getCustomerAccountName() {
		return customerAccountName;
	}


	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getMarketSegment() {
		return marketSegment;
	}


	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}


	public String getCreditClass() {
		return creditClass;
	}


	public void setCreditClass(String creditClass) {
		this.creditClass = creditClass;
	}	
}

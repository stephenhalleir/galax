package microservices.backend.eir_subscription_management_backend.data_model.custom;

public class B2BAccountAttributeSet {

	private String salesforceCustomerId;
	private String groupIcid;
	private String agreementDuration;
	private String accountManager;
	private String customerCostCenter;
	private String vpnAccountNumber;
	private String salesforceCaseNumber;
	private String indoorCoverageSolutionDate;

	public B2BAccountAttributeSet() {
		super();
	}

	public String getSalesforceCustomerId() {
		return salesforceCustomerId;
	}

	public void setSalesforceCustomerId(String salesforceCustomerId) {
		this.salesforceCustomerId = salesforceCustomerId;
	}

	public String getGroupIcid() {
		return groupIcid;
	}

	public void setGroupIcid(String groupIcid) {
		this.groupIcid = groupIcid;
	}

	public String getAgreementDuration() {
		return agreementDuration;
	}

	public void setAgreementDuration(String agreementDuration) {
		this.agreementDuration = agreementDuration;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getCustomerCostCenter() {
		return customerCostCenter;
	}

	public void setCustomerCostCenter(String customerCostCenter) {
		this.customerCostCenter = customerCostCenter;
	}

	public String getVpnAccountNumber() {
		return vpnAccountNumber;
	}

	public void setVpnAccountNumber(String vpnAccountNumber) {
		this.vpnAccountNumber = vpnAccountNumber;
	}

	public String getSalesforceCaseNumber() {
		return salesforceCaseNumber;
	}

	public void setSalesforceCaseNumber(String salesforceCaseNumber) {
		this.salesforceCaseNumber = salesforceCaseNumber;
	}

	public String getIndoorCoverageSolutionDate() {
		return indoorCoverageSolutionDate;
	}

	public void setIndoorCoverageSolutionDate(String indoorCoverageSolutionDate) {
		this.indoorCoverageSolutionDate = indoorCoverageSolutionDate;
	}
}

package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountAdditionalInfo {

	private int id;
	private String accountManager;
	private String customerCostCenter;
	private String groupIcid;
	private String vpnAccountNumber;
	private String salesforceCustomerId;
	private String salesforceCaseNumber;
	private String agreementDuration;
	private String indoorCoverageSolutionDate;

	public AccountAdditionalInfo() {

	}

	public AccountAdditionalInfo(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountManager = rs.getString("account_manager");
			customerCostCenter = rs.getString("customer_cost_center");
			groupIcid = rs.getString("group_icid");
			vpnAccountNumber = rs.getString("vpn_account_number");
			salesforceCustomerId = rs.getString("salesforce_customer_id");
			salesforceCaseNumber = rs.getString("salesforce_case_number");
			agreementDuration = rs.getString("agreement_duration");
			indoorCoverageSolutionDate = rs.getString("indoor_coverage_solution_date");
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

	public String getGroupIcid() {
		return groupIcid;
	}

	public void setGroupIcid(String groupIcid) {
		this.groupIcid = groupIcid;
	}

	public String getVpnAccountNumber() {
		return vpnAccountNumber;
	}

	public void setVpnAccountNumber(String vpnAccountNumber) {
		this.vpnAccountNumber = vpnAccountNumber;
	}

	public String getSalesforceCustomerId() {
		return salesforceCustomerId;
	}

	public void setSalesforceCustomerId(String salesforceCustomerId) {
		this.salesforceCustomerId = salesforceCustomerId;
	}

	public String getSalesforceCaseNumber() {
		return salesforceCaseNumber;
	}

	public void setSalesforceCaseNumber(String salesforceCaseNumber) {
		this.salesforceCaseNumber = salesforceCaseNumber;
	}

	public String getAgreementDuration() {
		return agreementDuration;
	}

	public void setAgreementDuration(String agreementDuration) {
		this.agreementDuration = agreementDuration;
	}

	public String getIndoorCoverageSolutionDate() {
		return indoorCoverageSolutionDate;
	}

	public void setIndoorCoverageSolutionDate(String indoorCoverageSolutionDate) {
		this.indoorCoverageSolutionDate = indoorCoverageSolutionDate;
	}

}
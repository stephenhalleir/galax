package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class B2BAccount {

	private int id;
	private String name;
	private int collectionExempt;
	private int paymentTerm;
	private int hardwareBalanceId;
	private int billAnalyserConsent;
	private int creditLimit;
	private String creditClass;
	private int deposit;
	private int parentId;
	private int companyId;
	private String hardwareFundTerm;
	private int isInvoiceOwner;
	private int taxDetailsId;
	private int rootAccountId;

	public B2BAccount() {

	}

	public B2BAccount(ResultSet rs) {
		try {
			id = rs.getInt("id");
			name = rs.getString("name");
			collectionExempt = rs.getInt("collection_exempt");
			paymentTerm = rs.getInt("payment_term");
			hardwareBalanceId = rs.getInt("hardware_balance_id");
			billAnalyserConsent = rs.getInt("bill_analyser_consent");
			creditLimit = rs.getInt("credit_limit");
			creditClass = rs.getString("credit_class");
			deposit = rs.getInt("deposit");
			parentId = rs.getInt("parent_id");
			companyId = rs.getInt("company_id");
			hardwareFundTerm = rs.getString("hardware_fund_term");
			isInvoiceOwner = rs.getInt("is_invoice_owner");
			taxDetailsId=rs.getInt("tax_details_id");
			rootAccountId=rs.getInt("root_account_id");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCollectionExempt() {
		return collectionExempt;
	}

	public void setCollectionExempt(int collectionExempt) {
		this.collectionExempt = collectionExempt;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public int getHardwareBalanceId() {
		return hardwareBalanceId;
	}

	public void setHardwareBalanceId(int hardwareBalanceId) {
		this.hardwareBalanceId = hardwareBalanceId;
	}

	public int getBillAnalyserConsent() {
		return billAnalyserConsent;
	}

	public void setBillAnalyserConsent(int billAnalyserConsent) {
		this.billAnalyserConsent = billAnalyserConsent;
	}

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCreditClass() {
		return creditClass;
	}

	public void setCreditClass(String creditClass) {
		this.creditClass = creditClass;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getHardwareFundTerm() {
		return hardwareFundTerm;
	}

	public void setHardwareFundTerm(String hardwareFundTerm) {
		this.hardwareFundTerm = hardwareFundTerm;
	}

	public int getIsInvoiceOwner() {
		return isInvoiceOwner;
	}

	public void setIsInvoiceOwner(int isInvoiceOwner) {
		this.isInvoiceOwner = isInvoiceOwner;
	}

	public int getHardwareBalanceID() {
		return 0;
	}

	public int getTaxDetailsId() {
		return taxDetailsId;
	}

	public void setTaxDetailsId(int taxDetailsId) {
		this.taxDetailsId = taxDetailsId;
	}

	public int getRootAccountId() {
		return rootAccountId;
	}

	public void setRootAccountId(int rootAccountId) {
		this.rootAccountId = rootAccountId;
	}
}

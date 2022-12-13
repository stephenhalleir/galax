package microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_account;

import java.util.List;

import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_account_general_details.UpdateGeneralDetailsDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_device_enrollment.DeviceEnrollmentDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_tax_details.TaxDetailsDTO;

public class GetAccountDTO {
	private int billingAccountId;
	private String creationDateTime;
	private String type;
	private boolean isRootAccount;
	private int id;
	private String name;
	private String status;
	private UpdateGeneralDetailsDTO accountAttributeList;
	private TaxDetailsDTO taxDetails;
	private String ownerPhoneNumber;
	private String ownerMobileNumber;
	private int billingCycle;
	private boolean isBillingAccount;
	private boolean billAnalyserConsent;
	private CompanyDTO company;
	private int overdueAmount;
	private long balanceAmount;
	private String creditClass;
	private String marketSegment;
	private List<DeviceEnrollmentDTO> deviceEnrollments;
	private int paymentTerm;
	private int hardwareFundId;
	private int hardwareFundTerm;
	private boolean billItemised;
	private String billDeliveryType;
	private String firstInvoiceDate;
	private String nextInvoiceDate;
	private String lastInvoiceDate;

	public GetAccountDTO() {
		super();
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIsRootAccount() {
		return isRootAccount;
	}

	public void setRootAccount(boolean isRootAccount) {
		this.isRootAccount = isRootAccount;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UpdateGeneralDetailsDTO getAccountAttributeList() {
		return accountAttributeList;
	}

	public void setAccountAttributeList(UpdateGeneralDetailsDTO accountAttributeList) {
		this.accountAttributeList = accountAttributeList;
	}

	public TaxDetailsDTO getTaxDetails() {
		return taxDetails;
	}

	public void setTaxDetails(TaxDetailsDTO taxDetails) {
		this.taxDetails = taxDetails;
	}

	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public String getOwnerMobileNumber() {
		return ownerMobileNumber;
	}

	public void setOwnerMobileNumber(String ownerMobileNumber) {
		this.ownerMobileNumber = ownerMobileNumber;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public boolean isIsBillingAccount() {
		return isBillingAccount;
	}

	public void setBillingAccount(boolean isBillingAccount) {
		this.isBillingAccount = isBillingAccount;
	}

	public boolean isBillAnalyserConsent() {
		return billAnalyserConsent;
	}

	public void setBillAnalyserConsent(boolean billAnalyserConsent) {
		this.billAnalyserConsent = billAnalyserConsent;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public int getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(int overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public long getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getCreditClass() {
		return creditClass;
	}

	public void setCreditClass(String creditClass) {
		this.creditClass = creditClass;
	}

	public String getMarketSegment() {
		return marketSegment;
	}

	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}

	public List<DeviceEnrollmentDTO> getDeviceEnrollments() {
		return deviceEnrollments;
	}

	public void setDeviceEnrollments(List<DeviceEnrollmentDTO> deviceEnrollments) {
		this.deviceEnrollments = deviceEnrollments;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public int getHardwareFundId() {
		return hardwareFundId;
	}

	public void setHardwareFundId(int hardwareFundId) {
		this.hardwareFundId = hardwareFundId;
	}

	public int getHardwareFundTerm() {
		return hardwareFundTerm;
	}

	public void setHardwareFundTerm(int hardwareFundTerm) {
		this.hardwareFundTerm = hardwareFundTerm;
	}

	public boolean isBillItemised() {
		return billItemised;
	}

	public void setBillItemised(boolean billItemised) {
		this.billItemised = billItemised;
	}

	public String getFirstInvoiceDate() {
		return firstInvoiceDate;
	}

	public void setFirstInvoiceDate(String firstInvoiceDate) {
		this.firstInvoiceDate = firstInvoiceDate;
	}

	public String getNextInvoiceDate() {
		return nextInvoiceDate;
	}

	public void setNextInvoiceDate(String nextInvoiceDate) {
		this.nextInvoiceDate = nextInvoiceDate;
	}

	public String getLastInvoiceDate() {
		return lastInvoiceDate;
	}

	public void setLastInvoiceDate(String lastInvoiceDate) {
		this.lastInvoiceDate = lastInvoiceDate;
	}

	public String getBillDeliveryType() {
		return billDeliveryType;
	}

	public void setBillDeliveryType(String billDeliveryType) {
		this.billDeliveryType = billDeliveryType;
	}
}

package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

import com.fasterxml.jackson.annotation.JsonInclude;

import framework.test_data.generic.Person;
import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_order_management_backend.api.OrderManagementAPI;

public class CreateAccountDTO {
	private String orderReference;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int parentAccountId;
	private GeneralDetails generalDetails;
	private MainAccountDetails mainAccountDetails;
	private DeviceEnrollment deviceEnrollment;
	private boolean isBillingAccount;
	private InvoicingDetails invoicingDetails;
	private PaymentDetails paymentDetails;
	private CompanyDetails companyDetails;
	private TaxDetails taxDetails;
	private B2BPerson owner;
	
	public CreateAccountDTO(String orderReference, int parentAccountId,String customerAccountName, B2BAccountType accountType) {

		this.orderReference=orderReference;
		this.parentAccountId=parentAccountId;
		isBillingAccount=true;
		generalDetails = new GeneralDetails();
		mainAccountDetails=new MainAccountDetails(customerAccountName, accountType.toString(), "ENTERPRISE_SME", "HIGH");
		deviceEnrollment=new DeviceEnrollment();
		invoicingDetails=new InvoicingDetails("4",true,"POSTAL",false);
		paymentDetails=new PaymentDetails(30);
		companyDetails = new CompanyDetails(customerAccountName);
		taxDetails=new TaxDetails("ELIGIBLE");
		
		Person randomPerson = RandomStringGenerator.getRandomB2BContact(customerAccountName);
		owner=new B2BPerson(randomPerson.getFirstName(),randomPerson.getLastName(),randomPerson.getEmailAddress(),randomPerson.getContactPhoneNumber(),new B2BAddress());
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public int getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(int parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public GeneralDetails getGeneralDetails() {
		return generalDetails;
	}

	public void setGeneralDetails(GeneralDetails generalDetails) {
		this.generalDetails = generalDetails;
	}

	public MainAccountDetails getMainAccountDetails() {
		return mainAccountDetails;
	}

	public void setMainAccountDetails(MainAccountDetails mainAccountDetails) {
		this.mainAccountDetails = mainAccountDetails;
	}

	public DeviceEnrollment getDeviceEnrollment() {
		return deviceEnrollment;
	}

	public void setDeviceEnrollment(DeviceEnrollment deviceEnrollment) {
		this.deviceEnrollment = deviceEnrollment;
	}

	public boolean isIsBillingAccount() {
		return isBillingAccount;
	}

	public void setBillingAccount(boolean isBillingAccount) {
		this.isBillingAccount = isBillingAccount;
	}

	public InvoicingDetails getInvoicingDetails() {
		return invoicingDetails;
	}

	public void setInvoicingDetails(InvoicingDetails invoicingDetails) {
		this.invoicingDetails = invoicingDetails;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public CompanyDetails getCompanyDetails() {
		return companyDetails;
	}

	public void setCompanyDetails(CompanyDetails companyDetails) {
		this.companyDetails = companyDetails;
	}

	public TaxDetails getTaxDetails() {
		return taxDetails;
	}

	public void setTaxDetails(TaxDetails taxDetails) {
		this.taxDetails = taxDetails;
	}

	public B2BPerson getOwner() {
		return owner;
	}

	public void setOwner(B2BPerson owner) {
		this.owner = owner;
	}
}

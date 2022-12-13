package testCases.testObjects.orders;

import java.text.SimpleDateFormat;

import framework.test_data.generic.Person;
import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_address_finder_backend.AddressFinderDAO;
import microservices.backend.eir_order_management_backend.api.OrderManagementAPI;

public class B2BOrder {

	private int accountNumber;
	private String accountName;
	private String eventTime;
	private String companyName;
	private String orderReference;
	private Person owner;
	private Person payer;
	private String customerCostCenter;
	private String groupICID;
	private String vpnAccountNumber;
	private String salesforceCustomerId;
	private String salesforceCaseNumber;
	private String taxNumber;
	private String taxCertNumber;
	private String companyRegistrationNumber;
	private String appleEnrollmentID;
	private String samsungEnrollmentID;
	private String googleEnrollmentID;
	private boolean payerSameAsOwner;
	private String area;
	
	public B2BOrder(int accountNumber) {
		this.accountNumber=accountNumber;
		area=null;
	}
	
	public B2BOrder() {
		this.accountNumber=accountNumber;
		area=null;
	}
	
	public void randomize() {
		
		// set the event time
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		java.util.Date d = new java.util.Date();
		eventTime = format.format(d);
		
		// set the other random values
		companyName = RandomStringGenerator.getRandomCompanyName();
		accountName=companyName;
		
		orderReference=OrderManagementAPI.getUniqueOrderNumber();
		
		owner = RandomStringGenerator.getRandomB2BContact(companyName);
		
		payerSameAsOwner = RandomStringGenerator.getRandomBoolean();
		
		if(payerSameAsOwner) {
			payer = owner;
		}
		else {
			payer = RandomStringGenerator.getRandomB2BContact(companyName);
		}
		
		customerCostCenter="EB" + RandomStringGenerator.getRandomString("0123456789", 14);
		groupICID="LR" + RandomStringGenerator.getRandomString("0123456789", 8);
		
		// this is a valid VPN number
		vpnAccountNumber="51038865";
		
		salesforceCustomerId=RandomStringGenerator.getRandomString("0123456789", 4);
		salesforceCaseNumber=RandomStringGenerator.getRandomString("0123456789", 4);
		taxNumber="E" + RandomStringGenerator.getRandomString("0123456789", 7) + "K";
		taxCertNumber=RandomStringGenerator.getRandomString("123456789", 2) + "/"
				+ RandomStringGenerator.getRandomString("0123456789", 4) + "/" + RandomStringGenerator.getRandomString("0123456789", 6);
		companyRegistrationNumber=RandomStringGenerator.getRandomString("J0123456789", 10);
		appleEnrollmentID="AP/DEP/" + RandomStringGenerator.getRandomString("0123456789", 5);
		samsungEnrollmentID="SAMSUNG/DEP/" + RandomStringGenerator.getRandomString("0123456789", 5);
		googleEnrollmentID="Z" + RandomStringGenerator.getRandomString("0123456789", 5);
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		owner = RandomStringGenerator.getRandomB2BContact(companyName);
		payer = RandomStringGenerator.getRandomB2BContact(companyName);
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Person getPayer() {
		return payer;
	}

	public void setPayer(Person payer) {
		this.payer = payer;
	}

	public String getCustomerCostCenter() {
		return customerCostCenter;
	}

	public void setCustomerCostCenter(String customerCostCenter) {
		this.customerCostCenter = customerCostCenter;
	}

	public String getGroupICID() {
		return groupICID;
	}

	public void setGroupICID(String groupICID) {
		this.groupICID = groupICID;
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

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getTaxCertNumber() {
		return taxCertNumber;
	}

	public void setTaxCertNumber(String taxCertNumber) {
		this.taxCertNumber = taxCertNumber;
	}

	public String getCompanyRegistrationNumber() {
		return companyRegistrationNumber;
	}

	public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
		this.companyRegistrationNumber = companyRegistrationNumber;
	}

	public String getAppleEnrollmentID() {
		return appleEnrollmentID;
	}

	public void setAppleEnrollmentID(String appleEnrollmentID) {
		this.appleEnrollmentID = appleEnrollmentID;
	}

	public String getSamsungEnrollmentID() {
		return samsungEnrollmentID;
	}

	public void setSamsungEnrollmentID(String samsungEnrollmentID) {
		this.samsungEnrollmentID = samsungEnrollmentID;
	}

	public String getGoogleEnrollmentID() {
		return googleEnrollmentID;
	}

	public void setGoogleEnrollmentID(String googleEnrollmentID) {
		this.googleEnrollmentID = googleEnrollmentID;
	}

	public boolean isPayerSameAsOwner() {
		return payerSameAsOwner;
	}

	public void setPayerSameAsOwner(boolean payerSameAsOwner) {
		this.payerSameAsOwner = payerSameAsOwner;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}

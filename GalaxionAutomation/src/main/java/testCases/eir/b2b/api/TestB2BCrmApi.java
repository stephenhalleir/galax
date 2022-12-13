package testCases.eir.b2b.api;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import framework.test_data.generic.Iban;
import framework.test_data.generic.RandomStringGenerator;
import io.restassured.path.json.JsonPath;
import json.common.AddressDTO;
import microservices.backend.eir_address_finder_backend.AddressFinderDAO;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import microservices.backend.eir_adjustment_backend.AdjustmentDAO;
import microservices.backend.eir_adjustment_backend.data_model.Adjustment;
import microservices.backend.eir_adjustment_backend.data_model.RefAdjustmentReason;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.Offer;
import microservices.backend.eir_catalog_core_backend.data_model.TariffPlan;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_contact_management_backend.data_model.Address;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.data_model.PhoneNumber;
import microservices.backend.eir_contact_management_backend.enums.AddressType;
import microservices.backend.eir_contact_management_backend.enums.ContactType;
import microservices.backend.eir_document_management_backend.dao.DocumentDAO;
import microservices.backend.eir_document_management_backend.data_model.Document;
import microservices.backend.eir_document_management_backend.enums.DocumentType;
import microservices.backend.eir_inventory_management_backend.InventoryCode;
import microservices.backend.eir_inventory_management_backend.api.InventoryManagementAPI;
import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import microservices.backend.eir_inventory_management_backend.objects.SimDetails;
import microservices.backend.eir_logistics_backend.utility.InventoryManager;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Event;
import microservices.backend.eir_order_management_backend.data_model.HardwareFund;
import microservices.backend.eir_order_management_backend.data_model.OmService;
import microservices.backend.eir_order_management_backend.data_model.OrderManagementAccount;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_order_management_backend.data_model.TerminationRequest;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.enums.EventType;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_order_management_backend.monitor.OrderMonitor;
import microservices.backend.eir_payment_center_backend.dao.PaymentCenterDAO;
import microservices.backend.eir_payment_center_backend.data_model.DirectDebitPaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.Payer;
import microservices.backend.eir_payment_center_backend.data_model.PaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.RefBank;
import microservices.backend.eir_provisioning_facade_backend.dao.ProvisioningFacadeDAO;
import microservices.backend.eir_provisioning_facade_backend.enums.ProvisioningAction;
import microservices.backend.eir_salt_ar_backend.dao.AccountsReceivableDAO;
import microservices.backend.eir_salt_ar_backend.data_model.Balance;
import microservices.backend.eir_salt_ar_backend.data_model.BalanceChange;
import microservices.backend.eir_salt_ar_backend.data_model.ImmediateAdjustment;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementValidator;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.AccountContact;
import microservices.backend.eir_subscription_management_backend.data_model.B2BAccount;
import microservices.backend.eir_subscription_management_backend.data_model.B2BAccountAttribute;
import microservices.backend.eir_subscription_management_backend.data_model.Company;
import microservices.backend.eir_subscription_management_backend.data_model.DeviceEnrollment;
import microservices.backend.eir_subscription_management_backend.data_model.RefAccountCategory;
import microservices.backend.eir_subscription_management_backend.data_model.RefBillingCycle;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.backend.eir_subscription_management_backend.data_model.TaxDetails;
import microservices.backend.eir_subscription_management_backend.data_model.custom.B2BAccountAttributeSet;
import microservices.backend.eir_subscription_management_backend.data_model.custom.BillingDetailsSet;
import microservices.backend.eir_subscription_management_backend.data_model.custom.DeviceEnrollmentSet;
import microservices.backend.eir_subscription_management_backend.enums.CreditClass;
import microservices.backend.eir_subscription_management_backend.enums.MarketSegment;
import microservices.backend.eir_subscription_management_backend.enums.TerminationReason;
import microservices.frontend.common_ui.response_objects.address_finder.ReturnedAddressFinderAddress;
import microservices.frontend.common_ui.response_objects.b2b_crm.GetRefBillCyclesResponse;
import microservices.frontend.common_ui.response_objects.subs_management.b2b_crm_get_subscription.SubscriptionJson;
import microservices.frontend.eir_b2b_crm_ui_frontend.B2BCRMAPI;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber.CatalogOffer;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_account.GetAccountDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_hardware_fund.GetHardwareFundDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_hardware_fund.HardwareFundTransaction;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_market_segments.GetMarketSegmentsResponse;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_payment_methods.PaymentMethodDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.hw_fund.get_ref_hw_fund_adjustments.GetRefHwFundAdjustmentReasons;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.results.search_results.B2BSearchResultResponse;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_device_enrollment.DeviceEnrollmentDTO;
import testCases.eir.b2b.responses.get_termination_request.GetPendingTerminationRequestDTO;
import testCases.eir.b2b.responses.validate_iban_response.ValidateIbanResponseDTO;
import utilities.generic.api.APITransaction;
import utilities.generic.files.PDFWriter;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class TestB2BCrmApi extends BaseTest {

	private String token;
	private int billingAccountID;
	private int accountID;
	private String accountName;
	private int subscriptionID;
	private String msisdn;
	private B2BAccount b2bAccount;
	private int serviceID;

	// store the order reference for the create subscriber test - it will be used by
	// Activate Subscriber
	private String newOrderReference;

	@Test(description = "B2B CRM UI API: Authenticate")
	public void testLogin() {
		token = B2BCRMAPI.getToken();
		assertNotNull(token);
		logPass("Token generated: " + token.substring(0, 200) + "...");
	}

	/*------------------------------------------------------------------
	 * Search screen
	 ------------------------------------------------------------------- */

	/**
	 * Search
	 * 
	 * Ref: B2B_A_SE_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_SE_01: B2B CRM UI API: Search by account name")
	public void B2B_A_SE_01_testSearchByName(ITestContext iTestContext) {

		// make a call to the API to retrieve the results
		APITransaction t = B2BCRMAPI.search(token, accountName, null, null, null, null, null, null, null, null);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json message into a list of objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<B2BSearchResultResponse> searchResults = jsonPathEvaluator.getList("", B2BSearchResultResponse.class);

		logPass(searchResults.size() + " results found");

		B2BSearchResultResponse resultToCheck = null;

		for (B2BSearchResultResponse accountResult : searchResults) {
			if (accountResult.getBillingAccountId() == billingAccountID) {
				resultToCheck = accountResult;
				break;
			}
		}

		// verify that our account has been found
		assertNotNull(resultToCheck);
		logPass("Result found for account " + resultToCheck.getBillingAccountId());

		this.checkSearchResult(accountID, billingAccountID, resultToCheck);
	}

	/**
	 * Search
	 * 
	 * Ref: B2B_A_SE_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_SE_01: B2B CRM UI API: Search by billing account ID")
	public void B2B_A_SE_01_testSearchByBillingAccountID(ITestContext iTestContext) {

		// make the call to the API to perform the search
		APITransaction t = B2BCRMAPI.search(token, null, null, null, null, null, null, billingAccountID, null, null);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json message into a list of objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<B2BSearchResultResponse> searchResults = jsonPathEvaluator.getList("", B2BSearchResultResponse.class);

		logPass(searchResults.size() + " results found");

		B2BSearchResultResponse resultToCheck = null;

		for (B2BSearchResultResponse accountResult : searchResults) {
			if (accountResult.getBillingAccountId() == billingAccountID) {
				resultToCheck = accountResult;
				break;
			}
		}

		// verify that our account has been found
		assertNotNull(resultToCheck);
		logPass("Result found for account " + resultToCheck.getBillingAccountId());

		this.checkSearchResult(accountID, billingAccountID, resultToCheck);
	}

	/**
	 * Search
	 * 
	 * Ref: B2B_A_SE_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_SE_01: B2B CRM UI API: Search by MSISDN")
	public void B2B_A_SE_01_testSearchByMSISDN(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.search(token, null, null, null, null, null, msisdn, null, null, null);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json message into a list of objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<B2BSearchResultResponse> searchResults = jsonPathEvaluator.getList("", B2BSearchResultResponse.class);

		logPass(searchResults.size() + " results found");

		B2BSearchResultResponse resultToCheck = null;

		for (B2BSearchResultResponse accountResult : searchResults) {
			if (accountResult.getBillingAccountId() == billingAccountID) {
				resultToCheck = accountResult;
				break;
			}
		}

		// verify that our account has been found
		assertNotNull(resultToCheck);
		logPass("Result found for account " + resultToCheck.getBillingAccountId());

		this.checkSearchResult(accountID, billingAccountID, resultToCheck);
	}

	/**
	 * Order Search
	 * 
	 * Ref: B2B_A_SE_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_SE_02: B2B CRM UI API: Order search")
	public void B2B_A_SE_02_testOrderSearch(ITestContext iTestContext) {
		String orderReference = OrderManagementDAO.getOrdersForBillingAccountID(billingAccountID).get(0).getReference();
		APITransaction t = B2BCRMAPI.orderSearch(token, orderReference);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: General tab
	 ------------------------------------------------------------------- */
	/**
	 * Get account hierarchy
	 * 
	 * Ref: B2B_A_GE_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_01: B2B CRM UI API: Get account hierarchy")
	public void B2B_A_GE_01_testGetAccountHierarchy(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAccountHierarchy(token, accountID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get account
	 * 
	 * Ref: B2B_A_GE_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_02: B2B CRM UI API: Get account details")
	public void B2B_A_GE_02_testGetAccountDetails(ITestContext iTestContext) {
		
		// make the API call to retrieve the account details
		APITransaction t = B2BCRMAPI.getAccount(token, accountID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
		
		// convert the json response into an object for processing
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetAccountDTO response = jsonPathEvaluator.getObject("", GetAccountDTO.class);
		
		// read the account details in the account yable
		Account account = SubscriptionManagementDAO.getAccountByID(accountID);
		assertEquals(account.getId(),response.getId());
		assertEquals(account.getBillingAccountID(),response.getBillingAccountId());
		assertEquals(account.getStatus(),response.getStatus());
		assertEquals(account.getCustomerType(),response.getType());
		assertEquals(account.getBillingCycleID(),response.getBillingCycle());
		assertEquals(account.getInvoiceDeliveryMethod(),response.getBillDeliveryType());
		assertEquals(account.getItemisedInvoice()==1,response.isBillItemised());
		logPass("Account is correct: " + account.getId() + ", " + account.getBillingAccountID() + ", " + account.getStatus() + ", " + account.getCustomerType());
		
		SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String dateString = longDateFormat.format(account.getCreatedAt());
		assertEquals(dateString,response.getCreationDateTime());
		logPass("Creation date is correct: " + response.getCreationDateTime());
		
		// verify the details in b2b_account
		B2BAccount b2bAccount = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		assertEquals(b2bAccount.getName(),response.getName());
		assertEquals(b2bAccount.getCreditClass(),response.getCreditClass());
		assertEquals(b2bAccount.getPaymentTerm(),response.getPaymentTerm());
		assertEquals(b2bAccount.getHardwareBalanceId(),response.getHardwareFundId());
		assertEquals(b2bAccount.getBillAnalyserConsent()==1,response.isBillAnalyserConsent());
		logPass("B2B Account is correct: " + response.getName() + ", " + response.getCreditClass() + ", " + response.getHardwareFundId());
		
		// verify the company details
		Company company = SubscriptionManagementDAO.getCompanyByID(b2bAccount.getCompanyId());
		assertEquals(company.getId(),response.getCompany().getId());
		assertEquals(company.getName(),response.getCompany().getName());
		assertEquals(company.getRegistrationNumber(),response.getCompany().getRegistrationNumber());
		logPass("Company is correct: " + response.getCompany().getId() + ", " + response.getCompany().getName() + ", " + response.getCompany().getRegistrationNumber());
		
		SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		// verify the tax details
		TaxDetails taxDetails = SubscriptionManagementDAO.getTaxDetails(b2bAccount.getTaxDetailsId());
		assertEquals(taxDetails.getCategory(),response.getTaxDetails().getTaxCategory());
		assertEquals(taxDetails.getNumber(),response.getTaxDetails().getTaxNumber());
		assertEquals(taxDetails.getCertNumber(),response.getTaxDetails().getTaxCertificateNumber());
		assertEquals(taxDetails.getExemptionDocumentType(),response.getTaxDetails().getTaxExemptionDocumentType());
		assertEquals(shortDateFormat.format(taxDetails.getExemptionStartDate()),response.getTaxDetails().getTaxExemptionStartDate());
		assertEquals(shortDateFormat.format(taxDetails.getExemptionEndDate()),response.getTaxDetails().getTaxExemptionEndDate());
		logPass("Tax details are correct: " + taxDetails.getCategory() + ", " + taxDetails.getNumber() + ", " + taxDetails.getCertNumber());
		
		// verify the details from the b2b_account_attribute table
		B2BAccountAttributeSet attributeSet = SubscriptionManagementDAO.getB2BAccountAttributeSet(accountID);
		assertEquals(attributeSet.getAccountManager(),response.getAccountAttributeList().getACCOUNT_MANAGER());
		assertEquals(attributeSet.getAgreementDuration(),response.getAccountAttributeList().getAGREEMENT_DURATION());
		assertEquals(attributeSet.getSalesforceCaseNumber(),response.getAccountAttributeList().getSALESFORCE_CASE_NUMBER());
		assertEquals(attributeSet.getSalesforceCustomerId(),response.getAccountAttributeList().getSALESFORCE_CUSTOMER_ID());
		assertEquals(attributeSet.getCustomerCostCenter(),response.getAccountAttributeList().getCUSTOMER_COST_CENTER());
		assertEquals(attributeSet.getVpnAccountNumber(),response.getAccountAttributeList().getVPN_ACCOUNT_NUMBER());
		assertEquals(attributeSet.getGroupIcid(),response.getAccountAttributeList().getGROUP_ICID());
		assertEquals(attributeSet.getIndoorCoverageSolutionDate(),response.getAccountAttributeList().getINDOOR_COVERAGE_SOLUTION_DATE());
		logPass("Attributes are correct: " + attributeSet.getAccountManager() + ", " + attributeSet.getGroupIcid() + ", " + attributeSet.getAgreementDuration());
		
		// read the market segment information
		String marketSegment = SubscriptionManagementDAO.getMarketSegment(accountID);
		assertNotNull(marketSegment);
		RefAccountCategory marketSegmentObj = SubscriptionManagementDAO.getRefAccountCategory(marketSegment);
		assertEquals(marketSegmentObj.getLabel(), response.getMarketSegment());
		logPass("Market segment is correct: " + response.getMarketSegment());
		
		// read the salt-ar.balance details
		Balance balance = AccountsReceivableDAO.getBalance(account.getBillingAccountID());
		assertNotNull(balance);
		assertEquals(balance.getNonOverdueAmount(),response.getBalanceAmount());
		assertEquals(balance.getOverdueAmount(),response.getOverdueAmount());
		logPass("SALT-AR balance is correct: " + response.getBalanceAmount() + ", " + response.getOverdueAmount());
		
		// read the hardware fund
		HardwareFund hwFund = OrderManagementDAO.getHardwareFund(b2bAccount.getHardwareBalanceId());
		assertEquals(hwFund.getTerm(),response.getHardwareFundTerm());
		
		// read the device enrollments
		ArrayList<DeviceEnrollment> deviceEnrollmentsFromDB = SubscriptionManagementDAO.getDeviceEnrollments(accountID);
		assertEquals(deviceEnrollmentsFromDB.size(),response.getDeviceEnrollments().size());
		
		for(DeviceEnrollmentDTO enr:response.getDeviceEnrollments()) {
			assertTrue(SubscriptionManagementValidator.deviceEnrollmentExists(deviceEnrollmentsFromDB, enr.getDeviceEnrollmentProvider(), enr.getDeviceEnrollmentRef()));
			logPass("Device enrollment found: " + enr.getDeviceEnrollmentProvider() + ", " + enr.getDeviceEnrollmentRef());
		}

		// read and verify the phone numbers
		String contactUuid = SubscriptionManagementDAO.getContactUuidForAccountID(accountID);
		ArrayList<PhoneNumber> phoneNumbers = ContactManagementDAO.getPhoneNumbers(contactUuid);
		
		for(PhoneNumber phoneNumber:phoneNumbers) {
			if(phoneNumber.getType().equals("MOBILE")) {
				assertEquals(phoneNumber.getPhoneNumber(),response.getOwnerMobileNumber());
				logPass("MOBILE number is correct: " + response.getOwnerMobileNumber());
			}
			else if(phoneNumber.getType().equals("FIXE")) {
				assertEquals(phoneNumber.getPhoneNumber(),response.getOwnerPhoneNumber());
				logPass("FIXED number is correct: " + response.getOwnerPhoneNumber());
			}
		}

		// verify whether the account is a billing account
		assertEquals(b2bAccount.getIsInvoiceOwner()==1,response.isIsBillingAccount());
		logPass("IS BILLING ACCOUNT = " + response.isIsBillingAccount());
		
		// for fields that have no validations yet, print to the report
		logInfo("Other details returned: isRootAccount: " + response.isIsRootAccount()  + ", firstInvoiceDate: " + response.getFirstInvoiceDate() + ", nextInvoiceDate: " + response.getNextInvoiceDate() + ", lastInvoiceDate: " + response.getLastInvoiceDate());
		
		//TODO - need to add validations for:
		/*
					"isRootAccount":true,
				   "firstInvoiceDate":"2021-11-01T00:00:00",
				   "nextInvoiceDate":"2022-04-01T00:00:00",
				   "lastInvoiceDate":"2022-03-01T00:00:00"
		 */
	}
	

	/**
	 * Make an account unbillable
	 * 
	 * Ref: B2B_A_GE_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_03: B2B CRM UI API: Make account unbillable")
	public void B2B_A_GE_03_testMakeAccountUnbillable(ITestContext iTestContext) {

		// get a child account on the main account
		B2BAccount account = SubscriptionManagementDAO.getChildAccounts(accountID).get(0);
		logInfo("Customer wishes to make account " + account.getId() + " unbillable");

		// first ensure that the account is billable before the test
		SubscriptionManagementDAO.makeAccountBillable(account.getId());

		// verify that the account is marked as billable before the test
		B2BAccount accountFromDatabase = SubscriptionManagementDAO.getB2BAccountByID(account.getId());
		assertEquals(1, accountFromDatabase.getIsInvoiceOwner());
		logInfo("Account is billable before the test - IS_INVOICE_OWNER=" + accountFromDatabase.getIsInvoiceOwner());

		// make a call to the API to disable billing on the account
		APITransaction t = B2BCRMAPI.setAccountToUnbillable(token, account.getId());
		assertEquals(t.getResponse().statusCode(), 204);
		logPass(t.toString());

		// read the contents of the B2B_ACCOUNT table after the action
		accountFromDatabase = SubscriptionManagementDAO.getB2BAccountByID(account.getId());

		// verify that the setting is now set to 0
		assertEquals(0, accountFromDatabase.getIsInvoiceOwner());
		logInfo("Account is now unbillable - IS_INVOICE_OWNER=0");

	}

	/**
	 * Update account main details
	 * 
	 * Ref: B2B_A_GE_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_04: B2B CRM UI API: Update account main details")
	public void B2B_A_GE_04_testUpdateMainAccountDetails(ITestContext iTestContext) {

		B2BAccount account = SubscriptionManagementDAO.getB2BAccountByID(accountID);

		// decide on the market segment to use
		MarketSegment newMarketSegment;
		String currentMarketSegment = SubscriptionManagementDAO.getMarketSegment(accountID);
		if (MarketSegment.valueOf(currentMarketSegment) == MarketSegment.ENTERPRISE_SME) {
			newMarketSegment = MarketSegment.PHONEWATCH;
		} else {
			newMarketSegment = MarketSegment.ENTERPRISE_SME;
		}

		logInfo("Account details before test: " + account.getCreditClass() + ", " + account.getName() + ", " + currentMarketSegment);

		// decide on the credit class to use
		CreditClass newCreditClass;

		if (CreditClass.valueOf(account.getCreditClass()) == CreditClass.LOW) {
			newCreditClass = CreditClass.MEDIUM;
		} else {
			newCreditClass = CreditClass.LOW;
		}

		B2BAccountType accountType = B2BAccountType.CORPORATE_OR_EBU;

		// newCreditClass = CreditClass.HIGH;

		String newName = "Auto New Name " + RandomStringGenerator.getRandomString("abcdefguhijklmnopqrstuvwxyx", 8);

		logInfo("Customer wishes to change to " + newName + ", " + newCreditClass + ", " + newMarketSegment);

		// make a call to the API to update the account details
		APITransaction t = B2BCRMAPI.updateMainAccountDetails(token, accountID, newCreditClass, newMarketSegment, newName, accountType);
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());

		// read the updated details from the database
		account = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		String updatedMarketSegment = SubscriptionManagementDAO.getMarketSegment(accountID);

		// verify that the details are updated correctly
		assertEquals(newCreditClass.toString(), account.getCreditClass());
		assertEquals(newMarketSegment.toString(), updatedMarketSegment);
		assertEquals(newName, account.getName());

		// log output to the report
		logPass("Account details updated: " + account.getCreditClass() + ", " + account.getName() + ", " + updatedMarketSegment);
	}

	/**
	 * Update account general details
	 * 
	 * Ref: B2B_A_GE_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_05: B2B CRM UI API: Update account general details (attributes)")
	public void B2B_A_GE_05_testUpdateAccountGeneralDetails(ITestContext iTestContext) {

		// read in the values before the test
		B2BAccountAttributeSet attributesFromDatabase = SubscriptionManagementDAO.getB2BAccountAttributeSet(accountID);

		// generate new values for the account update
		String newName = RandomStringGenerator.getRandomName();
		String newAgreementDuration = RandomStringGenerator.getRandomInteger(1, 60) + " months";
		String newCostCenter = RandomStringGenerator.getRandomNumericString(10);
		String newIcid = RandomStringGenerator.getRandomNumericString(10);
		String newSalesforceCaseNumber = RandomStringGenerator.getRandomNumericString(7);
		String newSalesforceCustomerId = RandomStringGenerator.getRandomNumericString(8);
		String newVpnAccountNumber = RandomStringGenerator.getRandomNumericString(8);
		String newIndoorCoverageSolutionDate = Timestamp.getCurrentTimestamp("dd/MM/yyyy");

		logInfo("Updating details to " + newName + ", " + newAgreementDuration + ", " + newCostCenter + ", " + newIcid + ", " + newSalesforceCaseNumber + ", "
				+ newSalesforceCustomerId + ", " + newVpnAccountNumber + ", " + newIndoorCoverageSolutionDate);

		// make a call to the API to update the account details
		APITransaction t = B2BCRMAPI.updateAccountGeneralDetails(token, accountID, newName, newAgreementDuration, newCostCenter, newIcid,
				newIndoorCoverageSolutionDate, newSalesforceCaseNumber, newSalesforceCustomerId, newVpnAccountNumber);
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());

		// read in the values after the test
		attributesFromDatabase = SubscriptionManagementDAO.getB2BAccountAttributeSet(accountID);

		// verify that the updates have been made successfully
		assertEquals(attributesFromDatabase.getAccountManager(), newName);
		assertEquals(attributesFromDatabase.getAgreementDuration(), newAgreementDuration);
		assertEquals(attributesFromDatabase.getCustomerCostCenter(), newCostCenter);
		assertEquals(attributesFromDatabase.getIndoorCoverageSolutionDate(), newIndoorCoverageSolutionDate);
		assertEquals(attributesFromDatabase.getVpnAccountNumber(), newVpnAccountNumber);
		assertEquals(attributesFromDatabase.getSalesforceCaseNumber(), newSalesforceCaseNumber);
		assertEquals(attributesFromDatabase.getSalesforceCustomerId(), newSalesforceCustomerId);
		assertEquals(attributesFromDatabase.getGroupIcid(), newIcid);

		// log output to the report
		logPass("Details successfully updated to " + newName + ", " + newAgreementDuration + ", " + newCostCenter + ", " + newIcid + ", "
				+ newSalesforceCaseNumber + ", " + newSalesforceCustomerId + ", " + newVpnAccountNumber + ", " + newIndoorCoverageSolutionDate);
	}

	/**
	 * Update company details
	 * 
	 * Ref: B2B_A_GE_06
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_06: B2B CRM UI API: Update company details")
	public void B2B_A_GE_06_testUpdateCompanyDetails(ITestContext iTestContext) {

		// read in the values before the test
		B2BAccount account = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		int companyId = account.getCompanyId();
		Company company = SubscriptionManagementDAO.getCompanyByID(companyId);
		logInfo("Company info before test: " + company.getId() + ", " + company.getName() + ", " + company.getRegistrationNumber());

		// generate new values for the company update
		String newName = "Auto " + RandomStringGenerator.getRandomString("abcdefghijklmnop", 8);
		String newRegistrationNumber = RandomStringGenerator.getRandomNumericString(10);
		logInfo("Updating details to " + newName + ", " + newRegistrationNumber);

		// make a call to the API to update the account details
		APITransaction t = B2BCRMAPI.updateCompanyDetails(token, company.getId(), newName, newRegistrationNumber);
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());

		// read in the values after the test
		company = SubscriptionManagementDAO.getCompanyByID(companyId);

		// verify that the updates have been made successfully
		assertEquals(company.getName(), newName);
		assertEquals(company.getRegistrationNumber(), newRegistrationNumber);

		// log output to the report
		logPass("Details successfully updated to " + company.getName() + ", " + company.getRegistrationNumber());
	}

	/**
	 * Update device enrollments
	 * 
	 * Ref: B2B_A_GE_07
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_07: B2B CRM UI API: Update device enrollments")
	public void B2B_A_GE_07_testDeviceEnrollments(ITestContext iTestContext) {

		// read the device enrollments from the database before the test
		DeviceEnrollmentSet enrollmentsFromDatabase = SubscriptionManagementDAO.getdeviceEnrollmentSet(accountID);
		logInfo("Device enrollment information before test: " + enrollmentsFromDatabase.getAndroidEnrollment() + ", "
				+ enrollmentsFromDatabase.getSamsungEnrollment() + ", " + enrollmentsFromDatabase.getAndroidEnrollment());

		// generate random new enrollment IDs
		String newAppleEnrollment = "AP" + RandomStringGenerator.getRandomNumericString(10);
		String newSamsungEnrollment = "KME" + RandomStringGenerator.getRandomNumericString(9);
		String newGoogleEnrollment = "AZ" + RandomStringGenerator.getRandomNumericString(10);

		// make a call to the API to update the device enrollments
		APITransaction t = B2BCRMAPI.updateDeviceEnrollment(token, accountID, newAppleEnrollment, newSamsungEnrollment, newGoogleEnrollment);
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());

		// check the database for updated values
		enrollmentsFromDatabase = SubscriptionManagementDAO.getdeviceEnrollmentSet(accountID);

		// verify that the results are correct
		assertEquals(enrollmentsFromDatabase.getAndroidEnrollment(), newGoogleEnrollment);
		assertEquals(enrollmentsFromDatabase.getSamsungEnrollment(), newSamsungEnrollment);
		assertEquals(enrollmentsFromDatabase.getAppleEnrollment(), newAppleEnrollment);

		// log output to the report
		logInfo("Device enrollment information successfully updated after test: " + enrollmentsFromDatabase.getAndroidEnrollment() + ", "
				+ enrollmentsFromDatabase.getSamsungEnrollment() + ", " + enrollmentsFromDatabase.getAndroidEnrollment());
	}

	/**
	 * Update tax details
	 * 
	 * Ref: B2B_A_GE_08
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_GE_08: B2B CRM UI API: Update tax details")
	public void B2B_A_GE_08_testUpdateTaxDetails(ITestContext iTestContext) {

		B2BAccount account = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		TaxDetails taxDetailsFromDatabase = SubscriptionManagementDAO.getTaxDetails(account.getTaxDetailsId());
		logInfo("Tax details before test: " + taxDetailsFromDatabase.getCategory() + ", " + taxDetailsFromDatabase.getNumber() + ", "
				+ taxDetailsFromDatabase.getCertNumber());

		String[] taxExemptionDocumentTypes = { "EU_SCH2_VATCA10", "DIP1A", "OUT_SCOPE_VAT", "VATCA10" };
		String[] taxExemptionDocumentCategories = { "ELIGIBLE", "EXEMPT" };

		// generate random new enrollment IDs
		String newTaxNumber = "E" + RandomStringGenerator.getRandomNumericString(8) + "K";
		String newTaxCertificateNumber = RandomStringGenerator.getRandomNumericString(14);
		String newTaxExemptionDocumentType = RandomStringGenerator.getRandomItemFromArray(taxExemptionDocumentTypes);
		String newTaxCategory = RandomStringGenerator.getRandomItemFromArray(taxExemptionDocumentCategories);
		logInfo("Changing values to " + newTaxCategory + ", " + newTaxCertificateNumber + ", " + newTaxExemptionDocumentType + ", " + newTaxNumber);

		// make a call to the API to update the device enrollments
		APITransaction t = B2BCRMAPI.updateTaxDetails(token, accountID, newTaxNumber, newTaxCertificateNumber, newTaxCategory, newTaxExemptionDocumentType,
				"2021-12-31", "2021-12-15");
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());

		// check the database for updated values
		account = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		taxDetailsFromDatabase = SubscriptionManagementDAO.getTaxDetails(account.getTaxDetailsId());

		// verify that the results are correct
		assertEquals(taxDetailsFromDatabase.getCategory(), newTaxCategory);
		assertEquals(taxDetailsFromDatabase.getCertNumber(), newTaxCertificateNumber);
		assertEquals(taxDetailsFromDatabase.getExemptionDocumentType(), newTaxExemptionDocumentType);
		assertEquals(taxDetailsFromDatabase.getNumber(), newTaxNumber);

		// log output to the report
		logInfo("Tax details successfully updated after test: " + taxDetailsFromDatabase.getCategory() + ", " + taxDetailsFromDatabase.getNumber() + ", "
				+ taxDetailsFromDatabase.getCertNumber());
	}

	/*------------------------------------------------------------------
	 * Account: Payments & Billing tab
	 ------------------------------------------------------------------- */
	/**
	 * Get payment methods
	 * 
	 * Ref: B2B_A_PY_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_PY_01: B2B CRM UI API: Get payment methods")
	public void B2B_A_PY_01_testGetPaymentMethods(ITestContext iTestContext) {

		// read the payer information for the billing account ID
		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);
		ArrayList<PaymentMethod> paymentMethodsFromDatabase = PaymentCenterDAO.getPaymentMethodsForPayer(payer.getId());

		// put the default method to the start of the list
		for (PaymentMethod method : paymentMethodsFromDatabase) {
			if (method.getId() == payer.getDefaultPaymentMethodId()) {
				int index = paymentMethodsFromDatabase.indexOf(method);
				paymentMethodsFromDatabase.remove(index);
				paymentMethodsFromDatabase.add(0, method);
				break;
			}
		}

		logPass("Payer " + payer.getId() + " has " + paymentMethodsFromDatabase.size() + " payment methods on their account");

		// make the API call to retrieve payment methods
		APITransaction t = B2BCRMAPI.getPaymentMethods(token, billingAccountID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<PaymentMethodDTO> paymentMethodsFromAPI = jsonPathEvaluator.getList("", PaymentMethodDTO.class);

		// verify that that correct number of payment methods have been returned
		assertEquals(paymentMethodsFromDatabase.size(), paymentMethodsFromAPI.size());
		logPass(paymentMethodsFromAPI.size() + " payment methods returned");

		// for each payment method
		for (int i = 0; i < paymentMethodsFromAPI.size(); i++) {

			// read the payment method information from the database and api
			PaymentMethod pmFromDatabase = paymentMethodsFromDatabase.get(i);
			PaymentMethodDTO pmFromApi = paymentMethodsFromAPI.get(i);

			// verify the values
			assertEquals(pmFromApi.getPaymentMethodId(), pmFromDatabase.getId());
			assertEquals(pmFromApi.getStatus(), pmFromDatabase.getStatus());
			assertEquals(pmFromApi.getPaymentMethodType(), pmFromDatabase.getPaymentMethodType());
			logPass("Payment method " + pmFromApi.getPaymentMethodId() + " is correct: " + pmFromApi.getStatus() + ", " + pmFromApi.getPaymentMethodType());

			// if the type is DIRECT_DEBIT
			if (pmFromDatabase.getPaymentMethodType().equals("DIRECT_DEBIT")) {

				// read the direct debit payment method from the database
				DirectDebitPaymentMethod ddPaymentMethod = PaymentCenterDAO.getDirectDebitPaymentMethod(pmFromDatabase.getId());

				// validate the values
				assertEquals(ddPaymentMethod.getAccountOwner(), pmFromApi.getAccountOwner());
				assertEquals(ddPaymentMethod.getBankName(), pmFromApi.getBankName());
				assertEquals(ddPaymentMethod.getBic(), pmFromApi.getBic());
				assertEquals(ddPaymentMethod.getBranchName(), pmFromApi.getBranchName());
				assertEquals(ddPaymentMethod.getMandateSignedAt(), pmFromApi.getMandateSignedAt());
				assertEquals(ddPaymentMethod.getType(), pmFromApi.getType());
				assertEquals(ddPaymentMethod.getUniqueMandateReference(), pmFromApi.getUniqueMandateReference());

				// log output to report
				logPass("Payment method " + pmFromApi.getPaymentMethodId() + " is correct: " + pmFromApi.getAccountOwner() + ", " + pmFromApi.getBankName()
						+ ", " + pmFromApi.getBranchName() + ", " + pmFromApi.getUniqueMandateReference());
			}
		}
	}

	/**
	 * Update billing details
	 * 
	 * Ref: B2B_A_PY_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_PY_02: B2B CRM UI API: Update billing details")
	public void B2B_A_PY_02_testUpdateBillingDetails(ITestContext iTestContext) {

		// read the details from the database before the test
		BillingDetailsSet detailsFromDatabase = SubscriptionManagementDAO.getBillingDetailsSet(accountID);
		logInfo("Billing details before test: " + detailsFromDatabase.getInvoiceDeliveryMethod() + ", " + detailsFromDatabase.isBillItemised() + ", "
				+ detailsFromDatabase.getBillCycleId() + ", " + detailsFromDatabase.isBillAnalyserConsent());

		// determine new values for the update
		String newBillDeliveryType;
		boolean newBillItemisedValue;
		int newBillCycleId;
		boolean newBillAnalyserConsent;

		if (detailsFromDatabase.getBillCycleId() == 3) {
			newBillCycleId = 4;
		} else {
			newBillCycleId = 3;
		}

		if (detailsFromDatabase.getInvoiceDeliveryMethod().equals("POSTAL")) {
			newBillDeliveryType = "EMAIL";
		} else {
			newBillDeliveryType = "POSTAL";
		}

		if (detailsFromDatabase.isBillItemised()) {
			newBillItemisedValue = false;
		} else {
			newBillItemisedValue = true;
		}

		if (detailsFromDatabase.isBillAnalyserConsent()) {
			newBillAnalyserConsent = false;
		} else {
			newBillAnalyserConsent = true;
		}

		logInfo("Updating values to " + newBillDeliveryType + ", " + newBillItemisedValue + ", " + newBillCycleId + ", " + newBillAnalyserConsent);

		// make the API call to trigger the updates
		APITransaction t = B2BCRMAPI.updateBillingDetails(token, accountID, newBillDeliveryType, newBillItemisedValue, newBillCycleId, newBillAnalyserConsent);
		assertEquals(204, t.getResponse().statusCode());
		logPass(t.toString());

		// read the values from the database after the test
		detailsFromDatabase = SubscriptionManagementDAO.getBillingDetailsSet(accountID);

		// verify that the changes have been made successfully in the database
		assertEquals(detailsFromDatabase.getBillCycleId(), newBillCycleId);
		assertEquals(detailsFromDatabase.getInvoiceDeliveryMethod(), newBillDeliveryType);
		assertEquals(detailsFromDatabase.isBillAnalyserConsent(), newBillAnalyserConsent);
		assertEquals(detailsFromDatabase.isBillItemised(), newBillItemisedValue);

		// log output to the report
		logPass("Billing details updated successfully: " + detailsFromDatabase.getInvoiceDeliveryMethod() + ", " + detailsFromDatabase.isBillItemised() + ", "
				+ detailsFromDatabase.getBillCycleId() + ", " + detailsFromDatabase.isBillAnalyserConsent());
	}

	/**
	 * Update payment term
	 * 
	 * Ref: B2B_A_PY_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_PY_03: B2B CRM UI API: Update payment term")
	public void B2B_A_PY_03_testUpdatePaymentTerm(ITestContext iTestContext) {

		// read the account details before the test
		B2BAccount account = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		logInfo("Payment term before test: " + account.getPaymentTerm());

		int newPaymentTerm;

		// determine a new payment term
		if (account.getPaymentTerm() == 60) {
			newPaymentTerm = 90;
		} else {
			newPaymentTerm = 60;
		}

		logInfo("Updating payment term to " + newPaymentTerm);

		// make the API call to trigger the updates
		APITransaction t = B2BCRMAPI.updatePaymentTerm(token, accountID, newPaymentTerm);
		assertEquals(204, t.getResponse().statusCode());
		logPass(t.toString());

		// read the values from the database after the test
		account = SubscriptionManagementDAO.getB2BAccountByID(accountID);

		// verify that the changes have been made successfully in the database
		assertEquals(account.getPaymentTerm(), newPaymentTerm);

		// log output to the report
		logInfo("Payment term updated successfully: " + account.getPaymentTerm());
	}

	/**
	 * Validate IBAN
	 * 
	 * Ref: B2B_A_PY_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_PY_04: B2B CRM UI API: Validate IBAN")
	public void B2B_A_PY_04_testValidateIban(ITestContext iTestContext) {

		// generate a random Iban object
		Iban iban = RandomStringGenerator.getRandomIrishIBAN();

		// read the bank details from the REF_BANK table
		RefBank bank = PaymentCenterDAO.getBank(iban.getBranch_code());

		// make the API call to trigger the updates
		APITransaction t = B2BCRMAPI.validateIBAN(token, iban.toString());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		ValidateIbanResponseDTO ibanValidationResponse = (ValidateIbanResponseDTO) jsonPathEvaluator.getObject("", ValidateIbanResponseDTO.class);

		// validate the results
		assertEquals(ibanValidationResponse.getBankName(), bank.getBankName());
		assertEquals(ibanValidationResponse.getBic(), bank.getBic());
		assertEquals(ibanValidationResponse.getBranchName(), bank.getBranchName());
		assertEquals(ibanValidationResponse.getIban().replace(" ", ""), iban.toString());
		logPass("Correct information returned from the API: " + ibanValidationResponse.getBankName() + ", " + ibanValidationResponse.getBic() + ", "
				+ ibanValidationResponse.getBranchName());
	}

	/**
	 * Add SEPA payment method
	 * 
	 * Ref: B2B_A_PY_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_PY_05: B2B CRM UI API: Add SEPA payment method")
	public void B2B_A_PY_05_testAddSepaPaymentMethod(ITestContext iTestContext) {

		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);
		ArrayList<PaymentMethod> paymentMethods = PaymentCenterDAO.getPaymentMethodsForPayer(payer.getId());
		int paymentMethodsBeforeTest = paymentMethods.size();
		logInfo("Before test: Payer " + payer.getId() + " has " + paymentMethodsBeforeTest + " payment methods on the account");

		// generate a new IBAN
		String iban = RandomStringGenerator.getRandomIrishIBAN().toString();
		logInfo("Customer wants to add an IBAN " + iban);

		// make the API call to validate the Iban
		APITransaction t = B2BCRMAPI.validateIBAN(token, iban);
		logPass("Validate IBAN: " + t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// convert the json response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		ValidateIbanResponseDTO ibanValidationResponse = (ValidateIbanResponseDTO) jsonPathEvaluator.getObject("", ValidateIbanResponseDTO.class);
		assertEquals(ibanValidationResponse.getIban().replace(" ", ""), iban);
		String mandateSignedAt = Timestamp.getCurrentTimestamp("yyyy-MM-dd");

		// make an API call to add the payment method, and validate the response code
		t = B2BCRMAPI.addSepaPaymentMethod(token, billingAccountID, "Steve Test", ibanValidationResponse.getBankName(), ibanValidationResponse.getBic(),
				ibanValidationResponse.getBranchName(), iban, mandateSignedAt);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 201);

		// count the payment methods after the test
		paymentMethods = PaymentCenterDAO.getPaymentMethodsForPayer(payer.getId());
		int paymentMethodsAfterTest = paymentMethods.size();
		logInfo("After test: Payer " + payer.getId() + " has " + paymentMethodsAfterTest + " payment methods on the account");

		assertEquals(paymentMethodsAfterTest - paymentMethodsBeforeTest, 1);
		logPass(paymentMethodsAfterTest - paymentMethodsBeforeTest + " payment method added");
	}

	/**
	 * Change default payment method
	 * 
	 * Ref: B2B_A_PY_06
	 */
	@Test(dependsOnMethods = "B2B_A_PY_05_testAddSepaPaymentMethod", description = "B2B_A_PY_06: B2B CRM UI API: Change default SEPA payment method")
	public void B2B_A_PY_06_testUpdateDefaultPaymentMethod(ITestContext iTestContext) {

		// read the payer and payment methods from the database
		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);
		ArrayList<PaymentMethod> paymentMethods = PaymentCenterDAO.getPaymentMethodsForPayer(payer.getId());
		logInfo(paymentMethods.size() + " payment methods exist on the account");

		// read the default payment method before the test
		int defaultPaymentMethodId = payer.getDefaultPaymentMethodId();

		PaymentMethod defaultPaymentMethod = null;
		PaymentMethod methodToChange = null;

		// determine a non-default payment method ID to modify for this test
		for (PaymentMethod method : paymentMethods) {
			if (method.getId() == defaultPaymentMethodId) {
				defaultPaymentMethod = method;
				logPass("Default payment method ID before test: " + defaultPaymentMethod.getId() + ", " + defaultPaymentMethod.getPaymentMethodType());
			} else if (method.getCancelledAt() == null && methodToChange == null) {
				methodToChange = method;
				logInfo("Customer will change default payment method ID to " + methodToChange.getId() + ", " + methodToChange.getPaymentMethodType());
			}
		}

		// verify that the default and new methods have been selected for the test
		assertNotNull(defaultPaymentMethod);
		assertNotNull(methodToChange);

		// make the API call to validate the Iban
		APITransaction t = B2BCRMAPI.updateDefaultPaymentMethod(token, billingAccountID, methodToChange.getId());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 204);

		// read the updated payer information from the database
		payer = PaymentCenterDAO.getPayer(billingAccountID);
		defaultPaymentMethodId = payer.getDefaultPaymentMethodId();

		// check that the detault payment method has been updated
		assertEquals(payer.getDefaultPaymentMethodId(), methodToChange.getId());
		logPass("Default payment method ID changed to " + defaultPaymentMethod);
	}

	/**
	 * Delete SEPA payment method
	 * 
	 * Ref: B2B_A_PY_07
	 */
	@Test(dependsOnMethods = "B2B_A_PY_06_testUpdateDefaultPaymentMethod", description = "B2B_A_PY_07: B2B CRM UI API: Delete SEPA payment method")
	public void B2B_A_PY_07_testDeleteSepaPaymentMethod(ITestContext iTestContext) {

		// read the payer and payment methods from the database
		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);
		ArrayList<PaymentMethod> paymentMethods = PaymentCenterDAO.getPaymentMethodsForPayer(payer.getId());
		logInfo(paymentMethods.size() + " payment methods exist on the account");

		// read the default payment method before the test
		int defaultPaymentMethodId = payer.getDefaultPaymentMethodId();

		PaymentMethod methodToDelete = null;

		// determine a non-default payment method ID to modify for this test
		for (PaymentMethod method : paymentMethods) {
			if (method.getCancelledAt() == null && method.getId() != defaultPaymentMethodId) {
				methodToDelete = method;
				logInfo("Customer will delete payment method ID " + methodToDelete.getId() + ", " + methodToDelete.getPaymentMethodType());
			}
		}

		// verify that the payment method has been selected for the test
		assertNotNull(methodToDelete);

		// make the API call to validate the Iban
		APITransaction t = B2BCRMAPI.deleteSepaPaymentMethod(token, billingAccountID, methodToDelete.getId());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 204);

		// read the updated payer information from the database
		paymentMethods = PaymentCenterDAO.getPaymentMethodsForPayer(payer.getId());

		// find the payment method and confirm that it's been cancelled
		for (PaymentMethod method : paymentMethods) {
			if (method.getId() == methodToDelete.getId()) {
				assertNotNull(method.getCancelledAt());
				logInfo("Payment method " + method.getId() + " is now in a state of cancelled");
				break;
			}
		}
	}

	/*------------------------------------------------------------------
	 * Account: Hardware Fund tab
	 ------------------------------------------------------------------- */
	/**
	 * Get hardware fund
	 * 
	 * Ref: B2B_A_HW_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_HW_01: B2B CRM UI API: Get hardware fund")
	public void B2B_A_HW_01_testGetHardwareFund() {

		// retrieve the hardware fund details from the database
		B2BAccount account = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		HardwareFund hardwareFund = OrderManagementDAO.getHardwareFund(account.getHardwareBalanceId());
		long balance = AccountsReceivableDAO.getBalance(account.getHardwareBalanceId()).getNonOverdueAmount();
		ArrayList<ImmediateAdjustment> immediateAdjustments = AccountsReceivableDAO.getImmediateAdjustments(hardwareFund.getHardwareFundID());

		int creationEventId = -1;

		// get the id of latest adjustment with code "NEGOTIATION_HARDWARE_FUND"
		for (ImmediateAdjustment adjustment : immediateAdjustments) {
			if (adjustment.getReasonCode().equals("NEGOTIATION_HARDWARE_FUND")) {
				creationEventId = adjustment.getId();
				break;
			}
		}

		assertNotEquals(creationEventId, -1);

		// find the balance change record that corresponds to the most recent hardware
		// fund creation/renewal
		BalanceChange creationBalanceChange = null;

		// read the list of balance changes on the account
		ArrayList<BalanceChange> balanceChanges = AccountsReceivableDAO.getBalanceChanges(hardwareFund.getHardwareFundID());
		for (BalanceChange change : balanceChanges) {
			if (change.getId() == creationEventId) {
				creationBalanceChange = change;
				break;
			}
		}

		assertNotNull(creationBalanceChange);

		// make the API call to retrieve the hardware fund
		APITransaction t = B2BCRMAPI.getHardwareFund(token, b2bAccount.getHardwareBalanceId());
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		// convert the response to an object, for processing
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetHardwareFundDTO response = (GetHardwareFundDTO) jsonPathEvaluator.getObject("", GetHardwareFundDTO.class);

		// convert the change_date field to an appropriate text string
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = simpleDateFormat.format(creationBalanceChange.getChangeDate());

		// verify the details in the response
		assertEquals(response.getCurrentTotalBalance(), balance);
		assertEquals(response.getHardwareFundAmount(), creationBalanceChange.getAmount());
		assertEquals(response.getCreationDate(), dateString);
		logPass("Hardware fund successfully retrieved: " + response.getHardwareFundAmount() + ", " + response.getCurrentTotalBalance() + ", "
				+ response.getCreationDate());

		List<HardwareFundTransaction> transactionsFromApi = response.getTransactionList();

		assertEquals(response.getTransactionList().size(), immediateAdjustments.size());

		for (int i = 0; i < response.getTransactionList().size(); i++) {
			HardwareFundTransaction transactionFromAPI = transactionsFromApi.get(i);
			ImmediateAdjustment adjustment = immediateAdjustments.get(i);
			BalanceChange balanceChange = balanceChanges.get(i);
			assertEquals(transactionFromAPI.getReference(), adjustment.getReference());
			assertEquals(transactionFromAPI.getAdditionalText(), adjustment.getFreeText());
			assertEquals(transactionFromAPI.getReason(), adjustment.getReasonCode());

			if (!balanceChange.getChangeType().equals("ACCOUNT_CREATION")) {
				assertEquals(transactionFromAPI.getAmount(), balanceChange.getAmount());
				assertEquals(transactionFromAPI.getTotalBalance(), balanceChange.getNonOverdueAmount());
				logPass("Transaction correctly received: " + transactionFromAPI.getReference() + ", " + transactionFromAPI.getReason() + ", "
						+ transactionFromAPI.getAmount());
			}
		}
	}

	/**
	 * Get hardware fund adjustment reasons
	 * 
	 * Ref: B2B_A_HW_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_HW_02: B2B CRM UI API: Get hardware fund adjustment reasons")
	public void B2B_A_HW_02_testGetHardwareFundAdjustmentReasons() {

		// read the list of ref adjustment reasons from the database
		ArrayList<RefAdjustmentReason> allAdjustmentReasons = AdjustmentDAO.getRefAdjustmentReasons();
		ArrayList<RefAdjustmentReason> hwFundReasons = new ArrayList<RefAdjustmentReason>();

		// get the list of only HARDWARE_POSTPAY adjustment reasons
		for (RefAdjustmentReason reason : allAdjustmentReasons) {
			if (reason.getAdjustmentType().equals("HARDWARE_POSTPAY")) {
				hwFundReasons.add(reason);
			}
		}

		// make the API call to get all hardware adjustment reasons
		APITransaction t = B2BCRMAPI.getRefHardwareFundAdjustmentReasons(token, B2BAccountType.CORPORATE_OR_EBU);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		// convert the response to an object, for processing
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetRefHwFundAdjustmentReasons> response = jsonPathEvaluator.getList("", GetRefHwFundAdjustmentReasons.class);

		// verify that the correct number of ref reasons are returned
		assertEquals(response.size(), hwFundReasons.size());

		// for each adjustment reason, verify the details are correct
		for (int i = 0; i < response.size(); i++) {
			assertEquals(response.get(i).getDisplayName(), hwFundReasons.get(i).getDisplayName());
			assertEquals(response.get(i).getValue(), hwFundReasons.get(i).getReasonKey());
			assertEquals(response.get(i).getAdjustmentFinancialType(), hwFundReasons.get(i).getAdjustmentFinancialType());
			logPass("Adjustment reason returned: " + response.get(i).getDisplayName());
		}
	}

	/**
	 * Create hardware fund
	 * 
	 * Ref: B2B_A_HW_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_HW_03: B2B CRM UI API: Create hardware fund")
	public void B2B_A_HW_03_testCreateHardwareFund() {

		// specify the values for the test
		int[] terms = new int[] { 12, 24, 30, 36 };
		int amount = RandomStringGenerator.getRandomInteger(1, 20) * 100000;
		int term = RandomStringGenerator.getRandomIntegerFromArray(terms);
		String comment = "Auto hardware fund creation " + System.currentTimeMillis();

		logInfo("User wishes to create a hardware fund with values: " + amount + ", " + term + ", " + comment);

		// read the list of orders before the test
		ArrayList<ProductOrder> ordersBeforeTest = OrderManagementDAO.getOrdersForSubsAccountID(accountID);

		// make the API call to create the hardware fund
		APITransaction t = B2BCRMAPI.createHardwareFund(token, accountID, amount, term, comment);
		assertEquals(t.getResponse().statusCode(), 201);
		logPass(t.toString());

		// read the list of orders after the test
		ArrayList<ProductOrder> ordersAfterTest = OrderManagementDAO.getOrdersForSubsAccountID(accountID);

		// verify that 1 new order has been added for the account
		assertEquals(ordersAfterTest.size() - ordersBeforeTest.size(), 1);

		// verify the product order
		ProductOrder newOrder = ordersAfterTest.get(ordersAfterTest.size() - 1);
		assertEquals(newOrder.getOrder_type(), "CROSS_SELL");
		assertEquals(newOrder.getSalesType(), "B2B");
		logPass("1 new order added: " + newOrder.getReference() + ", " + newOrder.getOrder_type() + ", " + newOrder.getSalesType());

		// wait for the order to complete
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(newOrder.getReference());
		assertEquals(orderCompleted, true);
		newOrder = OrderManagementDAO.getOrderByReference(newOrder.getReference());
		logPass("Order " + newOrder.getReference() + " is now in status " + newOrder.getStatus());

		// verify the order-management.event
		int eventID = newOrder.getEventId();
		Event newEvent = OrderManagementDAO.getEvent(eventID);
		assertEquals(newEvent.getType(), "CREATE_HARDWARE_FUND_ORDER");
		assertEquals(newEvent.getSource(), "TELESALES");
		assertEquals(newEvent.getStatus(), "COMPLETED");
		logPass("Event " + newEvent.getId() + " created: " + newEvent.getType() + ", " + newEvent.getSource() + ", " + newEvent.getStatus());

		// determine the hardware fund ID created for the order
		OrderManagementAccount account = OrderManagementDAO.getOrderManagementAccount(newOrder.getId());
		HardwareFund hwFund = OrderManagementDAO.getHardwareFundForOrderManagementAccountID(account.getId());
		assertNotNull(hwFund);
		logPass("Hardware fund " + hwFund.getHardwareFundID() + " created.");

		// verify the hardware fund values
		assertEquals(hwFund.getTerm(), term);
		assertEquals(hwFund.getComment(), comment);
		assertEquals(hwFund.getInitialAmount(), amount);
		logPass("Hardware fund correctly created: " + hwFund.getHardwareFundID() + ", " + hwFund.getTerm() + ", " + hwFund.getInitialAmount() + ", "
				+ hwFund.getComment());

		// check in AR that the hw fund balance has been created
		Balance arBalance = AccountsReceivableDAO.getBalance(hwFund.getHardwareFundID());
		assertEquals(arBalance.getBillingAccountID(), hwFund.getHardwareFundID());
		assertEquals(arBalance.getNonOverdueAmount(), amount);
		assertEquals(arBalance.getOverdueAmount(), 0);
		logPass("Accounts Receivable balance created: " + arBalance.getBillingAccountID() + ", " + arBalance.getNonOverdueAmount());
	}

	/**
	 * Renew hardware fund
	 * 
	 * Ref: B2B_A_HW_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_HW_04: B2B CRM UI API: Renew hardware fund")
	public void B2B_A_HW_04_testRenewHardwareFund() {
		B2BAccount account = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		APITransaction t = B2BCRMAPI.renewHardwareFund(token, accountID, 10000, 24, account.getHardwareBalanceId());
		assertEquals(t.getResponse().statusCode(), 204);
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Transactions tab
	 ------------------------------------------------------------------- */
	/**
	 * Get transaction history
	 * 
	 * Ref: B2B_A_TR_01
	 * 
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_TR_01: B2B CRM UI API: Get transaction history")
	public void B2B_A_TR_01_testGetTransactionHistory() {
		APITransaction t = B2BCRMAPI.getTransactionHistory(token, billingAccountID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get ref adjustment reason codes
	 * 
	 * Ref: B2B_A_TR_02
	 * 
	 */
	@Test(dependsOnMethods = "testLogin", dataProvider = "accountTypes", description = "B2B_A_TR_02: B2B CRM UI API: Get ref adjustment reasons")
	public void B2B_A_TR_02_testGetAdjustmentReasons(B2BAccountType accountType, ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getRefAdjustmentReasons(token, accountType);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get adjustment
	 * 
	 * Ref: B2B_A_TR_03
	 * 
	 */
	@Test(dependsOnMethods = "testLogin",description = "B2B_A_TR_03: B2B CRM UI API: Get adjustment details")
	public void B2B_A_TR_03_testGetAdjustment(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAdjustment(token, "UIH5PPJX");
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Documents tab
	 ------------------------------------------------------------------- */
	/**
	 * Get documents for an account
	 * 
	 * Ref: B2B_A_DO_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_DO_01: B2B CRM UI API: Get documents")
	public void B2B_A_DO_01_testGetDocuments() {
		APITransaction t = B2BCRMAPI.getDocuments(token, accountID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get document types
	 * 
	 * Ref: B2B_A_DO_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_DO_02: B2B CRM UI API: Get ref document types")
	public void B2B_A_DO_02_testGetRefDocumentTypes() {
		APITransaction t = B2BCRMAPI.getRefDocumentTypes(token);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Upload a document
	 * 
	 * Ref: B2B_A_DO_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_DO_03: B2B CRM UI API: Upload document")
	public void B2B_A_DO_03_testUploadDocument(ITestContext iTestContext) {

		DocumentType type = DocumentType.MANIFEST;
		ArrayList<Document> documentsBeforeTest = DocumentDAO.getDocuments(accountID);
		logInfo("Customer has " + documentsBeforeTest.size() + " documents before the test");

		String template = "templates\\documents\\DocumentTemplate.txt";

		// generate a new PDF document
		String documentName = Timestamp.getTimestamp("yyyyMMddhhmmss") + "_Document_" + accountID + "_" + type.toString() + ".pdf";
		String filepath = "files\\documents\\" + documentName;
		String content = TextReader.getContent(template);
		content = content.replace("$accountID", Integer.toString(accountID));
		content = content.replace("$documentType", type.toString());
		content = content.replace("$timestamp", Timestamp.getTimestamp("dd-MM-yyyy hh:mm:ss"));
		boolean fileCreated = PDFWriter.writePDF(content, filepath);

		// check that the file generation has completed ok
		assertTrue(fileCreated);

		// make a call to the API to upload the document
		APITransaction t = B2BCRMAPI.uploadDocument(token, accountID, type, filepath);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 204);

		ArrayList<Document> documentsAfterTest = DocumentDAO.getDocuments(accountID);
		assertEquals(1, documentsAfterTest.size() - documentsBeforeTest.size());
		logInfo("Customer has " + documentsAfterTest.size() + " documents after the test");

		// read the document from the database
		Document document = documentsAfterTest.get(0);
		assertNotNull(document);
		assertEquals("application/pdf", document.getDocumentType());

		// log output to the report
		logPass("Document uploaded: ID " + document.getId() + ", " + document.getDocumentFilename());
	}

	/**
	 * Delete a document
	 * 
	 * Ref: B2B_A_DO_04
	 */

	@Test(dependsOnMethods = "testLogin", description = "B2B_A_DO_04: B2B CRM UI API: Delete document")
	public void B2B_A_DO_04_testDeleteDocument(ITestContext iTestContext) {

		// read the list of documents on the account before the test
		ArrayList<Document> documentsBeforeTest = DocumentDAO.getDocuments(accountID);
		logInfo("Customer has " + documentsBeforeTest.size() + " documents before the test");

		assertTrue(documentsBeforeTest.size() > 0);
		
		// identify the document for deletion (we will use the oldest one)
		Document documentToDelete = documentsBeforeTest.get(documentsBeforeTest.size() - 1);

		// make a call to the API to delete the document
		APITransaction t = B2BCRMAPI.deleteDocument(token, documentToDelete.getDocumentFilename());
		assertEquals(204, t.getResponse().statusCode());
		logPass(t.toString());

		// verify that 1 document has been deleted from the table
		ArrayList<Document> documentsAfterTest = DocumentDAO.getDocuments(accountID);
		assertEquals(1, documentsBeforeTest.size() - documentsAfterTest.size());
		logInfo("Customer has " + documentsAfterTest.size() + " documents after the test");

		// attempt to read the document from the database
		Document document = DocumentDAO.getDocumentByFilename(documentToDelete.getDocumentFilename());

		// verify that the document can no longer be found in the database
		assertNull(document);

		// log output to the report
		logPass("Document uploaded: ID " + documentToDelete.getId() + ", " + documentToDelete.getDocumentFilename() + " is now deleted");
	}
	
	/**
	 * Download document
	 * 
	 * Ref: B2B_A_DO_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_DO_05: B2B CRM UI API: Download document")
	public void B2B_A_DO_05_testDownloadDocument(ITestContext iTestContext) {
		String documentName="2022040721293466-69-Manifest.pdf";
		APITransaction t = B2BCRMAPI.downloadDocument(token, documentName);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
		
		// No validations possible on this
	}

	/*------------------------------------------------------------------
	 * Account: Customer History
	 ------------------------------------------------------------------- */

	/**
	 * Get customer history filters
	 * 
	 * Ref: B2B_A_CH_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_01: B2B CRM UI API: Get customer history filters")
	public void B2B_A_CH_01_testGetCustomerHistoryFilters(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getCustomerHistoryFilters(token, accountID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get customer history
	 * 
	 * Ref: B2B_A_CH_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_02: B2B CRM UI API: Get customer history")
	public void B2B_A_CH_02_testGetCustomerHistory(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getCustomerHistory(token, accountID);
		logPass(t.toString().substring(0,1000));
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/**
	 * Add a note
	 * 
	 * Ref: B2B_A_CH_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_03: B2B CRM UI API: Add a note")
	public void B2B_A_CH_03_testAddNote(ITestContext iTestContext) {
		String testNote = "auto note " + System.currentTimeMillis();
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptionID);
		APITransaction t = B2BCRMAPI.addNote(token, accountID, testNote, service.getName());
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());
	}
	
	/**
	 * Get customer history item
	 * 
	 * Ref: B2B_A_CH_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_04: B2B CRM UI API: Get customer history item")
	public void B2B_A_CH_04_testGetCustomerHistoryItem(ITestContext iTestContext) {
		int customerHistoryItemId=16594;
		APITransaction t = B2BCRMAPI.getCustomerHistoryItem(token, customerHistoryItemId);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/**
	 * Get email notification subjects
	 * 
	 * Ref: B2B_A_CH_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_05: B2B CRM UI API: Get email notification subjects")
	public void B2B_A_CH_05_testGetEmailNotificationSubjects(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getEmailNotificationSubjects(token, accountID);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/**
	 * Get email notifications
	 * 
	 * Ref: B2B_A_CH_06
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_06: B2B CRM UI API: Get email notifications")
	public void B2B_A_CH_06_testGetEmailNotifications(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getEmailNotifications(token, accountID, "2021-10-01");
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/**
	 * Get sms notifications
	 * 
	 * Ref: B2B_A_CH_07
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_07: B2B CRM UI API: Get SMS notifications")
	public void B2B_A_CH_07_testGetSmsNotifications(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getSmsNotifications(token, accountID, "2021-10-01");
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/**
	 * Add a comment to a history item
	 * 
	 * Ref: B2B_A_CH_08
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CH_08: B2B CRM UI API: Add a comment to a history item")
	public void B2B_A_CH_08_testAddCommentToHistoryItem(ITestContext iTestContext) {
		int customerHistoryItemId=16594;
		String testComment = "auto note " + System.currentTimeMillis();
		APITransaction t = B2BCRMAPI.addCommentToHistoryItem(token, customerHistoryItemId,testComment);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}

	
	/*------------------------------------------------------------------
	 * Account: Manage Addons
	 ------------------------------------------------------------------- */
	/**
	 * Get active account level addons
	 * 
	 * Ref: B2B_A_AO_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_AO_01: B2B CRM UI API: Get active account level addons")
	public void B2B_A_AO_01_testGetActiveAccountLevelAddons(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getActiveAccountLevelAddons(token, accountID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get available account level addons
	 * 
	 * Ref: B2B_A_AO_02
	 */
	@Test(dependsOnMethods = "testLogin", dataProvider = "accountTypes", description = "B2B_A_AO_02: B2B CRM UI API: Get available account level addons")
	public void B2B_A_AO_02_testGetAvailableAccountLevelAddons(B2BAccountType accountType, ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAvailableAccountLevelAddons(token, accountType);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Manage Equipments
	 ------------------------------------------------------------------- */

	/**
	 * Get available handsets
	 * 
	 * Ref: B2B_A_EQ_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_EQ_01: B2B CRM UI API: Get available handsets")
	public void B2B_A_EQ_01_testGetAvailableHandsets(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAvailableHandsets(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get available handsets and dongles
	 * 
	 * Ref: B2B_A_EQ_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_EQ_02: B2B CRM UI API: Get available handsets & dongles")
	public void B2B_A_EQ_02_testGetAvailableHandsetsAndDongles(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAvailableHandsetsAndDongles(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Manage account level barring
	 ------------------------------------------------------------------- */

	/**
	 * Add network level barring
	 * 
	 * Ref: B2B_A_BR_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_BR_01: B2B CRM UI API: Add account level barring")
	public void B2B_A_BR_01_testAddAccountLevelBarring(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.addAccountLevelFullNetworkBarring(token, accountID);
		assertEquals(204, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	/**
	 * Remove network level barring
	 * 
	 * Ref: B2B_A_BR_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_BR_02: B2B CRM UI API: Remove account level barring")
	public void B2B_A_BR_02_testRemoveAccountLevelBarring(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.addAccountLevelFullNetworkBarring(token, accountID);
		assertEquals(204, t.getResponse().statusCode());
		logPass(t.toString());
	}
	/*------------------------------------------------------------------
	 * Account: Orders
	 ------------------------------------------------------------------- */
	/**
	 * Get order history
	 * 
	 * Ref: B2B_A_OR_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_OR_01: B2B CRM UI API: Get order history")
	public void B2B_A_OR_01_testGetOrderHistory() {
		APITransaction t = B2BCRMAPI.getOrderHistory(token, accountID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get filtered order history
	 * 
	 * Ref: B2B_A_OR_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_OR_02: B2B CRM UI API: Get filtered order history")
	public void B2B_A_OR_02_testGetFilteredOrderHistory(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getFilteredOrderHistory(token, accountID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get order
	 * 
	 * Ref: B2B_A_OR_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_OR_03: B2B CRM UI API: Get order")
	public void B2B_A_OR_03_testGetOrder(ITestContext iTestContext) {
		String orderReference = OrderManagementDAO.getOrdersForBillingAccountID(billingAccountID).get(0).getReference();
		APITransaction t = B2BCRMAPI.getOrder(token, orderReference);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Contacts
	 ------------------------------------------------------------------- */
	/**
	 * Get account contacts
	 * 
	 * Ref: B2B_A_CO_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CO_01_B2B CRM UI API: Get account contacts")
	public void B2B_A_CO_01_testGetAccountContacts() {
		APITransaction t = B2BCRMAPI.getAccountContacts(token, accountID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get ref contact types
	 * 
	 * Ref: B2B_A_CO_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CO_02: B2B CRM UI API: Get ref contact types")
	public void B2B_A_CO_02_testGetRefContactTypes() {
		APITransaction t = B2BCRMAPI.getRefContactTypes(token);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get all contacts
	 * 
	 * Ref: B2B_A_CO_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CO_03: B2B CRM UI API: Get all contacts")
	public void B2B_A_CO_03_testGetAllContacts() {
		APITransaction t = B2BCRMAPI.getAllContacts(token, accountID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Add a contact
	 * 
	 * Ref: B2B_A_CO_06
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CO_06: B2B CRM UI API: Add a new contact")
	public void B2B_A_CO_06_testAddContact(ITestContext iTestContext) {

		// specify eircode
		String eircode = "D24YX53";

		// read the list of contacts before the test
		ArrayList<AccountContact> contactsBeforeTest = SubscriptionManagementDAO.getAllAccountContacts(billingAccountID);
		logInfo("Contact has " + contactsBeforeTest + " contacts on their account before the test");

		// read the expected address from the database
		AddressFinderAddress addressFromDatabase = AddressFinderDAO.getAddresses(eircode).get(0);

		// populate the address DTO
		AddressDTO address = new AddressDTO();
		address.setAddressLine1(addressFromDatabase.getAddressLine1());
		address.setAddressLine2(addressFromDatabase.getAddressLine2());
		address.setAddressLine3(addressFromDatabase.getAddressLine3());
		address.setCity(addressFromDatabase.getTown());
		address.setCounty(addressFromDatabase.getCounty());
		address.setCountry("Ireland");
		address.setCode(addressFromDatabase.getEircode());

		// generate random contact details
		String firstName = RandomStringGenerator.getRandomFirstName();
		String lastName = RandomStringGenerator.getRandomLastName();
		String phoneNumber = RandomStringGenerator.getRandomMobilePhoneNumber();
		String email = firstName + "." + lastName + "_" + System.currentTimeMillis() + "@eirb2b.ie";
		email = email.toLowerCase();

		logInfo("Contact has " + contactsBeforeTest + " contacts on their account before the test");

		// make the call to the API to create the contact
		APITransaction t = B2BCRMAPI.addContact(token, accountID, firstName, lastName, email, phoneNumber, ContactType.PAYER, address);
		assertEquals(204, t.getResponse().statusCode());
		logPass(t.toString());

		ArrayList<AccountContact> contactsAfterTest = SubscriptionManagementDAO.getAllAccountContacts(billingAccountID);
		assertEquals(1, contactsAfterTest.size() - contactsBeforeTest.size());

		// the added contasct will be the most recent one in the list
		AccountContact addedAccountContact = contactsAfterTest.get(contactsAfterTest.size() - 1);
		logPass("New contact added is " + addedAccountContact.getUuid());

		// read the details from contact management and verify that they are correct
		Contact contact = ContactManagementDAO.getContact(addedAccountContact.getUuid());
		assertEquals(firstName, contact.getFirstName());
		assertEquals(lastName, contact.getLastName());
		assertEquals(email, contact.getEmailAddress());
		assertEquals(phoneNumber, contact.getPhoneNumber());

		// log output to the report
		logPass("Contact is successfully created: " + contact.getUuid() + ", " + contact.getFirstName() + " " + contact.getLastName() + ", "
				+ contact.getEmailAddress());
	}

	/**
	 * Update contact type
	 * 
	 * Ref: B2B_A_CO_07
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CO_07: B2B CRM UI API: Update contact type")
	public void B2B_A_CO_07_testUpdateContactType(ITestContext iTestContext) {

		// determine the contact type
		ContactType type = ContactType.OWNER;
		int expectedContactType = SubscriptionManagementDAO.getRefContactType(type.toString()).getId();

		String contactUuid = SubscriptionManagementDAO.getContactUuidForAccountID(accountID);

		// get the current contact with the specified type
		String currentContact = SubscriptionManagementDAO.getContactOfType(accountID, expectedContactType);
		logInfo("Current contact of type " + type + " is " + currentContact);

		// determine the expect contactID

		// make the API call to update the contact type
		APITransaction t = B2BCRMAPI.updateContactType(token, accountID, contactUuid, type);
		System.err.println(t);
		assertEquals(204, t.getResponse().statusCode());

		// log output to the report
		logPass(t.toString());
	}

	/**
	 * Lookup eircode Screen: Contacts > Edit > Address Details
	 * 
	 * Ref: B2B_A_CO_08
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CO_08: B2B CRM UI API: Eircode lookup")
	public void B2B_A_CO_08_testEircodeLookup(ITestContext iTestContext) {

		String eircode = "D24YX53";

		// read the expected address from the database
		ArrayList<AddressFinderAddress> addressesFromDatabase = AddressFinderDAO.getAddresses(eircode);

		// make the call to the API to retrieve the address
		APITransaction t = B2BCRMAPI.eircodeLookup(token, eircode);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<ReturnedAddressFinderAddress> addressesFromAPI = jsonPathEvaluator.getList("", ReturnedAddressFinderAddress.class);

		// verify that the correct number of addresses are returned
		assertEquals(addressesFromDatabase.size(), addressesFromAPI.size());
		logPass(addressesFromAPI.size() + " address(es) returned from the API as expected");

		// from each address returned, check that it's correct
		for (int i = 0; i < addressesFromAPI.size(); i++) {
			assertEquals(addressesFromAPI.get(i).getAddressLine1(), addressesFromDatabase.get(i).getAddressLine1());
			assertEquals(addressesFromAPI.get(i).getAddressLine2(), addressesFromDatabase.get(i).getAddressLine2());
			assertEquals(addressesFromAPI.get(i).getAddressLine3(), addressesFromDatabase.get(i).getAddressLine3());
			assertEquals(addressesFromAPI.get(i).getTown(), addressesFromDatabase.get(i).getTown());
			assertEquals(addressesFromAPI.get(i).getCounty(), addressesFromDatabase.get(i).getCounty());
			logPass("Address is correct: " + addressesFromAPI.get(i).getAddressLine1());
		}
	}

	/*------------------------------------------------------------------
	 * Account: Subscriptions
	 ------------------------------------------------------------------- */
	/**
	 * Get subscriptions
	 * 
	 * Ref: B2B_A_SU_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_SU_01: B2B CRM UI API: Get subscriptions")
	public void B2B_A_SU_01_testGetSubscriptions() {
		APITransaction t = B2BCRMAPI.getSubscriptions(token, accountID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get the SIM Details via the API
	 * 
	 * Ref: B2B_A_SU_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_SU_02: B2B CRM UI API: Get SIM details")
	public void B2B_A_SU_02_testGetSimDetails() {

		String iccid = "893530305243718034";

		// make a call to the API to retrieve the SIM details
		APITransaction t = B2BCRMAPI.getSimDetails(token, iccid);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// verify the sim details
		SimDetails simDetails = InventoryManagementAPI.getSimDetailsFromInventory(iccid);
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();

		// verify that the details returned are correct
		assertEquals((String) jsonPathEvaluator.get("imsi"), simDetails.getImsi());
		assertEquals((String) jsonPathEvaluator.get("pin1"), simDetails.getPin1());
		assertEquals((String) jsonPathEvaluator.get("pin2"), simDetails.getPin2());
		assertEquals((String) jsonPathEvaluator.get("puk1"), simDetails.getPuk1());
		assertEquals((String) jsonPathEvaluator.get("puk2"), simDetails.getPuk2());
	}

	/*------------------------------------------------------------------
	 * Service: General tab
	 ------------------------------------------------------------------- */
	/**
	 * Get the subscription via the API
	 * 
	 * Ref: B2B_S_GE_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_GE_01: B2B CRM UI API: Get subscription")
	public void B2B_S_GE_01_testGetSubscription() {

		// read the subscription from the database
		Subscription subscriptionFromDatabase = SubscriptionManagementDAO.getSubscriptionByID(subscriptionID);
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptionID);
		SimCard simCard = SubscriptionManagementDAO.getActiveSimCard(service.getName());

		// make a call to the API to retrieve the susbcription
		APITransaction t = B2BCRMAPI.getSubscription(token, subscriptionID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		// convert the json response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		SubscriptionJson subscriptionFromAPI = (SubscriptionJson) jsonPathEvaluator.getObject("", SubscriptionJson.class);

		// verify the values
		assertEquals(subscriptionFromAPI.getId(), subscriptionFromDatabase.getId());
		assertEquals(subscriptionFromAPI.getOfferCode(), subscriptionFromDatabase.getCatalogOfferCode());
		assertEquals(subscriptionFromAPI.getType(), subscriptionFromDatabase.getType());
		assertEquals(subscriptionFromAPI.getSubscriberName(), subscriptionFromDatabase.getName());
		assertEquals(subscriptionFromAPI.getCostCenter(), subscriptionFromDatabase.getCostCenter());
		assertEquals(subscriptionFromAPI.isVip(), subscriptionFromDatabase.getIsVip() == 1);
		assertEquals(subscriptionFromAPI.getStatus(), subscriptionFromDatabase.getStatus());
		assertEquals(subscriptionFromAPI.getIccid(), simCard.getIccid());

		// verify the service details
		assertEquals(subscriptionFromAPI.getServiceId(), service.getId());

		// log output to the report
		logPass("Subscription successfully retrieved: " + subscriptionID + ", " + service.getName() + ", " + subscriptionFromDatabase.getCatalogOfferCode()
				+ ", " + subscriptionFromAPI.getIccid());
	}

	/*------------------------------------------------------------------
	 * Service: Usage tab
	 ------------------------------------------------------------------- */

	/**
	 * Get ref usage types
	 * 
	 * Ref: B2B_S_US_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_US_01: B2B CRM UI API: Get ref usage types")
	public void B2B_S_US_01_testGetRefUsageTypes(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getRefUsageTypes(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	/**
	 * Get usage
	 * 
	 * Ref: B2B_S_US_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_US_02: B2B CRM UI API: Get Usage")
	public void B2B_S_US_02_testGetUsage(ITestContext iTestContext) {
		String period=Timestamp.getCurrentTimestamp("yyyyMM");
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptionID);
		APITransaction t = B2BCRMAPI.getUsage(token, service.getId(),period);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	/**
	 * Get usage summary
	 * 
	 * Ref: B2B_S_US_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_US_01: B2B CRM UI API: Get ref usage types")
	public void B2B_S_US_03_testGetUsageSummary(ITestContext iTestContext) {
		String period=Timestamp.getCurrentTimestamp("yyyyMM");
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptionID);
		APITransaction t = B2BCRMAPI.getUsageSummary(token, service.getId(),period);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Create Account
	 ------------------------------------------------------------------- */
	/**
	 * Generate a new order reference in order management
	 * 
	 * Ref: B2B_A_CA_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CA_03: B2B CRM UI API: Generate new order number")
	public void B2B_A_CA_03_testGenerateNewOrderNumber(ITestContext iTestContext) {

		// make a call to the API to generate a new order reference
		APITransaction t = B2BCRMAPI.generateNewOrderNumber(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the reference from the response

		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String reference = (String) jsonPathEvaluator.get("reference");
		assertNotNull(reference);

		// check that the reference has been created in order management's
		// ORDER_REFERENCE table
		String refFromDatabase = OrderManagementDAO.getOrderReference(reference);
		assertEquals(reference, refFromDatabase);
	}

	/*------------------------------------------------------------------
	 * Account: Create Subscription
	 ------------------------------------------------------------------- */
	/**
	 * Get available offers for customer type
	 * 
	 * Ref: B2B_A_CS_01
	 */
	@Test(dependsOnMethods = "testLogin", dataProvider = "accountTypes", description = "B2B_A_CS_01: B2B CRM UI API: Get offers for customer type")
	public void B2B_A_CS_01_testGetOffersForAccountType(B2BAccountType accountType, ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getOffersForCustomerType(token, accountType);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<CatalogOffer> offers = jsonPathEvaluator.getList("", CatalogOffer.class);
		System.err.println(offers.size() + " offers found");
	}

	/**
	 * Get available tariffs for offer code
	 * 
	 * Ref: B2B_A_CS_02
	 */
	@Test(dependsOnMethods = "testLogin", dataProvider = "getB2BOffers", description = "B2B_A_CS_02: B2B CRM UI API: Get tariff plans for offer")
	public void B2B_A_CS_02_testGetTariffPlansForOfferCode(TariffPlan offer, ITestContext iTestContext) {
		OfferCode offerCode = OfferCode.valueOf(offer.getOfferCode());
		APITransaction t = B2BCRMAPI.getTariffPlansForOfferCode(token, offerCode);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get available addons for offer
	 * 
	 * Ref: B2B_A_CS_03
	 */
	@Test(dependsOnMethods = "testLogin", dataProvider = "getCorporateOffers", description = "B2B_A_CS_03: B2B CRM UI API: Get available addons for Corporate offer")
	public void B2B_A_CS_03_testGetAddonsForCorporateOffer(TariffPlan offer, ITestContext iTestContext) {
		OfferCode offerCode = OfferCode.valueOf(offer.getOfferCode());
		APITransaction t = B2BCRMAPI.getAvailableAddonsForOffer(token, B2BAccountType.CORPORATE_OR_EBU, offerCode);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/**
	 * Get available addons for offer
	 * 
	 * Ref: B2B_A_CS_03
	 */
	@Test(dependsOnMethods = "testLogin", dataProvider = "getGovernmentOffers", description = "B2B CRM UI API: Get available addons for Government offer")
	public void testGetAddonsForGovernmentOffer(TariffPlan offer, ITestContext iTestContext) {
		OfferCode offerCode = OfferCode.valueOf(offer.getOfferCode());
		APITransaction t = B2BCRMAPI.getAvailableAddonsForOffer(token, B2BAccountType.GOVERNMENT, offerCode);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Create Account
	 ------------------------------------------------------------------- */

	/**
	 * Get ref market segments
	 * 
	 * Ref: B2B_A_CA_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CA_01: B2B CRM UI API: Get ref market segments")
	public void B2B_A_CA_01_testGetRefMarketSegments(ITestContext iTestContext) {

		// read the list of market segments from the database
		ArrayList<RefAccountCategory> marketSegmentsFromDatabase = SubscriptionManagementDAO.getAccountCategories("MARKET_SEGMENT");
		System.err.println(marketSegmentsFromDatabase.size());

		// make the API call to retrieve the list of market segments
		APITransaction t = B2BCRMAPI.getRefMarketSegments(token);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		// convert the json response to a list of response objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetMarketSegmentsResponse> segmentsFromAPI = jsonPathEvaluator.getList("", GetMarketSegmentsResponse.class);

		// log output to the report
		assertEquals(segmentsFromAPI.size(), marketSegmentsFromDatabase.size());

		logPass(segmentsFromAPI.size() + " market segments returned as expected");

		// create a comparator to sort the list from the API
		Comparator<GetMarketSegmentsResponse> compareByName = new Comparator<GetMarketSegmentsResponse>() {
			@Override
			public int compare(GetMarketSegmentsResponse o1, GetMarketSegmentsResponse o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		};

		// sort the list of market segments from the API
		List<GetMarketSegmentsResponse> sortedListFromAPI = new ArrayList<GetMarketSegmentsResponse>(segmentsFromAPI);
		Collections.sort(sortedListFromAPI, compareByName);

		// verify that the market segments returned match those in the database
		for (int i = 0; i < segmentsFromAPI.size(); i++) {
			assertEquals(sortedListFromAPI.get(i).getValue(), marketSegmentsFromDatabase.get(i).getValue());
			assertEquals(sortedListFromAPI.get(i).getLabel(), marketSegmentsFromDatabase.get(i).getLabel());
			logPass("Market segment " + sortedListFromAPI.get(i).getLabel() + ", " + sortedListFromAPI.get(i).getValue() + " returned as expected");
		}
	}

	/**
	 * Get ref billing cycles
	 * 
	 * Ref: B2B_A_CA_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_A_CA_02: B2B CRM UI API: Get ref bill cycles")
	public void B2B_A_CA_02_testGetRefBillCycles(ITestContext iTestContext) {

		// make a call to the API to retrieve the bill cycles
		APITransaction t = B2BCRMAPI.getRefBillCycles(token);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		// convert the response into a list of ref bill cycle objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetRefBillCyclesResponse> billCyclesFromAPI = jsonPathEvaluator.getList("", GetRefBillCyclesResponse.class);

		// read the list of bill cycles from the database
		ArrayList<RefBillingCycle> billCyclesFromDatabase = SubscriptionManagementDAO.getBillCycles("CORPORATE_OR_EBU");

		// verify that 2 items are returned
		assertEquals(billCyclesFromDatabase.size(), billCyclesFromAPI.size());

		// verify that the bill cycles returned match those in the database
		for (int i = 0; i < billCyclesFromDatabase.size(); i++) {
			assertEquals(billCyclesFromDatabase.get(i).getName(), billCyclesFromAPI.get(i).getLabel());
			assertEquals(billCyclesFromDatabase.get(i).getId(), Integer.parseInt(billCyclesFromAPI.get(i).getValue()));
			logPass("Bill cycle returned: " + billCyclesFromAPI.get(i).getLabel() + ", " + billCyclesFromAPI.get(i).getValue());
		}
	}

	/*------------------------------------------------------------------
	 * Service: MSISDN Swap
	 ------------------------------------------------------------------- */
	/**
	 * Get the next replacement MSISDN
	 * 
	 * Ref: B2B_S_MS_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_MS_01: B2B CRM UI API: Get next available MSISDN")
	public void B2B_S_MS_01_testGetNextMSISDN(ITestContext iTestContext) {

		// read the expected next MSISDN value from the database
		String msisdnFromDB = ProvisioningFacadeDAO.getNextAvailableReplacementMsisdn().getMsisdn();

		// make the call to the API
		APITransaction transaction = B2BCRMAPI.getNextMsisdn(token);

		// read the value of "msisdn" from the response
		JsonPath jsonPathEvaluator = transaction.getResponse().jsonPath();
		String msisdnFromAPI = (String) jsonPathEvaluator.get("msisdn");
		assertEquals(msisdnFromDB, msisdnFromAPI);

		// log output to the report
		logPass("Next msisdn successfully retrieved: " + msisdnFromAPI);
	}

	/**
	 * Submit MSISDN swap
	 * 
	 * Ref: B2B_S_MS_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_MS_02: B2B CRM UI API: Swap MSISDN")
	public void B2B_S_MS_02_testSwapMSISDN(ITestContext iTestContext) {

		// read the most recent MSISDN swap order from the database
		Event eventToIgnore = OrderManagementDAO.getMostRecentEventOfType(EventType.SERVICE_FULFILLMENT_SWAP_MSISDN);

		// get the next available MSISDN via the API
		APITransaction transaction = B2BCRMAPI.getNextMsisdn(token);
		logInfo("Service " + serviceID + " currently has MSISDN " + msisdn);

		// read the value of "msisdn" from the response
		JsonPath jsonPathEvaluator = transaction.getResponse().jsonPath();
		String newMsisdn = (String) jsonPathEvaluator.get("msisdn");
		assertNotNull(newMsisdn);
		logInfo("The service will swap to MSISDN " + newMsisdn);

		// make the API call to trigger the MSISDN swap
		APITransaction t = B2BCRMAPI.submitMsisdnSwap(token, serviceID, newMsisdn);
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());

		// wait for the event to get created in the order management EVENT table
		Event thisEvent = OrderManagementMonitor.waitForEventOfTypeAndLaterThanNamedEventID(EventType.SERVICE_FULFILLMENT_SWAP_MSISDN, eventToIgnore.getId());
		assertNotNull(thisEvent);
		assertEquals("TELESALES", thisEvent.getSource());
		assertEquals("SERVICE_FULFILLMENT_SWAP_MSISDN", thisEvent.getType());
		logPass("Event " + thisEvent.getId() + " created for this request: " + thisEvent.getType() + ", " + thisEvent.getSource() + ", "
				+ thisEvent.getStatus());

		// wait for the event to complete in order management
		boolean eventCompleted = OrderManagementMonitor.waitForEventToComplete(thisEvent.getId());
		assertTrue(eventCompleted);
		logPass("Event  " + thisEvent.getId() + " is now in a COMPLETED state");

		// read in the service details from the SERVICE table
		Service service = SubscriptionManagementDAO.getService(serviceID);

		// verify that the MSISDN has changed successfully
		assertEquals(newMsisdn, service.getName());
		logPass("Msisdn successfully swapped to " + service.getName());

		// update the MSISDN in the test suite for other tests in the suite
		msisdn = newMsisdn;
	}

	/*------------------------------------------------------------------
	 * Service: Manage Add-ons
	 ------------------------------------------------------------------- */
	/**
	 * Get available service level addons
	 * 
	 * Ref: B2B_S_AO_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_AO_01_B2B CRM UI API: Get available service level addons")
	public void B2B_S_AO_01_testGetAvailableServiceLevelAddons(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAvailableServiceLevelAddons(token, B2BAccountType.CORPORATE_OR_EBU, OfferCode.PERFORMANCE);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get active service level addons
	 * 
	 * Ref: B2B_S_AO_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_AO_02: B2B CRM UI API: Get active service level addons")
	public void B2B_S_AO_02_testGetActiveServiceLevelAddons(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getActiveServiceLevelAddons(token, serviceID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	/*------------------------------------------------------------------
	 * Service: Barring
	 ------------------------------------------------------------------- */
	/**
	 * Get barring
	 * 
	 * Ref: B2B_S_BR_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_BR_01: B2B CRM UI API: Get barrings")
	public void B2B_S_BR_01_testGetBarrings(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getBarrings(token, serviceID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	/*------------------------------------------------------------------
	 * Service: Change Offer
	 ------------------------------------------------------------------- */
	/**
	 * Get available offers for a change offer
	 * 
	 * Ref: B2B_S_CH_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_CH_01: B2B CRM UI API: Get available offers for change offer")
	public void B2B_S_CH_01_testGetAvailableOffersForChangeOffer(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAvailableOffersForChangeOffer(token, B2BAccountType.CORPORATE_OR_EBU);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	/**
	 * Get change offers on a subscription
	 * 
	 * Ref: B2B_S_CH_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_CH_02: B2B CRM UI API: Get change offer requests")
	public void B2B_S_CH_02_testGetChangeOfferEligibility(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getChangeOfferEligibility(token, subscriptionID);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}

	/*------------------------------------------------------------------
	 * Service: Terminations
	 ------------------------------------------------------------------- */
	/**
	 * Get pending terminations
	 * 
	 * Ref: B2B_S_CH_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_CH_01: B2B CRM UI API: Get pending termination requests")
	public void B2B_S_CH_01_testGetPendingTerminationRequests(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getPendingTerminations(token, serviceID);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/*------------------------------------------------------------------
	 * Service: Porting
	 ------------------------------------------------------------------- */
	/**
	 * Get porting times
	 * 
	 * Ref: B2B_S_PO_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_PO_01: B2B CRM UI API: Get porting times")
	public void B2B_S_PO_01_testGetPortingTimes(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getPortingTimes(token);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/**
	 * Get porting history
	 * 
	 * Ref: B2B_S_PO_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_PO_02: B2B CRM UI API: Get porting history")
	public void B2B_S_PO_02_testGetPortingHistory(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getPortingHistory(token, serviceID);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	/**
	 * Get incomplete ports
	 * 
	 * Ref: B2B_S_PO_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B_S_PO_03: B2B CRM UI API: Get incomplete ports")
	public void B2B_S_PO_03_testGetIncompletePorts(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getIncompletePorts(token, serviceID);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	// -----------------------------------------

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Create root account [CORPORATE_OR_EBU]")
	public void testCreateRootAccount(ITestContext iTestContext) {

		// generate a random company name fro the new account
		String companyName = RandomStringGenerator.getRandomCompanyName();

		// make a call to the API to generate a new order reference
		APITransaction t = B2BCRMAPI.generateNewOrderNumber(token);
		assertEquals(200, t.getResponse().statusCode());

		// read the reference from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String reference = (String) jsonPathEvaluator.get("reference");
		assertNotNull(reference);

		// now make a call to the API to generate the order
		t = B2BCRMAPI.createAccount(token, 0, companyName, B2BAccountType.CORPORATE_OR_EBU, reference);
		assertEquals(201, t.getResponse().statusCode());
		logPass(t.toString());
		logPass("Create Account order has been submitted: " + reference);

		// read the order back from the database
		ProductOrder order = OrderManagementDAO.getOrderByReference(reference);

		// verify that the record in the Product Order table are correct
		assertEquals("ACQUISITION", order.getOrder_type());
		assertEquals("B2B", order.getSalesType());
		logPass("Order " + order.getId() + ", " + reference + " is correct: " + order.getOrder_type() + ", " + order.getSalesType());

		// verify that the record in the Event table is correct
		Event event = OrderManagementDAO.getEvent(order.getEventId());
		assertNotNull(event);
		assertEquals("CREATE_ACCOUNT_ORDER", event.getType());
		assertEquals("TELESALES", event.getSource());
		logPass("Event " + event.getId() + " is correct: " + event.getType() + ", " + event.getSource());

		// wait for the order to complete
		boolean orderCompleted = OrderMonitor.waitForOrderToComplete(reference);

		// verify that the order is completed
		assertTrue(orderCompleted);

		// log output to the report
		logPass("Order " + reference + " is now completed");

		// verify that the record in the Account table is correct
		OrderManagementAccount omAccount = OrderManagementDAO.getOrderManagementAccount(order.getId());
		assertEquals("ENTERPRISE_SME", omAccount.getMarketSegment());
		assertEquals("HIGH", omAccount.getCreditClass());
		assertEquals("CORPORATE_OR_EBU", omAccount.getAccountType());
		assertNotNull(omAccount.getBillingAccountId());

		logPass("Account " + omAccount.getBillingAccountId() + " created in order management");

		// read the subscription account details from subs management
		Account subsAccount = SubscriptionManagementDAO.getAccountByBillingAccountID(omAccount.getBillingAccountId());
		assertNotNull(subsAccount);
		assertEquals("INITIAL", subsAccount.getStatus());
		assertEquals("EIR", subsAccount.getBrand());
		assertEquals("B2B", subsAccount.getType());
		assertEquals("POSTAL", subsAccount.getInvoiceDeliveryMethod());
		assertEquals("CORPORATE_OR_EBU", subsAccount.getCustomerType());
		assertEquals(omAccount.getBillingAccountId(), subsAccount.getBillingAccountID());
		logPass("Account " + subsAccount.getBillingAccountID() + " is correct in subscription management: " + subsAccount.getType() + ", "
				+ subsAccount.getCustomerType());

		// read the details of the b2b_account table
		B2BAccount b2bAccount = SubscriptionManagementDAO.getB2BAccountByID(subsAccount.getId());
		assertNotNull(b2bAccount);

		assertEquals(companyName, b2bAccount.getName());
		assertEquals(30, b2bAccount.getPaymentTerm());
		assertEquals("HIGH", b2bAccount.getCreditClass());
		logPass("B2B account " + omAccount.getBillingAccountId() + " created in subs management: " + b2bAccount.getName());

		Company company = SubscriptionManagementDAO.getCompanyByID(b2bAccount.getCompanyId());
		assertNotNull(company);
		assertEquals(companyName, company.getName());
		logPass("Company " + omAccount.getBillingAccountId() + " created in subs management: " + b2bAccount.getName());
	}

	/**
	 * Complete a SIM swap on the B2B CRM UI API
	 */
	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: SIM swap")
	public void testSimSwap(ITestContext iTestContext) {

		// read the most recent replacement order from the database
		Event eventToIgnore = OrderManagementDAO.getMostRecentEventOfType(EventType.REPLACE_PRODUCT_ORDER);

		// determine the SIM details before the test
		SimCard simCard = SubscriptionManagementDAO.getActiveSimCard(msisdn);
		String imsi = InventoryManagementAPI.getSimDetailsFromInventory(simCard.getIccid()).getImsi();
		logInfo("Before test: MSISDN " + msisdn + " has active card " + simCard.getIccid() + ", " + imsi);

		// get a replacement SIM from the inventory
		EquipmentPack pack = InventoryManager.getInventory(InventoryCode._2SIMREPEE);
		assertNotNull(pack);
		logInfo("Service " + serviceID + " will be swapped to use SIM card " + pack.getIccid() + ", " + pack.getImsi());

		// make the call to the API
		APITransaction transaction = B2BCRMAPI.submitSimSwap(token, serviceID, pack.getIccid());
		assertEquals(204, transaction.getResponse().getStatusCode());
		logPass("SIM Swap successfully submitted: " + transaction.toString());

		// wait for the event to get created in the order management EVENT table
		Event thisEvent = OrderManagementMonitor.waitForEventOfTypeAndLaterThanNamedEventID(EventType.REPLACE_PRODUCT_ORDER, eventToIgnore.getId());
		assertNotNull(thisEvent);
		assertEquals("TELESALES", thisEvent.getSource());
		assertEquals("REPLACE_PRODUCT_ORDER", thisEvent.getType());
		logPass("Event " + thisEvent.getId() + " created for this request: " + thisEvent.getType() + ", " + thisEvent.getSource() + ", "
				+ thisEvent.getStatus());

		// read the details from the product order table
		ProductOrder order = OrderManagementDAO.getProductOrderByEventID(thisEvent.getId());
		assertNotNull(order);
		assertEquals("REPLACEMENT", order.getOrder_type());
		assertEquals("B2B", order.getSalesType());
		logPass("Product order created: " + order.getReference() + ", " + order.getOrder_type() + ", " + order.getSalesType());

		// wait for the product order to complete
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(order.getReference());
		assertTrue(orderCompleted);

		// verify that the sim card is successfully swapped in subs management
		simCard = SubscriptionManagementDAO.getActiveSimCard(msisdn);
		assertEquals(pack.getIccid(), simCard.getIccid());
		logPass("SIM successfully swapped to " + simCard.getIccid());
	}

	// -------------------------------------------------------------

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Create & activate subscription")
	public void testCreateSubscription(ITestContext iTestContext) {

		// specify the offer ID
		int catalogOfferID = 3001;

		// read the offer details for the offer ID
		Offer offer = CatalogCoreDAO.getOffer(catalogOfferID);

		// get a tariff code for the offer
		String tariffCode = CatalogCoreDAO.getTariffForOfferCode(offer.getCode()).replace("-24", "-SIM");

		// read the most recent CREATE_SUBSCRIBER_ORDER order from the database
		Event eventToIgnore = OrderManagementDAO.getMostRecentEventOfType(EventType.CREATE_SUBSCRIBER_ORDER);

		String emailAddress = "johnie.hunt@weaveroil.net";

		// make an API request to create the subscription
		APITransaction t = B2BCRMAPI.createSubscription(token, accountID, catalogOfferID, tariffCode, emailAddress);
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());

		// poll the database for the new EVENT record to be created
		Event thisEvent = OrderManagementMonitor.waitForEventOfTypeAndLaterThanNamedEventID(EventType.CREATE_SUBSCRIBER_ORDER, eventToIgnore.getId());
		assertNotNull(thisEvent);
		assertEquals("TELESALES", thisEvent.getSource());
		logPass("Event " + thisEvent.getId() + " created for this request: " + thisEvent.getType() + ", " + thisEvent.getSource() + ", "
				+ thisEvent.getStatus());

		// read the details from the product order table
		ProductOrder order = OrderManagementDAO.getProductOrderByEventID(thisEvent.getId());
		assertNotNull(order);
		assertEquals("CROSS_SELL", order.getOrder_type());
		assertEquals("B2B", order.getSalesType());
		logPass("Product order created: " + order.getReference() + ", " + order.getOrder_type() + ", " + order.getSalesType());

		// complete the order logistics step
		ArrayList<LogisticsDTO> simsUsed = Logistics.processLogisticsBackend(order.getReference());
		logInfo("Order " + order.getReference() + " is fulfilled with sim " + simsUsed.get(0).getIccId() + ", " + simsUsed.get(0).getMsisdn() + ", "
				+ simsUsed.get(0).getImsi());

		// waif for the order to complete
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(order.getReference());
		assertTrue(orderCompleted);
		logPass("Order " + order.getReference() + " is now in state COMPLETED");

		// now get the subscription from subs management
		Subscription subscription = SubscriptionManagementDAO.getSubscriptionByOrderReference(order.getReference());

		// verify that the subscription is correct
		assertEquals("INITIAL", subscription.getStatus());
		assertEquals(catalogOfferID, subscription.getCatalogOfferID());
		assertEquals(tariffCode, subscription.getTariffPlanCode());
		assertNull(subscription.getTerminatedAt());
		logPass("Subscription " + subscription.getId() + " successfully created in INITIAL state");

		// read the service details
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscription.getId());
		assertEquals(service.getDomain(), "MOBILE");
		assertEquals(simsUsed.get(0).getMsisdn(), service.getName());
		logPass("Service " + service.getId() + " successfully created with MSISDN " + service.getName());

		// now activate the subscription
		logInfo("Agent now wishes to activate subscription " + subscription.getId() + ", " + service.getName());

		// use the API to activate the subscription
		t = B2BCRMAPI.activateSubscription(token, subscription.getId());
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());

		// wait for the subscription to go to ACTIVE
		boolean subscriptionActivated = SubscriptionManagementMonitor.waitForSubscriptionToReachStatus(subscription.getId(), "ACTIVE", 120);
		assertTrue(subscriptionActivated);
		logPass("Subscription " + subscription.getId() + " is now ACTIVE");
	}

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Get porting history")
	public void testGetPortingHistory(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getPortingHistory(token, serviceID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Get incomplete ports")
	public void testGetIncompletePorts(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getIncompletePorts(token, serviceID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Complex search by billing account ID, MSISDN and  name")
	public void testComplexSearch(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.search(token, accountName, null, null, null, null, msisdn, billingAccountID, null, null);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json message into a list of objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<B2BSearchResultResponse> searchResults = jsonPathEvaluator.getList("", B2BSearchResultResponse.class);

		logPass(searchResults.size() + " results found");

		B2BSearchResultResponse resultToCheck = null;

		for (B2BSearchResultResponse accountResult : searchResults) {
			if (accountResult.getBillingAccountId() == billingAccountID) {
				resultToCheck = accountResult;
				break;
			}
		}

		// verify that our account has been found
		assertNotNull(resultToCheck);
		logPass("Result found for account " + resultToCheck.getBillingAccountId() + ": " + t.getResponse().getTime() + " ms");

		this.checkSearchResult(accountID, billingAccountID, resultToCheck);
	}

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Get account transactions")
	public void testGetAccountTransactions(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getAccountTransactions(token, billingAccountID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Get adjustments")
	public void testGetAdjustments(ITestContext iTestContext) {
		Adjustment adjustment = AdjustmentDAO.getAdjustmentsForBillingAccountID(billingAccountID).get(0);
		APITransaction t = B2BCRMAPI.getAdjustment(token, adjustment.getReference());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Get ref porting times")
	public void testGetPortingTimes(ITestContext iTestContext) {
		APITransaction t = B2BCRMAPI.getPortingTimes(token);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "B2B CRM UI API: Schedule termination")
	public void testScheduleTermination(ITestContext iTestContext) {

		// get the current timestamp
		String terminationDate = Timestamp.getCurrentTimestamp("yyyy-MM-dd");
		int earlyCeaseCharge = 0;
		String approverName = "Steve Test";

		// select a termination reason
		TerminationReason reason = TerminationReason.COMPETITOR_OFFER;
		String approvedBy = approverName;

		// make the API call and valudate the response code
		APITransaction t = B2BCRMAPI.scheduleTermination(token, serviceID, reason, approvedBy, earlyCeaseCharge, terminationDate);
		assertEquals(t.getResponse().statusCode(), 204);
		logPass(t.toString());

		// verify that the termination has gone to the SERVICE table in order management
		OmService orderService = OrderManagementDAO.getOrderService(msisdn, ProvisioningAction.TERMINATE_SUBSCRIBER);
		assertNotNull(orderService);
		assertEquals(orderService.getProvisioningAction(), "TERMINATE_SUBSCRIBER");
		assertEquals(orderService.getOrderServiceStatus(), "SCHEDULED");

		// verify that the termination has gone to the TERMINATION_REQUEST table in
		// order management
		TerminationRequest request = OrderManagementDAO.getTerminationRequest(orderService.getId());
		assertNotNull(request);
		assertEquals(request.getReasonCode(), reason.toString());
		assertEquals(request.getApprovedBy(), approverName);

		// verify that the event has been correctly created in the EVENT table
		Event event = OrderManagementDAO.getEvent(orderService.getEventID());
		assertNotNull(event);
		assertEquals(event.getType(), "SERVICE_FULFILLMENT_TERMINATE_SERVICE");
		assertEquals(event.getStatus(), "IN_PROGRESS");
		assertEquals(event.getSource(), "CUSTOMER_OFFER");
	}

	@Test(dependsOnMethods = "testScheduleTermination", description = "B2B CRM UI API: Get pending termination")
	public void testGetTermination(ITestContext iTestContext) {

		// make the API call and valudate the response code
		APITransaction t = B2BCRMAPI.getPendingTerminations(token, serviceID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		// convert the json response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetPendingTerminationRequestDTO terminationRequestFromAPI = (GetPendingTerminationRequestDTO) jsonPathEvaluator.getObject("",
				GetPendingTerminationRequestDTO.class);

		// verify that the response is correct
		assertTrue(terminationRequestFromAPI.isHasPendingTerminationRequest());
		assertNotNull(terminationRequestFromAPI.getScheduledDate());
	}

	@Test(dependsOnMethods = "testGetTermination", description = "B2B CRM UI API: Cancel pending termination")
	public void testCancelTermination(ITestContext iTestContext) {

		// make the API call and valudate the response code
		APITransaction t = B2BCRMAPI.cancelTerminationRequest(token, serviceID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());

		// convert the json response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetPendingTerminationRequestDTO terminationRequestFromAPI = (GetPendingTerminationRequestDTO) jsonPathEvaluator.getObject("",
				GetPendingTerminationRequestDTO.class);

		// verify that the response is correct
		assertFalse(terminationRequestFromAPI.isHasPendingTerminationRequest());

		// verify that the pending OM service is cancelled in the database
		OmService orderService = OrderManagementDAO.getOrderService(msisdn, ProvisioningAction.TERMINATE_SUBSCRIBER);
		assertNotNull(orderService);
		assertEquals(orderService.getProvisioningAction(), "TERMINATE_SUBSCRIBER");
		assertEquals(orderService.getOrderServiceStatus(), "CANCELLED");
	}

	public void checkSearchResult(int accountID, int billingAccountID, B2BSearchResultResponse resultToCheck) {

		// read the expected acount details from the database
		Account accountFromDatabase = SubscriptionManagementDAO.getAccountByID(accountID);
		B2BAccount b2bAccountFromDatabase = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		String currentMarketSegment = SubscriptionManagementDAO.getMarketSegment(accountID);

		// verify the account details returned
		assertEquals(billingAccountID, resultToCheck.getBillingAccountId());
		assertEquals(b2bAccountFromDatabase.getName(), resultToCheck.getCustomerAccountName());
		assertEquals(accountID, resultToCheck.getAccountId());
		assertEquals(accountFromDatabase.getStatus(), resultToCheck.getAccountStatus());
		assertEquals(accountFromDatabase.getCustomerType(), resultToCheck.getAccountType());
		logPass("Correct account details account ID returned: " + billingAccountID + ", " + resultToCheck.getCustomerAccountName() + ", "
				+ resultToCheck.getAccountId() + ", " + resultToCheck.getAccountStatus() + ", " + resultToCheck.getAccountType());

		// verify the address returned
		String ownerUuid = SubscriptionManagementDAO.getContactOfType(accountID, 1);
		assertNotNull(ownerUuid);
		Address ownerBillingAddress = ContactManagementDAO.getAddressOfType(ownerUuid, AddressType.BILLING);
		assertEquals(ownerBillingAddress.getAddressLine1(), resultToCheck.getAddressLine1());
		assertEquals(ownerBillingAddress.getAddressLine2(), resultToCheck.getAddressLine2());
		assertEquals(ownerBillingAddress.getAddressLine3(), resultToCheck.getAddressLine3());
		logPass("Correct address returned: " + resultToCheck.getAddressLine1());

		// verify the market segment
		assertEquals(currentMarketSegment, resultToCheck.getMarketSegment());
		logPass("Correct market segment returned: " + resultToCheck.getMarketSegment());

		// verify the group ICID
		ArrayList<B2BAccountAttribute> accountAttributes = SubscriptionManagementDAO.getb2bAccountAttributes(accountID);
		for (B2BAccountAttribute attribute : accountAttributes) {
			if (attribute.getAttributeKey().equals("GROUP_ICID")) {
				assertEquals(attribute.getAttributeValue(), resultToCheck.getGroupIccid());
				logPass("Correct GROUP_ICID returned: " + resultToCheck.getGroupIccid());
				break;
			}
		}
	}

	@BeforeClass
	public void setUp() {
		billingAccountID = TestDataManager.getB2BCorporateAccount();
		billingAccountID = 70124903;
		Account account = SubscriptionManagementDAO.getAccountByBillingAccountID(billingAccountID);
		accountID = account.getId();
		b2bAccount = SubscriptionManagementDAO.getB2BAccountByID(accountID);
		accountName = b2bAccount.getName();
		subscriptionID = SubscriptionManagementDAO.getActiveSubscriptions(billingAccountID).get(0).getId();
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptionID);
		msisdn = service.getName();
		System.err.println("Service selected for this test suite is " + msisdn);
		serviceID = service.getId();
		b2bAccount.getHardwareBalanceId();
	}

	@DataProvider(name = "accountTypes")
	public Object[][] getAccountTypes() {
		Object[][] data = new Object[2][1];
		data[0] = new Object[] { B2BAccountType.CORPORATE_OR_EBU };
		data[1] = new Object[] { B2BAccountType.GOVERNMENT };
		return data;
	}

	@DataProvider(name = "getB2BOffers")
	public Object[][] getB2BOffers() {
		ArrayList<TariffPlan> offers = CatalogCoreDAO.getB2BCorporateOffers();

		Object[][] data = new Object[offers.size()][1];
		for (int i = 0; i < offers.size(); i++) {
			data[i] = new Object[] { offers.get(i) };
		}

		return data;
	}

	@DataProvider(name = "getCorporateOffers")
	public Object[][] getCorporateOffers() {
		ArrayList<TariffPlan> offers = CatalogCoreDAO.getB2BCorporateOffers();

		Object[][] data = new Object[offers.size()][1];
		for (int i = 0; i < offers.size(); i++) {
			data[i] = new Object[] { offers.get(i) };
		}

		return data;
	}

	@DataProvider(name = "getGovernmentOffers")
	public Object[][] getGovernmentOffers() {
		ArrayList<TariffPlan> offers = CatalogCoreDAO.getB2BGovernmentOffers();

		Object[][] data = new Object[offers.size()][1];
		for (int i = 0; i < offers.size(); i++) {
			data[i] = new Object[] { offers.get(i) };
		}

		return data;
	}

	@AfterClass
	public void tearDown() {

	}
}

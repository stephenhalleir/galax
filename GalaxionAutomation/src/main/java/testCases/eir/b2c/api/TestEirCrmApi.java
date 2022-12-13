package testCases.eir.b2c.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.ec20.monitor.EC20Monitor;
import external_systems.mobile_network.nodes.ec20.profile.EC20Profile;
import external_systems.mobile_network.nodes.hlr_fe.profile.HLRFEProfile;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestData;
import framework.test_data.galaxion.TestDataManager;
import framework.test_data.generic.RandomStringGenerator;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentType;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.ServiceDetail;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_contact_management_backend.data_model.Address;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.data_model.PhoneNumber;
import microservices.backend.eir_contact_management_backend.enums.AddressType;
import microservices.backend.eir_credit_score_backend.CreditScore;
import microservices.backend.eir_credit_score_backend.CreditScoreDAO;
import microservices.backend.eir_inventory_management_backend.api.InventoryManagementAPI;
import microservices.backend.eir_inventory_management_backend.objects.SimDetails;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_msisdn_swap_backend.dao.MsisdnSwapDAO;
import microservices.backend.eir_msisdn_swap_backend.data_model.MsisdnSwapRequest;
import microservices.backend.eir_msisdn_swap_backend.monitor.MsisdnSwapMonitor;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Event;
import microservices.backend.eir_order_management_backend.data_model.OmService;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_provisioning_facade_backend.dao.ProvisioningFacadeDAO;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfRequest;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfService;
import microservices.backend.eir_provisioning_facade_backend.enums.ProvisioningAction;
import microservices.backend.eir_salt_ar_backend.dao.AccountsReceivableDAO;
import microservices.backend.eir_salt_ar_backend.data_model.Balance;
import microservices.backend.eir_security_question_backend.dao.SecurityQuestionDAO;
import microservices.backend.eir_security_question_backend.data_model.Answer;
import microservices.backend.eir_security_question_backend.data_model.RefQuestion;
import microservices.backend.eir_security_question_backend.enums.SecurityQuestionCode;
import microservices.backend.eir_sim_swap_backend.dao.SimSwapDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementMonitor;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.AccountContact;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.backend.eir_subscription_management_backend.enums.AccountType;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.frontend.common_ui.enums.CustomerType;
import microservices.frontend.eir_crm_ui_frontend.api.PAYGCRMAPI;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.update_bill_delivery_type.BillDeliveryType;
import microservices.frontend.eir_crm_ui_frontend.dto.response.customer_search.EirB2CSearchResultDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_account.GetAccountResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_account_balance.GetAccountBalanceResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_barrings.Barring;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_contact.GetContactResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_contacts.GetAccountContactResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_credit_score_response.GetCreditScoreResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_ref_security_questions.GetRefSecurityQuestionsResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_security_question.GetSecurityQuestionResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_subscriptions.GetSubscriptionDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.response.get_voucher.GetVoucherResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.msisdn_swap_response.MsisdnSwapResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.post_adjustment_response.PostAdjustmentResponse;
import microservices.frontend.eir_crm_ui_frontend.dto.response.post_change_offer_response.PostChangeOfferResponse;
import utilities.galaxion.test_data.vouchers.Voucher;
import utilities.galaxion.test_data.vouchers.VoucherManager;
import utilities.generic.api.APITransaction;
import utilities.generic.mailhog.MailHog;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class TestEirCrmApi extends BaseTest {

	private Map<CustomerType, TestData> testDataMap;
	private Map<CustomerType, String> tokens;

	/*------------------------------------------------------------------
	 * Authentication
	 ------------------------------------------------------------------- */
	@Test(description = "Eir CRM UI API: Authenticate")
	public void testEirLogin() {
		String token = PAYGCRMAPI.getEirRetailToken();
		assertNotNull(token);
		logPass("Token generated: " + token.substring(1, 200) + "...");
		tokens.put(CustomerType.EIR_PREPAY, token);
		tokens.put(CustomerType.EIR_POSTPAY, token);
	}

	@Test(description = "GoMo CSR UI API: Authenticate")
	public void testGoMoLogin() {
		String token = PAYGCRMAPI.getGoMoToken();
		assertNotNull(token);
		logPass("Token generated: " + token.substring(1, 200) + "...");
		tokens.put(CustomerType.GOMO, token);
	}

	/*------------------------------------------------------------------
	 * Account: Search engine
	 ------------------------------------------------------------------- */

	/**
	 * Test customer search functionality
	 * 
	 * Ref: CRM_A_SE_01
	 * 
	 */
	@Test(description = "CRM_A_SE_01: CRM UI: Search by MSISDN", dataProvider = "all_data_types")
	public void CRM_A_SE_01_testSearchByMsisdn(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the search call to the API
		APITransaction t = PAYGCRMAPI.search(testData.getBrand(), token, null, null, null, null, testData.getMsisdn(), null, null);
		logPass(t.toString() + "\nResponse time: " + t.getResponse().getTime() + "ms");

		// verify that a 200 response has been returned
		assertEquals(t.getResponse().statusCode(), 200, "Response code");

		// convert the response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<EirB2CSearchResultDTO> searchResults = jsonPathEvaluator.getList("content", EirB2CSearchResultDTO.class);

		// verify that only 1 response was returned
		assertEquals(searchResults.size(), 1);

		EirB2CSearchResultDTO result = searchResults.get(0);
		logPass(searchResults.size() + " results found");

		// read the contact details from the database
		Contact contact = ContactManagementDAO.getContact(testData.getContactUuid());

		// verify that the search result information returned is correct
		assertEquals(contact.getFirstName(), result.getOwner().getFirstName());
		assertEquals(contact.getLastName(), result.getOwner().getLastName());
		assertEquals(contact.getUuid(), result.getOwner().getUuid());
		assertEquals(testData.getBillingAccountId(), result.getBillingAccountId());
		assertEquals(contact.getEmailAddress(), result.getOwner().getMainEmail());

		List<Subscription> subscriptions = SubscriptionManagementDAO.getSubscriptionsForBillingAccountID(testData.getBillingAccountId());
		assertEquals(subscriptions.size(), result.getSubscriptions().size());

		logPass("Correct account retrieved: " + testData.getBillingAccountId() + ", " + result.getOwner().getFirstName() + " " + result.getOwner().getLastName()
				+ ", " + result.getOwner().getMainEmail());
	}

	/**
	 * Test customer search functionality
	 * 
	 * Ref: CRM_A_SE_01
	 * 
	 */
	@Test(description = "CRM_A_SE_01: CRM UI: Search by billing account ID", dataProvider = "all_data_types")
	public void CRM_A_SE_01_testSearchByBillingAccountID(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.search(testData.getBrand(), token, null, null, null, null, null, testData.getBillingAccountId(), null);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// convert the response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<EirB2CSearchResultDTO> searchResults = jsonPathEvaluator.getList("content", EirB2CSearchResultDTO.class);

		// verify that only 1 response was returned
		assertEquals(searchResults.size(), 1);

		EirB2CSearchResultDTO result = searchResults.get(0);
		logPass(searchResults.size() + " results found");

		// read the contact details from the database
		Contact contact = ContactManagementDAO.getContact(testData.getContactUuid());

		// verify that the search result information returned is correct
		assertEquals(contact.getFirstName(), result.getOwner().getFirstName());
		assertEquals(contact.getLastName(), result.getOwner().getLastName());
		assertEquals(contact.getUuid(), result.getOwner().getUuid());
		assertEquals(testData.getBillingAccountId(), result.getBillingAccountId());
		assertEquals(contact.getEmailAddress(), result.getOwner().getMainEmail());

		List<Subscription> subscriptions = SubscriptionManagementDAO.getSubscriptionsForBillingAccountID(testData.getBillingAccountId());
		assertEquals(subscriptions.size(), result.getSubscriptions().size());

		logPass("Correct account retrieved: " + testData.getBillingAccountId() + ", " + result.getOwner().getFirstName() + " " + result.getOwner().getLastName()
				+ ", " + result.getOwner().getMainEmail());
	}

	/*------------------------------------------------------------------
	 * Account: View Account screen
	 ------------------------------------------------------------------- */
	/**
	 * Get keycloak account and available channels
	 * 
	 * Ref: CRM_A_AC_01
	 */
	@Test(description = "CRM_A_AC_01: CRM UI: Get keycloak account and channels", dataProvider = "all_data_types")
	public void CRM_A_AC_01_testGetKeycloakAccount(CustomerType customerType, ITestContext iTestContext) {
		String token = getToken(customerType);
		APITransaction t = PAYGCRMAPI.getKeycloakAccount(token);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get account details
	 * 
	 * Ref: CRM_A_AC_02
	 */
	@Test(description = "CRM_A_AC_02: CRM UI: Get account details", dataProvider = "all_data_types")
	public void CRM_A_AC_02_testGetAccount(CustomerType customerType, ITestContext iTestContext) {

		// get the test data and token information
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the call to the API to retrieve the account
		APITransaction t = PAYGCRMAPI.getAccount(token, testData.getAccountId());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");

		// read the expected account details from the database
		Account accountFromDB = SubscriptionManagementDAO.getAccountByBillingAccountID(testData.getBillingAccountId());

		// convert the response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetAccountResponse response = (GetAccountResponse) jsonPathEvaluator.getObject("", GetAccountResponse.class);

		// verify the details
		assertEquals(accountFromDB.getId(), response.getId());
		assertEquals(accountFromDB.getBillingAccountID(), response.getBillingAccountId());
		assertEquals(accountFromDB.getBrand(), response.getBrand());
		assertEquals(accountFromDB.getStatus(), response.getStatus());
		assertEquals(accountFromDB.getOrderReference(), response.getOrderReference());
		assertEquals(accountFromDB.getType(), response.getType());
		assertEquals(accountFromDB.getCustomerType(), response.getCustomerType());
		assertEquals(accountFromDB.getInvoiceDeliveryMethod(), response.getInvoiceDeliveryMethod());

		// log the output to the report
		logPass("Account details retrieved correctly: " + response.getBillingAccountId() + ", " + response.getStatus());
	}

	/**
	 * Get contacts
	 * 
	 * Note that this will being back information from
	 * subscription-management.account_contact
	 * 
	 * Ref: CRM_A_AC_03
	 */
	@Test(description = "CRM_A_AC_03: Eir CRM: Get contacts", dataProvider = "all_data_types")
	public void CRM_A_AC_03_testGetContacts(CustomerType customerType, ITestContext iTestContext) {

		// get the test data and token information
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the call to the API to retrieve the contacts
		APITransaction t = PAYGCRMAPI.getContacts(token, testData.getAccountId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// convert the json response into an array of response objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetAccountContactResponse> contactsFromAPI = jsonPathEvaluator.getList("", GetAccountContactResponse.class);

		// read the expected contacts from the database
		ArrayList<AccountContact> contactsFromDB = SubscriptionManagementDAO.getAccountContactsEnhanced(testData.getAccountId());
		assertEquals(contactsFromAPI.size(), contactsFromDB.size());

		// verify the information is correct
		for (int i = 0; i < contactsFromAPI.size(); i++) {
			assertEquals(contactsFromAPI.get(i).getId(), contactsFromDB.get(i).getId());
			assertEquals(contactsFromAPI.get(i).getUuid(), contactsFromDB.get(i).getUuid());
			assertEquals(contactsFromAPI.get(i).getType(), contactsFromDB.get(i).getName());
			assertEquals(contactsFromAPI.get(i).getTypeDescription(), contactsFromDB.get(i).getDescription());
			logPass("Contact successfully retrieved: " + contactsFromAPI.get(i).getUuid() + ", " + contactsFromAPI.get(i).getTypeDescription());
		}
	}

	/**
	 * Get subscriptions
	 * 
	 * Ref: CRM_A_AC_04
	 */
	@Test(description = "CRM_A_AC_04: CRM UI: Get subscriptions", dataProvider = "all_data_types")
	public void CRM_A_AC_04_testGetSubscriptions(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call to retrieve the subscriptions
		APITransaction t = PAYGCRMAPI.getSubscriptions(token, testData.getAccountId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// convert the json response into an array of response objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetSubscriptionDTO> subscriptionsFromAPI = jsonPathEvaluator.getList("", GetSubscriptionDTO.class);

		// get the list of subscriptions from the database
		ArrayList<Subscription> subscriptionsFromDatabase = SubscriptionManagementDAO.getSubscriptionsForBillingAccountID(testData.getBillingAccountId());

		// verify that the correct number of subscriptions were returned
		assertEquals(subscriptionsFromAPI.size(), subscriptionsFromDatabase.size());
		logPass(subscriptionsFromAPI + " subscriptions received from the API as expected");

		// verify the details of each subscription
		for (int i = 0; i < subscriptionsFromAPI.size(); i++) {
			assertEquals(subscriptionsFromAPI.get(0).getId(), subscriptionsFromDatabase.get(0).getId());
			assertEquals(subscriptionsFromAPI.get(0).getCatalogOfferCode(), subscriptionsFromDatabase.get(0).getCatalogOfferCode());
			assertEquals(subscriptionsFromAPI.get(0).getOrderReference(), subscriptionsFromDatabase.get(0).getOrderReference());
			assertEquals(subscriptionsFromAPI.get(0).getStatus(), subscriptionsFromDatabase.get(0).getStatus());
			assertEquals(subscriptionsFromAPI.get(0).getName(), subscriptionsFromDatabase.get(0).getName());

			// log output to the report
			logPass("Subscription correctly returned: " + subscriptionsFromAPI.get(0).getId() + ", " + subscriptionsFromAPI.get(0).getCatalogOfferCode() + ", "
					+ subscriptionsFromAPI.get(0).getStatus());
		}
	}

	/**
	 * Get contact
	 *
	 * Ref:CRM_A_AC_05
	 */
	@Test(description = "CRM_A_AC_05: CRM UI: Get contact", dataProvider = "all_data_types")
	public void CRM_A_AC_05_testGetContact(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the call to the API to get the contact information
		APITransaction t = PAYGCRMAPI.getContact(token, testData.getContactUuid());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");

		// retrieve the contact object from the database
		Contact contactFromDatabase = ContactManagementDAO.getContact(testData.getContactUuid());

		// convert the json response into an array of response objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetContactResponse response = jsonPathEvaluator.getObject("", GetContactResponse.class);

		// verify that the correct information is returned
		assertEquals(contactFromDatabase.getUuid(), response.getUuid());
		assertEquals(contactFromDatabase.getFirstName(), response.getFirstName());
		assertEquals(contactFromDatabase.getLastName(), response.getLastName());
		assertEquals(contactFromDatabase.getEmailAddress(), response.getEmails().get(0).getEmail());
		assertEquals(contactFromDatabase.getUuid(), response.getUuid());
		assertEquals(contactFromDatabase.getBillingAddress().getAddressLine1(), response.getAddress("BILLING").getAddressLine1());
		assertEquals(contactFromDatabase.getDeliveryAddress().getAddressLine1(), response.getAddress("DELIVERY").getAddressLine1());

		// log output to the report
		logPass("Contact correctly retrieved: " + response.getUuid() + ", " + response.getFirstName() + " " + response.getLastName() + ", "
				+ contactFromDatabase.getEmailAddress() + ", " + contactFromDatabase.getBillingAddress().getAddressLine1());
	}

	/**
	 * Get account balance
	 * 
	 * Ref: CRM_A_AC_06
	 */
	@Test(description = "CRM_A_AC_06: CRM UI: Get account balance", dataProvider = "all_data_types")
	public void CRM_A_AC_06_testGetAccountBalance(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make a call to the API to retrieve the account balance
		APITransaction t = PAYGCRMAPI.getAccountBalance(token, testData.getBillingAccountId());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");

		// retrieve the balance record from the database
		Balance balanceFromDatabase = AccountsReceivableDAO.getBalance(testData.getBillingAccountId());

		// convert the json response into an array of response objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetAccountBalanceResponse response = jsonPathEvaluator.getObject("", GetAccountBalanceResponse.class);

		// verify that the response is correct
		assertEquals(balanceFromDatabase.getBillingAccountID(), response.getBillingAccountId());
		assertEquals(balanceFromDatabase.getDelinquentEndAmount(), response.getDelinquentEndAmount());
		assertEquals(balanceFromDatabase.getDelinquentStartAmount(), response.getDelinquentStartAmount());
		assertEquals(balanceFromDatabase.getNonOverdueAmount(), response.getNonOverdueAmount());
		assertEquals(balanceFromDatabase.getOverdueAmount(), response.getOverdueAmount());

		// log output to the report
		logPass("Balance correctly retrieved: " + response.getBillingAccountId() + ", " + response.getOverdueAmount() + ", " + response.getNonOverdueAmount());
	}

	/**
	 * Get services
	 * 
	 * Ref: CRM_A_AC_07
	 */
	@Test(description = "CRM_A_AC_07: CRM UI: Get services", dataProvider = "all_data_types")
	public void CRM_A_AC_07_testGetServices(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getServices(token, testData.getAccountId());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Get web notifications
	 * 
	 * Ref: CRM_A_AC_08
	 */
	@Test(description = "CRM_A_AC_08: CRM UI: PAYG: Get web notifications", dataProvider = "all_data_types")
	public void CRM_A_AC_08_testGetWebNotifications(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getWebNotifications(token, testData.getContactUuid());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account: Profile Tab
	 ------------------------------------------------------------------- */

	/**
	 * Get contact permissions
	 * 
	 * Ref: CRM_A_PR_01
	 */
	@Test(description = "CRM_A_PR_01: CRM UI: Get contact permissions", dataProvider = "all_data_types")
	public void CRM_A_PR_01_testGetContactPermissions(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getContactPermissions(token, testData.getContactUuid());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get security answer
	 * 
	 * Ref: CRM_A_PR_02
	 */
	@Test(description = "CRM_A_PR_02: CRM UI: Get security question", dataProvider = "all_data_types")
	public void CRM_A_PR_02_testGetSecurityAnswer(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// read the contact's security question details from the database
		Answer answer = SecurityQuestionDAO.getAnswer(testData.getContactUuid());

		// make a call to the API to get the security question
		APITransaction t = PAYGCRMAPI.getSecurityAnswer(token, testData.getContactUuid());

		// if there is no security answer on the account, expect a 404 response
		if (answer == null) {
			assertEquals(404, t.getResponse().statusCode());
			assertTrue(t.getResponse().asString().contains("ANSWER_FOR_UUID_NOT_FOUND"));
			logPass(t.toString());
			logPass("Expected response code 404 received due to no security question");
		}
		// else if a security answer exists in the database for the customer, verify the
		// details
		else {
			assertEquals(t.getResponse().statusCode(), 200, "Response code");
			logPass(t.toString());
			RefQuestion question = SecurityQuestionDAO.getQuestion(answer.getRefQuestionID());

			// convert the json response to an object
			JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
			GetSecurityQuestionResponse response = jsonPathEvaluator.getObject("", GetSecurityQuestionResponse.class);

			// verify that the details received from the API match the database
			assertEquals(answer.getResponse(), response.getResponse());
			assertEquals(question.getCode(), response.getCode());
			assertEquals(question.getQuestion(), response.getQuestion());
			logPass("Security question correctly received: " + question.getCode() + ", " + question.getQuestion() + ", " + answer.getResponse());
		}

	}

	/**
	 * Get contact permissions v1
	 * 
	 * Ref: CRM_A_PR_03
	 */
	@Test(description = "CRM_A_PR_03: CRM UI: Get contact permissions v1", dataProvider = "all_data_types")
	public void CRM_A_PR_03_testGetContactPermissionsV1(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);
		
		// make the API call
		APITransaction t = PAYGCRMAPI.getContactPermissionsV1(token, testData.getContactUuid());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get ref security questions
	 *
	 * Ref: CRM_A_PR_04
	 */
	@Test(description = "CRM_A_PR_04: CRM UI: Get ref security questions", dataProvider = "all_data_types")
	public void CRM_A_PR_04_testGetRefSecurityQuestions(CustomerType customerType, ITestContext iTestContext) {

		// get the token
		String token = this.getToken(customerType);

		// make a call to the API to retrieve the ref data
		APITransaction t = PAYGCRMAPI.getRefSecurityQuestions(token);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// convert the response to a list of question objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetRefSecurityQuestionsResponse> response = jsonPathEvaluator.getList("questions", GetRefSecurityQuestionsResponse.class);

		// verify that 2 questions have been found
		assertEquals(2, response.size());

		logPass(response.size() + " questions have been found");
	}

	/**
	 * Update security question
	 *
	 * Ref: CRM_A_PR_05
	 */
	@Test(description = "CRM_A_PR_05: CRM UI: Update security question")
	public void CRM_A_PR_05_testUpdateSecurityQuestion(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_PREPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make a call to the API to retrieve the ref data
		APITransaction t = PAYGCRMAPI.updateSecurityQuestion(token, testData.getContactUuid(), SecurityQuestionCode.MEMORABLE_WORD,
				"Random word " + System.currentTimeMillis());
		assertEquals(t.getResponse().statusCode(), 204, "Response code");
		logPass(t.toString());

	}

	/**
	 * Eircode lookup
	 *
	 * Ref: CRM_A_PR_06
	 */
	@Test(description = "CRM_A_PR_06: CRM UI: Eircode lookup")
	public void CRM_A_PR_06_testEircodeLookup(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_PREPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.eircodeLookup(token, "C15EF6E");
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Update billing address
	 *
	 * Ref: CRM_A_PR_07
	 */
	@Test(description = "CRM_A_PR_07: CRM UI: Update billing address", dataProvider = "all_data_types")
	public void CRM_A_PR_07_testUpdateBillingAddress(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// retrieve the billing address from the database before the test
		Address billingAddressFromDatabase = ContactManagementDAO.getBillingAddress(testData.getContactUuid());
		logInfo("Customer's billing address before test is " + billingAddressFromDatabase.getAddressLine1() + ", " + billingAddressFromDatabase.getEircode());

		// get a random address for the update
		AddressFinderAddress randomAddress = RandomStringGenerator.getRandomAddressFromFile();
		logInfo("Customer will update the billing address to " + randomAddress.getEircode() + ": " + randomAddress.getAddressLine1());

		// make the API call to update the billing address
		APITransaction t = PAYGCRMAPI.updateAddress(token, testData.getContactUuid(), randomAddress.getAddressLine1(), randomAddress.getAddressLine2(),
				randomAddress.getAddressLine3(), randomAddress.getTown(), randomAddress.getCounty(), randomAddress.getEircode(), AddressType.BILLING);
		assertEquals(t.getResponse().statusCode(), 204, "Response code");
		logPass(t.toString());

		// retrieve the updated illing address from the database
		billingAddressFromDatabase = ContactManagementDAO.getBillingAddress(testData.getContactUuid());

		assertEquals(billingAddressFromDatabase.getAddressLine1(), randomAddress.getAddressLine1());
		assertEquals(billingAddressFromDatabase.getEircode(), randomAddress.getEircode());
		logPass("Billing address successfully updated to " + billingAddressFromDatabase.getAddressLine1() + ", " + billingAddressFromDatabase.getEircode());
	}

	/**
	 * Update contact details
	 *
	 * Ref: CRM_A_PR_08
	 */
	@Test(description = "CRM_A_PR_08: CRM UI: Update contact details", dataProvider = "all_data_types")
	public void CRM_A_PR_08_testUpdateContactDetails(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// retrieve the contact details from the database before the test
		Contact contact = ContactManagementDAO.getContact(testData.getContactUuid());
		logInfo("Contact details before test: " + contact.getFirstName() + " " + contact.getLastName() + ", " + contact.getBirthDate());

		// get random details for the update
		String firstName = RandomStringGenerator.getRandomFirstName();
		String lastName = RandomStringGenerator.getRandomLastName();
		String dob = RandomStringGenerator.getRandomDOBString();
		String newDob = dob.substring(4) + "-" + dob.substring(2, 4) + "-" + dob.substring(0, 2);

		// make the call to the API
		APITransaction t = PAYGCRMAPI.updateContact(token, testData.getContactUuid(), firstName, lastName, newDob);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 204, "Response code");

		// read the updated contact details from the database
		contact = ContactManagementDAO.getContact(testData.getContactUuid());

		// verify the details in the database
		assertEquals(contact.getFirstName(), firstName);
		assertEquals(contact.getLastName(), lastName);
		assertEquals(contact.getBirthDate("yyyy-MM-dd"), newDob);
		logPass("Contact details successfully updated: " + contact.getFirstName() + " " + contact.getLastName() + ", " + contact.getBirthDate());
	}

	/**
	 * Update email address
	 *
	 * Ref: CRM_A_PR_10
	 */
	@Test(description = "CRM_A_PR_10: CRM UI: Update email address", dataProvider = "all_data_types")
	public void CRM_A_PR_10_testUpdateEmailAddress(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// get the email address before the test
		String emailAddress = ContactManagementDAO.getEmailAddressForUUID(testData.getContactUuid());
		logInfo("Email address before test: " + emailAddress);

		// generate a new email address
		String newEmailAddress = "new_" + System.currentTimeMillis() + "@eir.ie";

		// make the API call to update the email address
		APITransaction t = PAYGCRMAPI.updateEmailAddress(token, testData.getContactUuid(), newEmailAddress, testData.getBrand());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 204, "Response code");

		// read the email from mailhog
		String emailContent = MailHog.getEmailBodyText(newEmailAddress, "Confirm your new email address", "");

		// verify that an email has been found
		assertNotNull(emailContent);
		logPass("Email received to " + newEmailAddress);

		// read the list of lines in the content string
		String[] lines = emailContent.split("\n");
		String deeplink = null;

		// for each line in the email, look for the change_email URL
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("/public/change_email/")) {
				String urlFullLine = lines[i];
				deeplink = urlFullLine.substring(urlFullLine.indexOf("http"));
				deeplink = deeplink.substring(0, deeplink.indexOf(" "));
				break;
			}
		}

		// verify that a deep link has been found
		assertNotNull(deeplink);
		logPass("Deep link received in the email: " + deeplink);

		// open a browser and visit the URL
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/res/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		driver.get(deeplink);
		WaitUtil.waitForSeconds(3);
		driver.close();
		logPass("Customer has visited the deep link " + deeplink + " in the browser");

		// read the new email address from the database
		emailAddress = ContactManagementDAO.getEmailAddressForUUID(testData.getContactUuid());
		assertEquals(emailAddress, newEmailAddress);
		logPass("Email address has been successfully updated to " + emailAddress);
	}

	/**
	 * Add authorized user
	 *
	 * Ref: CRM_A_PR_12
	 */
	@Test(description = "CRM_A_PR_12: CRM UI: Add authorized user", dataProvider = "all_data_types")
	public void CRM_A_PR_12_testAddAuthorizedUser(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		ArrayList<AccountContact> contactsBeforeTest = SubscriptionManagementDAO.getAllAccountContacts(testData.getBillingAccountId());

		// generate a random name for the new contact
		String firstName = RandomStringGenerator.getRandomFirstName();
		String lastName = RandomStringGenerator.getRandomLastName();

		// trigger the API call
		APITransaction t = PAYGCRMAPI.addAuthorizedUser(token, testData.getAccountId(), firstName, lastName);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 201, "Response code");

		ArrayList<AccountContact> contactsAfterTest = SubscriptionManagementDAO.getAllAccountContacts(testData.getBillingAccountId());
		assertEquals(contactsAfterTest.size() - contactsBeforeTest.size(), 1);

		AccountContact newContact = contactsAfterTest.get(contactsAfterTest.size() - 1);

		// verify that the new contact has type ID 3 (AUTHORIZED_USER)
		assertEquals(newContact.getTypeId(), 3);

		// verify the contact in contact management
		Contact c = ContactManagementDAO.getContactOnly(newContact.getUuid());
		assertEquals(c.getFirstName(), firstName);
		assertEquals(c.getLastName(), lastName);
	}

	/**
	 * Update phone number
	 *
	 * Ref: CRM_A_PR_13
	 */
	@Test(description = "CRM_A_PR_13: CRM UI: Update contact number", dataProvider = "all_data_types")
	public void CRM_A_PR_13_testUpdateContactNumber(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// generate a random phone number
		String newNumber = RandomStringGenerator.getRandomMobilePhoneNumber();

		// read the list of phone numbers on the customer
		ArrayList<PhoneNumber> numbers = ContactManagementDAO.getPhoneNumbers(testData.getContactUuid());

		// select the first one in the list to modify
		int phoneNumberId = numbers.get(0).getId();

		// trigger the API call
		APITransaction t = PAYGCRMAPI.updatePhoneNumber(token, phoneNumberId, newNumber, "MOBILE");
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 204, "Response code");

		// read the list of phone numbers again
		numbers = ContactManagementDAO.getPhoneNumbers(testData.getContactUuid());

		// check that the phone number has been updated successfully
		assertEquals(numbers.get(0).getPhoneNumber(), newNumber);
	}

	/**
	 * Add phone number
	 *
	 * Ref: CRM_A_PR_14
	 */
	@Test(description = "CRM_A_PR_14: CRM UI: Add contact number", dataProvider = "all_data_types")
	public void CRM_A_PR_14_testAddContactNumber(CustomerType customerType, ITestContext iTestContext) {

		// generate a random phone number
		String phoneNumber = RandomStringGenerator.getRandomMobilePhoneNumber();

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// read the list of phone numbers on the contact before the test
		ArrayList<PhoneNumber> numbersBefore = ContactManagementDAO.getPhoneNumbers(testData.getContactUuid());

		// trigger the API call
		APITransaction t = PAYGCRMAPI.addPhoneNumber(token, testData.getContactUuid(), phoneNumber, "MOBILE");
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 201, "Response code");

		// read the list of phone numbers again
		ArrayList<PhoneNumber> numbersAfter = ContactManagementDAO.getPhoneNumbers(testData.getContactUuid());

		assertEquals(numbersAfter.size() - numbersBefore.size(), 1);
		logPass("Number of contact numbers on the account has been increased by 1 to " + numbersAfter.size());

		// determine the last phone number added in the database
		PhoneNumber lastNumberAdded = (numbersAfter.get(numbersAfter.size() - 1));

		// check that the phone number has been updated successfully
		assertEquals(lastNumberAdded.getPhoneNumber(), phoneNumber);
	}

	/**
	 * Delete phone number
	 *
	 * Ref: CRM_A_PR_15
	 */
	@Test(description = "CRM_A_PR_15: CRM UI: Delete contact number", dataProvider = "all_data_types")
	public void CRM_A_PR_15_testDeleteContactNumber(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// read the list of phone numbers before the test
		ArrayList<PhoneNumber> numbersBefore = ContactManagementDAO.getPhoneNumbers(testData.getContactUuid());
		logInfo("Number of contact numbers on the account before the test: " + numbersBefore.size());

		PhoneNumber lastNumberAdded = (numbersBefore.get(numbersBefore.size() - 1));

		// trigger the API call
		APITransaction t = PAYGCRMAPI.deletePhoneNumber(token, lastNumberAdded.getId());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 204, "Response code");

		// read the list of phone numbers again
		ArrayList<PhoneNumber> numbersAfter = ContactManagementDAO.getPhoneNumbers(testData.getContactUuid());

		assertEquals(numbersBefore.size() - numbersAfter.size(), 1);
		logPass("Number of contact numbers on the account has been reduced by 1 to " + numbersAfter.size());

		// verify that the deleted number is no longer found in the database
		for (PhoneNumber number : numbersAfter) {
			assertNotEquals(number.getId(), lastNumberAdded.getId());
		}
	}

	/**
	 * Edit authorized user
	 *
	 * Ref: CRM_A_PR_16
	 */
	@Test(description = "CRM_A_PR_16: CRM UI: Edit authorized user", dataProvider = "all_data_types")
	public void CRM_A_PR_16_testEditAuthorizedUser(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		ArrayList<AccountContact> contactsBeforeTest = SubscriptionManagementDAO.getActiveAccountContacts(testData.getBillingAccountId());

		AccountContact contactToEdit = null;

		// determine an authorized user to edit
		for (AccountContact ac : contactsBeforeTest) {
			if (ac.getTypeId() == 3) {
				contactToEdit = ac;
				break;
			}
		}

		assertNotNull(contactToEdit);

		// generate a random name for the new contact
		String firstName = RandomStringGenerator.getRandomFirstName();
		String lastName = RandomStringGenerator.getRandomLastName();

		// trigger the API call
		APITransaction t = PAYGCRMAPI.editAuthorizedUser(token, contactToEdit.getUuid(), firstName, lastName);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 204, "Response code");

		ArrayList<AccountContact> contactsAfterTest = SubscriptionManagementDAO.getActiveAccountContacts(testData.getBillingAccountId());
		assertEquals(contactsAfterTest.size(), contactsBeforeTest.size());

		// verify the contact in contact management
		Contact c = ContactManagementDAO.getContactOnly(contactToEdit.getUuid());
		assertEquals(c.getFirstName(), firstName);
		assertEquals(c.getLastName(), lastName);
	}

	/**
	 * Delete authorized user
	 *
	 * Ref: CRM_A_PR_17
	 */
	@Test(description = "CRM_A_PR_17: CRM UI: Delete authorized user", dataProvider = "all_data_types")
	public void CRM_A_PR_17_testDeleteAuthorizedUser(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// read the list of active contacts on the account before the test
		ArrayList<AccountContact> contactsBeforeTest = SubscriptionManagementDAO.getActiveAccountContacts(testData.getBillingAccountId());

		AccountContact contactToDelete = null;

		// determine an authorized user to edit
		for (AccountContact ac : contactsBeforeTest) {
			if (ac.getTypeId() == 3) {
				contactToDelete = ac;
				break;
			}
		}

		// verify that the contact has been identified and that ended_at is null
		assertNotNull(contactToDelete);
		assertNull(contactToDelete.getEndedAt());

		// trigger the API call
		APITransaction t = PAYGCRMAPI.deleteAuthorizedUser(token, testData.getAccountId(), contactToDelete.getUuid());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// verify that there is one less active contact on the account after the test
		ArrayList<AccountContact> contactsAfterTest = SubscriptionManagementDAO.getActiveAccountContacts(testData.getBillingAccountId());
		assertEquals(contactsBeforeTest.size() - contactsAfterTest.size(), 1);
		logPass("There are now " + contactsAfterTest.size() + " active contacts on the account");

		// refresh the deleted contact record from the database
		contactToDelete = SubscriptionManagementDAO.getAccountContact(contactToDelete.getId());
		assertNotNull(contactToDelete.getEndedAt());
		logPass("Contact " + contactToDelete.getId() + " now has ended_at = " + contactToDelete.getEndedAt());
	}

	//

	/*------------------------------------------------------------------
	 * Account: Transactions Tab
	 ------------------------------------------------------------------- */

	/**
	 * Get postpay adjustment reason codes
	 * 
	 * Ref: CRM_A_TR_01
	 */
	@Test(description = "CRM_A_TR_01: CRM UI: Get ref postpay adjustment reasons")
	public void CRM_A_TR_01_testGetRefPostpayAdjustmentReasons(ITestContext iTestContext) {

		String token = this.getToken(CustomerType.EIR_POSTPAY);

		APITransaction t = PAYGCRMAPI.getRefPostpayAdjustmentReasons(token);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Get transaction history
	 * 
	 * Ref: CRM_A_TR_02
	 */
	@Test(description = "CRM_A_TR_02: CRM UI: Get transaction history", dataProvider = "all_data_types")
	public void CRM_A_TR_02_testGetTransactionHistory(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call to retrieve the transaction history
		APITransaction t = PAYGCRMAPI.getTransactionHistory(token, testData.getBillingAccountId());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Process account level adjustment
	 * 
	 * Ref: CRM_A_TR_03
	 */
	@Test(description = "CRM_A_TR_03: CRM UI: Process account level adjustment", dataProvider = "all_data_types")
	public void CRM_A_TR_03_testProcessAccountLevelAdjustment(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.processAccountLevelAdjustment(token, testData.getBillingAccountId(), 10, AdjustmentReason.GOODWILL,
				"test adjustment " + System.currentTimeMillis());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");

		// convert the response to an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		PostAdjustmentResponse response = jsonPathEvaluator.getObject("", PostAdjustmentResponse.class);
		logPass("Adjustment " + response.getAdjustmentReference() + " is created");
	}

	/*------------------------------------------------------------------
	 * Account: Collections Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get collections status
	 * 
	 * Ref: CRM_A_CO_01
	 */
	@Test(description = "CRM_A_CO_01: CRM UI: Get collection status", dataProvider = "all_data_types")
	public void CRM_A_CO_01_testGetCollectionsStatus(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getCollectionsStatus(token, testData.getBillingAccountId());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/*------------------------------------------------------------------
	 * Account: Payments & Billing Tab
	 ------------------------------------------------------------------- */

	/**
	 * Get payment methods
	 * 
	 * Ref: CRM_A_PM_01
	 */
	@Test(description = "CRM_A_PM_01: CRM UI: Get payment methods", dataProvider = "all_data_types")
	public void CRM_A_PM_01_testGetPaymentMethods(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call to get the payment methods
		APITransaction t = PAYGCRMAPI.getPaymentMethods(token, testData.getBillingAccountId());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Get credit score
	 * 
	 * Ref: CRM_A_PM_02
	 */
	@Test(description = "CRM_A_PM_02: CRM UI: Get credit score")
	public void CRM_A_PM_02_testGetCreditScore(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// query the database to read expected values
		CreditScore score = CreditScoreDAO.getCreditScore(testData.getContactUuid());
		assertNotNull(score);
		logInfo("Credit score in the database: " + score.getCreditScore() + ", " + score.getOccupation() + ", " + score.getResidentialStatus());

		// call the API
		APITransaction t = PAYGCRMAPI.getCreditScores(token, testData.getContactUuid());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");

		// convert the response to an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetCreditScoreResponse response = jsonPathEvaluator.getObject("", GetCreditScoreResponse.class);

		// validate the response
		assertEquals(score.getCreditScore(), response.getCreditScore());
		assertEquals(score.getOccupation(), response.getOccupation());
		assertEquals(score.getResidentialStatus(), response.getResidentialStatus());

		// log output to the report
		logPass("Correct information is returned from the API: " + response.getCreditScore() + ", " + response.getOccupation() + ", "
				+ response.getResidentialStatus());
	}

	/**
	 * Update bill delivery type
	 * 
	 * Ref: CRM_A_PM_03
	 */
	@Test(description = "CRM_A_PM_03: CRM UI: Update bill delivery type", dataProvider = "all_data_types")
	public void CRM_A_PM_03_testUpdateBillDeliveryType(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		Account acc = SubscriptionManagementDAO.getAccountByID(testData.getAccountId());
		logInfo("Invoice delivery method before test: " + acc.getInvoiceDeliveryMethod());

		// execute the test twice - once for POSTAL to ONLINE and once for ONLINE to
		// POSTAL
		for (int i = 0; i < 2; i++) {
			BillDeliveryType newDeliveryType = BillDeliveryType.POSTAL;

			if (acc.getInvoiceDeliveryMethod().equals("POSTAL")) {
				newDeliveryType = BillDeliveryType.ONLINE;
			}

			// make the API call to update the bill delivery type
			APITransaction t = PAYGCRMAPI.updateBillDeliveryMethod(token, testData.getAccountId(), newDeliveryType);
			logInfo("Setting the bill delivery type to " + newDeliveryType + ":\n" + t.toString());
			assertEquals(t.getResponse().statusCode(), 204, "Response code");

			// read the latest bill delivery type from the database
			acc = SubscriptionManagementDAO.getAccountByID(testData.getAccountId());
			assertEquals(acc.getInvoiceDeliveryMethod(), newDeliveryType.toString());
			logPass("Invoice delivery method after test: " + acc.getInvoiceDeliveryMethod());
		}
	}

	/*------------------------------------------------------------------
	 * Account: Customer History Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get customer history
	 * 
	 * Ref: CRM_A_CH_01
	 */
	@Test(description = "CRM_A_CH_01: CRM UI: Get customer history", dataProvider = "eir_data_types")
	public void CRM_A_CH_01_testGetCustomerHistory(CustomerType customerType, ITestContext iTestContext) {
		
		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getCustomerHistory(token, testData.getAccountId(), testData.getContactUuid());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Get filtered customer history
	 * 
	 * Ref: CRM_A_CH_02
	 */
	@Test(description = "CRM_A_CH_02: CRM UI: Get filtered customer history")
	public void CRM_A_CH_02_testGetFilteredCustomerHistory(ITestContext iTestContext) {

		// retrieve the relevant test data
		CustomerType customerType = CustomerType.EIR_POSTPAY;
		
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getFilteredCustomerHistory(token, testData.getAccountId(), testData.getContactUuid());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/*------------------------------------------------------------------
	 * Account: Documents
	 ------------------------------------------------------------------- */
	/**
	 * Get ref document names
	 * 
	 * Ref: CRM_A_DM_01
	 */
	@Test(description = "CRM_A_DM_01: Get ref document names")
	public void CRM_A_DM_01_testGetDocumentNames(ITestContext iTestContext) {

		String token = this.getToken(CustomerType.EIR_PREPAY);

		APITransaction t = PAYGCRMAPI.getDocumentNames(token);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Get documents
	 * 
	 * Ref: CRM_A_DM_02
	 */
	@Test(description = "CRM_A_DM_02: CRM UI: Get Documents")
	public void CRM_A_DM_02_testGetDocuments(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_PREPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getDocuments(token, testData.getAccountId());
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/*------------------------------------------------------------------
	 * Account: Orders Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get orders
	 * 
	 * Ref: CRM_A_OR_01
	 */
	@Test(description = "CRM_A_OR_01: CRM UI: Get order history", dataProvider = "all_data_types")
	public void CRM_A_OR_01_testGetOrderHistory(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getOrderHistory(token, testData.getAccountId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get order
	 * 
	 * Ref: CRM_A_OR_02
	 */
	@Test(description = "CRM_A_OR_02: CRM UI: Get order details", dataProvider = "all_data_types")
	public void CRM_A_OR_02_testGetOrder(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// read the order reference for the customer's original acquisition order
		String orderReference = OrderManagementDAO.getOrdersForBillingAccountID(testData.getBillingAccountId()).get(0).getReference();

		// make an API call to retrieve the order
		APITransaction t = PAYGCRMAPI.getOrderDetails(token, orderReference);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Service: Plan Detail tab
	 ------------------------------------------------------------------- */
	/**
	 * Get subscription
	 * 
	 * Ref: CRM_S_PD_01
	 */
	@Test(description = "CRM_S_PD_01: CRM UI: Get subscription", dataProvider = "all_data_types")
	public void CRM_S_PD_01_testGetSubscription(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getSubscription(token, testData.getSubscriptionId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get service offer
	 * 
	 * Ref: CRM_S_PD_02
	 */
	@Test(description = "CRM_S_PD_02: CRM UI: Get service offer", dataProvider = "all_data_types")
	public void CRM_S_PD_02_testGetServiceOffer(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getOffer(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get active subscription addons
	 * 
	 * Ref: CRM_S_PD_03
	 */
	@Test(description = "CRM_S_PD_03: CRM UI: Get active addons", dataProvider = "all_data_types")
	public void CRM_S_PD_03_testGetActiveAddons(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getActiveAddons(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get service balance
	 * 
	 * Ref: CRM_S_PD_04
	 */
	@Test(description = "CRM_S_PD_04:CRM UI: Get PAYG service balance")
	public void CRM_S_PD_04_testGetBalance(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_PREPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getBalance(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Update NDD setting
	 * 
	 * Ref: CRM_S_PD_05
	 */
	@Test(description = "CRM_S_PD_05: CRM UI: Update NDD setting", dataProvider = "all_data_types")
	public void CRM_S_PD_05_testUpdateNDDSetting(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// populate a list of all possible NDD settings
		ArrayList<NDDSetting> nddValues = new ArrayList<NDDSetting>();
		nddValues.add(NDDSetting.LISTED);
		nddValues.add(NDDSetting.UNLISTED);
		nddValues.add(NDDSetting.EXDIRECTORY);

		// determine the current NDD setting on the service
		NDDSetting currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(testData.getMsisdn());
		logInfo("NDD setting for " + testData.getMsisdn() + " before test: " + currentNDDSetting);

		// move the current NDD setting to the end of the list (we will switch to it
		// last)!
		nddValues.remove(currentNDDSetting);
		nddValues.add(currentNDDSetting);

		// for each possible NDD setting
		for (NDDSetting setting : nddValues) {
			logInfo("Changing NDD setting to " + setting.toString());

			// use the API to change the setting
			APITransaction t = PAYGCRMAPI.updateNddSetting(token, testData.getServiceId(), setting);

			// verify that the API call has been successful
			logPass(t.toString());
			assertEquals(t.getResponse().statusCode(), 204, "Response code");

			// read the current setting from the database
			currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(testData.getServiceId());

			// verify that the response received is correct
			assertEquals(setting.toString(), currentNDDSetting.toString());
			logPass("Correct NDD setting returned for " + testData.getMsisdn() + ": " + currentNDDSetting.toString());
		}
	}

	/*------------------------------------------------------------------
	 * Service: Prepay Balance tab
	 ------------------------------------------------------------------- */
	/**
	 * Get prepay adjustment reasons
	 * 
	 * Ref: CRM_S_BA_01
	 */
	@Test(description = "CRM_S_BA_01: CRM UI: Get ref prepay adjustment reasons")
	public void CRM_S_BA_01_testGetRefPrepayAdjustmentReasons(ITestContext iTestContext) {

		String token = this.getToken(CustomerType.EIR_PREPAY);

		APITransaction t = PAYGCRMAPI.getRefPrepayAdjustmentReasons(token);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Make a service level balance adjustment
	 * 
	 * Ref: CRM_S_BA_02
	 */
	@Test(description = "CRM_S_BA_02: CRM UI: Make service level balance adjustment")
	public void CRM_S_BA_02_testMakePrepayBalanceAdjustment(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_PREPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.makeServiceBalanceAdjustment(token, testData.getServiceId(), AdjustmentType.PREPAY_CREDIT,
				AdjustmentReason.CUST_CARE_CREDIT_SAVE, 10, "test adjustment" + System.currentTimeMillis());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/*------------------------------------------------------------------
	 * Service: Usage tab
	 ------------------------------------------------------------------- */
	/**
	 * Get ref usage subtotal types
	 * 
	 * Ref: CRM_S_US_01
	 */
	@Test(description = "CRM_S_US_01: CRM UI: Get ref rating subtotal types")
	public void CRM_S_US_01_testGetRefRatingSubtotalTypes(ITestContext iTestContext) {
		String token = getToken(CustomerType.EIR_PREPAY);

		// make the API call
		APITransaction t = PAYGCRMAPI.getRefRatingSubtotalTypes(token);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get usage
	 * 
	 * Ref: CRM_S_US_02
	 */
	@Test(description = "CRM_S_US_02: CRM UI: Get usage", dataProvider = "all_data_types")
	public void CRM_S_US_02_testGetUsage(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getUsage(token, testData.getServiceId(), "202108");
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/**
	 * Get usage summary
	 * 
	 * Ref: CRM_S_US_03
	 */
	@Test(description = "CRM_S_US_03: CRM UI: Get usage summary", dataProvider = "all_data_types")
	public void CRM_S_US_03_testGetUsageSummary(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// determine the service type - PREPAY/POSTPAY from CDR repo
		ServiceDetail serviceDetail = CDRRepoDAO.getServiceDetail(testData.getMsisdn());
		assertNotNull(serviceDetail, "service_detail");
		String period = Timestamp.getCurrentTimestamp("yyyyMM");

		APITransaction t = PAYGCRMAPI.getUsageSummary(token, testData.getServiceId(), serviceDetail.getServiceType(), period);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
	}

	/*------------------------------------------------------------------
	 * Service: Top Up
	 ------------------------------------------------------------------- */

	/**
	 * Check a prepay voucher number
	 * 
	 * Ref: CRM_S_VO_01
	 * 
	 */
	@Test(description = "CRM_S_VO_01: CRM UI: Get voucher status")
	public void CRM_S_VO_01_testGetVoucherStatus(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_PREPAY;

		// retrieve the relevant test data
		String token = getToken(customerType);

		// define the voucher number
		String serialNumber = "648494-0001-0002";

		// make a call to the API to get the voucher status
		APITransaction t = PAYGCRMAPI.getVoucherStatus(token, serialNumber);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// convert the response to an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetVoucherResponse response = jsonPathEvaluator.getObject("", GetVoucherResponse.class);

		// verify that the details are correct
		assertEquals(response.getVoucherSerialNumber(), serialNumber);
		assertEquals(response.getStatus(), "ACTIVE");
		assertEquals(response.getSubscriberMSISDN(), null);

		logPass("Voucher " + serialNumber + " has been successfully retrieved and is in a state of " + response.getStatus());
	}

	/**
	 * Process a topup
	 * 
	 * Ref: CRM_S_VO_02
	 * 
	 */
	@Test(dependsOnMethods = { "CRM_S_VO_01_testGetVoucherStatus" }, description = "CRM_S_VO_02: CRM UI: Process topup voucher")
	public void CRM_S_VO_02_testProcessTopup(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_PREPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// get a voucher from the inventory sheet
		Voucher voucher = VoucherManager.getVoucher(20);
		assertNotNull(voucher);

		// define the voucher values in variables
		String serialNumber = voucher.getVoucherNumber();
		String activationCode = voucher.getActivationCode();

		// read in the IN balance before the test
		String balanceString = MMWUtility.getEC20CreditBalanceStr(testData.getMsisdn());
		double mainBalanceBefore = Double.parseDouble(balanceString);
		logInfo("Main balance before test is " + mainBalanceBefore);

		// make a call to the API to process the topup
		APITransaction t = PAYGCRMAPI.makeTopupRequest(token, testData.getServiceId(), serialNumber, activationCode);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");

		// check the voucher status
		t = PAYGCRMAPI.getVoucherStatus(token, serialNumber);

		// convert the response into a response object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetVoucherResponse response = jsonPathEvaluator.getObject("", GetVoucherResponse.class);

		// verify that the details are correct
		assertEquals(response.getVoucherSerialNumber(), serialNumber);
		assertEquals(response.getStatus(), "USED");
		assertEquals(response.getSubscriberMSISDN(), testData.getMsisdn().substring(1));
		logPass("Voucher " + serialNumber + " has been successfully retrieved and is in a state of " + response.getStatus() + ", "
				+ response.getSubscriberMSISDN());

		// wait for the IN balance to change from the original credit amount
		double mainBalanceAfter = EC20Monitor.waitForCreditChange(testData.getMsisdn(), mainBalanceBefore);

		// verify that the balance has been updated by the correct amount
		assertEquals(mainBalanceAfter, mainBalanceBefore, voucher.getAmount());

		// log output to the report
		logPass("Balance increased by " + voucher.getAmount() + " to " + mainBalanceAfter);
	}

	/*------------------------------------------------------------------
	 * Service: Manage Offer tab
	 ------------------------------------------------------------------- */

	/**
	 * Get available addons
	 * 
	 * Ref: CRM_S_MO_01
	 */
	@Test(description = "CRM_S_MO_01: CRM UI: Get available addons", dataProvider = "all_data_types")
	public void CRM_S_MO_01_testAvailableAddons(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getAvailableAddons(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Service: Barring tab
	 ------------------------------------------------------------------- */
	/**
	 * Get barrings
	 * 
	 * Ref: CRM_S_BR_01
	 */
	@Test(description = "CRM_S_BR_01: CRM UI: Get barring details", dataProvider = "all_data_types")
	public void CRM_S_BR_01_testGetBarrings(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getBarrings(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// get the data from the "barrings" node and convert it to a list of Barring
		// objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<Barring> barrings = jsonPathEvaluator.getList("barrings", Barring.class);
	}

	/*------------------------------------------------------------------
	 * Service: SIM Replacement tab
	 ------------------------------------------------------------------- */

	/**
	 * Get pending SIM card requests
	 * 
	 * Ref: CRM_S_SR_02
	 */
	@Test(description = "CRM_S_SR_02: CRM UI: Get pending SIM replacements", dataProvider = "all_data_types")
	public void CRM_S_SR_02_testGetPendingSimReplacements(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getPendingSimReplacementRequests(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get SIM card charges
	 * 
	 * Ref: CRM_S_SR_03
	 */
	@Test(description = "CRM_S_SR_03: Eir CRM - PAYG: Get SIM swap eligibility", dataProvider = "all_data_types")
	public void CRM_S_SR_03_testGetSimSwapEligibility(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getSimReplacementCharges(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Place a replacement SIM card order
	 * 
	 * Ref: CRM_S_SR_04
	 */
	@Test(description = "CRM_S_SR_04: CRM UI: Order replacement SIM", dataProvider = "all_data_types")
	public void CRM_S_SR_04_testOrderReplacementSIM(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// "remove" and pending sim swaps on the service
		SimSwapDAO.markSIMSwapsAsDone(testData.getServiceId());

		// read the list of sim card addons before the test
		ArrayList<SimCard> addonSimCardsBefore = SubscriptionManagementDAO.getSIMCardAddonsForService(testData.getServiceId());

		// get the details of the active sim card before the test
		SimCard activeCard = SubscriptionManagementDAO.getActiveSimCard(testData.getMsisdn());
		logInfo("The active card on the service before the test is " + activeCard.getIccid() + ", " + activeCard.getImsi());

		// retrieve the most recent order-management.service record before the test
		OmService serviceToIgnore = OrderManagementDAO.getMostRecentOrderService(testData.getServiceId());
		logInfo("The most recent order management.SERVICE record on this sub is ID " + serviceToIgnore.getId() + ". We will ignore this one.");

		// make the API call to place the replacement SIM order
		APITransaction t = PAYGCRMAPI.orderReplacementSIM(token, testData.getServiceId());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 201, "Response code");

		// wait for and return a new record in the order management SERVICE table
		OmService newOrderManagementService = OrderManagementMonitor.waitForOrderServiceLaterThanNamedServiceID(testData.getServiceId(),
				serviceToIgnore.getId());
		logPass("Order management SERVICE " + newOrderManagementService.getId() + " successfully created");

		// read the details from the order management PRODUCT_ORDER table
		ProductOrder order = OrderManagementDAO.getOrderByID(newOrderManagementService.getOrderID());
		assertEquals("REPLACEMENT", order.getOrder_type());
		assertEquals("B2C", order.getSalesType());
		logPass("Product order " + order.getId() + " successfully created: " + order.getSalesType() + ", " + order.getOrder_type());

		// read the details from the order management EVENT table
		Event event = OrderManagementDAO.getEvent(order.getEventId());
		assertEquals("REPLACE_PRODUCT_ORDER", event.getType());
		assertEquals("IN_PROGRESS", event.getStatus());
		assertEquals("COMMON", event.getSource());
		logPass("Event " + event.getId() + " successfully created: " + event.getType() + ", " + event.getSource() + ", " + event.getStatus());

		// wait for the order to progress through to the DELIVERY step
		boolean orderInDeliveryStep = OrderManagementMonitor.waitForOrderToReachStep(order.getReference(), "DELIVERY");
		assertTrue(orderInDeliveryStep);
		logPass("Order " + order.getReference() + " is now awaiting delivery");

		// complete logistics steps
		ArrayList<LogisticsDTO> sims = Logistics.processLogisticsBackend(order.getReference());
		logInfo("Order " + order.getReference() + " has been fulfilled by SIM " + sims.get(0).getIccId() + ", " + sims.get(0).getImsi());

		// wait for the order to complete in order management
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(order.getReference());
		assertTrue(orderCompleted);
		logPass("Order " + order.getReference() + " is now completed");

		// read the list of sim card addons linked to the service
		ArrayList<SimCard> addonSimCardsAfter = SubscriptionManagementDAO.getSIMCardAddonsForService(testData.getServiceId());
		assertEquals(1, addonSimCardsAfter.size() - addonSimCardsBefore.size());
		logPass("1 new addon added to the addon_sim_card table for this service ID");

		// verify that the SIM cards in the SIM card table are correctly set/activated
		// after the test
		for (SimCard simCard : addonSimCardsAfter) {
			if (simCard.getIccid().equals(sims.get(0).getIccId())) {
				assertNull(simCard.getActivatedAt());
				assertNull(simCard.getTerminatedAt());
			} else if (simCard.getIccid().equals(activeCard.getIccid())) {
				assertNotNull(simCard.getActivatedAt());
				assertNull(simCard.getTerminatedAt());
			} else {
				assertNotNull(simCard.getTerminatedAt());
			}

			logPass("Sim card addon " + simCard.getIccid() + " has activation date of " + simCard.getActivatedAt() + " and termination date of "
					+ simCard.getTerminatedAt());
		}
	}

	/**
	 * Activate a replacement SIM using the PUK
	 * 
	 * Ref: CRM_S_SR_05
	 */
	@Test(dependsOnMethods = {
			"CRM_S_SR_04_testOrderReplacementSIM" }, description = "CRM_S_SR_05: CRM UI: Activate replacement SIM", dataProvider = "all_data_types")
	public void CRM_S_SR_05_activateSimSwap(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		SimCard activeCard = SubscriptionManagementDAO.getActiveSimCard(testData.getMsisdn());
		logInfo("The details card on the service before the test is " + activeCard.getIccid() + ", " + activeCard.getImsi());

		// get the list of addon sim cards on the service
		ArrayList<SimCard> addonSimCards = SubscriptionManagementDAO.getSIMCardAddonsForService(testData.getServiceId());

		String iccid = null;

		// determine the ICCID for the card waiting to be swapped
		for (SimCard simCard : addonSimCards) {
			if (simCard.getActivatedAt() == null && simCard.getTerminatedAt() == null) {
				iccid = simCard.getIccid();
				logPass("INITIAL sim card " + iccid + " found");
				break;
			}
		}

		// verify that a suitable ICCID has been found
		assertNotNull(iccid);

		// read the PUK from the inventory management API
		SimDetails simDetails = InventoryManagementAPI.getSimDetailsFromInventory(iccid);
		String puk = simDetails.getPuk1();
		assertNotNull(puk);
		logInfo("The subscriber will activate SIM " + iccid + "/" + simDetails.getImsi() + " with puk " + puk);

		// make the activation request via the API
		APITransaction t = PAYGCRMAPI.activateReplacementSIM(token, testData.getServiceId(), puk);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 202, "Response code");

		// wait for the sim card to become active (i.e. activated_at is not null)
		boolean simCardActivated = SubscriptionManagementMonitor.waitForSimCardToActivate(iccid);
		assertTrue(simCardActivated);
		logPass("Sim card " + iccid + " is now in a state of Active");

		// verify that the card specified is now the active sim card for the subscriber
		activeCard = SubscriptionManagementDAO.getActiveSimCard(testData.getMsisdn());
		assertEquals(activeCard.getIccid(), iccid);
		logPass("The active card on the service is now " + activeCard.getIccid() + ", " + activeCard.getImsi());

		// verify the changes on the SPR
		SPRProfile spr = MMWUtility.getSprProfile(testData.getMsisdn());
		assertEquals(simDetails.getImsi(), spr.getImsi());
		logPass("IMSI successfuly changed on the SPR: " + spr.getImsi());

		// verify the changes on the HLR
		HLRFEProfile hlr = MMWUtility.getHLRFEProfile(testData.getMsisdn());
		assertEquals(simDetails.getImsi(), hlr.getImsi());
		logPass("IMSI successfuly changed on the HLR-FE: " + hlr.getImsi());
	}

	/*------------------------------------------------------------------
	 * Service: MSISDN Swap tab
	 ------------------------------------------------------------------- */
	/**
	 * Place a random MSISDN swap
	 * 
	 * Ref: CRM_S_MS_01
	 */
	@Test(description = "CRM_S_MS_01: CRM UI: Place a random MSISDN Swap", dataProvider = "eir_data_types")
	public void CRM_S_MS_01_testMsisdnSwap(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		ArrayList<MsisdnSwapRequest> existingMsisdnSwaps = MsisdnSwapDAO.getMsisdnSwaps(testData.getServiceId());
		if (existingMsisdnSwaps.size() >= 3) {

		}

		// determine the service and subscription information
		Service subsService = SubscriptionManagementDAO.getService(testData.getServiceId());
		Subscription subscription = SubscriptionManagementDAO.getSubscriptionByID(subsService.getSubscriptionID());
		logInfo("Placing a MSISDN swap for " + subsService.getName() + ", service ID " + testData.getServiceId());

		String originalMSISDN = subsService.getName();

		// make the API call to swap the MSISDN
		APITransaction t = PAYGCRMAPI.requestRandomMsisdnSwap(token, testData.getServiceId());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(), 201, "Response code");

		// convert the response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		MsisdnSwapResponse response = (MsisdnSwapResponse) jsonPathEvaluator.getObject("", MsisdnSwapResponse.class);
		logInfo("MSISDN Swap placed. New MSISDN = " + response.getNewMsisdn());

		// get the MSISDN swap request
		MsisdnSwapRequest request = MsisdnSwapDAO.getMsisdnSwap(testData.getServiceId(), response.getNewMsisdn());
		assertNotNull(request);
		logPass("msisdn-swap-backend.request " + request.getId() + ", " + request.getStatus());

		boolean msisdnSwapCompleted = MsisdnSwapMonitor.waitForMsisdnSwapToComplete(request.getId(), request.getNewMsisdn());
		assertTrue(msisdnSwapCompleted);

		// get the order management service
		OmService service = OrderManagementDAO.getOrderServiceByRequestIDAndProvisioningAction(request.getId(), ProvisioningAction.SWAP_MSISDN);
		assertNotNull(service);
		assertEquals(service.getOrderServiceStatus(), "COMPLETED");
		logPass("order-management-backend.service " + service.getId() + ", " + service.getOrderServiceStatus());

		// get the order management event
		Event event = OrderManagementDAO.getEvent(service.getEventID());
		assertNotNull(event);
		assertEquals(event.getStatus(), "COMPLETED");
		logPass("order-management-backend.event " + event.getId() + ", " + event.getStatus());

		// read the provisioning facade request
		ArrayList<PfRequest> pfRequests = ProvisioningFacadeDAO.getRequestsBySubIdAndAction(subscription.getId(), ProvisioningAction.SWAP_MSISDN);
		PfRequest pfRequest = pfRequests.get(0);
		assertNotNull(pfRequest);
		assertEquals(pfRequest.getRequestStatus(), "COMPLETED");
		logPass("provisioning-facade-backend.request " + pfRequest.getId() + ", " + pfRequest.getRequestStatus());

		// read the provisioning facade requests
		ArrayList<PfService> pfServices = ProvisioningFacadeDAO.getServicesByRequestID(pfRequest.getId());
		PfService pfService = pfServices.get(0);
		assertNotNull(pfService);
		assertEquals(pfService.getServiceStatus(), "COMPLETED");
		logPass("provisioning-facade-backend.service " + pfService.getId() + ", " + pfService.getServiceStatus());

		// check subs-management.service
		subsService = SubscriptionManagementDAO.getService(testData.getServiceId());
		assertEquals(subsService.getName(), response.getNewMsisdn());
		logPass("subscription-management-backend.service.name = " + subsService.getName());

		// check HLR
		HLRFEProfile hlr = MMWUtility.getHLRFEProfile(response.getNewMsisdn());
		assertNotNull(hlr);
		logPass("HLR profile updated - " + hlr.getMsisdn() + ", " + hlr.getImsi());

		// check EC20
		EC20Profile ec20 = MMWUtility.getEC20Profile(response.getNewMsisdn());
		assertNotNull(ec20);
		logPass("EC20 profile updated - " + ec20.getMsisdn());

		// check SPR
		SPRProfile spr = MMWUtility.getSprProfile(response.getNewMsisdn());
		assertNotNull(spr);
		logPass("SPR profile updated - " + spr.getMsisdn() + ", " + spr.getImsi());

		// check that CDR repo has been updated as expected
		ServiceDetail newServiceDetail = CDRRepoDAO.getServiceDetail(response.getNewMsisdn());
		assertNotNull(newServiceDetail);
		assertEquals(newServiceDetail.getServiceID(), subsService.getId());
		assertEquals(newServiceDetail.getSubscriptionID(), subscription.getId());
		assertEquals(newServiceDetail.getServiceName(), response.getNewMsisdn());
		assertNull(newServiceDetail.getEffectiveEndDateTime());
		logPass("cdr-repository-backend.service_detail.service_name = " + newServiceDetail.getServiceName());

		// verify that the original service detail has had an end date placed
		ServiceDetail originalServiceDetail = CDRRepoDAO.getServiceDetail(originalMSISDN);
		assertNotNull(originalServiceDetail);
		assertEquals(originalServiceDetail.getServiceID(), subsService.getId());
		assertEquals(originalServiceDetail.getSubscriptionID(), subscription.getId());
		assertEquals(originalServiceDetail.getServiceName(), response.getNewMsisdn());
		assertNotNull(originalServiceDetail.getEffectiveEndDateTime());
		logPass("cdr-repository-backend.service_detail.effective_end_date_time = " + originalServiceDetail.getServiceName() + ", "
				+ originalServiceDetail.getServiceName());
	}

	/*------------------------------------------------------------------
	 * Service: Terminations tab
	 ------------------------------------------------------------------- */
	/**
	 * Get pending termination requests
	 * 
	 * Ref: CRM_S_TR_01
	 */
	@Test(description = "CRM_S_TR_01: CRM UI: Get termination requests", dataProvider = "all_data_types")
	public void CRM_S_TR_01_testGetTerminationRequests(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// make the API call
		APITransaction t = PAYGCRMAPI.getTerminationRequests(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get termination reasons
	 * 
	 * Ref: CRM_S_TR_02
	 */
	@Test(description = "CRM_S_TR_02: CRM UI: Get termination reasons", dataProvider = "all_data_types")
	public void CRM_S_TR_02_testGetRefTerminationReasons(CustomerType customerType, ITestContext iTestContext) {
		String token = getToken(customerType);
		APITransaction t = PAYGCRMAPI.getTerminationReasons(token, AccountType.B2C);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Service: Change Offer tab
	 ------------------------------------------------------------------- */

	/**
	 * Check change offer eligibility
	 * 
	 * Ref: CRM_S_CO_01
	 */
	@Test(description = "CRM_S_CO_01: CRM UI: Get change offer eligibility")
	public void CRM_S_CO_01_testGetChangeOfferEligibility(ITestContext iTestContext) {
		CustomerType customerType = CustomerType.EIR_POSTPAY;
		
		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getChangeOfferEligibility(token, testData.getSubscriptionId());
		assertEquals(t.getResponse().statusCode(), 204, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get early cease charges
	 * 
	 * Ref: CRM_S_CO_02
	 */
	@Test(description = "CRM_S_CO_02: CRM UI: Get change offer early cease charges")
	public void CRM_S_CO_02_testGetChangeOfferEarlyCeaseCharges(ITestContext iTestContext) {
		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);
		
		APITransaction t = PAYGCRMAPI.getChangeOfferEarlyCeaseCharges(token, testData.getSubscriptionId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get change offer ref waiving reasons
	 * 
	 * Ref: CRM_S_CO_03
	 */
	@Test(description = "CRM_S_CO_03: CRM UI: Get change offer ref waiving reasons")
	public void CRM_S_CO_03_testGetChangeOfferRefWaivingReasons(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;
		String token = this.getToken(customerType);
		
		APITransaction t = PAYGCRMAPI.getChangeOfferRefWaivingReasons(token);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Post change offer
	 * 
	 * Ref: CRM_S_CO_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "Eir CRM - PAYG: Post a change offer request")
	public void testPostChangeOffer(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		// CRM_S_CO_04: post the change offer order
		APITransaction t = PAYGCRMAPI.postChangeOffer(token, true, testData.getSubscriptionId(), "CSS");
		assertEquals(t.getResponse().statusCode(), 201, "Response code");
		logPass(t.toString());
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		PostChangeOfferResponse response = (PostChangeOfferResponse) jsonPathEvaluator.getObject("", PostChangeOfferResponse.class);
		System.out.println(response.getUuid());

		// CRM_S_CO_05: get the change offer order
		t = PAYGCRMAPI.getChangeOfferOrder(token, response.getUuid());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// CRM_S_CO_07: get change offer available offers
		t = PAYGCRMAPI.getChangeOfferOffers(token, response.getUuid());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());

		// CRM_S_CO_08: get tariff plans for offer
		t = PAYGCRMAPI.getChangeOfferTariffPlanForOffer(token, response.getUuid(), "BP_EM_CONN_PLUS5G");
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Service: Porting
	 ------------------------------------------------------------------- */

	/**
	 * Get porting times
	 * 
	 * Ref: CRM_S_PO_01
	 */
	@Test(description = "CRM_S_CO_01: CRM UI: Eir CRM - PAYG: Get porting times", dataProvider = "all_data_types")
	public void CRM_S_CO_01_testGetPortingTimes(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);
		
		APITransaction t = PAYGCRMAPI.getPortingTimes(token);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get current operator
	 * 
	 * Ref: CRM_S_PO_02
	 */
	@Test(description = "CRM_S_PO_02: CRM UI: Eir CRM - PAYG: Get current operator")
	public void CRM_S_PO_02_testGetCurrentOperator(ITestContext iTestContext) {

		String token = this.getToken(CustomerType.EIR_PREPAY);
		APITransaction t = PAYGCRMAPI.getCurrentOperator(token, "0861231231");
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get porting history
	 * 
	 * Ref: CRM_S_PO_03
	 */
	@Test(description = "CRM_S_CO_03: CRM UI: : Get port history", dataProvider = "all_data_types")
	public void CRM_S_PO_03_testGetPortHistory(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);
		
		APITransaction t = PAYGCRMAPI.getPortingHistory(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get incomplete ports
	 * 
	 * Ref: CRM_S_PO_04
	 */
	@Test(description = "CRM_S_CO_04: CRM UI: : Get port history", dataProvider = "all_data_types")
	public void CRM_S_PO_04_testGetIncompletePorts(CustomerType customerType, ITestContext iTestContext) {

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);
		
		APITransaction t = PAYGCRMAPI.getIncompletePorts(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Service: Manage Discounts
	 ------------------------------------------------------------------- */
	/**
	 * Get ref discount add reasons
	 * 
	 * Ref: CRM_S_DI_01
	 */
	@Test(description = "CRM_S_DI_01: CRM UI: Get ref discount add reasons")
	public void CRM_S_DI_01_testGetRefDiscountAddReasons(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getRefDiscountAddReasons(token);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get ref discount remove reasons
	 * 
	 * Ref: CRM_S_DI_02
	 */
	@Test(description = "CRM_S_DI_02: CRM UI: Get ref discount remove reasons")
	public void CRM_S_DI_02_testGetRefDiscountRemoveReasons(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getRefDiscountRemoveReasons(token);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get active discounts
	 * 
	 * Ref: CRM_S_DI_03
	 */
	@Test(description = "CRM_S_DI_03: CRM UI: Get active discounts")
	public void CRM_S_DI_03_testGetActiveDiscounts(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getActiveDiscounts(token, testData.getSubscriptionId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Get available discounts
	 * 
	 * Ref: CRM_S_DI_04
	 */
	@Test(description = "CRM_S_DI_04: CRM UI: Get available discounts")
	public void CRM_S_DI_04_testGetAvailableDiscounts(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getAvailableDiscounts(token, ChannelCode.EIR_RETAIL);
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Service: Pausible Usages
	 ------------------------------------------------------------------- */
	/**
	 * Get pausible features/usages
	 * 
	 * Ref: CRM_S_FE_01
	 */
	@Test(description = "CRM_S_FE_01: CRM UI: Get pausible usages")
	public void CRM_S_FE_01_testGetPausibleUsages(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.getPausibleUsages(token, testData.getServiceId());
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Pause feature
	 * 
	 * Ref: CRM_S_FE_02
	 */
	@Test(description = "CRM_S_FE_02: CRM UI: Pause feature")
	public void CRM_S_FE_02_testPauseFeature(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.pauseFeature(token, testData.getServiceId(), "WIFI_CALLING");
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}

	/**
	 * Unpause feature
	 * 
	 * Ref: CRM_S_FE_03
	 */
	@Test(description = "CRM_S_FE_03: CRM UI: Unpause feature")
	public void CRM_S_FE_03_testUnpauseFeature(ITestContext iTestContext) {

		CustomerType customerType = CustomerType.EIR_POSTPAY;

		// retrieve the relevant test data
		TestData testData = this.getTestData(customerType);
		logInfo(testData.toString());
		String token = this.getToken(customerType);

		APITransaction t = PAYGCRMAPI.unpauseFeature(token, testData.getServiceId(), "WIFI_CALLING");
		assertEquals(t.getResponse().statusCode(), 200, "Response code");
		logPass(t.toString());
	}
	/*
	 * 
	 * @Test(dependsOnMethods = "testLogin", description =
	 * "Eir CRM - PAYG: Get non-usage history") public void
	 * testNonUsageHistory(ITestContext iTestContext) { APITransaction t =
	 * PAYGCRMAPI.getNonUsageHistory(token, prepayServiceID); assertEquals(200,
	 * t.getResponse().statusCode()); logPass(t.toString()); }
	 * 
	 * @Test(dependsOnMethods = "testLogin", description =
	 * "Eir CRM - PAYG: Get email subjects") public void
	 * testGetEmailSubjects(ITestContext iTestContext) { APITransaction t =
	 * PAYGCRMAPI.getEmailSubjects(token, prepayAccountID); assertEquals(200,
	 * t.getResponse().statusCode()); logPass(t.toString()); }
	 * 
	 * @Test(dependsOnMethods = "testLogin", description =
	 * "Eir CRM - PAYG: Get email notifications") public void
	 * testGetEmailNotifications(ITestContext iTestContext) { APITransaction t =
	 * PAYGCRMAPI.getEmailNotifications(token, prepayAccountID); assertEquals(200,
	 * t.getResponse().statusCode()); logPass(t.toString()); }
	 * 
	 * @Test(dependsOnMethods = "testLogin", description =
	 * "Eir CRM - PAYG: Get SMS notifications") public void
	 * testGetSMSNotifications(ITestContext iTestContext) { APITransaction t =
	 * PAYGCRMAPI.getSMSNotifications(token, prepayAccountID); assertEquals(200,
	 * t.getResponse().statusCode()); logPass(t.toString()); }
	 * 
	 * @Test(enabled = false, dependsOnMethods = { "testLogin" }, description =
	 * "Eir CRM - PAYG: Schedule and cancel termination request") public void
	 * testScheduleAndCompleteTermination(ITestContext iTestContext) {
	 * 
	 * // read the list of existing termination requests on the account
	 * ArrayList<PfRequest> existingRequests =
	 * ProvisioningFacadeDAO.getRequestsBySubIdAndAction(prepaySubscriptionID,
	 * ProvisioningAction.TERMINATE_SUBSCRIBER);
	 * 
	 * int requestIdToIgnore = 0;
	 * 
	 * // confirm that there are no termination requests currently sitting in an
	 * error // state if (existingRequests.size() > 0) { PfRequest requestToIgnore =
	 * existingRequests.get(0); assertNotEquals(requestToIgnore.getRequestStatus(),
	 * "ERROR"); requestIdToIgnore = requestToIgnore.getId();
	 * logInfo("The most recent termination request on this service is REQUEST.ID "
	 * + requestIdToIgnore + ". We will ignore this one"); }
	 * 
	 * // schedule the termination this.scheduleTermination();
	 * 
	 * // get the termination request from the database OmService
	 * pendingTerminationService = OrderManagementDAO.getOrderService(prepayMsisdn,
	 * ProvisioningAction.TERMINATE_SUBSCRIBER);
	 * assertNotNull(pendingTerminationService);
	 * 
	 * // verify that the termination request is in a status of SCHEDULED
	 * assertEquals("SCHEDULED", pendingTerminationService.getOrderServiceStatus());
	 * logInfo("Pending termination request " + pendingTerminationService.getId() +
	 * " is in a status of " + pendingTerminationService.getOrderServiceStatus());
	 * 
	 * // reschedule the termination trigger time to the current time
	 * OrderManagementDAO.rescheduleCancellationToNow(prepayMsisdn);
	 * 
	 * // wait for a new REQUEST to get created in provisioning facade PfRequest
	 * provisioningRequest =
	 * ProvisioningFacadeMonitor.waitForNewProvisioningRequest(prepaySubscriptionID,
	 * ProvisioningAction.TERMINATE_SUBSCRIBER, requestIdToIgnore);
	 * assertNotNull(provisioningRequest); logInfo("Provisioning facade.REQUEST ID "
	 * + provisioningRequest.getId() + " created for the cancellation");
	 * 
	 * // wait for the provisioning request to move to either ERROR or COMPLETED
	 * status String provisioningStatus =
	 * ProvisioningFacadeMonitor.waitForRequestToErrorOrComplete(provisioningRequest
	 * .getId()); assertNotNull(provisioningStatus);
	 * logInfo("Provisioning request ended up in status " + provisioningStatus);
	 * 
	 * // if the provisioning request errors on the MMSC, handle the error if
	 * (provisioningStatus.equals("ERROR")) { ArrayList<PfService> pfServices =
	 * ProvisioningFacadeDAO.getServicesByRequestID(provisioningRequest.getId());
	 * for (PfService service : pfServices) { if
	 * (service.getErrorCode().equals("TerminateSubscriber:[MMSC][Adopt]")) {
	 * logInfo("Failed on MMSC - adopting this error");
	 * ProvisioningFacadeAPI.adoptProvisioningError(service.getId()); } } }
	 * 
	 * // wait for the request to move to status COMPLETED boolean
	 * terminationCompleted =
	 * ProvisioningFacadeMonitor.waitForRequestToComplete(provisioningRequest.getId(
	 * )); assertEquals(true, terminationCompleted);
	 * logPass("Provisioning request ended up in status " + provisioningStatus);
	 * 
	 * // confirm that the subscription is in a TERMINATED status Subscription
	 * subscription =
	 * SubscriptionManagementDAO.getSubscriptionByID(prepaySubscriptionID);
	 * assertEquals("TERMINATED", subscription.getStatus());
	 * logPass("Subscription is now in status " + subscription.getStatus());
	 * 
	 * // verify that the subscriber is removed from the network
	 * assertNull(MMWUtility.getHLRFEProfile(prepayMsisdn));
	 * assertNull(MMWUtility.getSprProfile(prepayMsisdn));
	 * assertNull(MMWUtility.getEC20Profile(prepayMsisdn));
	 * 
	 * // now process the reactivation Response r =
	 * OrderManagementAPI.reactivateSubscriber(prepaySubscriptionID);
	 * assertEquals(200, r.statusCode());
	 * 
	 * logInfo("The subscriber has requested to reactivate their service...");
	 * 
	 * // -------------------------------------------------------
	 * provisioningRequest =
	 * ProvisioningFacadeMonitor.waitForNewProvisioningRequest(prepaySubscriptionID,
	 * ProvisioningAction.REACTIVATION_REQUEST, requestIdToIgnore);
	 * assertNotNull(provisioningRequest); logInfo("Provisioning facade.REQUEST ID "
	 * + provisioningRequest.getId() + " created for reactivation");
	 * 
	 * // wait for the provisioning request to move to either ERROR or COMPLETED
	 * status provisioningStatus =
	 * ProvisioningFacadeMonitor.waitForRequestToErrorOrComplete(provisioningRequest
	 * .getId()); assertNotNull(provisioningStatus);
	 * logInfo("Provisioning request ended up in status " + provisioningStatus);
	 * 
	 * // if the provisioning request errors on the AUC issue if
	 * (provisioningStatus.equals("ERROR")) { ArrayList<PfService> pfServices =
	 * ProvisioningFacadeDAO.getServicesByRequestID(provisioningRequest.getId());
	 * for (PfService service : pfServices) { if
	 * (service.getErrorCode().equals("CreateSubscriberProfile:[EDA_HLR][IN]") &&
	 * service.getErrorDescription().contains("AUC Subscriber already exists")) {
	 * logInfo("Failed on MMSC - adopting this error");
	 * ProvisioningFacadeAPI.adoptProvisioningError(service.getId()); } } } //
	 * ------------------------------------------------------- // wait for the
	 * subscription to reactivate
	 * SubscriptionManagementMonitor.waitForSubscriptionToReachStatus(
	 * prepaySubscriptionID, "ACTIVE", 60); subscription =
	 * SubscriptionManagementDAO.getSubscriptionByID(prepaySubscriptionID);
	 * assertEquals("ACTIVE", subscription.getStatus());
	 * logPass("Subscription is now in status " + subscription.getStatus());
	 * 
	 * // verify that the subscriber is reactivated on the network
	 * assertNotNull(MMWUtility.getHLRFEProfile(prepayMsisdn));
	 * assertNotNull(MMWUtility.getSprProfile(prepayMsisdn));
	 * assertNotNull(MMWUtility.getEC20Profile(prepayMsisdn)); }
	 * 
	 * /** Remove all contact permissions for the contact
	 */
	/*
	 * @Test(dependsOnMethods = "testLogin", description =
	 * "Eir CRM - PAYG: Reset permissions") public void
	 * testResetContactPermissions(ITestContext iTestContext) {
	 * 
	 * PermissionGroupCode group = PermissionGroupCode.ACTIVE_CUSTOMER;
	 * 
	 * // make a call to the API to remove all permissions for a particular group
	 * APITransaction t = PAYGCRMAPI.removeAllPermissions(token, prepayContactUuid,
	 * group); assertEquals(t.getResponse().statusCode(),200,"Response code");
	 * logPass(t.toString());
	 * 
	 * // check that the contact is opted out of all permissions
	 * ArrayList<ContactManagementPermission> actualPermissions =
	 * ContactManagementDAO.getContactPermissionsForGroup(prepayContactUuid, group);
	 * assertEquals(0, actualPermissions.size());
	 * 
	 * logPass("All permissions have been removed for group " + group +
	 * " on contact " + prepayContactUuid); }
	 * 
	 * @Test(dependsOnMethods = "testLogin", description =
	 * "Eir CRM - PAYG: Get active services on account") public void
	 * testGetActiveServices(ITestContext iTestContext) { APITransaction t =
	 * PAYGCRMAPI.getActiveServicesOnAccount(token, prepayAccountID);
	 * assertEquals(t.getResponse().statusCode(),200,"Response code");
	 * logPass(t.toString()); }
	 * 
	 * @Test(dependsOnMethods = "testLogin", description =
	 * "Eir CRM - PAYG: Get customer history item") public void
	 * testGetCustomerHistoryItem(ITestContext iTestContext) { int
	 * customerHistoryItemID = 7649; APITransaction t =
	 * PAYGCRMAPI.getCustomerHistoryItem(token, customerHistoryItemID);
	 * assertEquals(t.getResponse().statusCode(),200,"Response code");
	 * logPass(t.toString()); }
	 * 
	 * public void scheduleTermination() { OmService serviceToIgnore =
	 * OrderManagementDAO.getMostRecentOrderService(prepayServiceID);
	 * logInfo("The most recent order management.SERVICE record on this sub is ID "
	 * + serviceToIgnore.getId() + ". We will ignore this one.");
	 * 
	 * TerminationReason reason = TerminationReason.BILLING_ISSUE;
	 * logInfo("Customer " + prepayMsisdn +
	 * " will request a termination with reason " + reason);
	 * 
	 * APITransaction t = PAYGCRMAPI.scheduleTermination(token, prepayServiceID,
	 * reason); assertEquals(t.getResponse().statusCode(),204, "Response code");
	 * logPass(t.toString());
	 * 
	 * // wait for and return a new record in the order management SERVICE table
	 * OmService newOrderManagementService =
	 * OrderManagementMonitor.waitForOrderServiceLaterThanNamedServiceID(
	 * prepayServiceID, serviceToIgnore.getId());
	 * assertNotNull(newOrderManagementService); assertEquals(prepayMsisdn,
	 * newOrderManagementService.getServiceName()); assertEquals(prepayServiceID,
	 * newOrderManagementService.getServiceID()); assertEquals("SCHEDULED",
	 * newOrderManagementService.getOrderServiceStatus());
	 * assertEquals("TERMINATE_SUBSCRIBER",
	 * newOrderManagementService.getProvisioningAction());
	 * logPass("Order management SERVICE " + newOrderManagementService.getId() +
	 * " successfully created");
	 * 
	 * // read the details from the order management EVENT table Event event =
	 * OrderManagementDAO.getEvent(newOrderManagementService.getEventID());
	 * assertEquals("SERVICE_FULFILLMENT_TERMINATE_SERVICE", event.getType());
	 * assertEquals("IN_PROGRESS", event.getStatus());
	 * assertEquals("CUSTOMER_OFFER", event.getSource()); logPass("Event " +
	 * event.getId() + " successfully created: " + event.getType() + ", " +
	 * event.getSource() + ", " + event.getStatus());
	 * 
	 * // verify that the termination request is added to the termination request
	 * table // in order management TerminationRequest terminationRequest =
	 * OrderManagementDAO.getTerminationRequest(newOrderManagementService.getId());
	 * assertNotNull(terminationRequest); assertEquals(reason.toString(),
	 * terminationRequest.getReasonCode());
	 * logPass("Order management TERMINATION_REQUEST " +
	 * terminationRequest.getOrderServiceID() + " successfully created");
	 * 
	 * // verify that the quartz trigger has been created QuartzTrigger
	 * quartzTrigger =
	 * OrderManagementDAO.getQuartsTrigger(newOrderManagementService.getId());
	 * assertNotNull(quartzTrigger); assertEquals("service-termination-triggers",
	 * quartzTrigger.getTriggerGroup()); logPass("Quartz trigger " +
	 * newOrderManagementService.getId() + " has been created with a fire time of "
	 * + quartzTrigger.getNextFireTime()); }
	 */

	@BeforeClass
	public void setUp() {

		// initialize the maps
		tokens = new HashMap<>();
		testDataMap = new HashMap<>();

		/*
		// retrieve and store eir prepay test data
		int prepayBillingAccountID = TestDataManager.getEirPAYGSubscriber();
		TestData prepayData = TestDataManager.getTestData(prepayBillingAccountID);
		testDataMap.put(CustomerType.EIR_PREPAY, prepayData);

		// retrieve and store eir postpay test data
		int postpayBillingAccountID = TestDataManager.getEirPostpaySubscriber();
		TestData postpayData = TestDataManager.getTestData(postpayBillingAccountID);
		testDataMap.put(CustomerType.EIR_POSTPAY, postpayData);

		// retrieve and store gomo test data
		int gomoBillingAccountID = TestDataManager.getGoMoMultilineSubscriber();
		TestData gomoData = TestDataManager.getTestData(gomoBillingAccountID);
		testDataMap.put(CustomerType.GOMO, gomoData);
		*/
	}

	@AfterClass
	public void tearDown() {

	}

	/**
	 * Get the token for the customer type
	 * 
	 * Retrieve the token for the map, or if it doesn't exist, create it
	 * 
	 * @param customerType
	 * @return token (string)
	 */
	public String getToken(CustomerType customerType) {

		// attempt to retrieve the token from the map
		String token = tokens.get(customerType);

		// if the token doesn't exist...
		if (token == null) {

			// generate the token relevant to the customer type
			if (customerType == CustomerType.GOMO) {
				testGoMoLogin();
			} else if (customerType == CustomerType.EIR_POSTPAY || customerType == CustomerType.EIR_PREPAY) {
				testEirLogin();
			}
		}

		// return the token
		return tokens.get(customerType);
	}

	/**
	 * Get the token for the customer type
	 * 
	 * Retrieve the token for the map, or if it doesn't exist, create it
	 * 
	 * @param customerType
	 * @return token (string)
	 */
	public TestData getTestData(CustomerType customerType) {

		// determine whether the data already exists
		TestData testData = testDataMap.get(customerType);
		if (testData == null) {
			int accountID;

			if (customerType == CustomerType.EIR_PREPAY) {
				accountID = TestDataManager.getEirPAYGSubscriber();
			} else if (customerType == CustomerType.EIR_POSTPAY) {
				accountID = TestDataManager.getEirPostpaySubscriber();
			} else if (customerType == CustomerType.GOMO) {
				accountID = TestDataManager.getGoMoMultilineSubscriber();
			} else {
				return null;
			}

			testData = TestDataManager.getTestData(accountID);
			testDataMap.put(customerType, testData);
		}

		return testData;
	}

	@DataProvider(name = "all_data_types")
	public Object[][] getAllCustomerTypes() {

		Object[][] data = { { CustomerType.EIR_PREPAY }, { CustomerType.EIR_POSTPAY }, { CustomerType.GOMO } };

		return data;
	}

	@DataProvider(name = "eir_data_types")
	public Object[][] getEirCustomerTypes() {

		Object[][] data = { { CustomerType.EIR_PREPAY }, { CustomerType.EIR_POSTPAY } };

		return data;
	}
}

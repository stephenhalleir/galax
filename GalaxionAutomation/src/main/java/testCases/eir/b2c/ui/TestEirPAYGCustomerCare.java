package testCases.eir.b2c.ui;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.ec20.monitor.EC20Monitor;
import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.Offer;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.data_model.ContactManagementPermission;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.backend.eir_contact_management_backend.validator.ContactPreferencesValidator;
import microservices.backend.eir_inventory_management_backend.api.InventoryManagementAPI;
import microservices.backend.eir_inventory_management_backend.objects.SimDetails;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Payment;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_security_question_backend.dao.SecurityQuestionDAO;
import microservices.backend.eir_security_question_backend.data_model.Answer;
import microservices.backend.eir_security_question_backend.data_model.RefQuestion;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.backend.eir_topup_backend.flows.CRMUITopupsFlow;
import microservices.frontend.common_ui.enums.QuickAction;
import selenium.drivers.DriverFactory;
import selenium.pages.eir_prepay.customer_care.CRMLandingPage;
import selenium.pages.eir_prepay.customer_care.CRMSearchPage;
import selenium.pages.eir_prepay.customer_care.CRMServicePage;
import selenium.pages.eir_prepay.customer_care.CRMViewOrderPage;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.mailhog.MailHog;
import utilities.generic.time.WaitUtil;

public class TestEirPAYGCustomerCare extends BaseTest {

	private int billingAccountID;
	private String contactUuid;

	public TestEirPAYGCustomerCare() {

	}

	@Test(enabled = true, description = "eir PAYG > CRM UI > Log in to CRM UI", invocationCount = 1) // (dataProvider = // "order-quantities")
	public void testLoginToCRMUI(ITestContext iTestContext) {

		// get the test data from the environments file
		billingAccountID = TestDataManager.getEirPAYGSubscriber();
		contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		// read the URL and login credentials and confirm that they are found
		// successfully
		String url = EnvironmentDirectory.getPaygCrmUiURL();
		LoginCredentials loginDetails = EnvironmentDirectory.getEirPAYGTelesalesLogin();

		// verify that the information has been retrieved successfully
		assertNotNull(url);
		assertNotNull(loginDetails);

		// browse to the URL
		driver.get(url);

		// log into the CRM UI
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		boolean loginSuccessful = loginPage.login(loginDetails.getUsername(), loginDetails.getPassword());

		// assertTrue(loginSuccessful);
		logPass("CRM UI is loaded successfully");
	}

	@Test(dependsOnMethods = { "testLoginToCRMUI" }, description = "eir PAYG > CRM UI > Customer Search", invocationCount = 1)
	public void testCustomerSearch(ITestContext iTestContext) {
		// read the contact object from the database
		Contact contact = ContactManagementDAO.getContact(contactUuid);
		CRMSearchPage searchPage = new CRMSearchPage(driver);

		// customer search
		searchPage.selectCustomerSearch();
		searchPage.enterFirstName(contact.getFirstName());
		searchPage.enterLastName(contact.getLastName());
		// searchPage.enterEmail("test");
		// searchPage.enterContactNumber("test");
		// searchPage.enterMSISDN("test");
		searchPage.enterAccountNumber(Integer.toString(billingAccountID));
		// searchPage.enterICCID("test");
		searchPage.clickSearch();
		WaitUtil.waitForSeconds(2);
		searchPage.clickText(Integer.toString(billingAccountID));
		// WaitUtil.waitForSeconds(3);
		searchPage.passVerification();
	}

	@Test(dependsOnMethods = { "testLoginToCRMUI" }, description = "eir PAYG > CRM UI > Order Search", invocationCount = 1) // (dataProvider =
																															// "order-quantities")
	public void testOrderSearch(ITestContext iTestContext) {

		// select an order to search for
		ArrayList<ProductOrder> orders = OrderManagementDAO.getOrdersForContact(contactUuid);
		ProductOrder orderToSearch = orders.get(0);
		CRMSearchPage searchPage = new CRMSearchPage(driver);

		// perform the order search
		searchPage.selectOrderSearch();
		searchPage.enterOrderReference(orderToSearch.getReference());
		searchPage.clickSearch();
		WaitUtil.waitForSeconds(3);

		// verify that the page is loaded - TODO: Add more checks
		CRMViewOrderPage ordersPage = new CRMViewOrderPage(driver);
		assertTrue(ordersPage.isLoaded());
	}

	/*
	 * This test will verify: - The information in the left banner - The information
	 * in the Customer Details section - The billing address - The marketing
	 * preferences
	 */
	@Test(dependsOnMethods = { "testCustomerSearch" }, enabled = true, description = "eir PAYG > CRM UI > View Customer Details", invocationCount = 1)
	public void testViewAccountDetails(ITestContext iTestContext) {

		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		// select an order to search for
		Account account = SubscriptionManagementDAO.getAccountByID(accountID);
		Contact contact = ContactManagementDAO.getContact(contactUuid);

		CRMLandingPage landingPage = new CRMLandingPage(driver);

		// verify the details in the left banner
		assertTrue(landingPage.getBannerName().equalsIgnoreCase(contact.getFirstName() + " " + contact.getLastName()));
		logPass("Correct name displayed in the banner: " + landingPage.getBannerName());

		assertTrue(landingPage.getBannerEmail().equalsIgnoreCase(contact.getEmailAddress()));
		logPass("Correct email address displayed in the banner: " + (landingPage.getBannerEmail()));

		assertTrue(landingPage.getBannerAccountNumber().equalsIgnoreCase(Integer.toString(billingAccountID)));
		logPass("Correct account number displayed in the banner: " + landingPage.getBannerAccountNumber());

		assertTrue(landingPage.getBannerAccountStatus().equalsIgnoreCase(account.getStatus()));
		logPass("Correct account status displayed in the banner: " + landingPage.getBannerAccountStatus());

		assertTrue(landingPage.getBannerAccountType().equalsIgnoreCase(account.getCustomerType()));
		logPass("Correct account type displayed in the banner: " + landingPage.getBannerAccountType());

		assertTrue(landingPage.getBannerPhoneNumber().equalsIgnoreCase(contact.getPhoneNumber()));
		logPass("Correct phone number displayed in the banner: " + landingPage.getBannerPhoneNumber());

		// verify the customer details
		assertTrue(landingPage.getCustDetailsFirstName().equalsIgnoreCase(contact.getFirstName()));
		logPass("Correct first name displayed in the customer details section: " + contact.getFirstName());

		assertTrue(landingPage.getCustDetailsLastName().equalsIgnoreCase(contact.getLastName()));
		logPass("Correct last name displayed in the customer details section: " + contact.getLastName());

		assertTrue(landingPage.getCustDetailsPhoneNumbers().equalsIgnoreCase(contact.getPhoneNumber()));
		logPass("Correct contact number displayed in the customer details section: " + contact.getPhoneNumber());

		System.err.println(landingPage.getCustDetailsDOB());
		System.err.println(contact.getBirthDate("dd/MM/yyyy"));

		assertTrue(landingPage.getCustDetailsDOB().equalsIgnoreCase(contact.getBirthDate("dd/MM/yyyy")));
		logPass("Correct date of birth displayed in the customer details section: " + landingPage.getCustDetailsDOB());

		// verify the address details

		assertTrue(landingPage.getAddressEircode().equalsIgnoreCase(contact.getBillingAddress().getEircode()));
		logPass("Correct eircode displayed in the address section: " + contact.getBillingAddress().getEircode());

		assertTrue(landingPage.getAddressLine1().equalsIgnoreCase(contact.getBillingAddress().getAddressLine1()));
		logPass("Correct address line 1 displayed in the address section: " + contact.getBillingAddress().getAddressLine1());

		// verify address line 2 only if the contact has an address line 2
		if (!contact.getBillingAddress().getAddressLine2().equals("")) {
			assertTrue(landingPage.getAddressLine2().equalsIgnoreCase(contact.getBillingAddress().getAddressLine2()));
			logPass("Correct address line 2 displayed in the address section: " + contact.getBillingAddress().getAddressLine2());
		}

		// verify address line 3 only if the contact has an address line 3
		if (!contact.getBillingAddress().getAddressLine3().equals("")) {
			assertTrue(landingPage.getAddressLine3().equalsIgnoreCase(contact.getBillingAddress().getAddressLine3()));
			logPass("Correct address line 3 displayed in the address section: " + contact.getBillingAddress().getAddressLine3());
		}

		assertTrue(landingPage.getTown().equalsIgnoreCase(contact.getBillingAddress().getTown()));
		logPass("Correct town displayed in the address section: " + contact.getBillingAddress().getTown());

		assertTrue(landingPage.getCounty().equalsIgnoreCase(contact.getBillingAddress().getCounty()));
		logPass("Correct county displayed in the address section: " + contact.getBillingAddress().getCounty());
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > View/Edit Contact Preferences", invocationCount = 1)
	public void testContactPreferences() {

		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		WaitUtil.waitForSeconds(3);

		Contact contact = ContactManagementDAO.getContact(contactUuid);

		// read the permissions set from the database before the test
		ArrayList<ContactManagementPermission> permissionsBefore = ContactManagementDAO.getContactPermissions(contactUuid);

		// access the page
		CRMLandingPage landingPage = new CRMLandingPage(driver);
		landingPage.scrollToMarketingPreferences();

		// confirm that that landing page matches the permissions set from the DB
		ContactPreferencesValidator.checkProfilePageCorrect(landingPage, permissionsBefore);

		// check that the 3rd party flag is as expected
		assertEquals(landingPage.is3rdPartyOptOutSelected(), contact.getAllowThirdParties() == 0);

		// change all permissions
		landingPage.toggleAllMarketingPermissions();

		// read the updated permissions from the database
		ArrayList<ContactManagementPermission> permissionsAfter = ContactManagementDAO.getContactPermissions(contactUuid);

		// confirm that that landing page matches the permissions set from the DB
		ContactPreferencesValidator.checkProfilePageCorrect(landingPage, permissionsAfter);

		// verify that the permissions have changed as expected
		ContactPreferencesValidator.checkAllContactPreferencesChanged(permissionsBefore, permissionsAfter, PermissionGroupCode.ACTIVE_CUSTOMER);
		ContactPreferencesValidator.checkAllContactPreferencesChanged(permissionsBefore, permissionsAfter, PermissionGroupCode.NO_LONGER_CUSTOMER);

		// refresh the contact information from the database
		contact = ContactManagementDAO.getContact(contactUuid);

		// check that the 3rd party flag is as expected
		assertEquals(landingPage.is3rdPartyOptOutSelected(), contact.getAllowThirdParties() == 0);
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > View/Edit security question", invocationCount = 1)
	public void testSecurityQuestion(ITestContext iTestContext) {

		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		WaitUtil.waitForSeconds(3);

		// access the page
		CRMLandingPage landingPage = new CRMLandingPage(driver);
		landingPage.scrollToMarketingPreferences();

		// retrieve the security question from the database
		Answer answer = SecurityQuestionDAO.getAnswer(contactUuid);
		RefQuestion question = SecurityQuestionDAO.getQuestion(answer.getRefQuestionID());

		// verify that the current security question and answer are displayed correctly
		// before the test
		assertEquals(landingPage.getSettingValue("Security question"), question.getQuestion());
		assertEquals(landingPage.getSettingValue("Answer"), answer.getResponse());
		logPass("Correct initial security question and answer displayed: " + question.getQuestion() + ", " + answer.getResponse());

		// click the Edit button for the security question
		landingPage.clickEditSecurityQuestion();

		int newQuestionID;

		// determine a new security question to select
		if (question.getId() == 1) {
			newQuestionID = 2;
		} else {
			newQuestionID = 1;
		}

		// determine the new question and answer
		RefQuestion newQuestion = SecurityQuestionDAO.getQuestion(newQuestionID);
		String newAnswer = "test automation " + System.currentTimeMillis();
		logInfo("Customer will change their security question to " + newQuestion.getQuestion() + ", " + newAnswer);

		// select the security question from the dropdown list
		landingPage.selectSecurityQuestion(newQuestion.getCode());
		logInfo("Agent selects new security question " + newQuestion.getCode());

		// enter the new security answer into the txt field
		landingPage.enterSecurityAnswer(newAnswer);
		logInfo("Agent enters new security answer " + newAnswer);

		// save the changes
		landingPage.saveContactChanges();
		logInfo("Agent clicks the 'Save' button");

		// wait for the changes to take effect
		WaitUtil.waitForSeconds(5);

		// read the new security question from the database
		answer = SecurityQuestionDAO.getAnswer(contactUuid);
		question = SecurityQuestionDAO.getQuestion(answer.getRefQuestionID());

		// verify that the values have changes successfully in the DB
		assertEquals(answer.getResponse(), newAnswer);
		assertEquals(answer.getRefQuestionID(), newQuestionID);

		// log the output
		logPass("Security question is now updated in the database to " + question.getQuestion() + ", " + answer.getResponse());
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > View service screen", invocationCount = 1)
	public void testViewServiceDetails(ITestContext iTestContext) {

		// read the service from the database
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		assertNotNull(service);
		logInfo("Test is being performed on MSISDN " + service.getName());

		// read the service/subscription/offer details from the database
		SimCard activeSimCard = SubscriptionManagementDAO.getActiveSimCard(service.getName());
		SimDetails simDetails = InventoryManagementAPI.getSimDetailsFromInventory(activeSimCard.getIccid());
		Subscription subscription = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());
		Offer offer = CatalogCoreDAO.getOfferByCode(subscription.getCatalogOfferCode());

		WaitUtil.waitForSeconds(2);

		CRMLandingPage landingPage = new CRMLandingPage(driver);
		landingPage.clickBannerMobileDropdown();
		landingPage.selectMsisdn(service.getName());

		CRMServicePage servicePage = new CRMServicePage(driver);

		WaitUtil.waitForSeconds(5);

		servicePage.scrollToNDD();

		DateFormat df = new SimpleDateFormat("dd/MM/YYYY");

		// check that the activation date is correct
		assertEquals(df.format(subscription.getActivatedAt()), servicePage.getOfferValue("Sim Activation date"));
		logPass("Correct 'Sim Activation date' displayed: " + servicePage.getOfferValue("Sim Activation date"));

		// check that the IMSI is correct
		assertEquals(activeSimCard.getImsi(), servicePage.getOfferValue("IMSI"));
		logPass("Correct 'IMSI' displayed: " + servicePage.getOfferValue("IMSI"));

		// check that the offer type (e.g. PREPAY) is correct
		assertEquals(subscription.getType(), servicePage.getOfferValue("Mobile Product"));
		logPass("Correct 'Mobile Product' displayed: " + servicePage.getOfferValue("Mobile Product"));

		// check that the subscription status is correct
		assertEquals(subscription.getStatus(), servicePage.getOfferValue("Subscription Status"));
		logPass("Correct 'Offer Status' displayed: " + servicePage.getOfferValue("Subscription Status"));

		// check that the displayed offer description is correct
		assertEquals(offer.getDescription(), servicePage.getOfferValue("Price Plan"));
		logPass("Correct 'Price Plan' displayed: " + servicePage.getOfferValue("Price Plan"));

		// check that the NDD settings displayed is correct
		assertEquals(servicePage.getSelectedNDD(), SubscriptionManagementDAO.getNDDSetting(service.getName()).toString());
		logPass("Correct 'Directory Preference' displayed: " + servicePage.getSelectedNDD());

		assertEquals(servicePage.getPin(), simDetails.getPin1());
		assertEquals(servicePage.getPuk(), simDetails.getPuk1());
		logPass("Correct Pin and Puk displayed: " + servicePage.getPin() + ", " + servicePage.getPuk());

		// TODO: Add checks for active subscription offer
		/*
		 * OCSProfile ocsProfile = new OCSProfile(service.getName());
		 * ocsProfile.populate(); for(OCSOffer ocsOffer:ocsProfile.getOffers()) {
		 * System.err.println(ocsOffer.getOfferID() + ", " + ocsOffer.getOfferName() +
		 * ", " + ocsOffer.getExpiryDate()); }
		 */

		// ---------------------------
		/*
		 * servicePage.clickTab("Usage"); waitForSeconds(1);
		 * servicePage.clickTab("Prepay Balance"); waitForSeconds(1);
		 * servicePage.clickTab("Offer Detail"); waitForSeconds(1);
		 * //servicePage.clickTab("Manage Subscription");
		 * servicePage.clickManageSubscriptionOption("Modify add-ons");
		 * waitForSeconds(1); servicePage.clickManageSubscriptionOption("Barring");
		 * waitForSeconds(1);
		 * servicePage.clickManageSubscriptionOption("SIM replacement");
		 * waitForSeconds(1); servicePage.clickManageSubscriptionOption("Porting");
		 * waitForSeconds(1);
		 * servicePage.clickManageSubscriptionOption("Terminate Subscription");
		 * 
		 * System.out.println(servicePage.getPin() + ", " + servicePage.getPuk());
		 * 
		 * waitForSeconds(10);
		 */
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > View/Edit NDD Settings", invocationCount = 1)
	public void testViewEditNDDSettings(ITestContext iTestContext) {
		// read the service from the database
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		assertNotNull(service);
		logInfo("Test is being performed on MSISDN " + service.getName());

		CRMLandingPage landingPage = new CRMLandingPage(driver);
		landingPage.clickBannerMobileDropdown();
		landingPage.selectMsisdn(service.getName());

		CRMServicePage servicePage = new CRMServicePage(driver);

		WaitUtil.waitForSeconds(2);

		servicePage.scrollToNDD();

		// set up the list of all NDD settings
		ArrayList<NDDSetting> nddValues = new ArrayList<NDDSetting>();
		nddValues.add(NDDSetting.LISTED);
		nddValues.add(NDDSetting.UNLISTED);
		nddValues.add(NDDSetting.EXDIRECTORY);

		// determine the current NDD setting on the service
		NDDSetting currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(service.getName());

		// move the current NDD setting to the end of the list (we will switch to it
		// last)!
		nddValues.remove(currentNDDSetting);
		nddValues.add(currentNDDSetting);

		// for each NDD value
		for (NDDSetting setting : nddValues) {

			// select the value
			servicePage.selectNDD(setting.toString());
			logPass("NDD value " + setting.toString() + " selected");

			WaitUtil.waitForSeconds(3);

			// refresh the current NDD setting from the database
			currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(service.getName());

			// verify that the setting has changed in the database
			assertEquals(currentNDDSetting.toString(), setting.toString());

			// verify that the setting has changed on the screen
			assertEquals(servicePage.getSelectedNDD(), currentNDDSetting.toString());
			logPass("Database shows NDD setting " + currentNDDSetting);
		}
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > Edit Contact Details", invocationCount = 1)
	public void testEditContactDetails(ITestContext iTestContext) {

		Contact contact = ContactManagementDAO.getContact(contactUuid);

		CRMLandingPage landingPage = new CRMLandingPage(driver);

		// click the Edit button
		landingPage.clickEditContactDetails();

		// generate random new data for the change
		String firstName = RandomStringGenerator.getRandomFirstName();
		String lastName = RandomStringGenerator.getRandomLastName();
		String phoneNumber = RandomStringGenerator.getRandomMobilePhoneNumber();
		Date startDate = new GregorianCalendar(1970, 01, 01).getTime();
		Date endDate = new GregorianCalendar(2003, 07, 07).getTime();
		String newDOB = RandomStringGenerator.getRandomDateInRange(startDate, endDate, new SimpleDateFormat("ddMMyyyy"));

		// update the values in the fields
		landingPage.enterFirstName(firstName);
		landingPage.enterLastName(lastName);
		landingPage.enterContactNumber(phoneNumber);
		landingPage.enterDOB(newDOB);

		// save the changes
		landingPage.saveContactChanges();

		// verify that the details are updated on the screen
		assertEquals(firstName, landingPage.getCustDetailsFirstName());
		assertEquals(lastName, landingPage.getCustDetailsLastName());
		assertEquals(phoneNumber, landingPage.getCustDetailsPhoneNumbers());

		// poll the DB for 30 seconds to wait for the record to update
		long waitEndTime = System.currentTimeMillis() + 30000;
		while (!contact.getFirstName().equals(firstName) && System.currentTimeMillis() < waitEndTime) {

			// refresh the contact record from the database
			contact = ContactManagementDAO.getContact(contactUuid);

			// wait before re-polling
			WaitUtil.waitForSeconds(2);
		}

		// verify that the details are updated in the DB
		assertEquals(firstName, contact.getFirstName());
		assertEquals(lastName, contact.getLastName());
		assertEquals(phoneNumber, contact.getPhoneNumber());
		assertEquals(newDOB, contact.getBirthDate("ddMMyyyy"));

		// log the output
		logPass("Contact details updated to " + contact.getFirstName() + " " + contact.getLastName() + ", " + contact.getBirthDate() + ", "
				+ contact.getPhoneNumber());
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eirPAYG > CRM UI > Edit Billing Address", invocationCount = 1)
	public void testEditBillingAddress(ITestContext iTestContext) {

		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		WaitUtil.waitForSeconds(3);

		Contact contact = ContactManagementDAO.getContact(contactUuid);

		// select a random address from the database
		microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress newAddress = RandomStringGenerator.getRandomAddressFromFile();

		CRMLandingPage landingPage = new CRMLandingPage(driver);

		// edit the details on the page
		landingPage.clickEditAddress();

		WaitUtil.waitForSeconds(1);

		landingPage.enterEircode(newAddress.getEircode());
		landingPage.clickConfirmEircode();
		landingPage.clickSaveAddressChanges();

		// wait for the change to take effect
		WaitUtil.waitForSeconds(5);

		// verify that the details are updated on the page
		assertEquals(landingPage.getAddressEircode(), newAddress.getEircode());
		assertEquals(landingPage.getAddressLine1(), newAddress.getAddressLine1());

		if (!newAddress.getAddressLine2().equals("")) {
			assertEquals(landingPage.getAddressLine2(), newAddress.getAddressLine2());
		}

		if (!newAddress.getAddressLine3().equals("")) {
			assertEquals(landingPage.getAddressLine3(), newAddress.getAddressLine3());
		}

		assertEquals(landingPage.getTown(), newAddress.getTown());
		assertEquals(landingPage.getCounty(), newAddress.getCounty());
		assertEquals(landingPage.getAddressEircode(), newAddress.getEircode());

		// refresh the details from the database
		contact = ContactManagementDAO.getContact(contactUuid);

		// verify that the details are updated in the DB
		assertEquals(contact.getBillingAddress().getEircode(), newAddress.getEircode());
		assertEquals(contact.getBillingAddress().getAddressLine1(), newAddress.getAddressLine1());
		assertEquals(contact.getBillingAddress().getAddressLine2(), newAddress.getAddressLine2());
		assertEquals(contact.getBillingAddress().getAddressLine3(), newAddress.getAddressLine3());
		assertEquals(contact.getBillingAddress().getTown(), newAddress.getTown());
		assertEquals(contact.getBillingAddress().getCounty(), newAddress.getCounty());

		logPass("Address updated in the database to " + newAddress.getEircode() + ", " + newAddress.getAddressLine1());
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > Perform CSR assisted topup", invocationCount = 1)
	public void testTopups(ITestContext iTestContext) {

		int voucherAmount = 5;

		// read the service from the database
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		assertNotNull(service);
		logInfo("Test is being performed on MSISDN " + service.getName());

		// get balance before test
		String balanceString = MMWUtility.getEC20CreditBalanceStr(service.getName());
		double mainBalanceBefore = Double.parseDouble(balanceString);

		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		// execute the topup flow for the service
		CRMUITopupsFlow topupFlow = new CRMUITopupsFlow(driver, extentLogger, logger4j);
		topupFlow.processTopup(service.getName(), voucherAmount);

		// wait for the IN balance to change from the original credit amount
		double mainBalanceAfter = EC20Monitor.waitForCreditChange(service.getName(), mainBalanceBefore);

		// verify that the balance has been updated by the correct amount
		assertEquals(mainBalanceAfter, mainBalanceBefore, voucherAmount);
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > Test Quick Actions")
	public void testQuickActions(ITestContext iTestContext) {

		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		CRMLandingPage landingPage = new CRMLandingPage(driver);
		landingPage.selectQuickAction(QuickAction.TEMP_UNLOCK);

		WaitUtil.waitForSeconds(2);

		landingPage.selectQuickAction(QuickAction.UPDATE_PASSWORD);

		WaitUtil.waitForSeconds(2);

		landingPage.selectQuickAction(QuickAction.VERIFY_EMAIL);
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eir PAYG > CRM UI > Test Quick Action: Update Password", invocationCount = 1)
	public void testUpdatePassword(ITestContext iTestContext) {

		// go to the profile page
		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		CRMLandingPage landingPage = new CRMLandingPage(driver);

		// select the UPDATE_PASSWORD quick action
		landingPage.selectQuickAction(QuickAction.UPDATE_PASSWORD);

		// get the email address for the account
		String contactUuid = SubscriptionManagementDAO.getContactUuidForAccountID(accountID);
		String emailAddress = ContactManagementDAO.getEmailAddressForUUID(contactUuid);

		// get the deep link from the email in mailhog
		String deepLink = MailHog.getDeeplinkFromMailhog(emailAddress, "updatePasswordEir");

		// veryify that the email address was found
		assertNotNull(deepLink);

		logPass("Deep link found: " + deepLink);
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "eirPAYG > CRM UI > View Order History", invocationCount = 1)
	public void viewOrderHistory() {

		// go to the view account page
		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");

		// click the orders tab
		CRMLandingPage landingPage = new CRMLandingPage(driver);
		landingPage.clickOrdersTab();

		CRMViewOrderPage ordersPage = new CRMViewOrderPage(driver);

		// read the list of orders from the database
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		ArrayList<ProductOrder> orders = OrderManagementDAO.getOrdersForContact(contactUuid);

		int index = 1;

		for (ProductOrder order : orders) {

			// only check the first 10
			if (index <= 10) {

				// check that the fields are correct
				assertEquals(ordersPage.getTableValue(index, 1), order.getCreatedDate("dd/MM/yyyy HH:mm"));
				assertEquals(ordersPage.getTableValue(index, 2), order.getReference());
				assertEquals(ordersPage.getTableValue(index, 4), order.getOrder_type());
				assertEquals(ordersPage.getTableValue(index, 5), order.getStatus());

				// determine the expected order amount for dispay
				String expectedCurrencyAmount;
				Payment payment = OrderManagementDAO.getPaymentForOrder(order.getId());
				if (payment == null) {
					expectedCurrencyAmount = "€0";
				} else {
					expectedCurrencyAmount = payment.getAmountAsCurrency();
				}

				// check that the order amount is displayed correctly
				assertEquals(ordersPage.getTableValue(index, 3), expectedCurrencyAmount);

				// log the pass result
				logPass("Order displayed as expected: " + order.getCreatedDate("dd/MM/yyyy HH:mm") + ", " + order.getReference() + ", " + order.getOrder_type()
						+ ", " + order.getStatus() + ", " + expectedCurrencyAmount);
			}

			// increase the index to move onto the next order
			index++;
		}
	}

	@BeforeClass
	public void setUp() {
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		// Close chromedriver
		// driver.close();
	}

}

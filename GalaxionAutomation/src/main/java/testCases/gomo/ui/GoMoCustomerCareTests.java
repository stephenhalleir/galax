package testCases.gomo.ui;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import framework.test_data.generic.RandomStringGenerator;
import framework.test_data.generic.StringUtil;
import microservices.backend.eir_adjustment_backend.AdjustmentDAO;
import microservices.backend.eir_adjustment_backend.data_model.Adjustment;
import microservices.backend.eir_adjustment_backend.data_model.RefAdjustmentReason;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_contact_management_backend.data_model.Address;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.data_model.ContactManagementPermission;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.backend.eir_contact_management_backend.validator.ContactPreferencesValidator;
import microservices.backend.eir_inventory_management_backend.api.InventoryManagementAPI;
import microservices.backend.eir_inventory_management_backend.objects.SimDetails;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.OmService;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_order_management_backend.data_model.QuartzTrigger;
import microservices.backend.eir_order_management_backend.data_model.TerminationRequest;
import microservices.backend.eir_provisioning_facade_backend.enums.ProvisioningAction;
import microservices.backend.eir_salt_ar_backend.dao.AccountsReceivableDAO;
import microservices.backend.eir_salt_ar_backend.data_model.Balance;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import selenium.flows.gomo.customer_care.CsrUiHelper;
import selenium.pages.gomo.csr_ui.CSRBasePage;
import selenium.pages.gomo.csr_ui.CSRDetailsPage;
import selenium.pages.gomo.csr_ui.CSRNDDPage;
import selenium.pages.gomo.csr_ui.CSROrdersPage;
import selenium.pages.gomo.csr_ui.CSRPaymentCenterPage;
import selenium.pages.gomo.csr_ui.CSRSearchPage;
import selenium.pages.gomo.csr_ui.CSRServicePage;
import selenium.pages.gomo.keycloak.KeycloakInitialVerificationPage;
import selenium.pages.gomo.keycloak.KeycloakSetPasswordPage;
import selenium.pages.gomo.my_gomo.flows.MyGoMoHelper;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.mailhog.MailHog;
import utilities.generic.time.WaitUtil;
import utilities.test.config_readers.PasswordUtil;

public class GoMoCustomerCareTests extends BaseTest {

	// specify the test data
	private int billingAccountID;
	private String contactUuid;

	/**
	 * Test Case: Log into the GoMo CSR UI 
	 */
	@Test(description = "GoMo > CSR UI > Log in to CSR UI")
	public void testLoginToCSRUI() {

		// get the test data from the environments file
		billingAccountID=TestDataManager.getGoMoMultilineSubscriber();
		contactUuid=SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		
		// open a new browser
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		// navigate to the CSR UI URL
		driver.get(EnvironmentDirectory.getGoMoCSRUIURL());

		// log the information to the report
		logInfo("Logging into CSR UI");

		LoginCredentials credentials = EnvironmentDirectory.getGoMoCSRLogin();
		CsrUiHelper csrHelper = new CsrUiHelper(driver);

		// check the that login is successful
		assertTrue(csrHelper.loginToCSRUI(credentials.getUsername(), credentials.getPassword()));
	}

	@Test(description = "GoMo > CSR UI > Perform customer search", dependsOnMethods = { "testLoginToCSRUI" })
	public void testCustomerSearch() {

		logInfo("Performing customer search for billing account ID " + billingAccountID);

		// search for the customer by account number
		// csrHelper.performCustomerSearchByAccountNumberAndSelect(billingAccountID);
		CSRSearchPage page = new CSRSearchPage(driver);
		page.selectCustomerSearchTab();
		page.searchCustomerByDetails(null, null, null, null, Integer.toString(billingAccountID), null);

		// log a screenshot of the
		WaitUtil.waitForSeconds(1);

		page.selectResult(billingAccountID);

		// read the account number displayed at the top of the page
		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);

		// check that the account number is correct
		assertEquals(detailsPage.getAccountNumberFromBanner(), Integer.toString(billingAccountID));
		logPass("Displayed account number: " + detailsPage.getAccountNumberFromBanner());
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "GoMo > CSR UI > View Customer Details", enabled = true)
	public void testViewCustomerDetails() {

		// get the contact uuid from the database
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		// read the contact object from the database
		Contact contact = ContactManagementDAO.getContact(contactUuid);

		// log the contact's details from the contact management database
		logInfo("Customer retrieved from database " + contact.getFirstName() + " " + contact.getLastName() + ", " + contact.getEmailAddress() + ", "
				+ contact.getPhoneNumber() + ", " + contact.getBirthDate());

		// refresh the page (in case a previous test has left the page in a strange
		// state)
		driver.navigate().refresh();

		// set up the page
		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);

		// click the details tab
		detailsPage.selectDetailsTab();

		logInfo("CSR selects the DETAILS tab");

		// check the details in the DETAILS panel
		assertEquals(detailsPage.getFirstNameDisplayed(), contact.getFirstName());
		logPass("First name displayed in DETAILS section: " + detailsPage.getFirstNameDisplayed());

		assertEquals(detailsPage.getLastNameDisplayed(), contact.getLastName());
		logPass("Last name displayed in DETAILS section: " + detailsPage.getLastNameDisplayed());

		assertEquals(detailsPage.getPhoneNumberDisplayed(), contact.getPhoneNumber());
		logPass("Phone number displayed in DETAILS section: " + detailsPage.getPhoneNumberDisplayed());

		assertEquals(detailsPage.getEmailDisplayed(), contact.getEmailAddress());
		logPass("Email address displayed in DETAILS section: " + detailsPage.getEmailDisplayed());

		assertEquals(detailsPage.getDOBDisplayed(), contact.getBirthDate("yyyy-MM-dd"));
		logPass("Date of birth displayed in DETAILS section: " + detailsPage.getDOBDisplayed());

		extentLogger.info("Customer's billing address from DB is: " + contact.getBillingAddress());

		// check the details in the ADDRESS panel
		assertEquals(detailsPage.getAddressLine1Displayed(), contact.getBillingAddress().getAddressLine1());

		// only assert line2 if the customer has it set on their address
		if (!contact.getBillingAddress().getAddressLine2().equals("")) {
			assertEquals(detailsPage.getAddressLine2Displayed(), contact.getBillingAddress().getAddressLine2());
		}

		// only assert line3 if the customer has it set on their address
		if (!contact.getBillingAddress().getAddressLine3().equals("")) {
			assertEquals(detailsPage.getAddressLine3Displayed(), contact.getBillingAddress().getAddressLine3());
		}

		assertEquals(detailsPage.getTownDisplayed(), contact.getBillingAddress().getTown());
		assertEquals(detailsPage.getCountyDisplayed(), contact.getBillingAddress().getCounty().replace(". ", "_"));
		assertEquals(detailsPage.getEircodeDisplayed(), contact.getBillingAddress().getEircode());

		logPass("Correct billing address displayed: " + contact.getBillingAddress());

		// check the details in the top banner
		assertEquals(detailsPage.getNameFromBanner(), contact.getFirstName() + " " + contact.getLastName());
		logPass("Name displayed in banner: " + detailsPage.getNameFromBanner());

		assertEquals(detailsPage.getAccountNumberFromBanner(), Integer.toString(billingAccountID));
		logPass("Billing account ID displayed in banner: " + detailsPage.getAccountNumberFromBanner());

		assertEquals(detailsPage.getEmailFromBanner(), contact.getEmailAddress());
		logPass("Email address displayed in banner: " + detailsPage.getEmailFromBanner());

		assertEquals(detailsPage.getAddressFromBanner(), contact.getBillingAddress().getAddressLine1());
		logPass("Address displayed in banner: " + detailsPage.getAddressFromBanner());
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "GoMo > CSR UI > Edit Customer Details", enabled = true)
	public void testEditCustomerDetails() {

		// get the contact uuid from the database
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		Contact contact = ContactManagementDAO.getContact(contactUuid);
		String emailAddress = ContactManagementDAO.getEmailAddressForUUID(contactUuid);
		String contactNumber = ContactManagementDAO.getContactNumber(contactUuid);

		// log the contact's details from the contact management database
		logInfo("Customer retrieved from database " + contact.getFirstName() + " " + contact.getLastName() + ", " + emailAddress + ", " + contactNumber + ", "
				+ contact.getBirthDate());

		// generate random new details for the customer
		String newFirstName = RandomStringGenerator.getRandomFirstName();
		String newLastName = RandomStringGenerator.getRandomLastName();
		String newDOB = RandomStringGenerator.getRandomDOBString();
		String newPhoneNumber = RandomStringGenerator.getRandomMobilePhoneNumber();

		// update the details via CSR UI
		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);

		detailsPage.enterPersonalDetails(newFirstName, newLastName, newDOB, newPhoneNumber);
		log("Setting the name to " + newFirstName + " " + newLastName);

		long waitEndTime = System.currentTimeMillis() + 30000;

		// wait for the changes to take effect in the database
		while (!contact.getFirstName().equals(newFirstName) && System.currentTimeMillis() < waitEndTime) {
			contact = ContactManagementDAO.getContact(contactUuid);
			WaitUtil.waitForSeconds(2);
		}

		// retrieve the updated contact details from the database

		// check that the details are saved successfully in the database
		assertEquals(contact.getFirstName(), newFirstName);
		assertEquals(contact.getLastName(), newLastName);
		assertEquals(contact.getPhoneNumber(), newPhoneNumber);

		assertEquals(contact.getBirthDate("ddMMyyyy"), newDOB);

		// log the result
		logPass("Details changed to: " + newFirstName + " " + newLastName + ", " + newPhoneNumber + ", " + newDOB);
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description="GoMo > CSR UI > Update Billing Address", enabled = true)
	public void testUpdateBillingAddressByEircode() {

		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		// get a random "new" address from the file
		microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress newAddress = RandomStringGenerator.getRandomAddressFromFile();

		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);
		detailsPage.editBillingAddressByEircode(newAddress.getEircode(), false);

		Address currentBillingAddress = ContactManagementDAO.getBillingAddress(contactUuid);

		// wait for the changes to take effect
		long waitEndTime = System.currentTimeMillis() + 30000;
		while (!currentBillingAddress.getAddressLine1().equals(newAddress.getAddressLine1()) && System.currentTimeMillis() < waitEndTime) {
			currentBillingAddress = ContactManagementDAO.getBillingAddress(contactUuid);
			WaitUtil.waitForSeconds(1);
		}

		// check that the details are saved successfully in the database
		assertEquals(currentBillingAddress.getAddressLine1(), newAddress.getAddressLine1());
		assertEquals(currentBillingAddress.getAddressLine2Str(), newAddress.getAddressLine2Str());
		assertEquals(currentBillingAddress.getAddressLine3Str(), newAddress.getAddressLine3Str());
		assertEquals(currentBillingAddress.getTown(), newAddress.getTown());
		assertEquals(currentBillingAddress.getCounty(), newAddress.getCounty());
		assertEquals(currentBillingAddress.getEircode(), newAddress.getEircode());

		// log the result
		logPass("Details changed to: " + currentBillingAddress.getAddressLine1());
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description="GoMo > CSR UI > Temp Account Unlock", enabled = true)
	public void testTempUnlock() {

		String password = PasswordUtil.getPassword(billingAccountID);

		// determine the email address linked to the account
		String uuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		String emailAddress = ContactManagementDAO.getEmailAddressForUUID(uuid);

		// first we need to lock the account
		// create a new chromedriver to handle the login attempted
		WebDriver myAccountDriver = new ChromeDriver();
		MyGoMoHelper myaccountHelper = new MyGoMoHelper(myAccountDriver);
		myaccountHelper.goToMyAccountUI();

		// attempt 5 incorrect logins to lock the account
		for (int i = 1; i <= 10; i++) {
			myaccountHelper.login(emailAddress, "incorrectpassword");
			logInfo("Customer makes an unsuccessful login attempt " + i);
			myAccountDriver.navigate().refresh();
		}

		log("Attempting login with password " + password);

		// verify that the customer is now unable to log in
		assertEquals(myaccountHelper.login(emailAddress, password), false);
		logPass("Customer login unsuccessful with correct password (as expected), indicating that the account is now locked");

		// carry out the unlock action
		CSRBasePage page = new CSRBasePage(driver);
		page.selectTempUnlock();
		logInfo("CSR selects TEMP_UNLOCK action on CSR UI");

		// wait 5 seconds for the account unlock to take effect
		WaitUtil.waitForSeconds(5);

		// try to log in now that the account is unlocked
		boolean loginSuccessful = myaccountHelper.login(emailAddress, password);

		// verify that the customer can log in again
		assertEquals(loginSuccessful, true);
		logPass("Login successful following CSR password unlock");
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description = "GoMo > CSR UI > Reset Password", enabled = true)
	public void testResetPassword() {

		// determine the email address linked to the account
		String uuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		String emailAddress = ContactManagementDAO.getEmailAddressForUUID(uuid);

		// generate a new password
		String newPassword = "Password" + System.currentTimeMillis();
		logInfo("Setting password to " + newPassword);

		// select the PASSWORD RESET action in CSR UI
		CSRBasePage page = new CSRBasePage(driver);
		page.selectPasswordReset();

		logInfo("CSR selects PASSWORD RESET action");

		// follow deep link
		String deepLink = MailHog.getDeeplinkFromMailhog(emailAddress, "updatePassword");
		logPass("Update password deep link found: " + deepLink);

		// visit the deep link
		WebDriver deeplinkDriver = new ChromeDriver();
		deeplinkDriver.get(deepLink);

		KeycloakInitialVerificationPage initialPage = new KeycloakInitialVerificationPage(deeplinkDriver);
		initialPage.proceedToPasswordScreen();

		KeycloakSetPasswordPage keycloakPage = new KeycloakSetPasswordPage(deeplinkDriver);

		// specify, confirm and save the new password
		keycloakPage.setPassword(newPassword);
		logPass("Password updated to " + newPassword);

		// update the password in the passwords config file for future use
		PasswordUtil.updatePassword(billingAccountID, newPassword);

		// first we need to lock the account
		// create a new chromedriver to handle the login attempted
		WebDriver myAccountDriver = new ChromeDriver();
		MyGoMoHelper myaccountHelper = new MyGoMoHelper(myAccountDriver);
		myaccountHelper.goToMyAccountUI();

		// verify that the customer can log in with the updated password
		assertEquals(myaccountHelper.login(emailAddress, newPassword), true);

		logPass("Customer can successfully log into MyAccount with new password " + newPassword);
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, description="GoMo > CSR UI > View/Edit Contact Preferences",enabled = true)
	public void testContactPreferences() {

		// read the uuid from the DB
		String uuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		// read the permissions set before the test
		ArrayList<ContactManagementPermission> permissionsBefore = ContactManagementDAO.getContactPermissions(uuid);

		// scroll down to the bottom of the page
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// connect to the page
		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);

		detailsPage.selectDetailsTab();
		logInfo("CSR selects the DETAILS tab to access the contact permissions section of the DETAILS tab");

		// click the CUSTOMER button on the contact preferences section
		detailsPage.clickCustomer();
		logInfo("CSR clicks the CUSTOMER button");

		// log a screenshot
		WaitUtil.waitForSeconds(1);

		ContactPreferencesValidator.checkProfilePageCorrect(detailsPage, permissionsBefore, PermissionGroupCode.ACTIVE_CUSTOMER);

		// toggle all the checkboxes on the CUSTOMER section
		detailsPage.toggleAllPermissions();
		logInfo("CSR toggles all permissions on CUSTOMER section and clicks SAVE");

		WaitUtil.waitForSeconds(3);

		// read the permissions set after the update
		ArrayList<ContactManagementPermission> permissionsAfter = ContactManagementDAO.getContactPermissions(uuid);

		// check that the permissions displayed on the screen are correct
		ContactPreferencesValidator.checkProfilePageCorrect(detailsPage, permissionsAfter, PermissionGroupCode.ACTIVE_CUSTOMER);

		// verify that the permissions have been changed/toggled in the DB
		ContactPreferencesValidator.checkAllContactPreferencesChanged(permissionsBefore, permissionsAfter, PermissionGroupCode.ACTIVE_CUSTOMER);

		// click the NOT CUSTOMER button
		detailsPage.clickNotCustomer();
		logInfo("CSR clicks the NOT CUSTOMER button");

		// read the permissions for NO_LONGER_CUSTOMER before editing them
		permissionsBefore = ContactManagementDAO.getContactPermissions(uuid);

		// check that the permissions displayed on the screen are correct
		ContactPreferencesValidator.checkProfilePageCorrect(detailsPage, permissionsBefore, PermissionGroupCode.NO_LONGER_CUSTOMER);

		// toggle all the checkboxes on the CUSTOMER section
		detailsPage.toggleAllPermissions();
		logInfo("CSR toggles all permissions on CUSTOMER section and clicks SAVE");

		WaitUtil.waitForSeconds(3);

		// read the permissions set after the update
		permissionsAfter = ContactManagementDAO.getContactPermissions(uuid);

		// check that the permissions displayed on the screen are correct
		ContactPreferencesValidator.checkProfilePageCorrect(detailsPage, permissionsAfter, PermissionGroupCode.NO_LONGER_CUSTOMER);

		// verify that the permissions have been changed/toggled in the DB
		ContactPreferencesValidator.checkAllContactPreferencesChanged(permissionsBefore, permissionsAfter, PermissionGroupCode.NO_LONGER_CUSTOMER);
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, enabled = true, description = "GoMo > CSR UI > Modify NDD settings")
	public void testUpdateNDDSettings() {

		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);

		assertNotNull(service);

		logInfo("Test is being performed on MSISDN " + service.getName());

		// connect to the page
		CSRBasePage basePage = new CSRBasePage(driver);

		// select the MSISDN from the top banner
		basePage.selectService(service.getName());

		WaitUtil.waitForSeconds(2);

		CSRServicePage servicePage = new CSRServicePage(driver);

		// click the NDD tab
		servicePage.clickTabNDD();

		WaitUtil.waitForSeconds(2);

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

		CSRNDDPage nddPage = new CSRNDDPage(driver);

		// check that the correct NDD setting is displayed before the change
		assertEquals(nddPage.getSelectedNDDValue(), currentNDDSetting.toString());

		// for each NDD value
		for (NDDSetting setting : nddValues) {

			// select the value
			nddPage.selectNDD(setting.toString());
			logPass("NDD value " + setting.toString() + " selected");

			WaitUtil.waitForSeconds(1);

			// save the changes
			nddPage.clickSaveNDD();
			WaitUtil.waitForSeconds(5);

			// refresh the current NDD setting from the database
			currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(service.getName());

			// verify that the setting has changed in the database
			assertEquals(currentNDDSetting.toString(), setting.toString());

			// verify that the setting has changed on the screen
			assertEquals(nddPage.getSelectedNDDValue(), currentNDDSetting.toString());
			logPass("Database shows NDD setting " + currentNDDSetting);
		}
	}

	@Test(dependsOnMethods = { "testCustomerSearch" }, enabled = true, description = "GoMo > CSR UI > View the PIN, PUK and IMSI")
	public void testViewPinPukIMSI() {

		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		assertNotNull(service);

		logInfo("Test is being performed on MSISDN " + service.getName());

		// connect to the page
		CSRBasePage basePage = new CSRBasePage(driver);

		// select the MSISDN from the top banner
		basePage.selectService(service.getName());

		WaitUtil.waitForSeconds(5);

		CSRServicePage servicePage = new CSRServicePage(driver);
		log(servicePage.getPIN());
		log(servicePage.getPUK());
		log(servicePage.getIMSI());

		// read the active sim pack from on the service
		SimCard activePack = SubscriptionManagementDAO.getActiveSimCard(service.getName());

		// retrieve the expected pin and puk from the inventory management API
		SimDetails securityDetails = InventoryManagementAPI.getSimDetailsFromInventory(activePack.getIccid());

		// check that the details displayed are correct
		assertEquals(servicePage.getPIN(), securityDetails.getPin1());
		assertEquals(servicePage.getPUK(), securityDetails.getPuk1());
		assertEquals(servicePage.getIMSI(), securityDetails.getImsi());

		logPass("Correct details displayed on the page: [Pin: " + servicePage.getPIN() + "], [Puk: " + servicePage.getPUK() + "], [IMSI: "
				+ servicePage.getIMSI() + "]");
	}
	
	@Test(dependsOnMethods = { "testCustomerSearch" }, enabled = true, description = "GoMo > CSR UI > Make Adjustment on the account")
	public void testAdjustments() {

		// read the AR balance before the adjustment
		Balance b = AccountsReceivableDAO.getBalance(billingAccountID);
		int balanceBeforeTest = (int)b.getNonOverdueAmount();
		
		// update the username on existing adjustments for this customer (to avoid agent constraints)
		AdjustmentDAO.updateAgentUsernamesOnAdjustments(billingAccountID, "galaxion_test_automation@eir.ie");

		// connect to the page
		CSRBasePage basePage = new CSRBasePage(driver);

		// select the MSISDN from the top banner
		basePage.selectPaymentCenterAdjustmentsTab();

		WaitUtil.waitForSeconds(2);

		AdjustmentReason reason = AdjustmentReason.MISCHARGE;
		int amount=999;
		
		// place the adjustment
		CSRPaymentCenterPage paymentsPage = new CSRPaymentCenterPage(driver);
		String adjustmentComment = paymentsPage.makeAdjustment(reason, amount);
		
		Adjustment a=null;
		
		long waitEndTime=System.currentTimeMillis()+30000;
		
		// wait 30 seconds for the adjustment to complete
		while(a==null && System.currentTimeMillis()<waitEndTime) {
			a= AdjustmentDAO.getAdjustmentByComment(adjustmentComment);
			WaitUtil.waitForSeconds(1);
		}
		
		// check that the adjustment is correct in the database
		assertNotNull(a);
		assertEquals(a.getAmount(), amount);
		assertEquals(adjustmentComment, a.getText());
		
		logPass("Adjustment " + a.getAdjustmentReasonKey());
		
		// read the AR balance after the adjustment
		b = AccountsReceivableDAO.getBalance(billingAccountID);
		int balanceAfterTest = (int)b.getNonOverdueAmount();
		
		// determine whether the adjustment reason is credit or debit
		RefAdjustmentReason rs = AdjustmentDAO.getRefAdjustmentReason("MISCHARGE");
		
		// verify that the balance is adjusted accordingly
		if(rs.getAdjustmentFinancialType().equals("CREDIT")) {
			assertEquals(balanceBeforeTest + amount, balanceAfterTest);
		}
		else if(rs.getAdjustmentFinancialType().equals("DEBIT")) {
			assertEquals(balanceBeforeTest - amount, balanceAfterTest);
		}
		else if(rs.getAdjustmentFinancialType().equals("REFUND")) {

		}
		
		driver.get(driver.getCurrentUrl());
	}
	
	@Test(dependsOnMethods = { "testCustomerSearch" }, description="GoMo > CSR UI > Change Email", enabled = true)
	public void testChangeEmailAddress() {
		
		// read the uuid from the DB
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		String emailAddressBeforeTest = ContactManagementDAO.getEmailAddressForUUID(contactUuid);
		String password = PasswordUtil.getPassword(billingAccountID);
		
		logInfo("Initial email address: " + emailAddressBeforeTest);

		// generate a new, unique email address
		String newEmail = "steve.auto_" + System.currentTimeMillis() + "@gomo.ie";
		logInfo("Changing email address to " + newEmail);

		// submit the email address change in CSR UI
		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);

		// select the DETAILS tab
		detailsPage.selectDetailsTab();

		// select EDIT to edit the customer's details
		detailsPage.selectEditDetails();

		// enter the updated values
		detailsPage.enterEmailAddress(newEmail);
		detailsPage.enterConfirmEmailAddress(newEmail);

		// save the changes
		detailsPage.saveCustomerDetails();

		// retrieve the deep link
		String deepLink = MailHog.getDeeplinkFromMailhog(newEmail, "confirmEmailChange");
		extentLogger.pass("Deep link retrieved: " + deepLink);
		log("Change email deep link = " + deepLink);

		WebDriver deepLinkDriver = new ChromeDriver();

		// visit the deep link
		deepLinkDriver.get(deepLink);

		// create a separate driver object to check the login on MyAccount
		// WebDriver myAccountDriver = new ChromeDriver();
		MyGoMoHelper myaccountHelper = new MyGoMoHelper(deepLinkDriver);
		myaccountHelper.goToMyAccountUI();

		// determine whether login is successful
		boolean loginSuccessful = myaccountHelper.login(newEmail, password);
		logPass("Login successful with new email " + loginSuccessful + " and password " + password);
	}
	
	@Test(dependsOnMethods = { "testCustomerSearch" }, enabled = true, description = "GoMo > CSR UI > Schedule Cancellation")
	public void testScheduleCancellation() {

		// select an active service from the account
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		assertNotNull(service);
		logInfo("Test is being performed on MSISDN " + service.getName());

		// check whether there are any existing order services for this service before test start
		OmService existingService = OrderManagementDAO.getOrderService(service.getName(),ProvisioningAction.TERMINATE_SUBSCRIBER);
		
		// connect to the page
		CSRBasePage basePage = new CSRBasePage(driver);

		// select the MSISDN from the top banner
		basePage.selectService(service.getName());

		CSRServicePage servicePage = new CSRServicePage(driver);
		servicePage.clickCancelNumber();
		
		WaitUtil.waitForSeconds(2);
		
		servicePage.selectReason("Not using the service");
		
		WaitUtil.waitForSeconds(2);
		servicePage.clickCancelNow();
		
		OmService orderManagementService = null;
		
		// wait 30 seconds for it to reach order-management's "service" table
		long waitEndTime=System.currentTimeMillis()+30000;
		
		while(orderManagementService==null && System.currentTimeMillis() < waitEndTime) {
			orderManagementService = OrderManagementDAO.getOrderService(service.getName(),ProvisioningAction.TERMINATE_SUBSCRIBER);
			WaitUtil.waitForSeconds(1);
			
			// make sure that we haven't found an old termination request
			if(orderManagementService!=null && orderManagementService.getId()==existingService.getId()) {
				orderManagementService=null;
			}
		}
		
		assertNotNull(orderManagementService);
		logPass("Termination request has been sent to order-management's SERVICE table with ID " + orderManagementService.getId());
		
		// verify that a termination request has been entered into the TERMINATION_REQUEST table
		TerminationRequest tr = OrderManagementDAO.getTerminationRequest(orderManagementService.getId());
		assertNotNull(tr);
		
		// verify that the quartz trigger has been created
		QuartzTrigger trigger = OrderManagementDAO.getQuartsTrigger(orderManagementService.getId());
		assertNotNull(trigger);
	}
	
	@Test(dependsOnMethods = { "testCustomerSearch" }, description="GoMo > CSR UI > View Order History")
	public void testViewOrders() {
		
		this.goToAccountPage();
		
		// read the list of orders from the database
		ArrayList<ProductOrder> orders = OrderManagementDAO.getOrdersForContact(contactUuid);
		
		// set up the page
		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);

		// click the details tab
		detailsPage.selectOrdersTab();
		logInfo("CSR selects the ORDERS tab");
		
		WaitUtil.waitForSeconds(5);
		
		CSROrdersPage ordersPage = new CSROrdersPage(driver);
		int index=1;
		
		// for each order belonging to the customer...
		for(ProductOrder order:orders) {
			if(index<=10) {
				assertEquals(ordersPage.getCreatedDate(index), order.getCreatedDate("dd/MM/yyyy"));
				assertEquals(ordersPage.getOrderReference(index), order.getReference());
				
				if(order.getStatus().toUpperCase().equals("IN_PROGRESS")) {
					assertEquals(ordersPage.getOrderStatus(index).toUpperCase(), "IN PROGRESS IN ORDER MANAGEMENT");
				}
				else {
					assertEquals(ordersPage.getOrderStatus(index).toUpperCase(), order.getStatus().toUpperCase());
				}
				
				
				// verify that the correct order type is displayed
				if(order.getOrder_type().equals("REPLACEMENT")) {
					assertEquals(ordersPage.getOrderType(index), "SIM Replacement");
				}
				else if(order.getOrder_type().equals("CROSS_SELL")) {
					assertEquals(ordersPage.getOrderType(index), "Additional Purchase");
				}
				else if(order.getOrder_type().equals("ACQUISITION")) {
					assertEquals(ordersPage.getOrderType(index), "Initial Order");
				}
				
				// retrieve the order cost from the database
				int orderCost = OrderManagementDAO.getOrderPrice(order.getReference());
				
				// verify that the correct order cost is displayed
				if(orderCost==0) {
					assertEquals(ordersPage.getOrderCost(index), "€0");
				}
				else {
					assertEquals(ordersPage.getOrderCost(index), "€"+StringUtil.toCurrency(orderCost));
				}
				
				logPass("Order " + order.getReference() + " correctly displayed");
			}
			index++;
		}		
	}
	
	public void goToAccountPage() {
		String url=EnvironmentDirectory.getGoMoCSRUIURL() + "/customerDashboardBillingAccount/" + contactUuid + "/" + billingAccountID;
		driver.get(url);
	}
}
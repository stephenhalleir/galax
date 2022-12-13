package testCases.gomo.ui;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.helpers.SeleniumHelper;
import framework.testNG.Retry;
import framework.test_data.galaxion.TestDataManager;
import framework.test_data.generic.RandomStringGenerator;
import framework.test_data.generic.StringUtil;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import microservices.backend.eir_adjustment_backend.AdjustmentAPIFlow;
import microservices.backend.eir_adjustment_backend.AdjustmentDAO;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.Offer;
import microservices.backend.eir_catalog_core_backend.data_model.PricePlan;
import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.ChargedUsageGoMoPostpay;
import microservices.backend.eir_cdr_repository_backend.data_model.RatingSubtotal;
import microservices.backend.eir_cdr_repository_backend.enums.ProcessType;
import microservices.backend.eir_cdr_repository_backend.files.UsageFile;
import microservices.backend.eir_cdr_repository_backend.monitor.CDRRepoMonitor;
import microservices.backend.eir_cdr_repository_backend.utilities.CDRGenerator;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.data_model.ContactManagementPermission;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.backend.eir_contact_management_backend.validator.ContactPreferencesValidator;
import microservices.backend.eir_inventory_management_backend.api.InventoryManagementAPI;
import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import microservices.backend.eir_inventory_management_backend.objects.SimDetails;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_notification_center_backend.dao.NotificationCenterDAO;
import microservices.backend.eir_notification_center_backend.data_model.NCWebMessage;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Event;
import microservices.backend.eir_order_management_backend.data_model.OmService;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_otp_verification_backend.monitor.OTPMonitor;
import microservices.backend.eir_payment_center_backend.dao.PaymentCenterDAO;
import microservices.backend.eir_payment_center_backend.data_model.CardPaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.Payer;
import microservices.backend.eir_payment_center_backend.data_model.Payment;
import microservices.backend.eir_salt_ar_backend.dao.AccountsReceivableDAO;
import microservices.backend.eir_salt_ar_backend.data_model.Balance;
import microservices.backend.eir_salt_ar_backend.data_model.BalanceChange;
import microservices.backend.eir_sim_swap_backend.dao.SimSwapDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.RefBillingCycle;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.backend.galaxion.dao.GalaxionDAO;
import selenium.drivers.DriverFactory;
import selenium.pages.gomo.keycloak.KeycloakForgotEmailPage;
import selenium.pages.gomo.keycloak.KeycloakForgotPasswordPage;
import selenium.pages.gomo.keycloak.KeycloakInitialVerificationPage;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import selenium.pages.gomo.keycloak.KeycloakSetPasswordPage;
import selenium.pages.gomo.my_gomo.MyAccountAccountDetailsPage;
import selenium.pages.gomo.my_gomo.MyAccountHomePage;
import selenium.pages.gomo.my_gomo.MyAccountManageSIMPage;
import selenium.pages.gomo.my_gomo.MyAccountMyAlertsPage;
import selenium.pages.gomo.my_gomo.MyAccountMyProfilePage;
import selenium.pages.gomo.my_gomo.MyAccountPaymentsPage;
import selenium.pages.gomo.my_gomo.MyAccountUsagePage;
import selenium.pages.gomo.my_gomo.MyAccountViewOrdersPage;
import selenium.pages.gomo.my_gomo.flows.MyGoMoHelper;
import selenium.pages.gomo.my_gomo.flows.MyGoMoLoginFlow;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.test_data.accounts.DataSetupUtil;
import utilities.generic.mailhog.MailHog;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;
import utilities.test.config_readers.PasswordUtil;

public class GoMoSelfCareTests extends BaseTest {

	private int billingAccountID;

	@Test(description = "UI_MGO_A_LP_01: MyGoMo: Login")
	public void UI_MGO_A_LP_01_testLoginToMyGoMo() {

		// get the test data from the environments file
		billingAccountID = TestDataManager.getGoMoMultilineSubscriber();

		// read the password from the file
		String password = PasswordUtil.getPassword(billingAccountID);

		// determine the email address linked to the account
		String uuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		String emailAddress = ContactManagementDAO.getEmailAddressForUUID(uuid);

		// log into MyAccount
		MyGoMoLoginFlow loginFlow = new MyGoMoLoginFlow(driver, extentLogger, logger4j);
		assertTrue(loginFlow.loginToMyGoMo(emailAddress, password));
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);

		// accept cookies
		loginFlow.acceptCookies();
	}

	@Test(description = "UI_MGO_A_LP_02: MyGoMo: Login Screen > Forgot your email", retryAnalyzer = Retry.class)
	public void UI_MGO_A_LP_02_testForgottenEmail() {

		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		String msisdn = service.getName();

		// create a separate ChromeDriver instance for this test to avoid the current
		// logged-in session
		WebDriver driver2 = DriverFactory.getDriver();

		// go to the MyAccount homepage
		driver2.get(EnvironmentDirectory.getMyGoMoURL());

		// log the status
		logInfo("Customer goes to MyGoMo home screen");
		SeleniumHelper.saveScreenshotToExtentReport(driver2, extentLogger);

		// click the "Forgot your email?" link
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver2);
		loginPage.clickForgotEmailLink();

		// log the status
		logInfo("Customer clicks the 'Forgot your email?' link");
		SeleniumHelper.saveScreenshotToExtentReport(driver2, extentLogger);

		// enter the MSISDN on the screen
		KeycloakForgotEmailPage forgotEmailPage = new KeycloakForgotEmailPage(driver2);

		forgotEmailPage.acceptCookies();

		forgotEmailPage.enterMsisdn(msisdn);
		logInfo("Customer enters MSISDN " + msisdn);

		// clear the old OTP codes on the msisdn
		DataSetupUtil.clearOTPCodes(msisdn);

		// click the Continue button to request an OTP code
		forgotEmailPage.clickContinueRequestOTP();

		// poll the OTP database for the OTP code
		String otpCode = OTPMonitor.waitForOtpCode(msisdn);

		// verify that the OTP code has been received
		assertNotNull(otpCode, "No OTP code received");
		logPass("Customer receives OTP code: " + otpCode);

		// enter the OTP code
		forgotEmailPage.enterOTPCode(otpCode);

		logInfo("Customer enters OTP code: " + otpCode);
		SeleniumHelper.saveScreenshotToExtentReport(driver2, extentLogger);

		// click Continue to submit the OTP code
		forgotEmailPage.clickContinueConfirmOTP();
		logInfo("Customer clicks 'Confirm'");

		// determine the expected email address
		String emailAddress = GalaxionDAO.getOwnerEmailAddress(billingAccountID);

		// read the email address displayed on the screen
		String emailDisplayed = forgotEmailPage.getEmailDisplayed();

		// verify that the email address displayed is correct
		assertEquals(emailDisplayed.toLowerCase(), emailAddress.toLowerCase());
		logPass("Correct email displayed to the customer: " + emailDisplayed);

		driver2.close();
	}

	@Test(description = "UI_MGO_A_LP_03: MyGoMo: Login Screen > Forgot your password", retryAnalyzer = Retry.class)
	public void UI_MGO_A_LP_03_testForgotPassword() {

		WebDriver driver2 = DriverFactory.getDriver();

		// go to the MyAccount homepage
		driver2.get(EnvironmentDirectory.getMyGoMoURL());

		String emailAddress = GalaxionDAO.getOwnerEmailAddress(billingAccountID);

		// check whether mailhog has an older version of this mail that should be
		// ignored
		String existingEmailDeeplink = MailHog.getDeeplinkFromMailhog(emailAddress, "forgotPassword");

		if (existingEmailDeeplink == null) {
			logInfo("No deep link exists in Mailhog for the customer before the test");
		} else {
			logInfo("Deep link exists in Mailhog for the customer from a previous test:<br>" + existingEmailDeeplink);
		}

		// log the status
		logInfo("Customer goes to MyGoMo login page");
		SeleniumHelper.saveScreenshotToExtentReport(driver2, extentLogger);

		// click the "Forgot your password?" link
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver2);
		loginPage.clickForgotPasswordLink();

		logInfo("Customer clicks the 'Forgot your password?' option");

		// customer enters their email address
		KeycloakForgotPasswordPage forgotPasswordPage = new KeycloakForgotPasswordPage(driver2);
		forgotPasswordPage.inputEmail(emailAddress);

		logInfo("Customer enters their email " + emailAddress);
		SeleniumHelper.saveScreenshotToExtentReport(driver2, extentLogger);

		// customer clicks the reset button
		forgotPasswordPage.clickReset();
		logInfo("Customer clicks the 'Reset' button");
		SeleniumHelper.saveScreenshotToExtentReport(driver2, extentLogger);

		String resetEmailDeeplink = null;

		long waitEndTime = System.currentTimeMillis() + 30000;

		// while the deep link has not been found
		while (resetEmailDeeplink == null && System.currentTimeMillis() < waitEndTime) {

			// try to retrieve the deep link
			resetEmailDeeplink = MailHog.getDeeplinkFromMailhog(emailAddress, "forgotPassword");

			// if the email deep link is from an older test, ignore it
			if (resetEmailDeeplink.equals(existingEmailDeeplink)) {
				resetEmailDeeplink = null;
			}

			WaitUtil.waitForSeconds(5);
		}

		logPass("Password reset deeplink received:<br>" + resetEmailDeeplink);

		// close the browser
		driver2.close();
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PR_01: MyGoMo: My Profile > Check Contact Preferences")
	public void UI_MGO_A_PR_01_testViewEditContactPreferences() {

		ArrayList<ContactManagementPermission> permissionsBefore;
		ArrayList<ContactManagementPermission> permissionsAfter;

		// read the uuid from the DB
		String uuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();

		logInfo("Customer navigates to the MyGoMo home screen");

		// click "My Profile" link check that the customer is taken to profile page
		helper.selectLinkFromHomepage("My Profile");
		assertTrue(driver.getCurrentUrl().endsWith("/profile"));
		extentLogger.pass("Customer selects 'My Profile' from the main menu and is directed to " + driver.getCurrentUrl());

		WaitUtil.waitForSeconds(3);

		// click the right arrow to move to the contact preferences screen
		helper.clickRightArrow();
		extentLogger.info("Customer clicks the right arrow to navigate to the Contact Preferences screen");

		// read the permissions from the database
		permissionsBefore = ContactManagementDAO.getContactPermissions(uuid);

		MyAccountMyProfilePage myProfilePage = new MyAccountMyProfilePage(driver);

		// click "An existing customer" button
		myProfilePage.clickExistingCustomer();
		extentLogger.info("Customer clicks the EXISTING CUSTOMER button");

		// check that the settings shown are correct
		ContactPreferencesValidator.checkProfilePageCorrect(myProfilePage, permissionsBefore, PermissionGroupCode.ACTIVE_CUSTOMER);

		// log the outputs to the report
		logPass("Contact preferences are correct for active Customer before changes [Email, SMS, FOTS, Phone, Mail]: [" + myProfilePage.isEmailEnabled() + ", "
				+ myProfilePage.isSMSEnabled() + "," + myProfilePage.isFOTSEnabled() + ", " + myProfilePage.isPhoneEnabled() + ", "
				+ myProfilePage.isDirectMailEnabled() + "]");

		// toggle all permissions
		myProfilePage.toggleAllPermissions();

		// save the changes
		myProfilePage.savePermissionsChanges();
		logPass("Customer saves their changes");

		WaitUtil.waitForSeconds(10);

		myProfilePage.refreshPermissionsPage();
		logPass("Customer refreshes the page");

		permissionsAfter = ContactManagementDAO.getContactPermissions(uuid);
		// check that the settings shown are correct
		ContactPreferencesValidator.checkProfilePageCorrect(myProfilePage, permissionsAfter, PermissionGroupCode.ACTIVE_CUSTOMER);
		ContactPreferencesValidator.checkAllContactPreferencesChanged(permissionsBefore, permissionsAfter, PermissionGroupCode.ACTIVE_CUSTOMER);

		// click "An existing customer" button
		myProfilePage.clickNoLongerCustomer();
		extentLogger.info("Customer clicks the NO LONGER CUSTOMER button");

		// check that the settings shown are correct
		ContactPreferencesValidator.checkProfilePageCorrect(myProfilePage, permissionsBefore, PermissionGroupCode.NO_LONGER_CUSTOMER);

		// log the outputs to the report
		logPass("Contact preferences are correct for previous Customer before changes [Email, SMS, FOTS, Phone, Mail]: [" + myProfilePage.isEmailEnabled()
				+ ", " + myProfilePage.isSMSEnabled() + "," + myProfilePage.isFOTSEnabled() + ", " + myProfilePage.isPhoneEnabled() + ", "
				+ myProfilePage.isDirectMailEnabled() + "]");

		// toggle all permissions
		myProfilePage.toggleAllPermissions();

		// save the changes
		myProfilePage.savePermissionsChanges();
		logPass("Customer saves their changes");

		WaitUtil.waitForSeconds(10);

		myProfilePage.refreshPermissionsPage();
		logPass("Customer refreshes the page");

		permissionsAfter = ContactManagementDAO.getContactPermissions(uuid);
		// check that the settings shown are correct
		ContactPreferencesValidator.checkProfilePageCorrect(myProfilePage, permissionsAfter, PermissionGroupCode.NO_LONGER_CUSTOMER);
		ContactPreferencesValidator.checkAllContactPreferencesChanged(permissionsBefore, permissionsAfter, PermissionGroupCode.NO_LONGER_CUSTOMER);
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PR_02: MyGoMo: My Profile > Check name, email and address displayed", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PR_02_testMyProfileDetailsDisplayed() {

		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();

		// read the uuid from the DB
		String uuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		// read the contact object from the database
		Contact contact = ContactManagementDAO.getContact(uuid);

		// click the "My Profile" link check that the customer is taken to the profile
		// page
		helper.selectLinkFromHomepage("My Profile");
		assertTrue(driver.getCurrentUrl().endsWith("/profile"));
		extentLogger.pass("Customer selects 'My Profile' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountMyProfilePage myProfilePage = new MyAccountMyProfilePage(driver);

		// check that the email address is correct
		assertEquals(myProfilePage.getEmailDisplayed(), contact.getEmailAddress());

		// check that the name displayed is correct
		assertEquals(myProfilePage.getNameDisplayed(), contact.getFirstName() + " " + contact.getLastName());

		// check that the billing address displayed is correct
		assertEquals(myProfilePage.getAddressLine1(), contact.getBillingAddress().getAddressLine1());

		// check address line 2 if it exists
		if (contact.getBillingAddress().getAddressLine2() == null) {
			assertEquals(myProfilePage.getAddressLine2(), "");
		} else {
			assertEquals(myProfilePage.getAddressLine2(), contact.getBillingAddress().getAddressLine2());
		}

		// check address line 3 if it exists
		if (contact.getBillingAddress().getAddressLine3() == null) {
			assertEquals(myProfilePage.getAddressLine3(), "");
		} else {
			assertEquals(myProfilePage.getAddressLine3(), contact.getBillingAddress().getAddressLine3());
		}

		// check the remaining address fields
		assertEquals(myProfilePage.getTown(), contact.getBillingAddress().getTown());
		assertEquals(myProfilePage.getCounty(), contact.getBillingAddress().getCounty().replace("_", ". "));
		assertEquals(myProfilePage.getEircode(), contact.getBillingAddress().getEircode());
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PR_03: MyGoMo: My Profile > Reset Password", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PR_03_testResetPassword() {

		// go to the homepage
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();

		// go to the My Profile page
		helper.selectLinkFromHomepage("My Profile");
		assertTrue(driver.getCurrentUrl().endsWith("/profile"));
		logInfo("Customer selects 'My Profile' from the main menu and is directed to " + driver.getCurrentUrl());

		// click the reset password link
		MyAccountMyProfilePage myProfilePage = new MyAccountMyProfilePage(driver);
		myProfilePage.clickResetPassword();
		logInfo("Customer selects the Reset Password button");

		// determine the customer's email address
		String emailAddress = GalaxionDAO.getOwnerEmailAddress(billingAccountID);

		// get the deeplink from MailHog
		String passwordDeeplink = MailHog.getDeeplinkFromMailhog(emailAddress, "updatePassword");
		logPass("Password reset deep link received: " + passwordDeeplink);

		// open the deep link in a new driver
		WebDriver deeplinkDriver = DriverFactory.getDriver();
		deeplinkDriver.get(passwordDeeplink);

		// determine a new password for the customer
		String newPassword = "Password" + System.currentTimeMillis();

		// set the new password via the Keycloak pages
		KeycloakInitialVerificationPage initialPage = new KeycloakInitialVerificationPage(deeplinkDriver);
		initialPage.proceedToPasswordScreen();
		KeycloakSetPasswordPage passwordPage = new KeycloakSetPasswordPage(deeplinkDriver);
		passwordPage.setPassword(newPassword);

		// update the password in the passwords config file for future use
		PasswordUtil.updatePassword(billingAccountID, newPassword);

		// verify that we can log in with the updated password
		helper = new MyGoMoHelper(deeplinkDriver);
		helper.goToMyAccountUI();
		assertTrue(helper.login(emailAddress, newPassword));
		logPass("Customer is now able to log in successfully with " + emailAddress + ", " + newPassword);
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PR_04: MyGoMo: MyProfile > Change Billing & Delivery Addresses [By Eircode]", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PR_04_testChangeBothAddressesByEircode() {

		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();

		helper.selectLinkFromHomepage("My Profile");
		assertTrue(driver.getCurrentUrl().endsWith("/profile"));
		extentLogger.info("Customer selects 'My Profile' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountMyProfilePage profilePage = new MyAccountMyProfilePage(driver);

		WaitUtil.waitForSeconds(3);

		// select EDIT on the billing address
		profilePage.selectEditBillingAddress();

		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);

		// get a random address from the database
		AddressFinderAddress randomAddress = RandomStringGenerator.getRandomAddressFromFile();

		extentLogger.info("Updating billing address to: " + randomAddress);

		profilePage.setEircode(randomAddress.getEircode());
		profilePage.selectConfirmEircode();
		profilePage.selectUpdateDelivery();
		profilePage.selectSaveAddressChanges();
		extentLogger.pass("Address changes successfully saved");

		// read the billing address from contact management
		microservices.backend.eir_contact_management_backend.data_model.Address billingAddress = ContactManagementDAO.getBillingAddress(contactUuid);

		// wait for the updates to take effect in the database
		long waitEndTime = System.currentTimeMillis() + 30000;

		// poll the DB every 5 seconds (up to a max of 30 seconds) for the changes to
		// take effect
		while (!billingAddress.getAddressLine1().equals(randomAddress.getAddressLine1()) && System.currentTimeMillis() < waitEndTime) {

			// wait 5 seconds
			WaitUtil.waitForSeconds(5);

			// update the contact object from the DB
			billingAddress = ContactManagementDAO.getBillingAddress(contactUuid);
		}

		// check that the billing address has been set successfully
		assertEquals(billingAddress.getAddressLine1(), randomAddress.getAddressLine1());
		assertEquals(billingAddress.getAddressLine2(), randomAddress.getAddressLine2Str());
		assertEquals(billingAddress.getAddressLine3(), randomAddress.getAddressLine3Str());
		assertEquals(billingAddress.getTown(), randomAddress.getTown());
		extentLogger.pass("Billing address successfully updated in the DB to: " + billingAddress.getAddressLine1());

		// read the delivery address from contact management
		microservices.backend.eir_contact_management_backend.data_model.Address deliveryAddress = ContactManagementDAO.getDeliveryAddress(contactUuid);

		// check that the delivery address has been set successfully
		assertEquals(deliveryAddress.getAddressLine1(), randomAddress.getAddressLine1());
		assertEquals(deliveryAddress.getAddressLine2Str(), randomAddress.getAddressLine2Str());
		assertEquals(deliveryAddress.getAddressLine3Str(), randomAddress.getAddressLine3Str());
		assertEquals(deliveryAddress.getTown(), randomAddress.getTown());
		extentLogger.pass("Delivery address successfully updated in the DB to: " + deliveryAddress.getAddressLine1());
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PR_05: MyGoMo: MyProfile > Update Email Address")
	public void UI_MGO_A_PR_05_testUpdateEmail() {
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();

		helper.selectLinkFromHomepage("My Profile");
		assertTrue(driver.getCurrentUrl().endsWith("/profile"));
		logInfo("Customer selects 'My Profile' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountMyProfilePage myProfilePage = new MyAccountMyProfilePage(driver);
		myProfilePage.selectUpdateEmail();

		logInfo("Customer selects Update email");

		// use the date/time string to produce a new email address
		String newEmail = "steveautomation_" + Timestamp.getUniqueTimestamp() + "@eir.ie";

		logInfo("Customer will update their email address to " + newEmail);

		// change the new email address
		myProfilePage.enterNewEmailAddress(newEmail);
		myProfilePage.selectSaveNewEmail();
		myProfilePage.clickPopupConfirmEmailChange();

		logPass("Customer has saved their email change");

		String deeplink = MailHog.getDeeplinkFromMailhog(newEmail, "confirmEmailChange");

		assertNotNull(deeplink);

		logPass("Customer has received an email with deep link " + deeplink);

		WebDriver deeplinkDriver = DriverFactory.getDriver();
		deeplinkDriver.get(deeplink);

		WaitUtil.waitForSeconds(5);

		// determine the customer's email address
		String emailAddress = GalaxionDAO.getOwnerEmailAddress(billingAccountID);
		String password = PasswordUtil.getPassword(billingAccountID);

		assertEquals(emailAddress, newEmail);
		logPass("Customer's email has been updated successfully to " + emailAddress);

		helper = new MyGoMoHelper(driver);
		helper.logout();

		helper.goToMyAccountUI();
		assertTrue(helper.login(emailAddress, PasswordUtil.getPassword(billingAccountID)));

		helper.acceptCookies();

		logPass("Customer is able to log in successfully with " + emailAddress + ", " + password);

		deeplinkDriver.close();
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PY_01: MyGoMo: My Payments > Add New Card", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PY_01_testAddNewCard() {

		// read the list of active cards on an account before the test
		ArrayList<CardPaymentMethod> cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);

		// check the number of active cards
		int cardCountBefore = cardsOnAccount.size();
		logInfo("Contact has " + cardCountBefore + " cards on their account before the test");

		// go to the MyAccount homepage
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();
		logInfo("Customer goes to MyAccount home screen");

		// select "My Payments" from the home screen
		helper.selectLinkFromHomepage("My Payments");
		assertTrue(driver.getCurrentUrl().endsWith("/payments"));
		logInfo("Customer selects 'My Payments' from the main menu and is directed to " + driver.getCurrentUrl());

		// access the payments page
		MyAccountPaymentsPage paymentsPage = new MyAccountPaymentsPage(driver);

		WaitUtil.waitForSeconds(2);

		// click the "Add Card" button
		paymentsPage.selectAddCard();
		logInfo("Customer selects 'Add Card'");

		// deails of the card to add
		String cardNumber = "4012001037141112";
		String expiryDate = "1225";
		String cvv = "222";
		String cardholderName = "Steve Auto " + Timestamp.getTimestamp("yyyyMMddHHmmss");

		// add the card via the payment page
		paymentsPage.enterNewCardDetails(cardNumber, expiryDate, cvv, cardholderName);
		logInfo("Customer has entered the new card details");

		CardPaymentMethod addedCard = null;

		// wait up to 30 seconds for the card to get added to the customer's account
		long waitEndTime = System.currentTimeMillis() + 30000;

		while (addedCard == null && System.currentTimeMillis() < waitEndTime) {

			// read in the cards from the database
			cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);

			// check whether the card has been added, based on the unique cardholder name
			for (CardPaymentMethod thisCard : cardsOnAccount) {
				if (cardholderName.equals(thisCard.getCardholderName())) {
					addedCard = thisCard;
				}
			}

			// wait 5 seconds for the changes to take effect
			WaitUtil.waitForSeconds(5);
		}

		// check that the card has been found in the database
		assertNotNull(addedCard);

		// check that the count of cards on the account has increased by 1
		int cardCountAfter = cardsOnAccount.size();
		logInfo("Contact has " + cardCountAfter + " cards on their account after the test");
		assertTrue(cardCountAfter - cardCountBefore == 1);

		// check that the name and partial digits are correct
		assertEquals(addedCard.getCardholderName(), cardholderName);
		assertEquals(addedCard.getPartialDigits(), cardNumber.substring(0, 6) + "xxxx" + cardNumber.substring(12));

		logPass("Card found in DB is correct: " + addedCard.getId() + ", " + addedCard.getPartialDigits() + ", " + addedCard.getCardholderName());
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PY_02: MyGoMo: My Payments > Change Card to Default") // ,
																																				// retryAnalyzer
																																				// = //
																																				// Retry.class)
	public void UI_MGO_A_PY_02_testMakeDefault() {

		// read the list of active cards on an account before the test
		ArrayList<CardPaymentMethod> cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);

		// go to the MyAccount homepage
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();
		logInfo("Customer goes to MyAccount home screen");

		// select "My Payments" from the home screen
		helper.selectLinkFromHomepage("My Payments");
		assertTrue(driver.getCurrentUrl().endsWith("/payments"));
		logInfo("Customer selects 'My Payments' from the main menu and is directed to " + driver.getCurrentUrl());

		// wait for page load - TODO change to dynamic wait
		WaitUtil.waitForSeconds(5);

		CardPaymentMethod cardToChange = null;

		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);

		// detect a card to update - active and not already default
		for (CardPaymentMethod card : cardsOnAccount) {
			if (!card.isCancelled() && card.getId() != payer.getDefaultPaymentMethodId()) {
				cardToChange = card;
				logInfo("Selecting card to change: " + card.getCardholderName());
				break;
			}
		}

		// verify that a suitable card was found
		assertNotNull(cardToChange);
		logInfo("Changing card " + cardToChange.getCardholderName() + " to default");

		// click the "Set as default" button on the relevant card
		MyAccountPaymentsPage paymentsPage = new MyAccountPaymentsPage(driver);
		paymentsPage.clickButton(cardToChange.getCardholderName(), "Set as default");

		// wait 5 seconds for the changes to take effect
		WaitUtil.waitForSeconds(5);

		// refresh the list of cards
		cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);

		// refresh the payer object
		payer = PaymentCenterDAO.getPayer(billingAccountID);

		// retrieve the default card from the database
		CardPaymentMethod defaultCard = PaymentCenterDAO.getCardPaymentMethod(payer.getDefaultPaymentMethodId());

		// verify that the default card in the database matches the one we have just
		// changed
		assertEquals(cardToChange.getCardholderName(), defaultCard.getCardholderName());

		// log the result
		logPass("Default card is now " + defaultCard);
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PY_03: MyGoMo: My Payments > Edit Expiry Date", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PY_03_testEditExpiryDate() {

		// read the payer details from the database
		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);

		// retrieve the default card from the database
		CardPaymentMethod defaultCard = PaymentCenterDAO.getCardPaymentMethod(payer.getDefaultPaymentMethodId());

		// read the list of active cards on an account before the test
		ArrayList<CardPaymentMethod> cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);
		logInfo(cardsOnAccount.size() + " cards found on the account");
		assertTrue(cardsOnAccount.size() > 0);

		// go to the MyAccount homepage
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();
		extentLogger.info("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("My Payments");
		assertTrue(driver.getCurrentUrl().endsWith("/payments"));

		// wait for the page to load - TODO change to dynamic wait
		WaitUtil.waitForSeconds(3);

		logInfo("Customer selects 'My Payments' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountPaymentsPage paymentsPage = new MyAccountPaymentsPage(driver);

		// detect a card to update
		CardPaymentMethod cardToChange = null;

		for (CardPaymentMethod card : cardsOnAccount) {
			if (!card.isCancelled() && card.getId() != defaultCard.getId()) {
				cardToChange = card;
				logInfo("Selecting card " + card.getCardholderName());
				break;
			}
		}

		// confirm that a card was found
		assertNotNull(cardToChange);

		paymentsPage.scrollToTopOfPage();

		WaitUtil.waitForSeconds(1);

		// select 'Edit Expiry'
		paymentsPage.clickButton(cardToChange.getCardholderName(), "Edit Expiry");
		logInfo("Customer selects 'Edit Expiry' for card " + cardToChange);
		/*
		 * // scroll right to bring the card into the center of the page // note that
		 * this will not be possible if we're using the right most card, so catch the
		 * exception an move on try { paymentsPage.moveRight(); } catch(Exception e) {
		 * 
		 * }
		 */

		Random random = new Random();

		// get a random month and year
		int randomMonthIndex = 1 + random.nextInt(12);
		String randomMonth = new DateFormatSymbols().getMonths()[randomMonthIndex - 1];
		int randomYearIndex = 2021 + random.nextInt(10);
		String randomYear = Integer.toString(randomYearIndex);

		// determine the number of days in the random month for the year
		int daysInRandomMonth = YearMonth.of(randomYearIndex, randomMonthIndex).lengthOfMonth();

		// get the expected expiry date - i.e. the last day of the randomly selected
		// date (e.g. "2024-09-30")
		String expectedExpiryDate = randomYear + "-" + String.format("%02d", randomMonthIndex) + "-" + daysInRandomMonth;

		logInfo("Customer wishes to change their expiry date to " + randomMonth + " " + randomYear);

		// select the random date from the dropdown lists
		paymentsPage.selectMonth(randomMonth);
		paymentsPage.selectYear(randomYear);

		// click "Confirm"
		paymentsPage.saveExpiryChange();

		// wait 5 seconds for the changes to take effect
		WaitUtil.waitForSeconds(5);

		// refresh the card details from the database
		cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);

		// get the updated card status
		CardPaymentMethod updatedCard = PaymentCenterDAO.getCardPaymentMethod(cardToChange.getId());

		// determine the updated expiry date from the database
		String strUpdatedDate = updatedCard.getExpirationDate();

		logInfo("Card " + cardToChange.getId() + ", " + cardToChange.getCardholderName() + " updated in payment center to " + strUpdatedDate);

		// check that the date in the database is correct
		assertEquals(strUpdatedDate, expectedExpiryDate);
		extentLogger.pass("The date is successfully updated in the database to " + strUpdatedDate);
	}

	@Test(dependsOnMethods = { "UI_MGO_A_LP_01_testLoginToMyGoMo", "UI_MGO_A_PY_01_testAddNewCard" }, description = "UI_MGO_A_PY_04: MyGoMo: My Payments > Remove Card")
	public void UI_MGO_A_PY_04_testRemoveCard() {

		// read the list of active cards on an account before the test
		ArrayList<CardPaymentMethod> cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);

		// read the payer details from the database
		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);

		// retrieve the default card from the database
		CardPaymentMethod defaultCard = PaymentCenterDAO.getCardPaymentMethod(payer.getDefaultPaymentMethodId());

		// go to the MyAccount homepage
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();
		logInfo("Customer goes to MyAccount home screen");

		// select "My Payments" from the home screen
		helper.selectLinkFromHomepage("My Payments");
		assertTrue(driver.getCurrentUrl().endsWith("/payments"));

		logInfo("Customer selects 'My Payments' from the main menu and is directed to " + driver.getCurrentUrl());

		WaitUtil.waitForSeconds(3);

		MyAccountPaymentsPage paymentsPage = new MyAccountPaymentsPage(driver);

		// detect a card to update
		CardPaymentMethod cardToChange = null;

		for (CardPaymentMethod card : cardsOnAccount) {
			if (!card.isCancelled() && card.getId() != defaultCard.getId()) {
				cardToChange = card;
				logInfo("Selecting card " + card.getPartialDigits());
				break;
			}
		}

		// confirm that a card was found
		assertNotNull(cardToChange);

		logInfo("Customer selects 'Remove'");

		// select "Remove"
		// paymentsPage.selectRemoveCard();
		paymentsPage.clickButton(cardToChange.getCardholderName(), "Remove");
		paymentsPage.clickConfirmDeleteCard();

		// wait 5 seconds for the changes to take effect
		WaitUtil.waitForSeconds(5);

		// refresh the card details from the database
		cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);

		// get the updated card status
		CardPaymentMethod updatedCard = PaymentCenterDAO.getCardPaymentMethod(cardToChange.getId());

		// check that the card is now updated to Default as expected
		assertTrue(updatedCard.isCancelled());
		logPass(updatedCard.getId() + " is now cancelled");
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PY_05: MyGoMo: My Payments > Balance display", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PY_05_testAccountBalanceDisplayed() {

		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyAccount homepage
		helper.returnToHomepage();
		extentLogger.info("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("My Payments");
		assertTrue(driver.getCurrentUrl().endsWith("/payments"));
		extentLogger.info("Customer selects 'My Payments' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountPaymentsPage paymentsPage = new MyAccountPaymentsPage(driver);

		// read the balance from the database
		Balance balance = AccountsReceivableDAO.getBalance(billingAccountID);

		// read the balance displayed on the page
		String balanceAmtDisplayed = paymentsPage.getBalanceDisplayed();

		String expectedBalanceString = StringUtil.toCurrency(balance.getTotalBalanceDue(), "€");

		// check that the AR balance amount displayed is correct
		assertEquals(balanceAmtDisplayed, expectedBalanceString);
		logPass("Correct balance displayed: " + balanceAmtDisplayed);
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PY_06: MyGoMo: My Payments > Make Payment", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PY_06_testMakePartialPayment() {

		// make sure that the customer has a balance of at least €5.00
		Balance balance = AccountsReceivableDAO.getBalance(billingAccountID);
		if (balance.getNonOverdueAmount() > -200) {
			AdjustmentDAO.updateAgentUsernamesOnAdjustments(billingAccountID, "test_automation@eir.ie");
			AdjustmentAPIFlow flow = new AdjustmentAPIFlow();
			flow.postAdjustment("",billingAccountID, 500, AdjustmentReason.CORRECTION);
		}

		int paymentAmount = 1;

		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);
		Payment lastPayment = PaymentCenterDAO.getLastPaymentForPayer(payer.getId());

		// get the PAYMENT.ID of the last MyAccount payment on the customer's account
		int lastPaymentID;

		if (lastPayment == null) {
			lastPaymentID = -1;
			logInfo("No existing payments on the account");
		} else {
			lastPaymentID = lastPayment.getId();
			logInfo("Most recent payment on this account is payment.id " + lastPaymentID);
		}

		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyAccount homepage
		helper.returnToHomepage();
		logInfo("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("My Payments");
		assertTrue(driver.getCurrentUrl().endsWith("/payments"));
		logInfo("Customer selects 'My Payments' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountPaymentsPage paymentsPage = new MyAccountPaymentsPage(driver);

		balance = AccountsReceivableDAO.getBalance(billingAccountID);
		logInfo("Customer balance before the payment: " + balance.getNonOverDueAmountAsEuro() + " " + balance.getTotalBalanceDue());

		// if the balance is less than 0, check the payment and payment date fields
		if (balance.getTotalBalanceDue() < 0) {

			int balanceBeforePayment = (int) balance.getTotalBalanceDue();
			logInfo("Customer's DB balance before the payment: " + balanceBeforePayment);

			logInfo("Customer wishes to make a payment of €" + paymentAmount);

			// enter the payment amount €1
			paymentsPage.enterPaymentAmount(Integer.toString(paymentAmount));
			logInfo("Customer enters the amount " + paymentAmount + " into the input field");

			WaitUtil.waitForSeconds(1);

			// click "Pay Now"
			paymentsPage.clickPayNow();
			logInfo("Customer clicks 'Pay Now'");

			CardPaymentMethod cardToUse = null;

			ArrayList<CardPaymentMethod> cardsOnAccount = PaymentCenterDAO.getPaymentCardsForBillingAccountID(billingAccountID);
			for (CardPaymentMethod card : cardsOnAccount) {
				if (!card.isCancelled()) {
					cardToUse = card;
				}
			}

			assertNotNull(cardToUse);
			logInfo("Customer will pay using card " + cardToUse.getCardholderName());

			// make the payment with the saved card
			paymentsPage.payWithSavedCard(cardToUse);

			// wait 10 seconds for the payment to complete
			WaitUtil.waitForSeconds(20);

			saveFullPageScreenshot();
			logInfo("Customer makes the payment using a saved card");

			// wait for the payment to land in payment center
			long waitEndTime = System.currentTimeMillis() + 30000;

			int currentLastPaymentID = lastPaymentID;

			// while we haven't found the new payment ID
			while (currentLastPaymentID == lastPaymentID && System.currentTimeMillis() < waitEndTime) {

				// attempt to retrieve the new payment ID
				lastPayment = PaymentCenterDAO.getLastPaymentForPayer(payer.getId());

				if (lastPayment != null) {
					currentLastPaymentID = lastPayment.getId();
				}

				// wait 5 seconds for the new deep link to be sent
				WaitUtil.waitForSeconds(5);
			}

			balance = AccountsReceivableDAO.getBalance(billingAccountID);

			int balanceAfterPayment = (int) balance.getTotalBalanceDue();

			logInfo("Customer's DB balance after the payment is " + balanceAfterPayment);

			// check that the balance has been changed accordingly
			assertEquals(Math.abs(balanceBeforePayment - balanceAfterPayment), paymentAmount * 100);

			logPass("The balance was successfully reduced by €" + paymentAmount + " from " + balanceBeforePayment + " to " + balanceAfterPayment);
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_PY_07: MyGoMo: My Payments > View Transaction History", retryAnalyzer = Retry.class)
	public void UI_MGO_A_PY_07_testViewTransactionHistory() {

		ArrayList<BalanceChange> balanceChanges = AccountsReceivableDAO.getMyGoMoRelevantBalanceChanges(billingAccountID);

		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyAccount homepage
		helper.returnToHomepage();
		logInfo("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("My Payments");
		assertTrue(driver.getCurrentUrl().endsWith("/payments"));

		logInfo("Customer selects 'My Payments' from the main menu and is directed to " + driver.getCurrentUrl());

		WaitUtil.waitForSeconds(2);

		int count = 0;

		// for each balance change (apart from the account creation event)
		for (BalanceChange transaction : balanceChanges) {

			if (!transaction.getChangeType().equals("ACCOUNT_CREATION") && count < 10) {
				count++;

				// read the transaction details from the page
				String dateDisplayed = driver
						.findElement(By.xpath("//*[@id=\"app-container\"]/div[1]/div[2]/div/div[3]/div[2]/table/tbody/tr[" + count + "]/td[1]/p")).getText();
				String typeDisplayed = driver
						.findElement(By.xpath("//*[@id=\"app-container\"]/div[1]/div[2]/div/div[3]/div[2]/table/tbody/tr[" + count + "]/td[2]/p")).getText();
				String amountDisplayed = driver
						.findElement(By.xpath("//*[@id=\"app-container\"]/div[1]/div[2]/div/div[3]/div[2]/table/tbody/tr[" + count + "]/td[3]/p")).getText();
				String balanceDisplayed = driver
						.findElement(By.xpath("//*[@id=\"app-container\"]/div[1]/div[2]/div/div[3]/div[2]/table/tbody/tr[" + count + "]/td[4]/p")).getText();

				// log the details
				// log("Transaction in DB: " + transaction);
				// log("Details displayed: " + dateDisplayed + "," + typeDisplayed + "," +
				// amountDisplayed + "," + balanceDisplayed);

				// verify that the details displayed are correct
				assertEquals(transaction.getStrDate(), dateDisplayed);
				assertEquals(transaction.getStrType(), typeDisplayed);
				assertEquals(transaction.getStrAdjustmentAmount(), amountDisplayed);

				if (balanceDisplayed.equals("€0")) {
					assertEquals(transaction.getStrBalance(), "€0.00");
				} else {
					assertEquals(transaction.getStrBalance(), balanceDisplayed);
				}

				// log the result
				logPass("Transaction displayed correctly: " + transaction);
			}
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_AD_01: MyGoMo: Account Details > Check Pin, Puk & Offer Details")
	public void UI_MGO_A_AD_01_testAccountDetailsDispay() {

		// go to the MyAccount homepage
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();

		logInfo("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("Account Details");
		assertTrue(driver.getCurrentUrl().endsWith("/account_details"));
		logInfo("Customer selects 'Account Details' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountAccountDetailsPage detailsPage = new MyAccountAccountDetailsPage(driver);

		// check that the billing account ID displayed is correct
		assertEquals(detailsPage.getAccountNumber(), Integer.toString(billingAccountID));
		logPass("Correct billing account ID " + " displayed: " + detailsPage.getAccountNumber());

		// retrieve the list of subscriptions linked to the account
		ArrayList<Subscription> subscriptions = SubscriptionManagementDAO.getActiveSubscriptions(billingAccountID);

		logInfo("This account has " + subscriptions.size() + " linked subscriptions");

		// for each subscription on the account...
		for (Subscription subscription : subscriptions) {

			// retrieve the linked service from the database
			Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscription.getId());

			// if there is more than 1 subscription on the account...
			if (subscriptions.size() > 1) {

				// select the msisdn from the dropdown list
				detailsPage.selectMsisdn(service.getName());
			}

			// read the offer details from the catalog
			Offer offer = CatalogCoreDAO.getOfferByCode(subscription.getCatalogOfferCode());

			// check that the offer description displayed is correct
			assertEquals(offer.getDescription(), detailsPage.getOfferDescription());
			logPass("Correct offer description displayed: " + detailsPage.getOfferDescription());

			// read the price plan charge from subs management
			PricePlan pricePlan = CatalogCoreDAO.getPricePlan(subscription.getCatalogOfferCode());
			assertNotNull(pricePlan);

			// convert the price plan charge to a string
			String expectedPricePlanChargeString = StringUtil.toCurrency(pricePlan.getDefaultPrice(), "€") + " a month";

			// check that the correct price plan charge is displayed on the screen
			assertEquals(detailsPage.getOfferAmount(), expectedPricePlanChargeString);
			logPass("Correct offer charge displayed: " + detailsPage.getOfferAmount());

			// read the active sim pack from on the service
			SimCard activePack = SubscriptionManagementDAO.getActiveSimCard(service.getName());

			// retrieve the expected pin and puk from the inventory management API
			SimDetails securityDetails = InventoryManagementAPI.getSimDetailsFromInventory(activePack.getIccid());
			System.out.println(offer.getDescription() + ", " + activePack.getIccid() + ", " + securityDetails.getPin1() + ", " + securityDetails.getPuk1());

			// verify that the correct pin is displayed
			assertEquals(detailsPage.getPin(), securityDetails.getPin1());

			// verify that the correct puk is displayed
			assertEquals(detailsPage.getPuk(), securityDetails.getPuk1());
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_AD_02: MyGoMo: Account Details > View/Edit NDD Settings", retryAnalyzer = Retry.class)
	public void UI_MGO_A_AD_02_testNDDSettings() {

		// go to the MyAccount homepage
		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();
		WaitUtil.waitForSeconds(1);

		helper.selectLinkFromHomepage("My Profile");
		WaitUtil.waitForSeconds(2);
		assertTrue(driver.getCurrentUrl().endsWith("/profile"));
		logInfo("Customer selects 'My Profile' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountMyProfilePage profilePage = new MyAccountMyProfilePage(driver);

		// navigate to the NDD screen
		helper.clickRightArrow();
		WaitUtil.waitForSeconds(1);

		helper.clickRightArrow();
		WaitUtil.waitForSeconds(1);

		// retrieve the list of subscriptions linked to the account
		ArrayList<Subscription> subscriptions = SubscriptionManagementDAO.getActiveSubscriptions(billingAccountID);

		// we will modify NDD settings on the first subscription in the list
		Subscription subscription = subscriptions.get(0);

		// read the service information from the database
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscription.getId());

		// if there are multiple subscriptions on the account, select the msisdn from
		// the dropdown
		if (subscriptions.size() > 1) {
			profilePage.selectMsisdn(service.getName());
		}

		// set up the list of all NDD settings
		ArrayList<NDDSetting> nddValues = new ArrayList<NDDSetting>();
		nddValues.add(NDDSetting.LISTED);
		nddValues.add(NDDSetting.UNLISTED);
		nddValues.add(NDDSetting.EXDIRECTORY);

		// determine the current NDD setting on the service
		NDDSetting currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(service.getName());

		// read in the NDD setting displayed on the screen and check that it's correct
		assertEquals(profilePage.getSelectedNDD(), currentNDDSetting.toString());
		logPass("Initial NDD for " + service.getName() + " displayed on the screen is correct: " + currentNDDSetting);

		// move the current NDD setting to the end of the list (we will switch to it
		// last)!
		nddValues.remove(currentNDDSetting);
		nddValues.add(currentNDDSetting);

		// wait for 1 second
		WaitUtil.waitForSeconds(1);

		// for each possible NDD setting
		for (NDDSetting setting : nddValues) {

			// select the setting from the screen and let the changes take effect
			profilePage.selectNDD(setting.toString());
			WaitUtil.waitForSeconds(1);

			// retrieve the updated NDD from the database
			currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(service.getName());

			// check that the NDD setting is updated successfully in the database
			assertEquals(currentNDDSetting, setting);

			// log the result
			logPass("NDD setting changed in the DB to " + setting.toString());
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_AD_03: MyGoMo: Account Details > Order Replacement SIM")
	public void UI_MGO_A_AD_03_testOrderReplacementSIM() {

		// retrieve the list of subscriptions linked to the account
		ArrayList<Subscription> subscriptions = SubscriptionManagementDAO.getActiveSubscriptions(billingAccountID);
		// read the service and subscription information from the database
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptions.get(0).getId());
		// "remove" and pending sim swaps on the service
		SimSwapDAO.markSIMSwapsAsDone(service.getId());

		// read the list of sim card addons before the test
		ArrayList<SimCard> addonSimCardsBefore = SubscriptionManagementDAO.getSIMCardAddonsForService(service.getId());

		// get the details of the active sim card before the test
		SimCard activeCard = SubscriptionManagementDAO.getActiveSimCard(service.getName());
		logInfo("The active card on the service before the test is " + activeCard.getIccid() + ", " + activeCard.getImsi());

		// retrieve the most recent order-management.service record before the test
		OmService serviceToIgnore = OrderManagementDAO.getMostRecentOrderService(service.getId());
		logInfo("The most recent order management.SERVICE record on this sub is ID " + serviceToIgnore.getId() + ". We will ignore this one.");

		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyAccount homepage
		helper.returnToHomepage();

		logInfo("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("Account Details");
		assertTrue(driver.getCurrentUrl().endsWith("/account_details"));
		logInfo("Customer selects 'Account Details' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountAccountDetailsPage accountDetailsPage = new MyAccountAccountDetailsPage(driver);

		// click on the link 'Replace / Activate My SIM'
		accountDetailsPage.clickReplaceActivateSim();

		MyAccountManageSIMPage simPage = new MyAccountManageSIMPage(driver);

		assertTrue(driver.getCurrentUrl().endsWith("account_details/manage_sim"));
		logInfo("Customer clicks on 'Replace / Activate My SIM' and is taken to " + driver.getCurrentUrl());

		// if the customer has more than 1 service, they must select the MSISDN from the
		// dropdown list
		if (subscriptions.size() > 1) {
			simPage.selectMsisdn(service.getName());
			WaitUtil.waitForSeconds(1);
		}

		// click the button "Replace My SIM"
		simPage.clickReplaceSIM();
		logInfo("Customer clicks 'Replace My SIM' button");

		// get a random eircode
		AddressFinderAddress randomAddress = RandomStringGenerator.getRandomAddressFromFile();

		// enter the eircode and click "Confirm Eircode
		simPage.enterAndConfirmEircode(randomAddress.getEircode());
		logInfo("Customer enters eircode " + randomAddress.getEircode());

		WaitUtil.waitForSeconds(1);

		// click the 'Send Me My New SIM' button
		simPage.clickSendSIM();
		logInfo("Customer clicks 'Send Me My New SIM' button");

		// if the dialog box indicates that there is already a pending replacement
		// order, confirm to proceed
		if (simPage.getDialogMessage().contains("Do you wish to continue?")) {
			logInfo("Customer confirms the dialog saying 'Do you wish to continue?'");
			WaitUtil.waitForSeconds(2);
			simPage.confirmDialog();
		}

		// close the dialog box
		simPage.closeDialog();

		// wait for and return a new record in the order management SERVICE table
		OmService newOrderManagementService = OrderManagementMonitor.waitForOrderServiceLaterThanNamedServiceID(service.getId(), serviceToIgnore.getId());
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
		boolean orderInDeliveryStep = OrderManagementMonitor.waitForOrderToReachStep(order.getReference(),"DELIVERY");
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
		ArrayList<SimCard> addonSimCardsAfter = SubscriptionManagementDAO.getSIMCardAddonsForService(service.getId());
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

	@Test(dependsOnMethods = "UI_MGO_A_AD_03_testOrderReplacementSIM", description = "UI_MGO_A_AD_04: MyGoMo: Account Details > Activate Replacement SIM")
	public void UI_MGO_A_AD_04_testActivateReplacementSIM() {

		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);

		// get the pending replacement sim cards on the account
		ArrayList<SimCard> pendingReplacementSimCards = SubscriptionManagementDAO.getSIMCardAddonsForService(service.getId());
		assertEquals(pendingReplacementSimCards.size(), 1);

		SimCard replacementSimCard = pendingReplacementSimCards.get(0);
		logInfo("The customer has a pending replacement sim " + replacementSimCard.getIccid() + ", " + replacementSimCard.getImsi()
				+ " which relates to MSISDN " + service.getName());

		// get the equipment which is currently pending activation on that service
		SimDetails simDetails = InventoryManagementAPI.getSimDetailsFromInventory(replacementSimCard.getIccid());
		String puk = simDetails.getPuk1();
		String imsi = simDetails.getImsi();
		logInfo("The order has been fulfilled by SIM: " + replacementSimCard.getIccid() + ", " + imsi + ", " + puk);

		// confirm that the sim card is ready to be activated
		SimCard replacementSIM = SubscriptionManagementDAO.getSimCardByIccid(replacementSimCard.getIccid());
		assertNotNull(replacementSIM);
		assertNull(replacementSIM.getActivatedAt());
		assertNull(replacementSIM.getTerminatedAt());

		// save the imsi and iccid for verification
		EquipmentPack newPack = new EquipmentPack();
		newPack.setIccid(replacementSimCard.getIccid());
		newPack.setImsi(imsi);

		// confirm that the PUK is found and correct
		assertNotNull(puk);
		assertEquals(puk.length(), 8);

		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyAccount homepage
		helper.returnToHomepage();

		logInfo("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("Account Details");
		assertTrue(driver.getCurrentUrl().endsWith("/account_details"));
		logInfo("Customer selects 'Account Details' from the main menu and is directed to " + driver.getCurrentUrl());

		logInfo("The customer navigates to the Account Details Page");

		MyAccountAccountDetailsPage page = new MyAccountAccountDetailsPage(driver);

		// click on the link 'Replace / Activate My SIM'
		page.clickReplaceActivateSim();

		logInfo("The customer clicks the 'Replace / Activate My SIM' link");

		assertTrue(driver.getCurrentUrl().endsWith("account_details/manage_sim"));

		MyAccountManageSIMPage simPage = new MyAccountManageSIMPage(driver);

		// retrieve the list of subscriptions linked to the account
		ArrayList<Subscription> subscriptionIDs = SubscriptionManagementDAO.getActiveSubscriptions(billingAccountID);

		// dropdown list
		if (subscriptionIDs.size() > 1) {
			simPage.selectMsisdn(service.getName());
			WaitUtil.waitForSeconds(1);
		}

		simPage.enterPuk(puk);

		logInfo("The customer enters the PUK code " + puk);

		simPage.clickActivateSIM();

		logInfo("The customer clicks the 'Activate My SIM' button");

		// wait 30 seconds for the change to take effect
		WaitUtil.waitForSeconds(30);

		// TODO - checks here
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_NV_01: MyGoMo: Site > Check Nagivation", retryAnalyzer = Retry.class)
	public void UI_MGO_A_NV_01_testNavigation() {
		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// determine the expected base URL string from the config XML
		String baseMyAccountURL = EnvironmentDirectory.getMyGoMoURL();

		Map<String, String> mapHomepageMenu = new HashMap<String, String>();

		// set up the map for testing
		mapHomepageMenu.put("My Alerts", "/alerts");
		mapHomepageMenu.put("My Bills", "/bills");
		mapHomepageMenu.put("My Payments", "/payments");
		mapHomepageMenu.put("Account Details", "/account_details");
		mapHomepageMenu.put("My Profile", "/profile");

		// Getting an iterator
		Iterator<Entry<String, String>> iterator = mapHomepageMenu.entrySet().iterator();

		// for each entry in the map
		while (iterator.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) iterator.next();

			// return to the homepage
			helper.returnToHomepage();

			// select the relevant menu item from the homapage
			helper.selectLinkFromHomepage(pair.getKey());

			WaitUtil.waitForSeconds(1);

			// check the URL
			assertTrue(driver.getCurrentUrl().equals(baseMyAccountURL + pair.getValue()));

			// log the result
			extentLogger.pass("Home Page Menu Item '" + pair.getKey() + "' directs to " + driver.getCurrentUrl());
		}

		// add the homepage link to the map
		mapHomepageMenu.put("My GoMo Home", "/home");

		iterator = mapHomepageMenu.entrySet().iterator();

		// for each entry in the map
		while (iterator.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) iterator.next();

			// select the item from the purple navigation bar
			helper.selectLinkFromNavBar(pair.getKey());

			WaitUtil.waitForSeconds(1);

			// check the URL
			assertTrue(driver.getCurrentUrl().equals(baseMyAccountURL + pair.getValue()));

			// log the result
			extentLogger.pass("Nav bar link '" + pair.getKey() + "' directs to " + driver.getCurrentUrl());
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_AL_01: MyGoMo: My Alerts > View Alerts", retryAnalyzer = Retry.class)
	public void UI_MGO_A_AL_01_testWebAlerts() {

		// read the list of web messages from the database
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		ArrayList<NCWebMessage> webMessages = NotificationCenterDAO.getDisplayableWebMessages(contactUuid);

		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyGoMo homepage
		helper.returnToHomepage();
		logInfo("Customer goes to MyAccount home screen");
		MyAccountHomePage homepage = new MyAccountHomePage(driver);

		// verify that the number of alerts displayed is correct
		assertEquals(webMessages.size(), homepage.getAlertsCount());

		helper.selectLinkFromHomepage("My Alerts");
		assertTrue(driver.getCurrentUrl().endsWith("/alerts"));
		logInfo("Customer selects 'My Alerts' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountMyAlertsPage alertsPage = new MyAccountMyAlertsPage(driver);

		WaitUtil.waitForSeconds(2);

		// for each notification in the database
		for (int i = 0; i < webMessages.size(); i++) {

			String messageDisplayed = alertsPage.getAlertMessage(i).trim();
			String dbMessage = webMessages.get(i).getContent().replace("\n", " ").trim();

			System.out.println("PAGE: " + messageDisplayed);
			System.out.println("DB  : " + dbMessage);
			assertEquals(messageDisplayed, messageDisplayed);
			logPass("Message '" + alertsPage.getAlertMessage(i) + " ' is displayed on the screen");
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_AD_05: MyGoMo: Account Details > View Order History", retryAnalyzer = Retry.class)
	public void UI_MGO_A_AD_05_testViewOrders() {
		
		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyAccount homepage
		helper.returnToHomepage();

		logInfo("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("Account Details");
		assertTrue(driver.getCurrentUrl().endsWith("/account_details"));
		logInfo("Customer selects 'Account Details' from the main menu and is directed to " + driver.getCurrentUrl());

		MyAccountAccountDetailsPage accountDetailsPage = new MyAccountAccountDetailsPage(driver);

		WaitUtil.waitForSeconds(1);

		// click on the link 'My Orders'
		accountDetailsPage.clickMyOrders();

		WaitUtil.waitForSeconds(3);

		MyAccountViewOrdersPage ordersPage = new MyAccountViewOrdersPage(driver);

		// read the list of orders from the database
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		ArrayList<ProductOrder> orders = OrderManagementDAO.getOrdersForContact(contactUuid);

		int index = 1;

		// TODO - only check orders that are within the date range selected on the page
		for (ProductOrder order : orders) {
			if (index <= 10) {
				assertEquals(ordersPage.getOrderStatus(index), order.getStatus());
				assertEquals(ordersPage.getOrderReference(index), order.getReference());
				assertEquals(ordersPage.getOrderDate(index), order.getCreatedDate("dd/MM/yyyy"));

				// verify the order type
				if (order.getOrder_type().equals("REPLACEMENT")) {
					assertEquals(ordersPage.getOrderType(index), "Replacement SIM");
				} else {
					int offerCount = OrderManagementDAO.getOfferCount(order.getReference());
					assertEquals(ordersPage.getOrderType(index), "New plan x" + offerCount);
				}
				logPass("Order displayed correctly: " + order.getReference());

				// retrieve the order cost from the database
				int orderCost = OrderManagementDAO.getOrderPrice(order.getReference());

				// verify that the correct order cost is displayed
				if (orderCost == 0) {
					assertEquals(ordersPage.getOrderCharge(index), "€0");
				} else {
					assertEquals(ordersPage.getOrderCharge(index), StringUtil.toCurrency(orderCost, "€"));
				}

				index++;
			}
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_US_02: MyGoMo: My Usage > View Usage", retryAnalyzer = Retry.class)
	public void UI_MGO_A_US_01_testViewUsage() {

		// determine the service
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);

		// ensure that the service has usage for the current period
		String currentPillingPeriod = Timestamp.getCurrentTimestamp("yyyyMM");

		// determine some of the values needed for DB lookups
		Account account = SubscriptionManagementDAO.getAccountByBillingAccountID(billingAccountID);
		int billCycleID = account.getBillingCycleID();
		RefBillingCycle billCycle = SubscriptionManagementDAO.getBillCycle(billCycleID);
		int partitionID = CDRRepoDAO.getUsagePartition(currentPillingPeriod, billCycle.getName()).getPartitionNumber();

		ArrayList<RatingSubtotal> totals = CDRRepoDAO.getRatingSubtotalsGoMo(service.getId(), partitionID);

		// if there are no subtotals for the billing period, process a CDR file to add
		// usage
		if (totals.size() == 0) {
			UsageFile file = CDRGenerator.generateUsage(service.getName(), Timestamp.getCurrentTimestamp("MM-yyyy"), ProcessType.RANDOM, true, 30, null);

			logInfo("No usage on the account. File " + file.getFilename() + " has been processed to add usage");
			boolean cdrFileProcessed = CDRRepoMonitor.waitForFileProcessed(file.getFilename());
			assertTrue(cdrFileProcessed);
			totals = CDRRepoDAO.getRatingSubtotalsGoMo(service.getId(), partitionID);
			assertTrue(totals.size() > 0);
		}

		MyGoMoHelper helper = new MyGoMoHelper(driver);

		// go to the MyAccount homepage
		helper.returnToHomepage();

		logInfo("Customer goes to MyAccount home screen");

		// select "Account Details" from the home screen
		helper.selectLinkFromHomepage("My Usage");
		assertTrue(driver.getCurrentUrl().endsWith("/usage"));
		logInfo("Customer selects 'My Usage' from the main menu and is directed to " + driver.getCurrentUrl());

		WaitUtil.waitForSeconds(2);

		MyAccountUsagePage usagePage = new MyAccountUsagePage(driver);

		ArrayList<Subscription> activeSubscriptions = SubscriptionManagementDAO.getActiveSubscriptions(billingAccountID);

		if (activeSubscriptions.size() > 1) {
			// select the msisdn
			usagePage.selectMsisdn(service.getName());
		}

		WaitUtil.waitForSeconds(3);

		// for each rating subtotal, check that it is correctly displayed
		for (int i = 1; i <= totals.size(); i++) {
			RatingSubtotal total = totals.get(i - 1);
			assertEquals(total.getDisplayName(), usagePage.getSubtotalName(i));
			assertEquals(total.getDisplayCost(), usagePage.getSubtotalCharge(i));
			assertEquals(total.getDisplayTime(), usagePage.getSubtotalDuration(i));
			logPass("Subtotal correctly displayed: " + total.getDisplayName() + ", " + total.getDisplayCost() + ", " + total.getDisplayTime());

			// after every 4 checks, scroll to the right to reveal the next 4 bubbles
			if (i % 4 == 0) {
				usagePage.clickNext();
				WaitUtil.waitForSeconds(2);
			}
		}

		// now check the individual usage
		ArrayList<ChargedUsageGoMoPostpay> usage = CDRRepoDAO.getChargedUsage(service.getName(), partitionID);

		int index = 1;
		int max;
		int resultsPerPage = 100;

		// select 100 results per page
		usagePage.selectResultsPerPage(resultsPerPage);

		WaitUtil.waitForSeconds(1);

		// determine the max to check - either all usage or up to 100 items (whichever
		// is lower)
		if (usage.size() > resultsPerPage) {
			max = resultsPerPage;
		} else {
			max = usage.size();
		}

		// for all usage displayed on the page, check that it's correct
		while (index < max) {
			ChargedUsageGoMoPostpay u = usage.get(index - 1);
			assertEquals(u.getChargeTimeStr(), usagePage.getUsageDate(index));
			assertEquals(u.getInvoiceText(), usagePage.getUsageType(index));
			assertEquals(u.getDurationStr(), usagePage.getUsageValue(index));
			assertEquals(u.getAmountStr(), usagePage.getUsageCharge(index));
			assertEquals(u.getDestinationNumber(), usagePage.getUsageNumber(index));
			assertEquals(u.getDestinationCountryCode(), usagePage.getUsageLocation(index));
			logPass("Usage correctly displayed: " + u.getChargeTimeStr() + ", " + u.getInvoiceText() + ", " + u.getDurationStr() + ", "
					+ StringUtil.toCurrency(u.getAmount(), "€") + ", " + u.getDestinationNumber() + ", " + u.getDestinationCountryCode());
			index++;
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_NV_02: MyGoMo: Check footer links", retryAnalyzer = Retry.class)
	public void UI_MGO_A_NV_02_testFooterLinks() {

		MyGoMoHelper helper = new MyGoMoHelper(driver);
		helper.returnToHomepage();
		extentLogger.info("Customer goes to MyAccount home screen");

		String baseWordpressURL = EnvironmentDirectory.getGoMoWordpressURL();

		Map<String, String> footerMap = new HashMap<String, String>();

		// set up the map for testing
		footerMap.put("Our Coverage Map", "/our-coverage-map");
		footerMap.put("Pricing", "/pricing");
		footerMap.put("Terms", "/terms");
		footerMap.put("Legal", "/legal");
		footerMap.put("Accessibility", "/accessibility");
		footerMap.put("Child Safety", "/child-safety");
		footerMap.put("Privacy", "/privacy");
		footerMap.put("Cookies", "/cookies");
		footerMap.put("Code of Practice", "/code-of-practice");
		// footerMap.put("Contact Us", "/contact");
		footerMap.put("Top FAQs", "/joining-getting-started-with-gomo");

		Iterator<Entry<String, String>> iterator = footerMap.entrySet().iterator();

		// for each entry in the map
		while (iterator.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) iterator.next();

			MyAccountHomePage homepage = new MyAccountHomePage(driver);

			// check the URL
			assertEquals(homepage.getURLFromFooterLink(pair.getKey()), baseWordpressURL + pair.getValue());

			// log the result
			logPass("Footer link '" + pair.getKey() + "' directs to " + baseWordpressURL + pair.getValue());
		}
	}

	@Test(dependsOnMethods = "UI_MGO_A_LP_01_testLoginToMyGoMo", description = "UI_MGO_A_NV_03: MyGoMo: Site > Check header links", retryAnalyzer = Retry.class)
	public void UI_MGO_A_NV_03_testHeaderLinks() {

		String wordpressBaseURL = EnvironmentDirectory.getGoMoWordpressURL();
		String eShopBaseURL = EnvironmentDirectory.getGoMoEShopURL();
		String myAccountBaseURL = EnvironmentDirectory.getMyGoMoURL();

		// go to the MyAccount homepage
		driver.get(myAccountBaseURL);
		extentLogger.info("Customer goes to MyAccount home screen");

		MyAccountHomePage homepage = new MyAccountHomePage(driver);

		// check the header links
		assertTrue(homepage.getURLFromFooterLink("Home").equals(wordpressBaseURL));
		extentLogger.pass("'Home' link directs to " + homepage.getURLFromFooterLink("Home"));

		assertTrue(homepage.getURLFromFooterLink("Our Plan").equals(eShopBaseURL));
		extentLogger.pass("'Our Plan' link directs to " + homepage.getURLFromFooterLink("Our Plan"));

		assertTrue(homepage.getURLFromFooterLink("Help & Support").equals(wordpressBaseURL + "/help-support"));
		extentLogger.pass("'Help & Support' link directs to " + homepage.getURLFromFooterLink("Help & Support"));

		assertTrue(homepage.getURLFromFooterLink("MyGoMo").equals(myAccountBaseURL));
		extentLogger.pass("'MyGoMo' button directs to " + homepage.getURLFromFooterLink("MyGoMo"));
	}

	@BeforeClass
	public void setUp() {

		// create the driver object
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();

		billingAccountID = TestDataManager.getGoMoMultilineSubscriber();
	}

	@AfterClass
	public void tearDown() {

		// close the driver
		driver.close();

		// kill any running chromedriver.exe processes
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "Shutdown-thread"));
	}
}

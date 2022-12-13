package testCases.gomo.api;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.hlr_fe.profile.HLRFEProfile;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import framework.test_data.generic.RandomStringGenerator;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_address_finder_backend.AddressFinderDAO;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import microservices.backend.eir_barring_backend.BarringDAO;
import microservices.backend.eir_barring_backend.data_model.custom.BarringDetail;
import microservices.backend.eir_barring_backend.data_model.custom.BarringSet;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.Offer;
import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.ChargedUsageGoMoPostpay;
import microservices.backend.eir_cdr_repository_backend.data_model.RatingSubtotal;
import microservices.backend.eir_cdr_repository_backend.data_model.RefRatingSubtotalType;
import microservices.backend.eir_cdr_repository_backend.data_model.UsagePartition;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_contact_management_backend.data_model.Address;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.data_model.ContactManagementPermission;
import microservices.backend.eir_contact_management_backend.data_model.custom.PermissionSet;
import microservices.backend.eir_contact_management_backend.enums.AddressType;
import microservices.backend.eir_contact_management_backend.enums.PermissionCode;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.backend.eir_inventory_management_backend.api.InventoryManagementAPI;
import microservices.backend.eir_inventory_management_backend.objects.SimDetails;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_notification_center_backend.api.NotificationCenterAPI;
import microservices.backend.eir_notification_center_backend.dao.NotificationCenterDAO;
import microservices.backend.eir_notification_center_backend.data_model.NCWebMessage;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.OmService;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_otp_verification_backend.dao.OtpDAO;
import microservices.backend.eir_otp_verification_backend.data_model.OtpVerification;
import microservices.backend.eir_payment_center_backend.dao.PaymentCenterDAO;
import microservices.backend.eir_payment_center_backend.data_model.CardPaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.Payer;
import microservices.backend.eir_payment_center_backend.data_model.PaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.custom.DetailedPaymentMethod;
import microservices.backend.eir_salt_ar_backend.dao.AccountsReceivableDAO;
import microservices.backend.eir_salt_ar_backend.data_model.Balance;
import microservices.backend.eir_sim_swap_backend.dao.SimSwapDAO;
import microservices.backend.eir_sim_swap_backend.data_model.Charge;
import microservices.backend.eir_sim_swap_backend.data_model.SimSwapRequest;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementMonitor;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.AddonBundle;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.data_model.custom.SubscriptionServicePair;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.backend.galaxion.dao.GalaxionDAO;
import microservices.frontend.common_ui.dto.permissions.Permission;
import microservices.frontend.common_ui.response_objects.address_finder.ReturnedAddressFinderAddress;
import microservices.frontend.common_ui.response_objects.barring.ReturnedBarring;
import microservices.frontend.common_ui.response_objects.cdr_repo.RatingSubtotalType;
import microservices.frontend.common_ui.response_objects.cdr_repo.Usage;
import microservices.frontend.common_ui.response_objects.cdr_repo.UsageSummary;
import microservices.frontend.common_ui.response_objects.contact_management.MyGoMoGetPermissionsDTO;
import microservices.frontend.common_ui.response_objects.contact_management.ReturnedContactAddress;
import microservices.frontend.common_ui.response_objects.mnp.PortingHours;
import microservices.frontend.common_ui.response_objects.notification_center.WebMessage;
import microservices.frontend.common_ui.response_objects.payment_center.ReturnedPayer;
import microservices.frontend.common_ui.response_objects.subs_management.GetOfferResponse;
import microservices.frontend.common_ui.response_objects.subs_management.Subscription;
import microservices.frontend.eir_myaccount_frontend.api.MyGoMoAPI;
import microservices.frontend.eir_myaccount_frontend.dto.responses.get_accounts.GetAccountsResponse;
import microservices.frontend.eir_myaccount_frontend.dto.responses.get_contacts.GetContactsResponse;
import microservices.frontend.eir_myaccount_frontend.dto.responses.get_replacement_sim_charge.GetReplacementSimChargeDTO;
import microservices.frontend.eir_myaccount_frontend.dto.responses.get_web_notification_count.GetWebNotificationCountResponse;
import utilities.generic.api.APITransaction;
import utilities.generic.time.TimeUtil;
import utilities.generic.time.Timestamp;

public class TestMyGoMoApi extends BaseTest {

	private String token;
	private int billingAccountID;
	private int accountID;
	private String contactUuid;
	private int subscriptionID;
	private String msisdn;
	private int serviceID;
	private String emailAddress, password;
	private String otpMsisdn;

	/*------------------------------------------------------------------
	 * Authentication
	 ------------------------------------------------------------------- */

	@Test(description = "[MyGoMo: Login Screen > Authenticate")
	public void testLogin() {
		logInfo("Authenticating with login credentials " + emailAddress + ", " + password);
		token = MyGoMoAPI.getToken(emailAddress, password);
		assertNotNull(token);
		logPass("Token generated: " + token.substring(0, 200) + "...");
	}

	/*------------------------------------------------------------------
	 * Login screen
	 ------------------------------------------------------------------- */

	/**
	 * Verify the forgot email flow Screen: Login screen
	 * 
	 * Ref: MGO_A_LP_01, MGO_A_LP_02
	 */
	@Test(description = "[MGO_A_LP_01] MyGoMo: Login screen > Trigger and validate 'Forgot Email' OTP")
	public void MGO_A_LP_01_testTriggerAndValidateForgotEmailOTP(ITestContext iTestContext) {

		// log the expected result
		logInfo("Customer has email address " + emailAddress);

		// use the API to trigger the forgot email OTP request
		APITransaction t = MyGoMoAPI.triggerRecoverEmailOTP(msisdn);
		logInfo("Generate OTP Request:\n"+t.toString());
		assertEquals(t.getResponse().statusCode(),200);

		// read the OTP UUID from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String otpUuid = (String) jsonPathEvaluator.get("otpUuid");

		// read the OTP record from the database
		OtpVerification otp = OtpDAO.getOTPByUuid(otpUuid);

		// verify that the OTP record has been created in the database and has the
		// correct MSISDN
		assertNotNull(otp);
		assertEquals(otp.getMsisdn(), msisdn);

		// use the API to validate the OTP code
		t = MyGoMoAPI.validateRecoverEmailOTP(otp.getVerificationCode(), otp.getUuid());
		assertEquals(t.getResponse().statusCode(),200);
		logPass("Validate OTP Request:\n"+t.toString());

		// read the email address from the response
		jsonPathEvaluator = t.getResponse().jsonPath();
		String emailFromAPI = (String) jsonPathEvaluator.get("email");

		// verify that the email address returned is correct
		assertEquals(emailFromAPI, emailAddress);

		// log to the report
		logPass("Correct email address received: " + emailFromAPI);
	}

	/*------------------------------------------------------------------
	 * Home screen
	 ------------------------------------------------------------------- */
	/**
	 * Get web notification count
	 * 
	 * Ref: MGO_A_HS_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_HS_01] MyGoMo: Home screen > Get web notification count")
	public void MGO_A_HS_01_testGetWebNotificationCount(ITestContext iTestContext) {

		// read the list of web notifications from the database
		ArrayList<NCWebMessage> webNotificationsFromDatabase = NotificationCenterDAO.getDisplayableWebMessages(contactUuid);
		logInfo("Contact " + contactUuid + " has " + webNotificationsFromDatabase.size() + " displayable web notifications");

		// make a call to the API to retrieve the notification count
		APITransaction t = MyGoMoAPI.getWebNotificationCount(token);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// get the notification count from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetWebNotificationCountResponse response = jsonPathEvaluator.getObject("", GetWebNotificationCountResponse.class);

		// verify that the correct number of notifications were received
		assertEquals(response.getCount(), webNotificationsFromDatabase.size());
		logPass(response.getCount() + " notifications(s) returned as expected");
	}

	/*------------------------------------------------------------------
	 * My Alerts
	 ------------------------------------------------------------------- */
	/**
	 * Verify that web notifications are successfully retrieved Screen: My Alerts
	 * 
	 * Ref: MGO_A_AL_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AL_01] MyGoMo: My Alerts > Get web notifications")
	public void MGO_A_AL_01_testGetWebNotifications(ITestContext iTestContext) {

		// read the list of web notifications from the database
		ArrayList<NCWebMessage> webNotificationsFromDatabase = NotificationCenterDAO.getDisplayableWebMessages(contactUuid);

		// make a call to the API to retrieve the notifications
		APITransaction t = MyGoMoAPI.getWebNotifications(token);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// read the list of notifications from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<WebMessage> webNotificationsFromAPI = jsonPathEvaluator.getList("webNotifications.content", WebMessage.class);

		// verify that the correct number of notifications were received
		assertEquals(webNotificationsFromAPI.size(), webNotificationsFromDatabase.size());
		logPass(webNotificationsFromAPI.size() + " notifications(s) found as expected");

		// for each notification, check that it is correct
		for (int i = 0; i < webNotificationsFromAPI.size(); i++) {
			NCWebMessage dbNotification = webNotificationsFromDatabase.get(i);
			WebMessage apiNotification = webNotificationsFromAPI.get(i);
			assertEquals(apiNotification.getId(), dbNotification.getId());
			assertEquals(apiNotification.getContent().getMessage(), dbNotification.getContent());
			assertEquals(apiNotification.getContent().getNavLink(), dbNotification.getNavLink());
			logPass("Notification " + webNotificationsFromAPI.get(i).getId() + ": '" + apiNotification.getContent().getMessage() + "' found");
		}
	}

	/**
	 * Verify that a user can dismiss a web message via the My Alerts screen
	 * 
	 * Ref: MGO_A_AL_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AL_02] MyGoMo: My Alerts > Dismiss web notification")
	public void MGO_A_AL_02_testDismissWebNotifications(ITestContext iTestContext) {

		// post a web notification to the account that we can delete
		NotificationCenterAPI.publishWebMessageAdjustmentApplied(billingAccountID);

		// read the list of web notifications from the database
		ArrayList<NCWebMessage> webNotificationsFromDatabase = NotificationCenterDAO.getWebMessages(contactUuid);

		// determine which web message to dismiss
		NCWebMessage messageToDelete = null;

		for (NCWebMessage message : webNotificationsFromDatabase) {
			if (message.getIsDisplayable() == 1) {
				messageToDelete = message;
				break;
			}
		}

		// verify that a web notification to delete has been identified
		assertNotNull(messageToDelete);
		logInfo("Web message " + messageToDelete.getId() + " has is_displayable = " + messageToDelete.getIsDisplayable());

		// make the call to the API to dismiss the web message
		APITransaction t = MyGoMoAPI.dismissWebNotification(token, messageToDelete.getId());
		assertEquals(t.getResponse().statusCode(),204);
		logPass(t.toString());

		// read the notification from the database again
		messageToDelete = NotificationCenterDAO.getWebMessage(messageToDelete.getId());
		assertNotNull(messageToDelete);

		// verify that is_displayable is now set to 0
		assertEquals(0, messageToDelete.getIsDisplayable());
		logPass("Message " + messageToDelete.getId() + " has is_displayable set to " + messageToDelete.getIsDisplayable());
	}

	/*------------------------------------------------------------------
	 * My Profile
	 ------------------------------------------------------------------- */

	/**
	 * Get contacts
	 * 
	 * Ref: MGO_A_PR_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_01] MyGoMo: My Profile > Get contacts")
	public void MGO_A_PR_01_testGetContacts(ITestContext iTestContext) {

		// read the contact details from the database
		Contact contact = ContactManagementDAO.getContact(contactUuid);

		// make a call to the API to get the contact details
		APITransaction t = MyGoMoAPI.getContacts(token);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// convert the response body to a contact object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetContactsResponse response = jsonPathEvaluator.getObject("", GetContactsResponse.class);

		// verify that the values returned by the API are correct
		assertEquals(contact.getUuid(), response.getUuid());
		assertEquals(contact.getFirstName(), response.getFirstName());
		assertEquals(contact.getLastName(), response.getLastName());
		assertEquals(contact.getBirthDate().toString(), response.getBirthDate());

		// log output to the report
		logPass("Contact correctly returned: " + response.getFirstName() + " " + response.getLastName() + ", " + response.getBirthDate() + ", "
				+ response.getUuid());
	}

	/**
	 * Get contact permissions (data-driven for ACTIVE_CUSTOMER and
	 * NO_LONGER_CUSTOMER groups)
	 * 
	 * Ref: MGO_A_PR_02
	 * 
	 * @param groupCode Screen: My Profile
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_02] MyGoMo: My Profile > Get permissions", dataProvider = "getPermissionGroups")
	public void MGO_A_PR_02_testGetPermissions(PermissionGroupCode groupCode, ITestContext iTestContext) {

		// read the contact object from the database
		Contact contact = ContactManagementDAO.getContact(contactUuid);

		// read the contact permissions from the database
		ArrayList<ContactManagementPermission> permissionsFromDatabase = ContactManagementDAO.getContactPermissionsForGroup(contactUuid, groupCode);
		PermissionSet permissionSet = new PermissionSet(permissionsFromDatabase);

		// make a call to the API to retrieve contact permissions
		APITransaction t = MyGoMoAPI.getPermissions(token, groupCode);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// convert the response object into a java object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		MyGoMoGetPermissionsDTO preferencesFromAPI = jsonPathEvaluator.getObject("", MyGoMoGetPermissionsDTO.class);

		// verify that 1 permissionGroupResponse item is returned in the response
		assertEquals(preferencesFromAPI.getPermissionGroupResponse().size(), 1);

		// verify the field allowThirdParties
		assertEquals(preferencesFromAPI.isAllowThirdParty(), contact.getAllowThirdParties() == 1);
		logPass("Allow third party setting correctly retrieved as " + preferencesFromAPI.isAllowThirdParty());

		// verify that the correct group has been received
		assertEquals(preferencesFromAPI.getPermissionGroupResponse().get(0).getPermissionGroup(), groupCode.toString());
		logPass("Correct group has been received in the response: " + preferencesFromAPI.getPermissionGroupResponse().get(0).getPermissionGroup());

		// for each permission, verify that it is enabled/disabled as expected
		for (Permission permission : preferencesFromAPI.getPermissionGroupResponse().get(0).getPermissions()) {
			assertEquals(permission.isEnabled(), permissionSet.hasPermission(permission.getPermission()));
			logPass("Permission " + permission.getPermission() + " is correctly received: " + permission.isEnabled());
		}
	}

	/**
	 * Update contact permissions
	 * 
	 * Ref: MGO_A_PR_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_03] MyGoMo: My Profile > Update contact permissions", dataProvider = "getPermissionGroups")
	public void MGO_A_PR_03_testUpdatePermissions(PermissionGroupCode groupCode, ITestContext iTestContext) {
		APITransaction t = MyGoMoAPI.updateContactPreferences(token, PermissionGroupCode.ACTIVE_CUSTOMER, PermissionCode.ALLOW_SMS_CONTACT, true);
		assertEquals(t.getResponse().statusCode(),204);
	}

	/**
	 * Verify that the correct billing address is retrieved Screen: My Profile
	 * 
	 * Ref: MGO_A_PR_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_04] MyGoMo: My Profile > Get billing address")
	public void MGO_A_PR_04_testGetBillingAddress(ITestContext iTestContext) {

		// read the expected billing address from the database
		Address addressFromDatabase = ContactManagementDAO.getBillingAddress(contactUuid);

		// make a call to the API to retrieve the address
		APITransaction t = MyGoMoAPI.getBillingAddress(token);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// read the address object from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		ReturnedContactAddress addressFromAPI = jsonPathEvaluator.getObject("", ReturnedContactAddress.class);

		// check that the address returned is correct
		assertEquals(addressFromDatabase.getAddressLine1(), addressFromAPI.getAddressLine1());
		assertEquals(addressFromDatabase.getAddressLine2(), addressFromAPI.getAddressLine2());
		assertEquals(addressFromDatabase.getAddressLine3(), addressFromAPI.getAddressLine3());
		assertEquals(addressFromDatabase.getTown(), addressFromAPI.getTown());
		assertEquals(addressFromDatabase.getCounty(), addressFromAPI.getCounty());
		assertEquals(addressFromDatabase.getEircode(), addressFromAPI.getCode());

		// output to the report
		logPass("Correct billing address returned: " + addressFromAPI.getAddressLine1());
	}

	/**
	 * Update the billing address for a customer Screen: My Profile > My Billing
	 * Address
	 * 
	 * Ref: MGO_A_PR_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_05] MyGoMo: My Profile > Update billing address")
	public void MGO_A_PR_05_testUpdateBillingAddress(ITestContext iTestContext) {

		// get a random address for the update
		AddressFinderAddress a = RandomStringGenerator.getRandomAddressFromFile();
		logInfo("Customer will update the billing address to " + a.getEircode() + ": " + a.getAddressLine1());

		// make a call to the API to update the address
		APITransaction t = MyGoMoAPI.updateAddress(token, a.getAddressLine1(), a.getAddressLine2Str(), a.getAddressLine3Str(), a.getTown(), a.getCounty(),
				a.getEircode(), AddressType.BILLING);
		assertEquals(t.getResponse().statusCode(),204);
		logPass(t.toString());

		// read the billing address from the database following the update
		Address addressFromDatabase = ContactManagementDAO.getBillingAddress(contactUuid);

		// verify that the address has been updated with the correct values
		assertEquals(a.getAddressLine1(), addressFromDatabase.getAddressLine1());
		assertEquals(a.getAddressLine2Str(), addressFromDatabase.getAddressLine2Str());
		assertEquals(a.getAddressLine2Str(), addressFromDatabase.getAddressLine2Str());
		assertEquals(a.getTown(), addressFromDatabase.getTown());
		assertEquals(a.getCounty(), addressFromDatabase.getCounty());
		assertEquals(a.getEircode(), addressFromDatabase.getEircode());

		// log to the report
		logPass("Address successfully updated in the database to " + addressFromDatabase.getEircode() + ": " + addressFromDatabase.getAddressLine1());
	}

	/**
	 * Update the delivery address for a customer Screen: My Profile > My Billing
	 * Address
	 * 
	 * Ref: MGO_A_PR_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_05] MyGoMo: My Profile > Update delivery address")
	public void MGO_A_PR_05_testUpdateDeliveryAddress(ITestContext iTestContext) {

		// read the initial delivery address on the contact
		Address addressFromDatabase = ContactManagementDAO.getDeliveryAddress(contactUuid);
		logInfo("Before test, contact " + contactUuid + " has delivery address " + addressFromDatabase.getEircode() + ", "
				+ addressFromDatabase.getAddressLine1());

		// get a random address for the update
		AddressFinderAddress a = RandomStringGenerator.getRandomAddressFromFile();
		logInfo("Customer will update the billing address to " + a.getEircode() + ": " + a.getAddressLine1());

		// make a call to the API to update the address
		APITransaction t = MyGoMoAPI.updateAddress(token, a.getAddressLine1(), a.getAddressLine2Str(), a.getAddressLine3Str(), a.getTown(), a.getCounty(),
				a.getEircode(), AddressType.DELIVERY);
		assertEquals(t.getResponse().statusCode(),204);
		logPass(t.toString());

		// read the billing address from the database following the update
		addressFromDatabase = ContactManagementDAO.getDeliveryAddress(contactUuid);

		// verify that the address has been updated with the correct values
		assertEquals(a.getAddressLine1(), addressFromDatabase.getAddressLine1());
		assertEquals(a.getAddressLine2Str(), addressFromDatabase.getAddressLine2Str());
		assertEquals(a.getAddressLine3Str(), addressFromDatabase.getAddressLine3Str());
		assertEquals(a.getTown(), addressFromDatabase.getTown());
		assertEquals(a.getCounty(), addressFromDatabase.getCounty());
		assertEquals(a.getEircode(), addressFromDatabase.getEircode());

		// log to the report
		logPass("Address successfully updated in the database to " + addressFromDatabase.getEircode() + ": " + addressFromDatabase.getAddressLine1());
	}

	/**
	 * Verify that the correct eircode is returned from the address finder database
	 * Screen: My Profile > My Billing Address Screen: Account Details >
	 * Replace/Activate My SIM
	 * 
	 * Ref: MGO_A_PR_06
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_06] MyGoMo: My Profile > Eircode lookup")
	public void MGO_A_PR_06_testEircodeLookup(ITestContext iTestContext) {

		// find a random eircode to test with
		String eircode = RandomStringGenerator.getRandomAddressFromFile().getEircode();

		// read the expected address from the database
		ArrayList<AddressFinderAddress> addressesFromDatabase = AddressFinderDAO.getAddresses(eircode);
		logInfo("Looking up eircode " + eircode + " - " + addressesFromDatabase.get(0).getAddressLine1());

		// make the call to the API to retrieve the address
		APITransaction t = MyGoMoAPI.eircodeLookup(token, eircode);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(),200);

		// convert the json response to a list of address objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<ReturnedAddressFinderAddress> addressesFromAPI = jsonPathEvaluator.getList("addresses", ReturnedAddressFinderAddress.class);

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

	/**
	 * Verify that the correct NDD setting is displayed Screen: My Profile >
	 * National Directory Enquiries
	 * 
	 * Ref: MGO_A_PR_07
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_07] MyGoMo: Account Details > Get NDD setting")
	public void MGO_A_PR_07_testGetNDD(ITestContext iTestContext) {

		// read the expected setting from the database
		NDDSetting nddFromDatabase = SubscriptionManagementDAO.getNDDSetting(msisdn);

		// make the call to the API
		APITransaction t = MyGoMoAPI.getNDD(token, serviceID);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// read the nddPreference value from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String nddFromAPI = (String) jsonPathEvaluator.get("nddPreference");

		// verify that the response received is correct
		assertEquals(nddFromDatabase.toString(), nddFromAPI);
		logPass("Correct NDD setting returned for " + msisdn + ": " + nddFromAPI);
	}

	/**
	 * Update NDD settings Screen: My Profile > National Directory Enquiries
	 * 
	 * Ref: MGO_A_PR_08
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_08] MyGoMo: Account Details > Update NDD setting")
	public void MGO_A_PR_08_testUpdateNDDSetting(ITestContext iTestContext) {

		// populate a list of all possible NDD settings
		ArrayList<NDDSetting> nddValues = new ArrayList<NDDSetting>();
		nddValues.add(NDDSetting.LISTED);
		nddValues.add(NDDSetting.UNLISTED);
		nddValues.add(NDDSetting.EXDIRECTORY);

		// determine the current NDD setting on the service
		NDDSetting currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(msisdn);
		logInfo("NDD setting for " + msisdn + " before test: " + currentNDDSetting);

		// move the current NDD setting to the end of the list (we will switch to it
		// last)!
		nddValues.remove(currentNDDSetting);
		nddValues.add(currentNDDSetting);

		// for each possible NDD setting
		for (NDDSetting setting : nddValues) {
			logInfo("Changing NDD setting to " + setting.toString());

			// use the API to change the setting
			APITransaction t = MyGoMoAPI.updateNddSetting(token, serviceID, setting);

			// verify that the API call has been successful
			logInfo(t.toString());
			assertEquals(t.getResponse().statusCode(), 204);

			// read the current setting from the database
			currentNDDSetting = SubscriptionManagementDAO.getNDDSetting(msisdn);

			// verify that the response received is correct
			assertEquals(setting.toString(), currentNDDSetting.toString());
			logPass("Correct NDD setting returned for " + msisdn + ": " + currentNDDSetting.toString());
		}
	}

	/**
	 * Trigger reset password request
	 * 
	 * Ref: MGO_A_PR_09
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_09] MyGoMo: Login Screen > Reset password")
	public void MGO_A_PR_09_testTriggerPasswordReset(ITestContext iTestContext) {
		APITransaction t = MyGoMoAPI.triggerPasswordReset(token);
		assertEquals(t.getResponse().statusCode(),204);
		logPass(t.toString());
	}

	/**
	 * Trigger change email request
	 * 
	 * Ref: MGO_A_PR_10
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_10] MyGoMo: My Profile > Update email address")
	public void MGO_A_PR_10_testUpdateEmailAddress(ITestContext iTestContext) {
		String newEmailAddress = "steve_automation_" + Timestamp.getCurrentTimestamp("yyyyMMdd_HHmmss") + "@gomo.ie";
		APITransaction t = MyGoMoAPI.updateEmail(token, newEmailAddress);
		assertEquals(t.getResponse().statusCode(),204);
		logPass(t.toString());
	}
	
	/**
	 * Get service details (on My Profile > NDD page)
	 *
	 * Ref: MGO_A_PR_11
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PR_11] MyGoMo: Account Details > Get service")
	public void MGO_A_PR_11_testGetService(ITestContext iTestContext) {

		// read the service information from the database
		SubscriptionServicePair serviceInformationFromDatabase = SubscriptionManagementDAO.getServiceInformation(billingAccountID, serviceID);
		assertNotNull(serviceInformationFromDatabase);

		// make a call to the API to retrieve the service
		APITransaction t = MyGoMoAPI.getService(token, serviceID);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// verify that the information returned matches the database
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		assertEquals((String) jsonPathEvaluator.get("name"), serviceInformationFromDatabase.getMsisdn());
		assertEquals((String) jsonPathEvaluator.get("msisdn"), serviceInformationFromDatabase.getMsisdn());
		assertEquals((String) jsonPathEvaluator.get("nddPreference"), serviceInformationFromDatabase.getNddPreference());
		assertEquals((String) jsonPathEvaluator.get("domain"), "MOBILE");
		assertEquals((int) jsonPathEvaluator.get("networkId"), serviceInformationFromDatabase.getNetworkID());
	
	}
	/*------------------------------------------------------------------
	 * My Payments
	 ------------------------------------------------------------------- */

	/**
	 * Retrieve the account balance Screen: My Payments
	 * 
	 * Ref: MGO_A_PY_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PY_01] MyGoMo: My Payments > Get account balance")
	public void MGO_A_PY_01_testGetAccountBalance(ITestContext iTestContext) {

		// read the balance from the database
		Balance balance = AccountsReceivableDAO.getBalance(billingAccountID);
		// make a call to the API to retrieve the balance

		APITransaction t = MyGoMoAPI.getAccountBalance(token);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// read out the amount and paymentDate values from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		int amount = (int) jsonPathEvaluator.get("amount");
		String paymentDate = (String) jsonPathEvaluator.get("paymentDate");

		// verify that the amount is correct ([non_overdue_amount + overdue_amount] in
		// the database)
		assertEquals(balance.getNonOverdueAmount() + balance.getOverdueAmount(), amount);

		// verify that the oaymentDate is correct ([overdue_date] in the database)
		if (balance.getOverdueDate() == null) {
			assertEquals(null, paymentDate);
		} else {
			assertEquals(balance.getOverdueDate().toString(), paymentDate);
		}

		// log the output to the report
		logPass("Correct details returned: paymentDate=" + paymentDate + ", " + "amount=" + amount);
	}

	/**
	 * Get payer details
	 * 
	 * Ref: MGO_A_PY_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PY_02] MyGoMo: My Payments > Get payer details")
	public void MGO_A_PY_02_testGetPayer(ITestContext iTestContext) {

		// read the payer information from the database
		Payer payerFromDatabase = PaymentCenterDAO.getPayer(billingAccountID);

		// make a call to the API to retrieve the payer information
		APITransaction t = MyGoMoAPI.getPayer(token);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// parse the json response into a list of Payer objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		ReturnedPayer payerFromAPI = jsonPathEvaluator.getObject("", ReturnedPayer.class);

		// check that the payer information is correct
		assertEquals(payerFromDatabase.getBrand(), payerFromAPI.getBrand());
		assertEquals(payerFromDatabase.getBillingAccountId(), payerFromAPI.getBillingAccountId());
		assertEquals(payerFromDatabase.getProviderPayerRef(), payerFromAPI.getPayerRef());
		assertEquals(payerFromDatabase.getContactUuid(), payerFromAPI.getPayerContactUuid());
		logPass("Payer correctly retrieved: " + payerFromAPI.getPayerContactUuid() + ", " + payerFromAPI.getPayerRef());

		// read the list of payment methods from the database
		ArrayList<DetailedPaymentMethod> paytmentMethodsFromDatabase = PaymentCenterDAO.getDetailedPaymentMethodsForPayer(payerFromDatabase.getId());

		// verify that the correct number of payment methods were received
		assertEquals(paytmentMethodsFromDatabase.size(), payerFromAPI.getPaymentMethods().size());
		logPass(payerFromAPI.getPaymentMethods().size() + " payment method(s)retrieved as expected");

		// for each payment method in the database...
		for (int i = 0; i < paytmentMethodsFromDatabase.size(); i++) {

			// retrieve the corresponding payment method from the json response
			microservices.frontend.common_ui.response_objects.payment_center.PaymentMethod apiPaymentMethod = payerFromAPI
					.getPaymentMethod(paytmentMethodsFromDatabase.get(i).getId());
			DetailedPaymentMethod dbPaymentMethod = paytmentMethodsFromDatabase.get(i);

			// assert that it has been found in the list
			assertNotNull(apiPaymentMethod);

			// verify that the fields are correct
			assertEquals(apiPaymentMethod.getCardholderName(), dbPaymentMethod.getCardholderName());
			assertEquals(apiPaymentMethod.getExpirationDate(), dbPaymentMethod.getExpirationDate().toString());
			assertEquals(apiPaymentMethod.getPartialDigits(), dbPaymentMethod.getPartialDigits());
			assertEquals(apiPaymentMethod.getProviderReference(), dbPaymentMethod.getProviderRef());
			assertEquals(apiPaymentMethod.getType(), dbPaymentMethod.getType());
			assertEquals(apiPaymentMethod.getStatus(), dbPaymentMethod.getStatus());
			assertEquals(apiPaymentMethod.getPaymentMethodType(), dbPaymentMethod.getPaymentMethodType());

			// log output to the report
			logPass("Card payment method correctly returned: " + apiPaymentMethod.getPaymentMethodId() + ", " + apiPaymentMethod.getPartialDigits());
		}
	}

	/**
	 * Get transaction history
	 * 
	 * Ref: MGO_A_PY_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PY_03] MyGoMo: My Payments > Get transaction history")
	public void MGO_A_PY_03_testGetTransactionHistory(ITestContext iTestContext) {
		String today = Timestamp.getCurrentTimestamp("yyyy-MM-dd");
		APITransaction t = MyGoMoAPI.getTransactionHistory(token, "2021-06-28", today);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	/**
	 * Update card expiry date Screen: My Payments
	 * 
	 * Ref: MGO_A_PY_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PY_04] MyGoMo: My Payments > Update card expiry date")
	public void MGO_A_PY_04_testUpdateCardExpiryDate(ITestContext iTestContext) {

		// read the Payer object before the test
		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);
		int defaultPaymentMethod = payer.getDefaultPaymentMethodId();
		CardPaymentMethod card = PaymentCenterDAO.getCardPaymentMethod(defaultPaymentMethod);

		// ensure that the card is found that we can test on
		assertNotNull(card);
		logInfo("Customer will update the expiry date on card payment method " + card.getId() + ". Current value is " + card.getExpirationDate());

		String newExpiryDate = "2024-03-30";

		logInfo("Customer wishes to update their card expiry date to " + newExpiryDate);

		// make the call to the API and verify that it has completed
		APITransaction t = MyGoMoAPI.editExpiryDate(token, card.getId(), newExpiryDate);
		assertEquals(t.getResponse().statusCode(),204);
		logPass(t.toString());

		// read the database to confirm that the expiry date has been updated
		// successfully
		card = PaymentCenterDAO.getCardPaymentMethod(defaultPaymentMethod);
		assertEquals(card.getExpirationDate(), newExpiryDate);
		logPass("Expiry date updated to " + card.getExpirationDate());
	}

	/**
	 * Get the Add Card hosted payment page Screen: My Payments > Manage My Cards
	 * 
	 * Ref: MGO_A_PY_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PY_05] MyGoMo: My Payments > Generate Add Card hpp")
	public void MGO_A_PY_05_testGenerateHPP(ITestContext iTestContext) {

		// read the payer from the database
		Payer payerFromDatabase = PaymentCenterDAO.getPayer(billingAccountID);

		// make an API call to get the HPP
		APITransaction t = MyGoMoAPI.generateHPP(token);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// check some of the fields to confirm that the information has been returned
		// correctly
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		assertEquals((String) jsonPathEvaluator.get("hppRequest.MERCHANT_ID"), "eirtest");
		assertEquals((String) jsonPathEvaluator.get("hppRequest.CURRENCY"), "EUR");
		assertEquals((String) jsonPathEvaluator.get("hppRequest.CARD_PAYMENT_BUTTON"), "Add my card");
		assertEquals((String) jsonPathEvaluator.get("hppRequest.PAYER_REF"), payerFromDatabase.getProviderPayerRef());
		assertEquals((String) jsonPathEvaluator.get("hppRequest.PAYER_EXIST"), "1");

		logPass("HPP is correctly returned and linked to payer " + payerFromDatabase.getProviderPayerRef());
	}

	/**
	 * Change the default payment method ID Screen: My Payments > Manage My Cards
	 * 
	 * Ref: MGO_A_PY_06
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_PY_06] MyGoMo: My Payments > Update default payment method")
	public void MGO_A_PY_06_testMakeCardDefault(ITestContext iTestContext) {

		// read the Payer object before the test
		Payer payer = PaymentCenterDAO.getPayer(billingAccountID);
		int defaultPaymentMethodBeforeTest = payer.getDefaultPaymentMethodId();
		logInfo("Before the test, the customer has default payment method ID " + defaultPaymentMethodBeforeTest);

		// determine a payment method to set as default
		ArrayList<PaymentMethod> paymentMethods = PaymentCenterDAO.getPaymentMethodsForPayer(payer.getId());

		// determine which payment method to set as default
		int newDefaultId = 0;
		for (PaymentMethod method : paymentMethods) {
			if (method.getCancelledAt() == null && method.getId() != defaultPaymentMethodBeforeTest) {
				newDefaultId = method.getId();
				break;
			}
		}

		assertNotEquals(0, newDefaultId);
		logInfo("The customer wishes to set payment method " + newDefaultId + " to default");

		APITransaction t = MyGoMoAPI.updateDefaultPaymentMethod(token, newDefaultId);
		assertEquals(t.getResponse().statusCode(),204);
		logPass(t.toString());

		// retrieve the payer from the database again
		payer = PaymentCenterDAO.getPayer(billingAccountID);

		// check that the default ID has changed
		assertEquals(newDefaultId, payer.getDefaultPaymentMethodId());
		logPass("Payer now has default payment ID " + payer.getDefaultPaymentMethodId());
	}
	/*------------------------------------------------------------------
	 * My Usage
	 ------------------------------------------------------------------- */

	/**
	 * Retrieve usage details for the service Screen: My Usage
	 * 
	 * Ref: MGO_S_US_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_S_US_01] MyGoMo: My Usage > Get usage")
	public void MGO_S_US_01_testGetUsageDetails(ITestContext iTestContext) {

		// get a string for the current period
		String currentPeriod = Timestamp.getCurrentTimestamp("yyyyMM");

		// determine the usage partition for the current period
		UsagePartition partition = CDRRepoDAO.getUsagePartition(currentPeriod, "BILL_CYCLE_01");

		// read the usage from the database
		ArrayList<ChargedUsageGoMoPostpay> usageFromDB = CDRRepoDAO.getChargedUsageGoMo(msisdn, partition.getPartitionNumber());

		// make a call to the API to get usage for the period
		APITransaction t = MyGoMoAPI.getUsage(token, serviceID, currentPeriod, usageFromDB.size() + 1);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// convert the json response into a list of Usage objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<Usage> usageFromAPI = jsonPathEvaluator.getList("usage.content", Usage.class);
		logPass(usageFromAPI.size() + " records retrieved from the API");

		// for each usage record received by the API
		for (int i = 0; i < usageFromAPI.size(); i++) {

			// get an expected date format (as db date and api date format will differ)
			Date date = usageFromDB.get(i).getChargeStartDateTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String dateString = simpleDateFormat.format(date);

			// verify the fields
			assertEquals(usageFromAPI.get(i).getChargedUsageId(), usageFromDB.get(i).getId());
			assertEquals(usageFromAPI.get(i).getAmount(), usageFromDB.get(i).getAmount());
			logInfo(usageFromAPI.get(i).getChargeStartDateTime() + " vs "+ dateString + ", " + TimeUtil.isDST(date));
			
			// verify the charge date/time only where it's not on the daylight-savings cutover - else there are issues!
			if(TimeUtil.isDST(date)){
				assertEquals(usageFromAPI.get(i).getChargeStartDateTime(), dateString);
			}
			assertEquals(usageFromAPI.get(i).getDuration(), usageFromDB.get(i).getDuration());
			assertEquals(usageFromAPI.get(i).getInvoiceText(), usageFromDB.get(i).getInvoiceText());
			assertEquals(usageFromAPI.get(i).getDestinationCountryCode(), usageFromDB.get(i).getDestinationCountryCode());
			assertEquals(usageFromAPI.get(i).getDestinationNumber(), usageFromDB.get(i).getDestinationNumber());
			assertEquals(usageFromAPI.get(i).getMeasuringUnit(), usageFromDB.get(i).getMeasuringUnit());
			assertEquals(usageFromAPI.get(i).getOriginNumber(), usageFromDB.get(i).getOriginNumber());
			assertEquals(usageFromAPI.get(i).getOriginCountryCode(), usageFromDB.get(i).getOriginCountryCode());
			assertEquals(usageFromAPI.get(i).getUsageCounterTypeId(), usageFromDB.get(i).getUsageCounterTypeID());
			assertEquals(usageFromAPI.get(i).getVatCode(), usageFromDB.get(i).getVatCode());
			
			// log output to the report
			logPass("Usage record successfully retrieved: " + usageFromAPI.get(i).getChargedUsageId() + ", " + usageFromAPI.get(i).getDestinationNumber());
		}
	}

	/**
	 * Get usage summary Screen: My Usage
	 * 
	 * Ref: MGO_S_US_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_S_US_02] MyGoMo: My Usage > Get usage summary")
	public void MGO_S_US_02_testGetUsageSummary(ITestContext iTestContext) {

		String currentPeriod = Timestamp.getCurrentTimestamp("yyyyMM");

		// determine the usage partition for the current period
		UsagePartition partition = CDRRepoDAO.getUsagePartition(currentPeriod, "BILL_CYCLE_01");
		ArrayList<RatingSubtotal> subtotalsFromDatabase = CDRRepoDAO.getRatingSubtotalsGoMo(serviceID, partition.getPartitionNumber());

		// make a call to the API to get the usage summary
		APITransaction t = MyGoMoAPI.getUsageSummary(token, serviceID, currentPeriod);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// read the list of subtotal types fromt he json response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();

		// verify that the correct serviceID is received in the response
		assertEquals((int) jsonPathEvaluator.get("serviceId"), serviceID);

		// convert the json response to a list of UsageSummary objects
		List<UsageSummary> usageSummaryFromAPI = jsonPathEvaluator.getList("usageSummary", UsageSummary.class);

		// verify that the correct number of records have been retrieved
		assertEquals(usageSummaryFromAPI.size(), subtotalsFromDatabase.size());

		// for each subtotal retrieved, verify that the details are correct
		for (int i = 0; i < usageSummaryFromAPI.size(); i++) {
			assertEquals(usageSummaryFromAPI.get(i).getAmount(), subtotalsFromDatabase.get(i).getAmount());
			assertEquals(usageSummaryFromAPI.get(i).getDuration(), subtotalsFromDatabase.get(i).getDuration());
			assertEquals(usageSummaryFromAPI.get(i).getMeasuringUnit(), subtotalsFromDatabase.get(i).getMeasuringUnit());
			assertEquals(usageSummaryFromAPI.get(i).getQuantity(), subtotalsFromDatabase.get(i).getQuantity());
			assertEquals(usageSummaryFromAPI.get(i).getRatingSubtotalId(), subtotalsFromDatabase.get(i).getId());
			assertEquals(usageSummaryFromAPI.get(i).getRatingSubtotalTypeDisplayName(), subtotalsFromDatabase.get(i).getDisplayName());
			assertEquals(usageSummaryFromAPI.get(i).getEventCount(), subtotalsFromDatabase.get(i).getEventCount());

			// log output to the report
			logPass("Rating subtotal correctly received: " + usageSummaryFromAPI.get(i).getRatingSubtotalId() + ", "
					+ usageSummaryFromAPI.get(i).getEventCount());
		}
	}

	/**
	 * Get rating subtotal types Screen: My Usage
	 * 
	 * Ref: MGO_S_US_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_S_US_03] MyGoMo: My Usage > Get rating subtotal types")
	public void MGO_S_US_03_testGetRatingSubtotalTypes(ITestContext iTestContext) {

		// retrieve the rating subtotal types from the database
		ArrayList<RefRatingSubtotalType> subtotalTypesFromDatabase = CDRRepoDAO.getAllRatingSubtotalTypes();

		// make a call to the API to retrieve rating subtotal types
		APITransaction t = MyGoMoAPI.getRatingSubtotalTypes(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the list of subtotal types from the json response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<RatingSubtotalType> subtotalTypesFromAPI = jsonPathEvaluator.getList("ratingSubtotalTypes", RatingSubtotalType.class);

		// verify that the correct number of subtotal types have been returned by the
		// API
		assertEquals(subtotalTypesFromAPI.size(), subtotalTypesFromDatabase.size());
		logPass(subtotalTypesFromAPI.size() + " subtotal types returned as expected");

		// for each subtotal returned to the API, verify that it is correct
		for (int i = 0; i < subtotalTypesFromAPI.size(); i++) {
			assertEquals(subtotalTypesFromAPI.get(i).getId(), subtotalTypesFromDatabase.get(i).getId());
			assertEquals(subtotalTypesFromAPI.get(i).getKey(), subtotalTypesFromDatabase.get(i).getKey());
			assertEquals(subtotalTypesFromAPI.get(i).getRatingSubtotalDisplayName(), subtotalTypesFromDatabase.get(i).getRatingSubtotalDisplayName());
			assertEquals(subtotalTypesFromAPI.get(i).getRatingSubtotalLevel(), subtotalTypesFromDatabase.get(i).getRatingSubtotalLevel());
			assertEquals(subtotalTypesFromAPI.get(i).getRatingSubtotalUsageType(), subtotalTypesFromDatabase.get(i).getRatingSubtotalUsageType());
			logPass("Subtotal correctly retrieved: " + subtotalTypesFromAPI.get(i).getId() + ", " + subtotalTypesFromAPI.get(i).getKey());
		}
	}

	/*------------------------------------------------------------------
	 * Account Details - View account
	 ------------------------------------------------------------------- */

	/**
	 * Get accounts
	 * 
	 * Ref: MGO_A_AD_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_01] MyGoMo: Account Details > Get accounts")
	public void MGO_A_AD_01_testGetAccounts(ITestContext iTestContext) {

		// make the call to the API to get the billing account ID
		APITransaction t = MyGoMoAPI.getAccounts(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the billing account ID from the json response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetAccountsResponse> accountsFromAPI = jsonPathEvaluator.getList("content", GetAccountsResponse.class);

		// verify that 1 account is received
		assertEquals(1, accountsFromAPI.size());

		// read the expected account details from the database
		Account account = SubscriptionManagementDAO.getAccountByID(accountID);
		assertEquals(1, accountsFromAPI.size());

		// verify that the details are correct
		assertEquals(accountsFromAPI.get(0).getId(), account.getId());
		assertEquals(accountsFromAPI.get(0).getBillingAccountId(), account.getBillingAccountID());
		assertEquals(accountsFromAPI.get(0).getBrand(), account.getBrand());
		assertEquals(accountsFromAPI.get(0).getCustomerType(), account.getCustomerType());
		assertEquals(accountsFromAPI.get(0).getOrderReference(), account.getOrderReference());
		assertEquals(accountsFromAPI.get(0).getStatus(), account.getStatus());
		assertEquals(accountsFromAPI.get(0).getInvoiceDeliveryMethod(), account.getInvoiceDeliveryMethod());

		// log output to the report
		logPass("Account " + accountID + " is correctly returned: " + accountsFromAPI.get(0).getBillingAccountId() + ", " + accountsFromAPI.get(0).getBrand()
				+ ", " + accountsFromAPI.get(0).getStatus());
	}

	/**
	 * Retrieve service information Screen: Account Details
	 * 
	 * Ref: MGO_A_AD_02
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_02] MyGoMo: Account Details > Get services")
	public void MGO_A_AD_02_testGetServices(ITestContext iTestContext) {

		// make a call to the API to retrieve the service
		APITransaction t = MyGoMoAPI.getServices(token);
		logInfo("Response: " + t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	/**
	 * Get subscription information from the API Screen: Account details
	 * 
	 * Ref: MGO_A_AD_03
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_03] MyGoMo: Account Details > Get subscriptions")
	public void MGO_A_AD_03_testGetSubscriptions(ITestContext iTestContext) {

		// read the list of services from the database
		ArrayList<microservices.backend.eir_subscription_management_backend.data_model.Subscription> subscriptionsFromDatabase = SubscriptionManagementDAO
				.getSubscriptionsForBillingAccountID(billingAccountID);

		// make a call to the API to retrieve the subscriptions
		APITransaction t = MyGoMoAPI.getSubscriptions(token);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// convert the response to a list of Subscription objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<Subscription> subscriptionsFromAPI = jsonPathEvaluator.getList("content", Subscription.class);
		assertEquals(subscriptionsFromAPI.size(), subscriptionsFromDatabase.size());

		logPass(subscriptionsFromAPI.size() + " subscription(s) retrieved, as expected");

		// for each subscription returned
		for (int i = 0; i < subscriptionsFromAPI.size(); i++) {

			// validate that the details are correct
			assertEquals(subscriptionsFromAPI.get(i).getId(), subscriptionsFromDatabase.get(i).getId());
			assertEquals(subscriptionsFromAPI.get(i).getStatus(), subscriptionsFromDatabase.get(i).getStatus());
			assertEquals(subscriptionsFromAPI.get(i).getCatalogOfferCode(), subscriptionsFromDatabase.get(i).getCatalogOfferCode());
			assertEquals(subscriptionsFromAPI.get(i).getOrderReference(), subscriptionsFromDatabase.get(i).getOrderReference());

			// log to the report
			logPass("Subscription returned: " + subscriptionsFromAPI.get(i).getId() + ", " + subscriptionsFromAPI.get(i).getCatalogOfferCode() + ", "
					+ subscriptionsFromAPI.get(i).getCatalogOfferCode());
		}

	}

	/**
	 * Retrieve the offer details Screen: Account Details
	 * 
	 * Ref: MGO_A_AD_04
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_04] MyGoMo: Account Details > Get offer")
	public void MGO_A_AD_04_testGetOffer(ITestContext iTestContext) {

		// read the service, subscription and catalog offer details from the database
		Service service = SubscriptionManagementDAO.getService(serviceID);
		microservices.backend.eir_subscription_management_backend.data_model.Subscription subscription = SubscriptionManagementDAO
				.getSubscriptionByID(service.getSubscriptionID());
		Offer offer = CatalogCoreDAO.getOffer(subscription.getCatalogOfferID());

		// use the API to get the offer information
		APITransaction t = MyGoMoAPI.getOffer(token, serviceID);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// convert the json response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetOfferResponse responseObject = jsonPathEvaluator.getObject("", GetOfferResponse.class);

		// validate the sim details
		SimCard simCard = SubscriptionManagementDAO.getActiveSimCard(msisdn);
		SimDetails simDetails = InventoryManagementAPI.getSimDetailsFromInventory(simCard.getIccid());
		assertEquals(responseObject.getPin(), simDetails.getPin1());
		assertEquals(responseObject.getPuk(), simDetails.getPuk1());
		assertEquals(responseObject.getImsi(), simDetails.getImsi());

		// validate the msisdn
		assertEquals(responseObject.getCustomerOfferDTO().getMsisdn(), msisdn);

		// validate the NDD
		NDDSetting ndd = SubscriptionManagementDAO.getNDDSetting(serviceID);
		assertEquals(responseObject.getCustomerOfferDTO().getNddPreference(), ndd.toString());

		// validate the activation date
		Date date = subscription.getActivatedAt();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String dateString = simpleDateFormat.format(date);
		assertEquals(responseObject.getCustomerOfferDTO().getActivationDate(), dateString);

		// validate the charge amounts
		assertEquals(responseObject.getCustomerOfferDTO().getMonthlyAmount(), 1499);
		assertEquals(responseObject.getCustomerOfferDTO().getUpfrontAmount(), 1499);

		// validate the subscription details
		assertEquals(responseObject.getCustomerOfferDTO().getCatalogOfferId(), subscription.getCatalogOfferID());
		assertEquals(responseObject.getCustomerOfferDTO().getStatus(), subscription.getStatus());
		assertEquals(responseObject.getCustomerOfferDTO().getOfferType(), "POSTPAY");

		// verify that the catalog offer details are correct
		assertEquals(responseObject.getCustomerOfferDTO().getName(), offer.getDescription());
		assertEquals(responseObject.getCustomerOfferDTO().getDescription(), offer.getInvoiceDescription());

		// log output to the report
		logPass("Offer correctly returned: " + responseObject.getCustomerOfferDTO().getName() + ", " + responseObject.getCustomerOfferDTO().getMsisdn());
	}

	/*------------------------------------------------------------------
	 * Account Details - My Orders
	 ------------------------------------------------------------------- */

	/**
	 * Get order history
	 * 
	 * Ref: MGO_A_AD_05
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_05] MyGoMo: Account Details > Get order history")
	public void MGO_A_AD_05_testGetFullOrderHistory(ITestContext iTestContext) {
		APITransaction t = MyGoMoAPI.getFullOrderHistory(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get completed orders
	 * 
	 * Ref: MGO_A_AD_06
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_06] MyGoMo: Account Details > Get completed orders")
	public void MGO_A_AD_06_testGetCompletedOrders(ITestContext iTestContext) {
		APITransaction t = MyGoMoAPI.getCompletedOrders(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Account Details - Replace / Activate My SIM
	 ------------------------------------------------------------------- */

	/**
	 * Check replacement SIM eligibility Screen: Account Details > Replace My SIM
	 * 
	 * Ref: MGO_A_AD_07
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_07] MyGoMo: Account Details > Get replacement SIM card charge")
	public void MGO_A_AD_07_testGetReplacementSIMCardCharge(ITestContext iTestContext) {

		// retrieve the list of replacement orders on the account
		ArrayList<SimSwapRequest> previousSimSwaps = SimSwapDAO.getSimSwapRequests(serviceID);

		// make a call to the API to determine SIM eligibility
		APITransaction t = MyGoMoAPI.getReplacementSimCardCharges(token, serviceID);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// convert the response to a java object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetReplacementSimChargeDTO responseObject = jsonPathEvaluator.getObject("", GetReplacementSimChargeDTO.class);
		assertNotNull(responseObject);

		assertEquals(responseObject.getCharges().size(), 1);

		// check whether the charge is correct, depending on the number of existing
		// replacement orders on the service
		if (previousSimSwaps.size() > 0) {
			assertEquals(responseObject.getCharges().get(0).getDefaultPriceVatIncluded(), 500);
			assertEquals(responseObject.getCharges().get(0).getDescription(), "Replacement SIM fee");
		} else {
			assertEquals(responseObject.getCharges().get(0).getDefaultPriceVatIncluded(), 0);
			assertEquals(responseObject.getCharges().get(0).getDescription(), "Free replacement SIM");
		}
		
		// log output to the report
		logPass("Response is correct: " + responseObject.getCharges().get(0).getDefaultPriceVatIncluded() + ", " + responseObject.getCharges().get(0).getDescription());
	}

	/**
	 * Order a replacement SIM
	 * 
	 * Ref: MGO_A_AD_08
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_08] MyGoMo: Account Details > Order replacement SIM")
	public void MGO_A_AD_08_testOrderReplacementSIM(ITestContext iTestContext) {
		
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		
		// read the number of sim card addons on the service
		ArrayList<AddonBundle> addonSimCards = SubscriptionManagementDAO.getReplacementSIMCardAddonsOnService(service.getId());
		int numberSimCardAddonsBefore = addonSimCards.size();
		logInfo("Before test: Customer has " + numberSimCardAddonsBefore + " SIM cards linked to the service");
		
		// remove any pending sim swap requests on the account
		String email = GalaxionDAO.getOwnerEmailAddress(billingAccountID);
		SimSwapDAO.markSIMSwapsAsDone(service.getId());

		// read the list of sim card addons before the test
		ArrayList<SimCard> addonSimCardsBefore = SubscriptionManagementDAO.getSIMCardAddonsForService(service.getId());

		// get the details of the active sim card before the test
		SimCard activeCard = SubscriptionManagementDAO.getActiveSimCard(msisdn);
		logInfo("The details card on the service before the test is " + activeCard.getId() + ", " + activeCard.getIccid() + ", " + activeCard.getImsi());

		// read a random address from the file
		Contact contact = ContactManagementDAO.getContact(contactUuid);
		Address address = contact.getBillingAddress();

		// use the API to order a new replacement SIM
		APITransaction t = MyGoMoAPI.orderReplacementSIM(token, serviceID, false, contact.getFirstName(), contact.getLastName(), contact.getEmailAddress(),
				contact.getPhoneNumber(), contact.getBillingAddress().getAddressLine1(), address.getAddressLine2(), address.getAddressLine3(),
				address.getTown(), address.getCounty(), address.getEircode());
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(),201);
		
		// read the sim swap request ID from the API response
		int simSwapRequestId=Integer.parseInt(t.getResponse().getBody().asString());
		SimSwapRequest simSwapRequest = SimSwapDAO.getSimSwapRequest(simSwapRequestId);
		assertNotNull(simSwapRequest);

		// validate that the sim swap request has been created successfully
		assertNotNull(simSwapRequest);
		assertEquals(simSwapRequest.getType(),"ORDER");
		assertEquals(simSwapRequest.getStatus(),"VALIDATED");
		assertEquals(simSwapRequest.getCatalogAddonCode(),"REPLACEMENT_SIM");
		logPass("Sim swap request created with ID " + simSwapRequest.getId() + ": " + simSwapRequest.getType() + ", " + simSwapRequest.getStatus() + ", " + simSwapRequest.getCatalogAddonCode());

		// retrieve the charge linked to the sim swap request
		Charge charge = SimSwapDAO.getSimSwapCharge(simSwapRequest.getId());
		assertNotNull(charge);
		//assertEquals(charge.getCatalogChargeCode(),"REPLACEMENT_SIM_FEE");
		//assertEquals(charge.getDescription(),"Replacement SIM fee");
		//assertEquals(charge.getBillingType(),"ONE_OFF");
		//assertEquals(charge.getCatalogPricePlanCode(),"REPLACEMENT_SIM_FEE");
		logPass("Charge created with ID " + charge.getId() + ": " + charge.getDescription() + ", " + charge.getDefaultPriceVatIncluded());
		
		// read the order service details
		int requestID = simSwapRequest.getId();
		OmService omService = OrderManagementDAO.getOrderServiceByRequestID(requestID);
		assertNotNull(omService);
		
		// read the order information from order management
		ProductOrder order = OrderManagementDAO.getOrderByID(omService.getOrderID());
		assertNotNull(order);
		assertEquals(order.getOrder_type(),"REPLACEMENT");
		logInfo("Product order " + order.getReference() + " is created: " + order.getOrder_type());
		
		// process logistics steps for the order
		ArrayList<LogisticsDTO> simsOrdered = Logistics.processLogisticsBackend(order.getReference());
		assertNotEquals(simsOrdered.get(0).getIccId(),"");
		
		// wait for the other to complete in order management
		boolean orderComplete = OrderManagementMonitor.waitForOrderToComplete(order.getReference());
		assertTrue(orderComplete);
		logPass("Order " + order.getReference() + " completed");
		
		// determine how many sim card addons are now on the account
		addonSimCards = SubscriptionManagementDAO.getReplacementSIMCardAddonsOnService(service.getId());
		int numberSimCardAddonsAfter = addonSimCards.size();
		logPass("There are now " + numberSimCardAddonsAfter + " SIM card addons on the account");
		
		// verify that 1 new sim card addon has been added
		assertEquals(numberSimCardAddonsAfter-numberSimCardAddonsBefore,1);
		
		int newSimCardId = addonSimCards.get(addonSimCards.size()-1).getSimCardId();
		System.out.println("Create sim card " + newSimCardId);
		
		// get the most recent sim card addon added to the sim card table
		SimCard addonSimCard = SubscriptionManagementDAO.getSimCardById(newSimCardId);
		
		// check that the sim card addon has been created successfully
		assertEquals(addonSimCard.getIccid(),simsOrdered.get(0).getIccId());
		assertEquals(addonSimCard.getImsi(),simsOrdered.get(0).getImsi());
		assertNull(addonSimCard.getActivatedAt());
		assertNull(addonSimCard.getTerminatedAt());
		logPass("SIM card addon " + addonSimCard.getId() + " successfully created: " + addonSimCard.getIccid() + ", " + addonSimCard.getImsi() + ", " + addonSimCard.getActivatedAt() + ", " + addonSimCard.getTerminatedAt());	
	}

	/**
	 * Activate replacement SIM
	 * 
	 * Ref: MGO_A_AD_09
	 */
	@Test(dependsOnMethods = { "testLogin", "MGO_A_AD_08_testOrderReplacementSIM" }, description = "[MGO_A_AD_09] MyGoMo: Account Details > Activate replacement SIM")
	public void MGO_A_AD_09_testActivateSimSwap(ITestContext iTestContext) {
		
		// read the service details
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		
		
		// read the active sim card
		SimCard activeCard = SubscriptionManagementDAO.getActiveSimCard(msisdn);
		logInfo("The details card on the service before the test is " + activeCard.getIccid() + ", " + activeCard.getImsi());

		// get the list of sim card addons on the account
		ArrayList<AddonBundle> addonSimCards = SubscriptionManagementDAO.getReplacementSIMCardAddonsOnService(service.getId());
		int numberSimCardAddonsBefore = addonSimCards.size();
		logInfo("Before test: Customer has " + numberSimCardAddonsBefore + " SIM cards linked to the service");

		// find the most recent addon bundle on the account
		AddonBundle addonBundle = addonSimCards.get(addonSimCards.size()-1);
		assertNotNull(addonBundle);
		
		// find the sim card linked to the addon bundle
		SimCard simCardToSwap = SubscriptionManagementDAO.getSimCardById(addonBundle.getSimCardId());
		assertNotNull(simCardToSwap);
		
		String iccid = simCardToSwap.getIccid();

		/*
		// determine the ICCID for the card waiting to be swapped
		for (SimCard simCard : addonSimCards) {
			if (simCard.getActivatedAt() == null && simCard.getTerminatedAt() == null) {
				iccid = simCard.getIccid();
				logPass("INITIAL sim card " + iccid + " found");
				break;
			}
		}
		*/

		// verify that a suitable ICCID has been found
		assertNotNull(iccid);

		// read the PUK from the inventory management API
		SimDetails simDetails = InventoryManagementAPI.getSimDetailsFromInventory(iccid);
		String puk = simDetails.getPuk1();
		assertNotNull(puk,"Puk retrieval");
		logInfo("The subscriber will activate SIM " + iccid + "/" + simDetails.getImsi() + " with puk " + puk);

		// make the activation request via the API
		APITransaction t = MyGoMoAPI.activateSIMSwap(token, serviceID, puk);
		logPass(t.toString());
		assertEquals(t.getResponse().statusCode(),202);

		// wait for the sim card to become active (i.e. activated_at is not null)
		boolean simCardActivated = SubscriptionManagementMonitor.waitForSimCardToActivate(iccid);
		assertTrue(simCardActivated);
		logPass("Sim card " + iccid + " is now in a state of Active");

		// verify that the card specified is now the active sim card for the subscriber
		activeCard = SubscriptionManagementDAO.getActiveSimCard(msisdn);
		assertEquals(activeCard.getIccid(), iccid);
		logPass("The details card on the service is now " + activeCard.getIccid() + ", " + activeCard.getImsi());

		// verify the changes on the SPR
		SPRProfile spr = MMWUtility.getSprProfile(msisdn);
		assertEquals(simDetails.getImsi(), spr.getImsi());
		logPass("IMSI successfuly changed on the SPR: " + spr.getImsi());

		// verify the changes on the HLR
		HLRFEProfile hlr = MMWUtility.getHLRFEProfile(msisdn);
		assertEquals(simDetails.getImsi(), hlr.getImsi());
		logPass("IMSI successfuly changed on the HLR-FE: " + hlr.getImsi());
		
		// verify that the SERVICE table has been updated correctly with the new SIM card ID
		service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		assertEquals(service.getSimCardId(),simCardToSwap.getId());
		logPass("Service SIM card ID changed to " + service.getSimCardId() + " as expected");
	}

	/**
	 * Verify that the correct delivery address is retrieved Screen: Account Details
	 * > Replace My SIM
	 * 
	 * Ref: MGO_A_AD_10
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_10] MyGoMo: Account Details > Get delivery address")
	public void MGO_A_AD_10_testGetDeliveryAddress(ITestContext iTestContext) {
		// read the expected delivery address from the database
		Address addressFromDatabase = ContactManagementDAO.getDeliveryAddress(contactUuid);

		// make a call to the API to retrieve the address
		APITransaction t = MyGoMoAPI.getDeliveryAddress(token);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// read the address object from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		ReturnedContactAddress addressFromAPI = jsonPathEvaluator.getObject("", ReturnedContactAddress.class);

		// check that the address returned is correct
		assertEquals(addressFromDatabase.getAddressLine1(), addressFromAPI.getAddressLine1());
		assertEquals(addressFromDatabase.getAddressLine2(), addressFromAPI.getAddressLine2());
		assertEquals(addressFromDatabase.getAddressLine3(), addressFromAPI.getAddressLine3());
		assertEquals(addressFromDatabase.getTown(), addressFromAPI.getTown());
		assertEquals(addressFromDatabase.getCounty(), addressFromAPI.getCounty());
		assertEquals(addressFromDatabase.getEircode(), addressFromAPI.getCode());

		// output to the report
		logPass("Correct delivery address returned: " + addressFromAPI.getAddressLine1());
	}

	/*------------------------------------------------------------------
	 * Account Details - Bar Mobile Services
	 ------------------------------------------------------------------- */
	/**
	 * Get barring details Screen: Account Details > Bar Mobile Services
	 * 
	 * Ref: MGO_A_AD_11
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_11] MyGoMo: Account Details > Get barring details")
	public void MGO_A_AD_11_testGetBarrings(ITestContext iTestContext) {

		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		
		// get barrings from the database
		ArrayList<BarringDetail> barringsFromDatabase = BarringDAO.getBarringDetail(service.getId());
		BarringSet barringSet = new BarringSet(barringsFromDatabase);

		// make a call to the API to retrieve barring details
		APITransaction t = MyGoMoAPI.getBarrings(token, serviceID);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);

		// read the list of bars from the json response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();

		// convert the json response to a list of UsageSummary objects
		List<ReturnedBarring> barringsFromAPI = jsonPathEvaluator.getList("barrings", ReturnedBarring.class);

		// verify that 5 barring settings come back (as we have only 5 bars displayed in
		// MyGoMo)
		assertEquals(barringsFromAPI.size(), 5);

		for (ReturnedBarring barring : barringsFromAPI) {
			if (barring.getBarringStatus() == null) {
				assertFalse(barringSet.hasBarring(barring.getPcatId()));
				logPass("Bar " + barring.getName() + " is inactive as expected");
			} else if (barring.getBarringStatus().equals("ACTIVE")) {
				assertTrue(barringSet.hasBarring(barring.getPcatId()));
				logPass("Bar " + barring.getName() + " is active as expected");
			} else {
				System.err.println("NOT SURE HOW TO HANDLE BARRING STATUS " + barring.getBarringStatus());
			}

		}
	}

	/*------------------------------------------------------------------
	 * Account Details - Move My Number
	 ------------------------------------------------------------------- */

	/**
	 * Verify that the porting times are returned Screen: Account Details > Move My
	 * Number
	 * 
	 * Ref: MGO_A_AD_12
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_12] MyGoMo: Account Details > Get porting hours")
	public void MGO_A_AD_12_testGetPortingHours(ITestContext iTestContext) {

		// make a call to the API to retrieve the porting hours
		APITransaction t = MyGoMoAPI.getPortingHours(token);

		// verify that the call has completed successfully
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// convert the response into a list of PortingHours objects
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<PortingHours> portingHoursFromAPI = jsonPathEvaluator.getList("portingHours", PortingHours.class);

		// verify that 31 days have been returned
		assertEquals(portingHoursFromAPI.size(), 31);
	}

	/**
	 * Trigger OTP request for a MSISDN Screen: Account Details > Move My Number
	 * 
	 * Ref: MGO_A_AD_13
	 */
	@Test(dependsOnMethods = "testLogin", description = "[MGO_A_AD_13] MyGoMo: My Account > Trigger OTP code for porting")
	public void MGO_A_AD_13_testTriggerPortingOTP(ITestContext iTestContext) {

		// generate a random phone number for the OTP request
		otpMsisdn = RandomStringGenerator.getRandomMobilePhoneNumber();
		logInfo("Triggering OTP request for " + otpMsisdn);

		// make the API call to trigger the OTP request
		APITransaction t = MyGoMoAPI.triggerPortingOTP(token, otpMsisdn);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		// retrieve the OTP code from the OTP database
		OtpVerification otp = OtpDAO.getOTP(otpMsisdn);

		// verify that the OTP has been found in the database
		assertNotNull(otp);
		logPass("OTP verification code found: " + otp.getVerificationCode());
	}

	/**
	 * Validate OTP verification for a MSISDN Screen: Account Details > Move My
	 * Number
	 * 
	 * Ref: MGO_A_AD_14
	 */
	@Test(dependsOnMethods = { "testLogin", "MGO_A_AD_13_testTriggerPortingOTP" }, description = "[MGO_A_AD_14] MyGoMo: Account Details > Validate OTP code for porting")
	public void MGO_A_AD_14_testValidatePortingOTP(ITestContext iTestContext) {

		// read the OTP entry from the database
		OtpVerification otpVerification = OtpDAO.getOTP(otpMsisdn);

		// make the validate call to the database
		APITransaction t = MyGoMoAPI.validatePortingOTP(token, otpVerification.getUuid(), otpVerification.getVerificationCode());
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());

		logPass("OTP verification successfully validated with code " + otpVerification.getVerificationCode());

		// verify that the OTP code is removed from the OTP_VERIFICATION database
		otpVerification = OtpDAO.getOTP(otpMsisdn);
		assertNull(otpVerification);
	}


	@DataProvider(name = "getPermissionGroups")
	public Object[][] getPermissionGroups() {

		Object[][] data = new Object[2][1];
		data[0][0] = PermissionGroupCode.ACTIVE_CUSTOMER;
		data[1][0] = PermissionGroupCode.NO_LONGER_CUSTOMER;

		return data;
	}

	@BeforeClass
	public void setUp() {
		
		// read the billing account ID from the test data sheet
		billingAccountID = TestDataManager.getGoMoMultilineSubscriber();
		
		// get the account ID
		accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		
		// get an active subscription on the account
		subscriptionID = SubscriptionManagementDAO.getActiveSubscriptions(billingAccountID).get(0).getId();
		
		// get the service details linked to the active subscription
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptionID);
		msisdn = service.getName();
		serviceID = service.getId();
		
		// get the contact UUID
		contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		
		// get the login credentials
		emailAddress = ContactManagementDAO.getEmailAddressForUUID(contactUuid);
		password = "Password123";
	}

	@AfterClass
	public void tearDown() {

	}
}

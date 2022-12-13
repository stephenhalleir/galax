package microservices.frontend.eir_myaccount_frontend.api;

import io.restassured.response.Response;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_contact_management_backend.enums.AddressType;
import microservices.backend.eir_contact_management_backend.enums.PermissionCode;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import microservices.backend.eir_otp_verification_backend.dto.TriggerOtpDTO;
import microservices.backend.eir_otp_verification_backend.dto.ValidateOtpDTO;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import microservices.frontend.common_ui.dto.ValidateOTPDTO;
import microservices.frontend.common_ui.dto.service_actions.request.update_ndd.UpdateNddDTO;
import microservices.frontend.eir_myaccount_frontend.dto.ActivateSimSwapMyGoMoDTO;
import microservices.frontend.eir_myaccount_frontend.dto.TriggerForgotEmailOtpDTO;
import microservices.frontend.eir_myaccount_frontend.dto.UpdateEmailDTO;
import microservices.frontend.eir_myaccount_frontend.dto.UpdateExpiryDateDTO;
import microservices.frontend.eir_myaccount_frontend.dto.requests.order_replacement_sim.GoMoOrderReplacementSimDTO;
import microservices.frontend.eir_myaccount_frontend.dto.requests.update_address.UpdateAddressDTO;
import microservices.frontend.eir_myaccount_frontend.dto.requests.update_contact_preferences.UpdatePermissionsDTO;
import testCases.Services;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;
import utilities.generic.api.HTTPMethod;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class MyGoMoAPI {
	
	/*------------------------------------------------------------------
	 * Authentication
	 ------------------------------------------------------------------- */
	/**
	 * Generate a token for MyGoMo for a given email and password
	 * 
	 * @param email
	 * @param password
	 * @return token as String
	 */
	public static String getToken(String email, String password) {
		Client c = KeycloakDAO.getClient(Services.MYGOMO, Realm.gomo);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.gomo, email, password);
	}
	
	/*------------------------------------------------------------------
	 * Login screen
	 ------------------------------------------------------------------- */
	/**
	 * Generate Forgot Email OTP code
	 * 
	 * Ref: MGO_A_LP_01
	 */
	public static APITransaction triggerRecoverEmailOTP(String msisdn) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/recover_email/otp/generate";
		TriggerForgotEmailOtpDTO dto = new TriggerForgotEmailOtpDTO(msisdn);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(HTTPMethod.GET,url, payload, r);
	}
	
	/**
	 * Validate Forgot Email OTP code
	 *
	 * Ref: MGO_A_LP_02
	 */
	public static APITransaction validateRecoverEmailOTP(String otpCode, String otpUuid) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/recover_email/otp/validate";
		ValidateOTPDTO payload = new ValidateOTPDTO(otpCode, otpUuid);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(payload), null);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Home screen
	 ------------------------------------------------------------------- */
	/**
	 * Get web notification count
	 * 
	 * Ref: MGO_A_HS_01
	 */
	public static APITransaction getWebNotificationCount(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/web_notification/count";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * My Alerts
	 ------------------------------------------------------------------- */
	/**
	 * Get web notifications
	 * 
	 * Ref: MGO_A_AL_01
	 */
	public static APITransaction getWebNotifications(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/web_notification?page=0";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Dismiss web notification
	 * 
	 * Ref: MGO_A_AL_02
	 */
	public static APITransaction dismissWebNotification(String token, int webNotificationId) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/web_notification/" + webNotificationId + "/dismiss";
		Response r = RestAssuredUtil.galaxionPatch(url, null, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * My Profile
	 ------------------------------------------------------------------- */

	/**
	 * Get contacts
	 * 
	 * Ref: MGO_A_PR_01
	 */
	public static APITransaction getContacts(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/contacts";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get contact permissions
	 * 
	 * Ref: MGO_A_PR_02
	 */
	public static APITransaction getPermissions(String token, PermissionGroupCode group) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/permissions?permissionGroupEnum=" + group;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Update contact permissions
	 * 
	 * Ref: MGO_A_PR_03
	 */
	public static APITransaction updateContactPreferences(String token, PermissionGroupCode groupCode, PermissionCode permission, boolean enabled) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/permissions";
		UpdatePermissionsDTO dto = new UpdatePermissionsDTO(groupCode);
		dto.getPermissionGroups().get(0).setPermission(permission, enabled);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Get billing address
	 * 
	 * Ref: MGO_A_PR_04
	 */
	public static APITransaction getBillingAddress(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/addresses/BILLING";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Update address
	 * 
	 * Ref: MGO_A_PR_05
	 */
	public static APITransaction updateAddress(String token, String addressLine1, String addressLine2, String addressLine3, String town, String county,
			String eircode, AddressType addressType) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/addresses";
		UpdateAddressDTO dto = new UpdateAddressDTO(addressLine1, addressLine2, addressLine3, town, county, eircode, addressType.toString());
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPatch(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Eircode lookup
	 * 
	 * Ref: MGO_A_PR_06
	 */
	public static APITransaction eircodeLookup(String token, String eircode) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/address?eircode=" + eircode;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Check NDD settings
	 * 
	 * Ref: MGO_A_PR_07
	 */
	public static APITransaction getNDD(String token, int serviceID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/services/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Update NDD settings
	 * 
	 * Ref: MGO_A_PR_08
	 */
	public static APITransaction updateNddSetting(String token, int serviceID, NDDSetting setting) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/services/" + serviceID;
		UpdateNddDTO dto = new UpdateNddDTO(setting);
		Response r = RestAssuredUtil.galaxionPatch(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Trigger password reset password
	 * 
	 * Ref: MGO_A_PR_09
	 */
	public static APITransaction triggerPasswordReset(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/password/reset";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Trigger change email request
	 *
	 * Ref: MGO_A_PR_10
	 */
	public static APITransaction updateEmail(String token, String newEmail) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/change_email/request";
		UpdateEmailDTO dto = new UpdateEmailDTO(newEmail);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Get service details (on My Profile > NDD page)
	 *
	 * Ref: MGO_A_PR_11
	 */
	public static APITransaction getService(String token, int serviceID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/services/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/*------------------------------------------------------------------
	 * My Payments
	 ------------------------------------------------------------------- */

	/**
	 * Get account balance
	 * 
	 * Ref: MGO_A_PY_01
	 */
	public static APITransaction getAccountBalance(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v1/public/auth/billing_account/balance";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get payer details
	 * 
	 * Ref: MGO_A_PY_02
	 */
	public static APITransaction getPayer(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/payer";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get transaction history
	 * 
	 * Ref: MGO_A_PY_03
	 * 
	 * @param from - date yyyy-MM-dd - e.g. 2021-06-28
	 * @param to   - date yyyy-MM-dd - e.g. 2021-06-28
	 */
	public static APITransaction getTransactionHistory(String token, String from, String to) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v1/public/auth/billing_account/account_summary?from=" + from + "&to=" + to + "&page=0";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Update a card's expiry date
	 * 
	 * Ref: MGO_A_PY_04
	 * 
	 * @param newExpiryDate - yyyy-MM-dd - e.g. 2023-01-31
	 */
	public static APITransaction editExpiryDate(String token, int cardID, String newExpiryDate) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/payer/card_payment_method/" + cardID + "/update_expiry_date";
		UpdateExpiryDateDTO dto = new UpdateExpiryDateDTO(newExpiryDate);
		Response r = RestAssuredUtil.galaxionPatch(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/**
	 * Get the Add Card hosted payment page Screen: My Payments > Manage My Cards
	 * 
	 * Ref: MGO_A_PY_05
	 */
	public static APITransaction generateHPP(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/hpp/add_card/generate";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Update default card
	 * 
	 * Ref: MGO_A_PY_06
	 */
	public static APITransaction updateDefaultPaymentMethod(String token, int paymentMethodID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/payer/default_payment_method/" + paymentMethodID;
		Response r = RestAssuredUtil.galaxionPut(url, null, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * My Usage
	 ------------------------------------------------------------------- */
	/**
	 * Get usage
	 * 
	 * Ref: MGO_S_US_01
	 * 
	 * @param billingPeriod "yyyyMM" - e.g. 202109
	 */
	public static APITransaction getUsage(String token, int serviceID, String billingPeriod, int maxRecords) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/usage/service/" + billingPeriod + "/POSTPAY/" + serviceID + "?page=0&size="
				+ maxRecords;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get usage summary
	 * 
	 * Ref: MGO_S_US_02
	 * 
	 * @param billingPeriod "yyyyMM" - e.g. 202109
	 */
	public static APITransaction getUsageSummary(String token, int serviceID, String billingPeriod) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/usage_summary/service/" + billingPeriod + "/POSTPAY/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get ref subtotal rating types
	 * 
	 * Ref: MGO_S_US_03
	 */
	public static APITransaction getRatingSubtotalTypes(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/reference/rating_subtotal_type";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account Details - View account
	 ------------------------------------------------------------------- */

	/**
	 * Get accounts
	 * 
	 * Ref: MGO_A_AD_01
	 */
	public static APITransaction getAccounts(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/accounts";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get services
	 * 
	 * Ref: MGO_A_AD_02
	 */
	public static APITransaction getServices(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/services";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get subscriptions
	 * 
	 * Ref: MGO_A_AD_03
	 */
	public static APITransaction getSubscriptions(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/subscriptions";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get offer details for service
	 * 
	 * Ref: MGO_A_AD_04
	 */
	public static APITransaction getOfferDetails(String token, int serviceID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/customer-offer-backend/public/auth/offer/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account Details - My Orders
	 ------------------------------------------------------------------- */

	/**
	 * Get order history
	 * 
	 * Ref: MGO_A_AD_05
	 */
	public static APITransaction getFullOrderHistory(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/order/?startDateTime=2018-01-07T19:24:58.730Z";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get completed orders
	 * 
	 * Ref: MGO_A_AD_06
	 */
	public static APITransaction getCompletedOrders(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/order/?startDateTime=2021-06-28T13:25:20.469Z&status=COMPLETED";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account Details - Replace / Activate My SIM
	 ------------------------------------------------------------------- */

	/**
	 * Get replacement SIM card charges
	 * 
	 * Ref: MGO_A_AD_07
	 */
	public static APITransaction getReplacementSimCardCharges(String token, int serviceID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/sim-swap/api/v1/public/auth/sim-cards/charges?serviceId=" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Order a replacement SIM
	 * 
	 * Ref: MGO_A_AD_08
	 */
	public static APITransaction orderReplacementSIM(String token, int serviceId, boolean barServices, String firstName, String lastName, String email, String phoneNumber, String addressLine1, String addressLine2, String addressLine3, String town,
			String county, String eircode) {

		// construct the URL
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/sim-swap/api/v1/public/auth/sim-cards/order";

		// build the DTO
		GoMoOrderReplacementSimDTO dto = new GoMoOrderReplacementSimDTO();
		dto.getAddress().setAddressLine1(addressLine1);
		dto.getAddress().setAddressLine2(addressLine2);
		dto.getAddress().setAddressLine3(addressLine3);
		dto.getAddress().setTown(town);
		dto.getAddress().setCounty(county);
		dto.getAddress().setCode(eircode);
		dto.setBarServices(barServices);
		dto.setServiceId(serviceId);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setEmail(email);
		dto.setPhoneNumber(phoneNumber);

		String payload=PojoToJsonConverter.getJSON(dto);
		
		// make the call to the API
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Activate replacement SIM
	 * 
	 * Ref: MGO_A_AD_09
	 */
	public static APITransaction activateSIMSwap(String token, int serviceId, String puk) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/sim-swap/api/v1/public/auth/sim-cards/activate";
		ActivateSimSwapMyGoMoDTO dto = new ActivateSimSwapMyGoMoDTO(puk,serviceId);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/**
	 * Get delivery address for replacement SIM order
	 * 
	 * Ref: MGO_A_AD_10
	 */
	public static APITransaction getDeliveryAddress(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/api/v2/public/auth/addresses/DELIVERY";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/*------------------------------------------------------------------
	 * Account Details - Bar Mobile Services
	 ------------------------------------------------------------------- */
	/**
	 * Get barrings
	 * 
	 * Ref: MGO_A_AD_11
	 */
	public static APITransaction getBarrings(String token, int serviceID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth//barring/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/*------------------------------------------------------------------
	 * Account Details - Move My Number
	 ------------------------------------------------------------------- */
	
	/**
	 * Trigger porting OTP
	 * 
	 * Ref: MGO_A_AD_13
	 */
	public static APITransaction triggerPortingOTP(String token, String msisdn) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/otp";
		TriggerOtpDTO dto = new TriggerOtpDTO(msisdn, Brand.GOMO);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/**
	 * Validate porting OTP
	 * 
	 * Ref: MGO_A_AD_14
	 */
	public static APITransaction validatePortingOTP(String token, String uuid, String otpCode) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/otp/" + uuid + "/validate";
		ValidateOtpDTO dto = new ValidateOtpDTO(otpCode);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}
	/*------------------------------------------------------------------
	 * To sort
	 ------------------------------------------------------------------- */

	

	

	public static APITransaction getOffer(String token, int serviceID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/customer-offer-backend/public/auth/offer/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	// This doesn't work on the environment currently
	public static APITransaction getBillHistory(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/billing_account/bill_history";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	public static APITransaction getPortingHours(String token) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/port/time/GOMO/MYACCOUNT";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	public static APITransaction getScheduledPorts(String token, int serviceID) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/rest/port/service/uncompleted/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	public static APITransaction getCurrentOperator(String token, String msisdn) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/port/operator/" + msisdn;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	public static APITransaction updateNDD(String token, int serviceID, NDDSetting setting) {
		String url = EnvironmentDirectory.getMyGoMoAPI() + "/public/auth/service/" + serviceID + "/ndd_preference";
		UpdateNddDTO dto = new UpdateNddDTO(setting);
		Response r = RestAssuredUtil.galaxionPut(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	

	
}

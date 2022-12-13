package microservices.frontend.eir_crm_ui_frontend.api;

import io.restassured.response.Response;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentType;
import microservices.backend.eir_catalog_core_backend.dto.ChannelDTO;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_contact_management_backend.data_model.Address;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.enums.AddressType;
import microservices.backend.eir_security_question_backend.dto.SecurityVerificationDTO;
import microservices.backend.eir_security_question_backend.enums.SecurityQuestionCode;
import microservices.backend.eir_security_question_backend.enums.VerificationStatusEnum;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.enums.AccountType;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import microservices.frontend.common_ui.dto.service_actions.request.update_ndd.UpdateNddDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.PAYGOrderReplacementSimDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.ScheduleTerminationDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.TriggerSimSwapPAYGRetailDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.acquisitions.AddAddonToProspectOfferDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.acquisitions.AddOfferToCartDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.acquisitions.AddTopupDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.acquisitions.AssignSIMCardDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.acquisitions.CreateProspectDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.adjustment.ProcessAccountAdjustmentDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.adjustment.ProcessServiceAdjustmentDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.change_offer.ChangeOfferDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.contact_management.AddAuthorizedUserDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.contact_management.EditAuthorizedUserDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.activate_replacement_sim.ActivateReplacementSIMDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.process_topup.ProcessTopupDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.update_bill_delivery_type.BillDeliveryType;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.update_bill_delivery_type.UpdateBillDeliveryTypeDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.update_contact.UpdateContactDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.update_contact_number.UpdateContactNumberDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.update_email.UpdateEmailAddressDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.update_security_question.UpdateSecurityQuestionDTO;
import microservices.frontend.eir_eshop_frontend.dto.AcceptTermsDTO;
import microservices.frontend.eir_eshop_frontend.dto.ValidateProspectDTO;
import microservices.frontend.eir_myaccount_frontend.dto.requests.update_address.UpdateAddressDTO;
import testCases.Services;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.api.APITransaction;
import utilities.generic.api.HTTPMethod;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class PAYGCRMAPI {

	public static String baseURL = EnvironmentDirectory.getPAYGCRMAPI();
	// public static String brand = "EIR";

	/*------------------------------------------------------------------
	 * Acquisitions
	 ------------------------------------------------------------------- */

	/**
	 * Create prospect
	 * 
	 * Ref: CRM_A_AQ_01
	 */
	public static APITransaction createProspect(String token, ChannelCode channelCode, OfferType offerType) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect";
		CreateProspectDTO dto = new CreateProspectDTO(Brand.EIR, channelCode, offerType);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Add offer to cart
	 * 
	 * Ref: CRM_A_AQ_02
	 */
	public static APITransaction addOfferToCart(String token, String prospectUuid, int offerId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart/offer";
		AddOfferToCartDTO dto = new AddOfferToCartDTO(offerId);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Get prospect customer details
	 * 
	 * Ref: CRM_A_AQ_03
	 */
	public static APITransaction getCustomerDetails(String token, String prospectUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/customer_details";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get cart details
	 * 
	 * Ref: CRM_A_AQ_04
	 */
	public static APITransaction getCartDetails(String token, String prospectUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get available offers
	 * 
	 * Ref: CRM_A_AQ_05
	 */
	public static APITransaction getAvailableOffers(String token, String prospectUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/offer?prospectUuid=" + prospectUuid;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get available addons
	 * 
	 * Ref: CRM_A_AQ_06
	 */
	public static APITransaction getAvailableAddons(String token, String prospectUuid, int catalogOfferId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/offer/" + catalogOfferId + "/addon?prospectUuid=" + prospectUuid;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Add addon to offer
	 * 
	 * Ref: CRM_A_AQ_07
	 */
	public static APITransaction addAddonToProspectOffer(String token, String prospectUuid, int offerID, int catalogAddonID) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart/offer/" + offerID + "/addon";
		AddAddonToProspectOfferDTO dto = new AddAddonToProspectOfferDTO(catalogAddonID);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get SIM details
	 * 
	 * Ref: CRM_A_AQ_08
	 */
	public static APITransaction getSIMDetails(String token, String msisdn) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/inventory-management/private/auth/simcards/search?number=" + msisdn
				+ "&status=ACTIVATED&inventorypool=METEOR_PAIRED_CUSTOMER_POOL";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Set NDD on prospect offer
	 * 
	 * Ref: CRM_A_AQ_09
	 */
	public static APITransaction setNDDOnProspectOffer(String token, String prospectUuid, int offerID, NDDSetting preference) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart/offer/" + offerID + "/directory";
		UpdateNddDTO dto = new UpdateNddDTO(preference);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, r);
	}

	/**
	 * Add topup prospect offer
	 * 
	 * Ref: CRM_A_AQ_10
	 */
	public static APITransaction setTopupAmountOnProspectOffer(String token, String prospectUuid, int offerID, int topupAmount) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart/offer/" + offerID + "/top_up";
		AddTopupDTO dto = new AddTopupDTO(topupAmount);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, r);
	}

	/**
	 * Assign a SIM card to an order
	 * 
	 * Ref: CRM_A_AQ_11
	 */
	public static APITransaction assignSIMToProspectOffer(String token, String prospectUuid, int offerID, String iccid, String msisdn) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart/offer/" + offerID + "/sim";
		AssignSIMCardDTO dto = new AssignSIMCardDTO(iccid, msisdn);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, r);
	}

	/**
	 * Update prospect as Anonymous
	 * 
	 * Ref: CRM_A_AQ_12
	 */
	public static APITransaction setProspectAsAnonymous(String token, String prospectUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/customer_details";

		// String payload =
		// "{\"contactPermission\":{\"allowThirdParty\":false,\"permissionGroupResponse\":[{\"permissionGroup\":\"ACTIVE_CUSTOMER\",\"permissions\":[{\"enabled\":false,\"permission\":\"ALLOW_EMAIL_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_SMS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_PHONE_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_FOTS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\"}]},{\"permissionGroup\":\"NO_LONGER_CUSTOMER\",\"permissions\":[{\"enabled\":false,\"permission\":\"ALLOW_EMAIL_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_SMS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_PHONE_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_FOTS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\"}]}]},\"owner\":{\"email\":\"\",\"person\":{\"firstName\":\"\",\"lastName\":\"\"}},\"deliveryAddress\":{\"addressLine1\":\"\",\"county\":\"\",\"eircode\":\"\",\"town\":\"\"},\"hasAgreedTermsAndConditions\":false,\"isAnonymous\":true}";

		String payload = "{\"contactPermission\":{\"allowThirdParty\":false,\"permissionGroupResponse\":[{\"permissionGroup\":\"NO_LONGER_CUSTOMER\",\"permissions\":[{\"enabled\":false,\"permission\":\"ALLOW_SMS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_PHONE_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_FOTS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\"},{\"enabled\":true,\"permission\":\"ALLOW_EMAIL_CONTACT\"}]},{\"permissionGroup\":\"ACTIVE_CUSTOMER\",\"permissions\":[{\"enabled\":false,\"permission\":\"ALLOW_SMS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_FOTS_CONTACT\"},{\"enabled\":false,\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\"},{\"enabled\":true,\"permission\":\"ALLOW_EMAIL_CONTACT\"},{\"enabled\":true,\"permission\":\"ALLOW_PHONE_CONTACT\"}]}]},\"owner\":{\"email\":\"$email\",\"mobileNumber\":\"0183139839\",\"person\":{\"firstName\":\"Steve\",\"lastName\":\"Test\",\"birthDate\":\"1981-10-10\"}},\"deliveryAddress\":{\"addressLine1\":\"UNIT 4050\",\"addressLine2\":\"KINGSWOOD AVENUE\",\"addressLine3\":\"CITYWEST BUSINESS CAMPUS\",\"county\":\"CO_DUBLIN\",\"eircode\":\"D24YX53\",\"town\":\"DUBLIN 24\"},\"billingAddress\":{\"addressLine1\":\"UNIT 4050\",\"addressLine2\":\"KINGSWOOD AVENUE\",\"addressLine3\":\"CITYWEST BUSINESS CAMPUS\",\"county\":\"CO_DUBLIN\",\"eircode\":\"D24YX53\",\"town\":\"DUBLIN 24\"},\"securityQuestion\":{\"questionCode\":\"MEMORABLE_DATE\",\"response\":\"fdgjhfyh\"},\"hasAgreedTermsAndConditions\":false,\"isAnonymous\":false}";
		payload = payload.replace("$email", "steve.test." + System.currentTimeMillis() + "@eir.ie");
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, r);
	}

	/**
	 * Accept T&Cs
	 * 
	 * Ref: CRM_A_AQ_14
	 */
	public static APITransaction acceptProspectTCs(String token, String prospectUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/terms_and_conditions";
		AcceptTermsDTO dto = new AcceptTermsDTO();
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, r);
	}

	/**
	 * Validate and submit order
	 * 
	 * Ref: CRM_A_AQ_15
	 */
	public static APITransaction validateRetailProspect(String token, String prospectUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/validate";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get HPP
	 * 
	 * Ref: CRM_A_AQ_16
	 */
	public static APITransaction getHostedPaymentPage(String token, String prospectUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/payment/" + prospectUuid + "/hosted_payment_page";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}


	/**
	 * Validate payment and submit prospect
	 * 
	 * Ref: CRM_A_AQ_17
	 */
	public static APITransaction validateTelesalesProspect(String token, String prospectUuid, String hppResponse) {

		ValidateProspectDTO dto = new ValidateProspectDTO(hppResponse);

		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/payment/" + prospectUuid + "/validate";
		String payload=PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(HTTPMethod.POST,url, payload, r);
	}

	/*------------------------------------------------------------------
	 * Search screen
	 ------------------------------------------------------------------- */
	/**
	 * Perform a search via the CRM UI API
	 * 
	 * Ref: CRM_A_SE_01
	 * 
	 * @param token
	 * @param contactFirstName
	 * @param contactLastName
	 * @param contactMainEmail
	 * @param contactPhoneNumber
	 * @param msisdn
	 * @param billingAccountId
	 * @param iccid
	 * @return
	 */
	public static APITransaction search(String brand, String token, String contactFirstName, String contactLastName, String contactMainEmail,
			String contactPhoneNumber, String msisdn, String billingAccountId, String iccid) {

		String url = getUrl() + "/search-engine/api/v1/private/auth/accounts/paginated?";

		if (contactFirstName != null) {
			url = url + "&contactFirstName=" + contactFirstName;
		}

		if (contactLastName != null) {
			url = url + "&contactLastName=" + contactLastName;
		}

		if (billingAccountId != null) {
			url = url + "&billingAccountId=" + billingAccountId;
		}

		if (contactMainEmail != null) {
			url = url + "&contactMainEmail=" + contactMainEmail;
		}

		if (iccid != null) {
			url = url + "&iccid=" + iccid;
		}

		if (msisdn != null) {
			url = url + "&msisdn=" + msisdn;
		}

		if (contactPhoneNumber != null) {
			url = url + "&contactPhoneNumber=" + contactPhoneNumber;
		}

		url = url + "&brand=" + brand + "&accountType=B2C&page=0";
		url = url.replace("?&", "?");

		// trigger the GET request
		return new APITransaction(url, RestAssuredUtil.galaxionGet(url, token));
	}

	/**
	 * Perform a search via the CRM UI API
	 * 
	 * Ref: CRM_A_SE_01
	 * 
	 * Same as above but takes billing account ID as an integer
	 */
	public static APITransaction search(String brand, String token, String contactFirstName, String contactLastName, String contactMainEmail,
			String contactPhoneNumber, String msisdn, int billingAccountId, String iccid) {
		return search(brand, token, contactFirstName, contactLastName, contactMainEmail, contactPhoneNumber, msisdn, Integer.toString(billingAccountId), iccid);
	}

	/*------------------------------------------------------------------
	 * Account: View Account screen
	 ------------------------------------------------------------------- */
	/**
	 * Get keycloak account and available channels
	 * 
	 * Ref: CRM_A_AC_01
	 */
	public static APITransaction getKeycloakAccount(String token) {
		return triggerGetRequest(token, EnvironmentDirectory.getKeycloakURL() + "/auth/realms/eir/account");
	}

	/**
	 * Get account details
	 * 
	 * Ref: CRM_A_AC_02
	 */
	public static APITransaction getAccount(String token, int accountID) {
		return triggerGetRequest(token, getUrl() + "/subscription-management/api/v2/private/auth/accounts/" + accountID);
	}

	/**
	 * Get contacts
	 * 
	 * Ref: CRM_A_AC_03
	 */
	public static APITransaction getContacts(String token, int accountID) {
		return triggerGetRequest(token, getUrl() + "/subscription-management/api/v2/private/auth/accounts/" + accountID + "/contacts");
	}

	/**
	 * Get subscriptions
	 * 
	 * Ref: CRM_A_AC_04
	 */
	public static APITransaction getSubscriptions(String token, int accountID) {
		return triggerGetRequest(token, getUrl() + "/subscription-management/api/v2/private/auth/accounts/" + accountID + "/subscriptions");
	}

	/**
	 * Get contact
	 *
	 * Ref:CRM_A_AC_05
	 */
	public static APITransaction getContact(String token, String contactUuid) {
		return triggerGetRequest(token, getUrl() + "/contact-management/api/v2/private/auth/contacts/" + contactUuid);
	}

	/**
	 * Get account balance
	 * 
	 * Ref: CRM_A_AC_06
	 */
	public static APITransaction getAccountBalance(String token, int billingAccountID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/account-receivable-facade/api/v1/private/auth/balance/" + billingAccountID);
	}

	/**
	 * Get services
	 * 
	 * Ref: CRM_A_AC_07
	 */
	public static APITransaction getServices(String token, int accountID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/services?accountId=" + accountID + "&size=999");
	}

	/**
	 * Get web notifications
	 * 
	 * Ref: CRM_A_AC_08
	 */
	public static APITransaction getWebNotifications(String token, String uuid) {
		return triggerGetRequest(token, getUrl() + "/notification-center/private/auth/web_notification/" + uuid + "?page=0");
	}

	/*------------------------------------------------------------------
	 * Account: Profile Tab
	 ------------------------------------------------------------------- */

	/**
	 * Get contact permissions
	 * 
	 * Ref: CRM_A_PR_01
	 */
	public static APITransaction getContactPermissions(String token, String contactUuid) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/contacts/" + contactUuid + "/permissions");
	}

	/**
	 * Get security answer
	 * 
	 * Ref: CRM_A_PR_02
	 */
	public static APITransaction getSecurityAnswer(String token, String contactUuid) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/security-question/private/auth/answer/" + contactUuid);
	}

	/**
	 * Get contact permissions v1
	 * 
	 * Ref: CRM_A_PR_03
	 */
	public static APITransaction getContactPermissionsV1(String token, String contactUuid) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/contacts/" + contactUuid + "/permissions");
	}

	/**
	 * Get ref security questions
	 *
	 * Ref: CRM_A_PR_04
	 */
	public static APITransaction getRefSecurityQuestions(String token) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/security-question/private/auth/question/reference");
	}

	/**
	 * Update security question
	 *
	 * Ref: CRM_A_PR_05
	 */
	public static APITransaction updateSecurityQuestion(String token, String contactUuid, SecurityQuestionCode questionCode, String response) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/security-question/private/auth/answer/" + contactUuid;
		UpdateSecurityQuestionDTO dto = new UpdateSecurityQuestionDTO(questionCode, response);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Eircode lookup
	 *
	 * Ref: CRM_A_PR_06
	 */
	public static APITransaction eircodeLookup(String token, String eircode) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/address-finder/public/address?eircode=" + eircode);
	}

	/**
	 * Update billing address
	 *
	 * Ref: CRM_A_PR_07
	 */
	public static APITransaction updateAddress(String token, String contactUuid, String addressLine1, String addressLine2, String addressLine3, String town,
			String county, String eircode, AddressType addressType) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/contacts/" + contactUuid + "/addresses";
		UpdateAddressDTO dto = new UpdateAddressDTO(addressLine1, addressLine2, addressLine3, town, county, eircode, addressType.toString());
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPatch(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update contact details
	 *
	 * Ref: CRM_A_PR_08
	 */
	public static APITransaction updateContact(String token, String contactUuid, String firstName, String lastName, String dateOfBirth) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/contacts/" + contactUuid;
		UpdateContactDTO dto = new UpdateContactDTO(firstName, lastName, dateOfBirth);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionMergePatch(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update email address
	 *
	 * Ref: CRM_A_PR_10
	 */
	public static APITransaction updateEmailAddress(String token, String contactUuid, String newEmailAddress, String brand) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/user-management/private/auth/user/" + contactUuid + "/change_email/request";
		UpdateEmailAddressDTO dto = new UpdateEmailAddressDTO(newEmailAddress, Brand.valueOf(brand));
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update phone number
	 *
	 * Ref: CRM_A_PR_13
	 */
	public static APITransaction updatePhoneNumber(String token, int phoneNumberId, String phoneNumber, String type) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/phone-numbers/" + phoneNumberId;
		UpdateContactNumberDTO dto = new UpdateContactNumberDTO(phoneNumber, type);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionMergePatch(url, payload, token);
		return new APITransaction(url, payload, r);
	}
	
	/**
	 * Add phone number
	 *
	 * Ref: CRM_A_PR_14
	 */
	public static APITransaction addPhoneNumber(String token, String contactUuid, String phoneNumber, String type) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/contacts/" + contactUuid + "/phone-numbers";
		UpdateContactNumberDTO dto = new UpdateContactNumberDTO(phoneNumber, type);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}
	
	/**
	 * Delete phone number
	 *
	 * Ref: CRM_A_PR_15
	 */
	public static APITransaction deletePhoneNumber(String token, int phoneNumberId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/phone-numbers/" + phoneNumberId;
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Add authorized user
	 *
	 * Ref: CRM_A_PR_12
	 */
	public static APITransaction addAuthorizedUser(String token, int accountId, String firstName, String lastName) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/contacts";
		AddAuthorizedUserDTO dto = new AddAuthorizedUserDTO(firstName,lastName,accountId,"AUTHORIZED_USER");
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}
	
	/**
	 * Edit authorized user
	 *
	 * Ref: CRM_A_PR_16
	 */
	public static APITransaction editAuthorizedUser(String token, String contactUuid, String firstName, String lastName) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/contact-management/api/v2/private/auth/contacts/" + contactUuid;
		EditAuthorizedUserDTO dto = new EditAuthorizedUserDTO(firstName,lastName);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionMergePatch(url, payload, token);
		return new APITransaction(url, payload, r);
	}
	
	/**
	 * Delete authorized user
	 *
	 * Ref: CRM_A_PR_17
	 */
	public static APITransaction deleteAuthorizedUser(String token, int accountId, String contactUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/accounts/" + accountId + "/contacts/" + contactUuid + "/types/AUTHORIZED_USER";
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: Transactions Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get postpay adjustment reason codes
	 * 
	 * Ref: CRM_A_TR_01
	 */
	public static APITransaction getRefPostpayAdjustmentReasons(String token) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/adjustment/private/auth/adjustment/postpay/reasons");
	}

	/**
	 * Get transaction history
	 * 
	 * Ref: CRM_A_TR_02
	 */
	public static APITransaction getTransactionHistory(String token, int billingAccountID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/account-receivable-facade/api/v1/private/auth/balance/" + billingAccountID + "/summary?page=0");
	}

	/**
	 * Make an account level adjustment
	 * 
	 * Ref: CRM_A_TR_03
	 */
	public static APITransaction processAccountLevelAdjustment(String token, int billingAccountID, int amount, AdjustmentReason reason, String freeText) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/adjustment/private/auth/adjustment/" + billingAccountID;

		// generate the dto
		ProcessAccountAdjustmentDTO dto = new ProcessAccountAdjustmentDTO(reason, amount, freeText);

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: Collections Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get collections status
	 * 
	 * Ref: CRM_A_CO_01
	 */
	public static APITransaction getCollectionsStatus(String token, int billingAccountId) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/collection/private/auth/collection/" + billingAccountId);
	}

	/*------------------------------------------------------------------
	 * Account: Payments & Billing Tab
	 ------------------------------------------------------------------- */

	/**
	 * Get payment methods
	 * 
	 * Ref: CRM_A_PM_01
	 */
	public static APITransaction getPaymentMethods(String token, int billingAccountID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/payment-center/api/v1/private/auth/payment_methods?billing_account_id=" + billingAccountID);
	}

	/**
	 * Get credit scores
	 * 
	 * Ref: CRM_A_PM_02
	 */
	public static APITransaction getCreditScores(String token, String contactUuid) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/credit-scores/api/v1/private/auth/credit-scores/" + contactUuid);
	}

	/**
	 * Update bill delivery method
	 * 
	 * Ref: CRM_A_PM_03
	 */
	public static APITransaction updateBillDeliveryMethod(String token, int accountID, BillDeliveryType deliveryType) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/accounts/" + accountID + "/billing";

		// generate the dto
		UpdateBillDeliveryTypeDTO dto = new UpdateBillDeliveryTypeDTO(deliveryType);

		String payload = PojoToJsonConverter.getJSON(dto);

		// trigger the request
		Response r = RestAssuredUtil.galaxionMergePatch(url, payload, token);

		return new APITransaction(url, payload, r);
	}

	/*------------------------------------------------------------------
	 * Account: Customer History Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get customer history
	 * 
	 * Ref: CRM_A_CH_01
	 */
	public static APITransaction getCustomerHistory(String token, int accountID, String contactUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/customer-history/private/auth/histories/search?query=(accountId==" + accountID + ",customerUuid=="
				+ contactUuid + ");createdAt%3E=2021-05-19&page=0";
		return new APITransaction(url, RestAssuredUtil.galaxionGetMT(url, token));
	}

	/**
	 * Get filtered customer history
	 * 
	 * Ref: CRM_A_CH_02
	 */
	public static APITransaction getFilteredCustomerHistory(String token, int accountID, String contactUuid) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/customer-history/private/auth/histories/filters?accountId=" + accountID + "&customerUuid="
				+ contactUuid;
		return new APITransaction(url, RestAssuredUtil.galaxionGetMT(url, token));
	}

	/*------------------------------------------------------------------
	 * Account: Documents Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get ref document names
	 * 
	 * Ref: CRM_A_DM_01
	 */
	public static APITransaction getDocumentNames(String token) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/document-management/private/auth/document_names/search/unpaged/?query=domain==B2C");
	}

	/**
	 * Get documents
	 * 
	 * Ref: CRM_A_DM_02
	 */
	public static APITransaction getDocuments(String token, int accountId) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/document-management/private/auth/documents/v2/search?page=0&size=10&query=accountId==" + accountId);
	}

	/*------------------------------------------------------------------
	 * Account: Orders Tab
	 ------------------------------------------------------------------- */
	/**
	 * Get orders
	 * 
	 * Ref: CRM_A_OR_01
	 */
	public static APITransaction getOrderHistory(String token, int accountID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/order-management/api/v1/private/auth/orders?subscriptionAccountId=" + accountID
				+ "&page=0&salesType=B2C");
	}

	/**
	 * Get order
	 * 
	 * 
	 * Ref: CRM_A_OR_02
	 */
	public static APITransaction getOrderDetails(String token, String orderReference) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/order-management/api/v1/private/auth/order/B2C/" + orderReference);
	}

	/*------------------------------------------------------------------
	 * Service: Plan Detail tab
	 ------------------------------------------------------------------- */
	/**
	 * Get subscription
	 * 
	 * Ref: CRM_S_PD_01
	 */
	public static APITransaction getSubscription(String token, int subscriptionID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/subscriptions/" + subscriptionID);
	}

	/**
	 * Get service ofer details
	 * 
	 * Ref: CRM_S_PD_02
	 */
	public static APITransaction getOffer(String token, int serviceID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/customer-offer/private/auth/offer/" + serviceID);
	}

	/**
	 * Get active subscription addons
	 * 
	 * Ref: CRM_S_PD_03
	 */
	public static APITransaction getActiveAddons(String token, int serviceID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/customer-offer/private/auth/offer/for_service/" + serviceID + "/addons/active");
	}

	/**
	 * Get service balance
	 * 
	 * Ref: CRM_S_PD_04
	 */
	public static APITransaction getBalance(String token, int serviceID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/customer-offer/private/auth/offer/" + serviceID + "/balance");
	}

	/**
	 * Update NDD setting
	 * 
	 * Ref: CRM_S_PD_05
	 */
	public static APITransaction updateNddSetting(String token, int serviceID, NDDSetting setting) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/services/" + serviceID;
		UpdateNddDTO dto = new UpdateNddDTO(setting);
		Response r = RestAssuredUtil.galaxionPatch(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Prepay Balance tab
	 ------------------------------------------------------------------- */
	/**
	 * Get prepay adjustment reasons
	 * 
	 * Ref: CRM_S_BA_01
	 */
	public static APITransaction getRefPrepayAdjustmentReasons(String token) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/adjustment/private/auth/adjustment/prepay/reasons");
	}

	/**
	 * Make a service level balance adjustment
	 * 
	 * Ref: CRM_S_BA_02
	 */
	public static APITransaction makeServiceBalanceAdjustment(String token, int serviceID, AdjustmentType type, AdjustmentReason reason, int amount,
			String freeText) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/adjustment/private/auth/adjustment/prepay/" + serviceID;

		// generate the dto
		ProcessServiceAdjustmentDTO dto = new ProcessServiceAdjustmentDTO(reason, type, amount, freeText);

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Usage tab
	 ------------------------------------------------------------------- */
	/**
	 * Get ref usage subtotal types
	 * 
	 * Ref: CRM_S_US_01
	 */
	public static APITransaction getRefRatingSubtotalTypes(String token) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/cdr-repository/public/reference/rating_subtotal_type");
	}

	/**
	 * Get usage
	 * 
	 * Ref: CRM_S_US_02
	 */
	public static APITransaction getUsage(String token, int serviceID, String period) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/cdr-repository/private/auth/usage/service/" + period + "/PREPAY/" + serviceID);
	}

	/**
	 * Get usage summary
	 * 
	 * Ref: CRM_S_US_03
	 */
	public static APITransaction getUsageSummary(String token, int serviceID, String customerType, String period) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/cdr-repository/private/auth/usage_summary/service/" + period + "/" + customerType + "/" + serviceID);
	}

	/*------------------------------------------------------------------
	 * Service: Top Up
	 ------------------------------------------------------------------- */

	/**
	 * Check a voucher number via the EIR CRM UI
	 * 
	 * Ref: CRM_S_VO_01
	 * 
	 * @param voucherNumber - e.g. 648034-0001-0002
	 * @return APITransaction object containing the URL and response object
	 * 
	 */
	public static APITransaction getVoucherStatus(String token, String voucherNumber) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/top-up/private/auth/voucher/" + voucherNumber);
	}

	/**
	 * Process a topup
	 * 
	 * Ref: CRM_S_VO_02
	 * 
	 * @param voucherSerialNumber - e.g. 648034-0001-0002
	 * @param activationCode      - e.g. 238748372982
	 * @return APITransaction object containing the URL and response object
	 */
	public static APITransaction makeTopupRequest(String token, int serviceID, String voucherSerialNumber, String voucherActivationCode) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/top-up/private/auth/voucher/service/" + serviceID + "/top_up";

		ProcessTopupDTO dto = new ProcessTopupDTO(voucherSerialNumber, voucherActivationCode);

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Manage Offer tab
	 ------------------------------------------------------------------- */
	/**
	 * Get available addons
	 * 
	 * Ref: CRM_S_MO_01
	 */
	public static APITransaction getAvailableAddons(String token, int serviceID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/customer-offer/private/auth/offer/for_service/" + serviceID + "/addons/available");
	}

	/*------------------------------------------------------------------
	 * Service: Barring tab
	 ------------------------------------------------------------------- */
	/**
	 * Get barrings
	 * 
	 * Ref: CRM_S_BR_01
	 */
	public static APITransaction getBarrings(String token, int serviceID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/barring/private/auth//barring/" + serviceID);
	}

	/*------------------------------------------------------------------
	 * Service: SIM Replacement tab
	 ------------------------------------------------------------------- */

	/**
	 * Get pending SIM card requests
	 * 
	 * Ref: CRM_S_SR_02
	 */
	public static APITransaction getPendingSimReplacementRequests(String token, int serviceID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/sim-swap/api/v1/private/auth/sim-cards-requests?serviceId=" + serviceID);
	}

	/**
	 * Get SIM card charges
	 * 
	 * Ref: CRM_S_SR_03
	 */
	public static APITransaction getSimReplacementCharges(String token, int serviceID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/sim-swap/api/v1/private/auth/sim-cards/charges?serviceId=" + serviceID);
	}

	/*------------------------------------------------------------------
	 * Service: Change Offer tab
	 ------------------------------------------------------------------- */
	/**
	 * Check change offer eligibility
	 * 
	 * Ref: CRM_S_CO_01
	 */
	public static APITransaction getChangeOfferEligibility(String token, int subscriptionId) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/eligibility?subscriptionId=" + subscriptionId);
	}

	/**
	 * Get early cease charges
	 * 
	 * Ref: CRM_S_CO_02
	 */
	public static APITransaction getChangeOfferEarlyCeaseCharges(String token, int subscriptionId) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI()
				+ "/change-offers/api/v1/private/auth/change-offers/early-cease-charges?contractRenewal=true&subscriptionId=" + subscriptionId);
	}

	/**
	 * Get change offer ref waiving reasons
	 * 
	 * Ref: CRM_S_CO_03
	 */
	public static APITransaction getChangeOfferRefWaivingReasons(String token) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/waiving-reasons");
	}

	/**
	 * Post change offer
	 * 
	 * Ref: CRM_S_CO_04
	 */
	public static APITransaction postChangeOffer(String token, boolean contractRenewal, int subscriptionId, String channel) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers";
		ChangeOfferDTO dto = new ChangeOfferDTO(contractRenewal, subscriptionId, channel);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Get change offer order by reference
	 * 
	 * Ref: CRM_S_CO_05
	 */
	public static APITransaction getChangeOfferOrder(String token, String reference) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference);
	}

	/**
	 * Get change offer cart
	 * 
	 * Ref: CRM_S_CO_06
	 */
	public static APITransaction getChangeOfferCart(String token, String reference) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/carts");
	}

	/**
	 * Get change offer offers for an order
	 * 
	 * Ref: CRM_S_CO_07
	 */
	public static APITransaction getChangeOfferOffers(String token, String reference) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/offers");
	}

	/**
	 * Get tariff plans for offer code
	 * 
	 * Ref: CRM_S_CO_08
	 */
	public static APITransaction getChangeOfferTariffPlanForOffer(String token, String reference, String offerCode) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference
				+ "/tariff-plans?offerCode=" + offerCode);
	}

	/**
	 * Get equipments for change offer
	 * 
	 * Ref: CRM_S_CO_11
	 */
	public static APITransaction getChangeOfferEquipments(String token, String reference) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/equipments/?size=20&page=0");
	}

	/**
	 * Get manufacturers for change offer
	 * 
	 * Ref: CRM_S_CO_12
	 */
	public static APITransaction getChangeOfferManufacturers(String token, String reference) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/manufacturers");
	}

	/**
	 * Get discounts for change offer
	 * 
	 * Ref: CRM_S_CO_13
	 */
	public static APITransaction getChangeOfferDiscounts(String token, String reference) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/discounts");
	}

	/**
	 * Get addon compatabilities for change offer
	 * 
	 * Ref: CRM_S_CO_14
	 */
	public static APITransaction getChangeOfferAddonCompatibilities(String token, String reference) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/add-on-compatibilities");
	}

	/**
	 * Post contracts
	 * 
	 * Ref: CRM_S_CO_15
	 */
	public static APITransaction postContracts(String token, String reference) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/contracts";
		return new APITransaction(url, RestAssuredUtil.galaxionPost(url, null, token));
	}
	
	/**
	 * Accept T&Cs
	 * 
	 * Ref: CRM_S_CO_16
	 */
	public static APITransaction acceptTermsAndConditions(String token, String reference) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/change-offers/api/v1/private/auth/change-offers/" + reference + "/carts";
		return new APITransaction(url, RestAssuredUtil.galaxionPatch(url, null, token));
	}

	/*------------------------------------------------------------------
	 * Service: Manage Discounts
	 ------------------------------------------------------------------- */
	/**
	 * Get ref discount add reasons
	 * 
	 * Ref: CRM_S_DI_01
	 */
	public static APITransaction getRefDiscountAddReasons(String token) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/discounts/api/v1/private/auth/add-reasons";
		return triggerGetRequest(token, url);
	}
	
	/**
	 * Get ref discount remove reasons
	 * 
	 * Ref: CRM_S_DI_02
	 */
	public static APITransaction getRefDiscountRemoveReasons(String token) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/discounts/api/v1/private/auth/remove-reasons";
		return triggerGetRequest(token, url);
	}
	
	/**
	 * Get active discounts
	 * 
	 * Ref: CRM_S_DI_03
	 */
	public static APITransaction getActiveDiscounts(String token, int subscriptionId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/discounts/api/v1/private/auth/discounts/active?subscriptionId="+subscriptionId;
		return triggerGetRequest(token, url);
	}
	
	/**
	 * Get available discounts
	 * 
	 * Ref: CRM_S_DI_04
	 */
	public static APITransaction getAvailableDiscounts(String token, ChannelCode channelCode) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/discounts/api/v1/private/auth/discounts/compatible?channelCode=" + channelCode.toString() + "&offerType=POSTPAY&discountItemType=OFFER&acquisitionType=CHANGE_OFFER&customerType=RESIDENTIAL";
		return triggerGetRequest(token, url);
	}
	
	
	/*------------------------------------------------------------------
	 * Service: Porting
	 ------------------------------------------------------------------- */
	/**
	 * Get porting times
	 * 
	 * Ref: CRM_S_PO_01
	 */
	public static APITransaction getPortingTimes(String token, int serviceId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/mnp-facade/public/port/time/EIR/RETAIL";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Get current operator
	 * 
	 * Ref: CRM_S_PO_02
	 */
	public static APITransaction getCurrentOperator(String token, String msisdn) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/mnp-facade/public/port/operator/" + msisdn;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Get porting history
	 * 
	 * Ref: CRM_S_PO_03
	 */
	public static APITransaction getPortingHistory(String token, int serviceId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/mnp-facade/private/auth/port/" + serviceId + "/port_history";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Get incomplete ports
	 * 
	 * Ref: CRM_S_PO_04
	 */
	public static APITransaction getIncompletePorts(String token, int serviceId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/mnp-facade/private/auth/port/service/uncompleted/" + serviceId;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/*------------------------------------------------------------------
	 * Service: MSISDN Swap tab
	 ------------------------------------------------------------------- */
	/**
	 * Request a random MSISDN Swap
	 * 
	 * Ref: CRM_S_MS_01
	 */
	public static APITransaction requestRandomMsisdnSwap(String token, int serviceId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/msisdn-swap/api/v1/private/auth/services/" + serviceId + "/msisdns/swap";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}
	
	/*------------------------------------------------------------------
	 * Service: Pausible Usages
	 ------------------------------------------------------------------- */
	/**
	 * Get pausible features/usages
	 * 
	 * Ref: CRM_S_FE_01
	 */
	public static APITransaction getPausibleUsages(String token, int serviceId) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/usages/api/v1/private/auth/services/" + serviceId + "/pausable-usages";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Pause feature
	 * 
	 * Ref: CRM_S_FE_02
	 */
	public static APITransaction pauseFeature(String token, int serviceId,String featureCode) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/usages/api/v1/private/auth/services/" + serviceId + "/pausable-usages/" + featureCode + "/pause";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Unpause feature
	 * 
	 * Ref: CRM_S_FE_03
	 */
	public static APITransaction unpauseFeature(String token, int serviceId,String featureCode) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/usages/api/v1/private/auth/services/" + serviceId + "/pausable-usages/" + featureCode + "/resume";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Terminations tab
	 ------------------------------------------------------------------- */

	/**
	 * Get pending termination requests
	 * 
	 * Ref: CRM_S_TR_01
	 */
	public static APITransaction getTerminationRequests(String token, int serviceID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/order-management/private/auth/service/" + serviceID + "/pending_termination_request");
	}

	/**
	 * Get termination reasons
	 * 
	 * Ref: CRM_S_TR_02
	 */
	public static APITransaction getTerminationReasons(String token, AccountType accountType) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI()
				+ "/subscription-management/api/v2/private/auth/subscriptions/termination-reasons?accountType=" + accountType.toString() + "&displayable=true");
	}

	/**
	 * Cancel a pending termination request
	 * 
	 * Ref: CRM_S_TR_03
	 * 
	 */
	public static APITransaction cancelTerminationRequest(String token, int terminationID) {
		String url=EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/subscriptions/terminations/" + terminationID;
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url, r);
	}
	
	
	/**
	 * Schedule a termination from the EIR CRM UI API
	 * 
	 * Ref: CRM_S_TR_04
	 * 
	 */
	public static APITransaction scheduleTermination(String token, int subscriptionID, String primaryReason, String secondaryReason, String scheduledAt, int earlyCeaseChargePrice, String source, String comment) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/subscriptions/" + subscriptionID + "/terminations";

		// build the message
		ScheduleTerminationDTO dto = new ScheduleTerminationDTO(primaryReason,secondaryReason,scheduledAt,earlyCeaseChargePrice,source,comment);

		String payload = PojoToJsonConverter.getJSON(dto);

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);

		return new APITransaction(url, payload, r);
	}
	/*------------------------------------------------------------------
	 * To sort
	 ------------------------------------------------------------------- */


	public static APITransaction getNonUsageHistory(String token, int serviceID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/cdr-repository/private/auth/non_usage/adjustment/" + serviceID + "/history");
	}

	public static APITransaction getPortingTimes(String token) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/mnp-facade/public/port/time/EIR/TELESALES");
	}

	public static APITransaction getActiveServicesOnAccount(String token, int accountID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/services?accountId=" + accountID + "&size=999");
	}

	public static APITransaction getAccountSummary(String token, int accountID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/private/auth/account/" + accountID + "?serviceStatus=ACTIVE");
	}

	public static APITransaction getEmailSubjects(String token, int accountID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/notification-center/private/auth/email_notification/" + accountID + "/subjects");
	}

	public static APITransaction getEmailNotifications(String token, int accountID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/notification-center/private/auth/email_notification/" + accountID
				+ "?from=2021-03-30T10:54:01.308Z&page=0");
	}

	public static APITransaction getSMSNotifications(String token, int accountID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/notification-center/private/auth/sms_notification/" + accountID
				+ "?from=2021-03-30T10:54:01.308Z&page=0");
	}

	// TODO - fix "Response: 415,
	// {"errorCode":"UNSUPPORTED_MEDIA_TYPE","errorMessage":"Content type
	// application/json;charset=UTF-8not supported"}"
	public static APITransaction getCustomerHistoryItem(String token, int customerHistoryItemID) {
		return triggerGetRequest(token, EnvironmentDirectory.getPAYGCRMAPI() + "/customer-history/private/auth/histories/" + customerHistoryItemID);
	}

	// --------------------------------------------------------------------

	public static APITransaction postSecurityVerification(String token, String contactUuid, VerificationStatusEnum status) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGAcquisitionAPI() + "/security-question/private/auth/verification/" + contactUuid;

		SecurityVerificationDTO dto = new SecurityVerificationDTO(status);

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}

	/**
	 * Trigger a SIM swap request from the EIR CRM UI API
	 * 
	 * @param serviceID
	 * @param iccid
	 * @return APITransaction object containing the URL and response object
	 */
	public static APITransaction replaceSIM(String token, int serviceID, String iccid) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/customer-offer/private/auth/service/" + serviceID + "/activate/replace_sim";

		// build the message
		TriggerSimSwapPAYGRetailDTO dto = new TriggerSimSwapPAYGRetailDTO();
		dto.setChannel(new ChannelDTO());
		dto.setIccid(iccid);

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}

	

	public static APITransaction cancelPendingTermination(String token, int serviceID) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/order-management/private/auth/service/" + serviceID + "/pending_termination_request";

		// trigger the request
		Response r = RestAssuredUtil.galaxionDelete(url, token);

		return new APITransaction(url, r);
	}

	public static APITransaction orderReplacementSIM(String token, int serviceID) {

		// read the contact information for the service
		Service service = SubscriptionManagementDAO.getService(serviceID);
		int billingAccountID = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(service.getName());
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID);
		Contact contact = ContactManagementDAO.getContact(contactUuid);

		// build the dto
		PAYGOrderReplacementSimDTO dto = new PAYGOrderReplacementSimDTO();
		dto.setFirstName(contact.getFirstName());
		dto.setLastName(contact.getLastName());
		dto.setPhoneNumber(contact.getPhoneNumber());
		dto.setEmail(contact.getEmailAddress());
		dto.setWaived(false);
		dto.setBarServices(true);
		dto.setServiceId(serviceID);
		Address address = ContactManagementDAO.getAddresses(contactUuid).get(0);
		dto.getAddress().setAddressLine1(address.getAddressLine1());
		dto.getAddress().setAddressLine2(address.getAddressLine2());
		dto.getAddress().setAddressLine3(address.getAddressLine3());
		dto.getAddress().setTown(address.getTown());
		dto.getAddress().setCounty(address.getCounty());
		dto.getAddress().setEircode(address.getEircode());

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/sim-swap/api/v1/private/auth/sim-cards/order";

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}

	public static APITransaction activateReplacementSIM(String token, int serviceID, String puk) {

		// build the dto
		ActivateReplacementSIMDTO dto = new ActivateReplacementSIMDTO(serviceID, puk);

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/sim-swap/api/v1/private/auth/sim-cards/activate";

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}

	public static APITransaction getOrders(String token, int accountID) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/order-management/private/auth/order?subscriptionAccountId=" + accountID + "&page=0");
	}

	public static APITransaction getMonthlyCharge(String token, int subscriptionId) {
		return triggerGetRequest(token,
				EnvironmentDirectory.getPAYGCRMAPI() + "/subscription-management/api/v2/private/auth/subscriptions/" + subscriptionId + "/monthly-charge");

	}

	/**
	 * Make a GET request to the PAYGCRMAPI
	 * 
	 * @param url
	 * @return
	 */
	private static APITransaction triggerGetRequest(String token, String url) {
		return new APITransaction(url, RestAssuredUtil.galaxionGet(url, token));
	}

	/**
	 * Get an authentication token for the session
	 * 
	 * @return
	 */
	public static String getEirRetailToken() {
		LoginCredentials login=EnvironmentDirectory.getEirPAYGRetailLogin();
		Client c = KeycloakDAO.getClient(Services.PAYG_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}

	public static String getEirTelesalesToken() {
		LoginCredentials login=EnvironmentDirectory.getEirPAYGTelesalesLogin();
		Client c = KeycloakDAO.getClient(Services.PAYG_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}
	
	public static String getGoMoToken() {
		LoginCredentials login=EnvironmentDirectory.getGoMoCSRLogin();
		Client c = KeycloakDAO.getClient(Services.EIR_CSR_UI, Realm.gomo);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.gomo, login.getUsername(), login.getPassword());
	}
	

	/**
	 * Determine the API endpoint for the CRM UI based on the customer type - i.e.
	 * GoMo or Eir
	 * 
	 * @param customerType
	 * @return
	 */
	/*
	private static String getUrl(CustomerType customerType) {

		if (customerType == CustomerType.EIR_POSTPAY || customerType == CustomerType.EIR_PREPAY) {
			return EnvironmentDirectory.getPAYGCRMAPI();
		} else if (customerType == CustomerType.GOMO) {
			return EnvironmentDirectory.getGoMoCSRUIURL();
		} else {
			return null;
		}
	}
	*/

	private static String getUrl() {

		return EnvironmentDirectory.getPAYGCRMAPI();

	}

}

package microservices.frontend.eir_eshop_frontend.api;

import io.restassured.response.Response;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_order_management_backend.enums.ChannelType;
import microservices.frontend.common_ui.dto.acquisitions.AddOfferToCartDTO;
import microservices.frontend.eir_eshop_frontend.dto.AcceptTermsDTO;
import microservices.frontend.eir_eshop_frontend.dto.CreateGoMoProspectDTO;
import microservices.frontend.eir_eshop_frontend.dto.ValidateProspectDTO;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class GoMoEShopAPI {

	/*------------------------------------------------------------------
	 * Offer selection screen
	 ------------------------------------------------------------------- */
	/**
	 * Get available GoMo offers
	 * 
	 * Ref: ESH_A_OS_01
	 */
	public static APITransaction getAvailableOffers() {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/offer?brand=GOMO&channel=ESHOP&customerType=residential&offerType=POSTPAY";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url, r);
	}

	/**
	 * Create prospect
	 * 
	 * Ref: ESH_A_OS_02
	 */
	public static APITransaction createProspect() {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/prospect";
		CreateGoMoProspectDTO dto = new CreateGoMoProspectDTO(Brand.GOMO, ChannelType.DIRECT, OfferType.POSTPAY);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Add offer to cart
	 * 
	 * Ref: ESH_A_OS_03
	 */
	public static APITransaction postOfferToCart(String prospectUuid, int offerID) {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/prospect/" + prospectUuid + "/cart/offer";
		AddOfferToCartDTO dto = new AddOfferToCartDTO(1003);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url, payload, r);
	}

	/*------------------------------------------------------------------
	 * Cart screen
	 ------------------------------------------------------------------- */

	/**
	 * Remove an offer from a cart
	 * 
	 * Ref: ESH_A_CT_01
	 * 
	 * @param offerID - reference to prospect.offer.id - (NOT the catalog offer ID)
	 */
	public static APITransaction deleteOfferFromCart(String prospectUuid, int offerID) {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/prospect/" + prospectUuid + "/cart/offer/" + offerID;
		Response r = RestAssuredUtil.galaxionDelete(url, null);
		return new APITransaction(url, r);
	}

	/**
	 * Get cart details
	 * 
	 * Ref: ESH_A_CT_02
	 */
	public static APITransaction getCart(String prospectUuid) {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/prospect/" + prospectUuid + "/cart";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Customer Details screen
	 ------------------------------------------------------------------- */

	/**
	 * Update customer details
	 * 
	 * Ref: ESH_A_CD_01
	 */
	public static APITransaction updateCustomerDetails(String prospectUuid) {

		String message = "{\"billingAddress\":{\"addressLine1\":\"18 BREWS HILL\",\"county\":\"CO_MEATH\",\"eircode\":\"C15EF6E\",\"town\":\"NAVAN\"},\"contactPermission\":{\"allowThirdParty\":false,\"permissionGroupResponse\":[{\"name\":\"Active customer\",\"permissionGroup\":\"ACTIVE_CUSTOMER\",\"permissions\":[{\"enabled\":false,\"name\":\"Email\",\"permission\":\"ALLOW_EMAIL_CONTACT\"},{\"enabled\":false,\"name\":\"SMS\",\"permission\":\"ALLOW_SMS_CONTACT\"},{\"permission\":\"ALLOW_PHONE_CONTACT\",\"name\":\"Phone\",\"enabled\":false},{\"permission\":\"ALLOW_FOTS_CONTACT\",\"name\":\"FOTS\",\"enabled\":false},{\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\",\"name\":\"Direct mail\",\"enabled\":false}]},{\"name\":\"No longer a customer\",\"permissionGroup\":\"NO_LONGER_CUSTOMER\",\"permissions\":[{\"enabled\":false,\"name\":\"Email\",\"permission\":\"ALLOW_EMAIL_CONTACT\"},{\"enabled\":false,\"name\":\"SMS\",\"permission\":\"ALLOW_SMS_CONTACT\"},{\"permission\":\"ALLOW_PHONE_CONTACT\",\"name\":\"Phone\",\"enabled\":false},{\"permission\":\"ALLOW_FOTS_CONTACT\",\"name\":\"FOTS\",\"enabled\":false},{\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\",\"name\":\"Direct mail\",\"enabled\":false}]}]},\"deliveryAddress\":{\"addressLine1\":\"18 BREWS HILL\",\"county\":\"CO_MEATH\",\"eircode\":\"C15EF6E\",\"town\":\"NAVAN\"},\"owner\":{\"email\":\"stevetest_"
				+ System.currentTimeMillis()
				+ "@gomo.ie\",\"mobileNumber\":\"0852123321\",\"person\":{\"birthDate\":\"1981-10-10\",\"firstName\":\"Steve\",\"lastName\":\"Test\"}},\"recaptchaResponse\":\"03AGdBq24Xvm9vJnxjmWPbi8xtI08t_I8qXSM3YFi0IQtIsAoWpZLz8gO0CSMp_QqPO9mKhDRe49lp7DyBbdnoP3YZpyVSb266Acte0SfOU8wa521QWs-Bg4rU7hDYPSi01ml3Uo-0bKikmzClgPcDBn7W2_fuhnCmScQn-NPVKljpj7nbQfrqlzhTRMbV7972QT924ht1tmhYQ9AKNXA4AFRXcNz4bLrh5nDDPlINFc3UgcTKOi_lzLnGbZw5s2pkvL2iqb90fV383QnFLb0PgPtqPnj8Fw4qlYfOAFaI1jkxQEm5CMqAHA6gcXyykqzPRMr1uS7om9uf6ep2MhU4NX6yKZBduHpMnQUyIA2wy0T4ecrpTl4vq6loFF10NlQbEuaGqbyikYNCLcaIgEXVq6tfcKDPvqQH50M9JpgamI9juJeOplHpBYVmhpGwQhA1H9i7kWQ1w3xqW7KMNMbjhf57g3-WfZpryA\"}";

		String url = EnvironmentDirectory.getEshopAPI() + "/public/prospect/" + prospectUuid + "/customer_details";
		Response r = RestAssuredUtil.galaxionPut(url, message, null);
		return new APITransaction(url, r);
	}

	/**
	 * Lookup eircode
	 * 
	 * Ref: ESH_A_CD_02
	 */
	public static APITransaction lookupEircode(String eircode) {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/address?eircode=" + eircode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url, r);
	}

	/**
	 * Get customer details (cross sell)
	 * 
	 * Ref: ESH_A_CD_03
	 */
	public static APITransaction getCustomerDetails(String prospectUuid) {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/prospect/" + prospectUuid + "/customer_details";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url, r);
	}

	/**
	 * Accept terms & conditions
	 * 
	 * Ref: ESH_A_CD_04
	 */
	public static APITransaction acceptTermsAndConditions(String prospectUuid) {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/prospect/" + prospectUuid + "/terms_and_conditions";
		AcceptTermsDTO dto = new AcceptTermsDTO();
		Response r = RestAssuredUtil.galaxionPut(url, PojoToJsonConverter.getJSON(dto), null);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Summary screen
	 ------------------------------------------------------------------- */
	/**
	 * Get hosted payment page
	 * 
	 * Ref: ESH_A_SU_01
	 */
	public static APITransaction getHostedPaymentPage(String prospectUuid) {
		String url = EnvironmentDirectory.getEshopAPI() + "/public/payment/" + prospectUuid + "/hosted_payment_page";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url, r);
	}

	/**
	 * Validate payment and submit prospect
	 * 
	 * Ref: ESH_A_SU_02
	 */
	public static APITransaction validate(String prospectUuid, String hppResponse) {
		
		ValidateProspectDTO dto = new ValidateProspectDTO(hppResponse);

		String url = EnvironmentDirectory.getEshopAPI() + "/public/payment/" + prospectUuid + "/validate";
		System.err.println("JSON = " + PojoToJsonConverter.getJSON(dto));
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), null);
		return new APITransaction(url, r);
	}

}
